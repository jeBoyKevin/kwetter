import {TableBody, withStyles, Table, TableCell, TableRow} from '@material-ui/core';
import React, {Component} from 'react';
import axios from 'axios';


const useStyles = (theme) => ({
    tweet: {
        float: "left",
        width: "calc(25% - 1px)",
        boxSizing: "inherit",
        borderRight: "1px solid #000"
    },
    profileFollowing: {
        width: "45%",
        float: "right",
        marginTop: "50px",
        boxSizing: "inherit",
        border: "1px solid #000",
        borderRight: "0"
    },
    tweetCell: {
        borderBottom: 0
    },
    link: {
        '&:hover': {
            cursor: "pointer"
        }
    }
})

class ProfileFollowing extends Component {

    state = {
        following: [],
        isloaded: false
    }

    async componentDidMount() {
        const response = await axios({
            method: 'get',
            url: `http://20.93.216.178/profile/followed/${this.props.profile_name}`
        })
        
        if (response.data.success === true) {
            this.setState ({
                following: response.data.followed,
                isloaded: true
            });
        }
    }
    render() {
        if (!this.state.isloaded) {
            return null;
        }
        const { classes } = this.props;
        return <div className={classes.profileFollowing}>
            <Table className={classes.table}>
            
            <TableBody>
            {this.state.following.map((follow, index) =>
    
            <TableRow className={classes.tweet}  key={follow} style ={ ((index > 3 && index < 8) || (index > 11 && index < 16)) ? { background : "#e8e8e8" }:{ background : "white" }}>
                <TableCell className={classes.tweetCell}>
                    <a href={'../profile/' + follow} className={classes.link}>{follow}</a>
                </TableCell>
            </TableRow>
            
            )}
            
            </TableBody>
            </Table>
        </div>
    }
}

export default withStyles(useStyles)(ProfileFollowing) 