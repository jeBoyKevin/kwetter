import {withStyles } from '@material-ui/core';
import React, {Component} from 'react';

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
        following: "47",
        followers: "123",
        tweets: "6347"
    }

    handleClick() {
        this.props.parentMethod();
    }

    render() {
        const { classes } = this.props;
        return <div id ="profileStats" className={classes.profileStats}>
            <p>{this.state.following} following</p>
            <p className={classes.followers} onClick={this.handleClick}><b>{this.state.followers} followers</b></p>
            <p>{this.state.tweets} tweets</p>
        </div>
    }
}

export default withStyles(useStyles)(ProfileStats) 