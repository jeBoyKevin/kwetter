import ProfileDetails from "./ProfileDetails";
import ProfileTweets from "./ProfileTweets";
import ProfileFollowing from "./ProfileFollowing";
import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';
import axios from 'axios';


const useStyles = (theme) => ({
    profilePage: {
        width: "100%",
        padding: "0 5%",
        margin: "50px 0 0 0",
        boxSizing: "border-box"
    }
})
class ProfilePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            profile_id: "",
            picture: "",
            location: "",
            website: "",
            bio: "",
            isLoaded: false,
            
        }

    }
    async componentDidMount() {
        const response = await axios({
            method: 'get',
            url: `http://localhost:8079/profile/${this.props.match.params.id}`
        })
        
        if (response.data.success === true) {
            console.log(response)
            this.setState ({
                profile_id: response.data.user_id,
                picture: response.data.picture,
                location: response.data.location,
                website: response.data.website,
                bio: response.data.bio,
                isLoaded: true
            });
        }
    }
    render() {
        if (!this.state.isLoaded) {
            return null;
        }
        const { classes } = this.props;
        return <div id="profilePage" className={classes.profilePage}>
            <ProfileDetails profile_name={this.props.match.params.id} picture={this.state.picture} location={this.state.location} website={this.state.website} bio={this.state.bio}></ProfileDetails>
            <ProfileTweets profile_name={this.props.match.params.id} profile_id={this.state.profile_id}></ProfileTweets>
            <ProfileFollowing profile_name={this.props.match.params.id}></ProfileFollowing>
        </div>
    }
}

export default withStyles(useStyles)(ProfilePage)