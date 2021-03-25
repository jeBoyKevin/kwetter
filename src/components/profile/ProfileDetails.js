import {withStyles } from '@material-ui/core';
import React, {Component} from 'react';
import axios from 'axios';

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
    },
    bioToggle: {
        display: "block",
        marginBlockStart: "1em",
        marginBlockEnd: "1em",
        marginInlineStart: "0px",
        marginInlineEnd: "0px"
    }
})

class ProfileDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "Undefined",
            picture: "",
            location: "",
            website: "",
            bio: "",
            isLoaded: false,
            bioToggle: true,
            locationToggle: true,
            websiteToggle: true
            
        }
        this.toggleInput = this.toggleInput.bind(this)
        this.handleChange = this.handleChange.bind(this)

    }

    async componentDidMount() {
        const response = await axios({
            method: 'get',
            url: `http://localhost:8079/profile/${this.props.id}`
        })
        
        if (response.data.success === true) {
            this.setState ({
                picture: response.data.picture,
                location: response.data.location,
                website: response.data.website,
                bio: response.data.bio,
                isLoaded: true
            });
        }
        else {
            this.setState({
                name: "This profile cannot be loaded"
            })
            console.log(response.data.errorMessage);
        }
        

    }

    async changeProfile() {
        await axios({
            method: 'PUT',
            url: `http://localhost:8079/profile/`,
            data: {
                user_id: this.props.id,
                website: this.state.website,
                location: this.state.location,
                bio: this.state.bio
            }
        });
        alert("Profile has been updated")
    }
    toggleInput(event) {
        console.log("hey")
        this.setState(prevState => ({
            [event.target.id]: !prevState[event.target.id]
          }));
          
        if (!this.state[event.target.id]) {
            this.changeProfile();
        }
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
      }

    render() {
        
        const { classes } = this.props;
        if (!this.state.isLoaded) {
            return null;
        }
        return (
            <div id="profileDetails" className={classes.profileDetails}>
                <div id="summary" className={classes.summary}>
                    <img src={this.state.picture} alt={'picture of ' + this.state.name} className={classes.profilePicture} />
                    <h1 className={classes.h1}>{this.state.name}</h1>
                </div>
                <div id="details" className={classes.details}>
                    <p><b>Name:</b> {this.state.name}</p>
                    {this.state.locationToggle ? (
                    <p name="location" id="locationToggle" onDoubleClick={this.toggleInput}><b>Location:</b> {this.state.location}</p>

                    ) : (
                        <input type="text" name="location" id="locationToggle" onDoubleClick={this.toggleInput} className={classes.bioToggle} value={this.state.location} onChange={this.handleChange} />
                    )}
                    {this.state.websiteToggle ? (
                    <p name="website" id="websiteToggle" onDoubleClick={this.toggleInput} className={classes.href}><b>Web:</b> {this.state.website}</p>
                    ) : (
                        <input type="text" name="website" id="websiteToggle" onDoubleClick={this.toggleInput} className={classes.bioToggle} value={this.state.website} onChange={this.handleChange} />

                    )}
                    {this.state.bioToggle ? (
                    <p name="bio" id="bioToggle" onDoubleClick={this.toggleInput}><b>Bio:</b> {this.state.bio}</p>
                    ) : (
                        <input type="text" name="bio" id="bioToggle" onDoubleClick={this.toggleInput} className={classes.bioToggle} value={this.state.bio} onChange={this.handleChange} />
                    )}
                </div>
            </div>
        )
    }
}

export default withStyles(useStyles)(ProfileDetails) 