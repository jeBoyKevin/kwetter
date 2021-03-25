import {withStyles } from '@material-ui/core';
import React, {Component} from 'react';
import axios from 'axios';

const useStyles = (theme) => ({
    profileStats: {
        boxSizing: "inherit",
        marginTop: "50px",
        width: "45%",
        float: "right",
        marginLeft: "10%",
        border: "1px solid #000",
        padding: "10px"
    },
    followers: {
        '&:hover': {
            cursor: "pointer"
        },
        color: "#0000EE"
    }
})

class ProfileStats extends Component {

    constructor(props) {
        super(props)
        this.handleClick = this.handleClick.bind(this)
    }
    state = {
        following: "0",
        followers: "0",
        tweets: "0",
        isLoaded: false
    }

    async componentDidMount() {
        const response = await axios({
            method: 'get',
            url: `http://localhost:8079/follow/stats/${this.props.id}`
        })
        
        if (response.data.success === true) {
            this.setState ({
                following: response.data.followed,
                followers: response.data.followers,
                isLoaded: true
            });
        }
        else {
            console.log(response.data.errorMessage);
        }
    }

    handleClick() {
        this.props.parentMethod();
    }

    render() {
        if (!this.state.isLoaded) {
            return null;
        }
        const { classes } = this.props;
        return <div id ="profileStats" className={classes.profileStats}>
            <p>{this.state.following} following</p>
            <p className={classes.followers} onClick={this.handleClick}><b>{this.state.followers} followers</b></p>
            <p>{this.state.tweets} tweets</p>
        </div>
    }
}

export default withStyles(useStyles)(ProfileStats) 