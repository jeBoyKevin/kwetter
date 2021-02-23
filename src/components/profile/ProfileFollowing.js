import {TableBody, withStyles, Table, TableCell, TableRow} from '@material-ui/core';
import React, {Component} from 'react';


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
        following: [
            {username: "petertje"},
            {username: "petertje2"},
            {username: "petertje3"},
            {username: "petertje4"},
            {username: "petertje5"},
            {username: "petertje6"},
            {username: "petertje7"},
            {username: "petertje8"},
            {username: "petertje9"},
            {username: "petertje10"},
            {username: "petertje11"},
            {username: "petertje12"},
            {username: "petertje13"},
            {username: "petertje14"},
            {username: "petertje15"},
            {username: "petertje16"},
        ]
    }

    render() {
        const { classes } = this.props;
        return <div className={classes.profileFollowing}>
            <Table className={classes.table}>
            
            <TableBody>
            {this.state.following.map((follow, index) =>
    
            <TableRow className={classes.tweet}  key={follow.username} style ={ ((index > 3 && index < 8) || (index > 11 && index < 16)) ? { background : "#e8e8e8" }:{ background : "white" }}>
                <TableCell className={classes.tweetCell}>
                    <p className={classes.link}>{follow.username}</p>
                </TableCell>
            </TableRow>
            
            )}
            
            </TableBody>
            </Table>
        </div>
    }
}

export default withStyles(useStyles)(ProfileFollowing) 