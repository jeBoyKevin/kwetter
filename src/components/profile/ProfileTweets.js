import {TableBody, withStyles, Table, TableCell, TableRow} from '@material-ui/core';
import React, {Component} from 'react';
import ProfileStats from './ProfileStats';
import axios from 'axios';
import TweetComponent from '../tweet/TweetComponent';

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
        float: "left",
        height: "256px",
        overflowY: "auto"
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
        tweets: [],
        profileId: 0
    }

    async componentDidMount() {
        const response2 = await axios({
            method: 'get',
            url: `http://20.93.216.178/tweet/${this.props.profile_id}`
        })
        if (response2.data.success === true) {
            this.setState({
                tweets: response2.data.tweets,
                isLoaded: true
            })
        }
    }
    
    parentMethod() {
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

        if (!this.state.isLoaded) {
            return null;
        }
        const { classes } = this.props;
        
        return  <div className={classes.wrapperDiv}>
            <ProfileStats id={this.props.profile_name} parentMethod={this.parentMethod}/>
            <div className={classes.profileTweets}>
            <Table id="uiTable" className={classes.table}>
            
            <TableBody>
            {this.state.tweets.map((tweet, index) =>

            <TableRow className={classes.tweet} key={index} style ={ index % 2? { background : "#e8e8e8" }:{ background : "white" }}>
                <TableCell className={classes.tweetCell}>
                    <TweetComponent user_id={tweet.user_id} message={tweet.message} date={tweet.date} likes={tweet.likes} tweet_id={tweet.tweet_id}></TweetComponent>
                </TableCell>
            </TableRow>
            )}
            
            </TableBody>
            </Table>
            <div className={classes.followers}>
                <Table id="followerTable" className={classes.followerTable}>
                
                <TableBody>
                {this.props.followers.map((follower, index) =>

                <TableRow className={classes.tweet}  key={follower} style ={ index % 2? { background : "#e8e8e8" }:{ background : "white" }}>
                    <TableCell className={classes.tweetCell}>
                        <a href={'../profile/' + follower} className={classes.tweetText}>{follower}</a>
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