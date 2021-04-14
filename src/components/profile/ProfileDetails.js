import {withStyles } from '@material-ui/core';
import React, {Component} from 'react';
import axios from 'axios';
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';

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
            profile_name: "",
            picture: "",
            location: "",
            website: "",
            bio: "",
            isLoaded: false,
            bioToggle: true,
            locationToggle: true,
            websiteToggle: true,
            errorOpen: false,
            succesOpen: false,
            errorText: ""
            
        }
        this.toggleInput = this.toggleInput.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleErrorClose = this.handleErrorClose.bind(this)
        this.handleSuccesClose = this.handleSuccesClose.bind(this)

    }

    async componentDidMount() {
        if (this.props.picture !== null)
        {
            this.setState ({
                profile_name: this.props.profile_name,
                picture: this.props.picture,
                location: this.props.location,
                website: this.props.website,
                bio: this.props.bio,
                isLoaded: true
            });
        }
        else {
            this.setState({
                errorText: "This profile doesn't exist",
                errorOpen: true,
                isLoaded: true
            })
        }

    }

    async changeProfile (e) {
        var user_id = "0";
        await axios.get("http://localhost:8083/user/me", { headers: { Authorization:  'Bearer ' + localStorage.getItem('token') } 
        })
        .then(response => {
            user_id = response.data.id;
        })
        .catch(response => {
            localStorage.removeItem('token');
            return;
        })
        if (user_id.toString() === this.props.id) {
            await axios({
                method: 'PUT',
                url: `http://localhost:8079/profile/`,
                data: {
                    user_id: user_id,
                    website: this.state.website,
                    location: this.state.location,
                    bio: this.state.bio
                }
            });
            this.setState({
                succesOpen: true
            })
            }       
        else {
            this.setState({
                errorText: "You do not have the permissions to change this profile",
                errorOpen: true
            })
        } 
    }
    handleErrorClose() {
        this.setState({
            errorOpen: false
        })
    }

    handleSuccesClose() {
        this.setState({
            succesOpen: false
        })
    }

    toggleInput(event) {
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
                    <img src={this.state.picture} alt={'picture of ' + this.state.profile_name} className={classes.profilePicture} />
                    <h1 className={classes.h1}>{this.state.profile_name}</h1>
                </div>
                <div id="details" className={classes.details}>
                    <p><b>Name:</b> {this.state.profile_name}</p>
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
                <Snackbar open={this.state.succesOpen} autoHideDuration={3000} onClose={this.handleSuccesClose}>
                    <Alert onClose={this.handleSuccesClose} severity="success">
                    Profile has been updated
                    </Alert>
                </Snackbar>
                <Snackbar open={this.state.errorOpen} autoHideDuration={8000} onClose={this.handleErrorClose}>
                    <Alert onClose={this.handleErrorClose} severity="error">
                        {this.state.errorText}
                    </Alert>
                </Snackbar>
            </div>
        )
    }
}

export default withStyles(useStyles)(ProfileDetails) 