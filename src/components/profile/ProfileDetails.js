import {withStyles } from '@material-ui/core';
import React, {Component} from 'react';

const useStyles = (theme) => ({
    summary: {
        float: "left",
        width: "45%",
        marginRight: "10%",
        border: "1px solid #000",
        boxSizing: "inherit",
        padding: "10px"
    },
    details: {
        float: "left",
        width: "45%",
        border: "1px solid #000",
        boxSizing: "inherit",
        padding: "10px"
    },
    profileDetails: {
        float: "left",
        width: "100%",
        boxSizing: "inherit"
    },
    profilePicture: {
        width: "75px",
        float: "left"
    },
    h1: {
        display: "inline",
        float: "left",
        marginLeft: "25px"
    },
    href: {
        display: "inline"
    }
})

class ProfileDetails extends Component {

    state = {
        name: "Kevints",
        picture: "/resources/images/avatar.png",
        location: "Geldrop, The Netherlands",
        website: "www.kevinit.nl",
        bio: "Hallo ik ben Kevin"
    }

    render() {
        const { classes } = this.props;
        return <div id="profileDetails" className={classes.profileDetails}>
            <div id="summary" className={classes.summary}>
                <img src={this.state.picture} alt={'picture of ' + this.state.name} className={classes.profilePicture} />
                <h1 className={classes.h1}>{this.state.name}</h1>
            </div>
            <div id="details" className={classes.details}>
                <p><b>Name:</b> {this.state.name}</p>
                <p><b>Location:</b> {this.state.location}</p>
                <p className={classes.href}><b>Web: </b></p><a href={'https://'+this.state.website}target="_blank" rel="noopener noreferrer">{this.state.website}</a>
                <p><b>Bio:</b> {this.state.bio}</p>
            </div>
        </div>
    }
}

export default withStyles(useStyles)(ProfileDetails) 