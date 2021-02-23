import {TableBody, withStyles, Table, TableCell, TableRow} from '@material-ui/core';
import React, {Component} from 'react';


const useStyles = (theme) => ({
    tweet: {
        boxSizing: "inherit",
        borderBottom: "1px solid #000",
        lineHeight: "25px"
    },
    tweetCell: {
        borderBottom: 0,
        lineHeight: "inherit", 
        padding: "10px"
    },
    profileTweets: {
        width: "45%",
        marginTop: "50px",
        border: "1px solid #000",
        borderBottom: "0",
        boxSizing: "inherit",
        float: "left"
    },
    tweetText: {
        margin: "0"
    },
    tweetDate: {
        margin: "15px 0 0 0",
        fontStyle: "italic",
        fontSize: "0.750rem"
    },
})

class ProfileTweets extends Component {

    state = {
        tweets: [
            {
                id: 1, text: "Test Tweet", date: "10 seconds ago"
            },
            {
                id: 2, text: "Test Tweet 2", date: "1 hour ago"
            },
            {
                id: 3, text: "Test Tweet 3", date: "13 hours ago"
            },
        ]
    }

    render() {
        const { classes } = this.props;
        return <div className={classes.profileTweets}>
            <Table className={classes.table}>
            
            <TableBody>
            {this.state.tweets.map((tweet, index) =>

            <TableRow className={classes.tweet}  key={tweet.id} style ={ index % 2? { background : "#e8e8e8" }:{ background : "white" }}>
                <TableCell className={classes.tweetCell}>
                    <p className={classes.tweetText}>{tweet.text}</p>
                    <p className={classes.tweetDate}>{tweet.date}</p>
                </TableCell>
            </TableRow>
            )}
            
            </TableBody>
            </Table>
        </div>
    }
}

export default withStyles(useStyles)(ProfileTweets) 