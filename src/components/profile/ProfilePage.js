import ProfileDetails from "./ProfileDetails";
import ProfileTweets from "./ProfileTweets";
import ProfileFollowing from "./ProfileFollowing";
import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';



const useStyles = (theme) => ({
    profilePage: {
        width: "100%",
        padding: "0 5%",
        margin: "50px 0 0 0",
        boxSizing: "border-box"
    }
})
class ProfilePage extends Component {
    render() {
        const { classes } = this.props;
        return <div id="profilePage" className={classes.profilePage}>
            <ProfileDetails id={this.props.match.params.id}></ProfileDetails>
            <ProfileTweets id={this.props.match.params.id}></ProfileTweets>
            <ProfileFollowing id={this.props.match.params.id}></ProfileFollowing>
        </div>
    }
}

export default withStyles(useStyles)(ProfilePage)