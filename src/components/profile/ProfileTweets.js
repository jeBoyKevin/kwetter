import {TableBody, withStyles, Table, TableCell, TableRow} from '@material-ui/core';
import React, {Component} from 'react';
import ProfileStats from './ProfileStats';

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
    followerTable: {
        display: "none"
    },
    wrapperDiv: {
        boxSizing: "border-box"
    }

})

class ProfileTweets extends Component {

    constructor(props) {
        super(props);
    
    
        this.parentMethod = this.parentMethod.bind(this);
      }

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
        ],
        followers: [
            {
                id: 1, name: "follower1"
            },
            {
                id: 2, name: "follower2"
            },
            {
                id: 3, name: "follower3"
            },
            {
                id: 4, name: "follower4"
            },
            {
                id: 5, name: "follower5"
            },
        ]
    }
    
    parentMethod() {
        console.log('Hey')
        if (document.getElementById('uiTable').style.display !== "none"){
            document.getElementById('uiTable').style.display = "none"
            document.getElementById('followerTable').style.display = "table"
        }
        else {
            document.getElementById('uiTable').style.display = "table"
            document.getElementById('followerTable').style.display = "none"

        }
    }

    render() {
        const { classes } = this.props;
        
        return  <div className={classes.wrapperDiv}>
            <ProfileStats parentMethod={this.parentMethod}/>
            <div className={classes.profileTweets}>
            <Table id="uiTable" className={classes.table}>
            
            <TableBody>
            {this.state.tweets.map((tweet, index) =>

            <TableRow className={classes.tweet} key={tweet.id} style ={ index % 2? { background : "#e8e8e8" }:{ background : "white" }}>
                <TableCell className={classes.tweetCell}>
                    <p className={classes.tweetText}>{tweet.text}</p>
                    <p className={classes.tweetDate}>{tweet.date}</p>
                </TableCell>
            </TableRow>
            )}
            
            </TableBody>
            </Table>
            <div className={classes.followers}>
                <Table id="followerTable" className={classes.followerTable}>
                
                <TableBody>
                {this.state.followers.map((follower, index) =>

                <TableRow className={classes.tweet}  key={follower.id} style ={ index % 2? { background : "#e8e8e8" }:{ background : "white" }}>
                    <TableCell className={classes.tweetCell}>
                        <p className={classes.tweetText}>{follower.name}</p>
                    </TableCell>
                </TableRow>
                )}
                
                </TableBody>
                </Table>
            </div>
        </div>
        </div> 
    }
}

export default withStyles(useStyles)(ProfileTweets) 