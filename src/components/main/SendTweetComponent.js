import React, {Component} from 'react';
import {Button, TextField, withStyles } from '@material-ui/core';
import axios from 'axios';
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';

const useStyles = (theme) => ({
    tweetComponent: {
        width: "50%",
        padding: "0 5%",
        border: "1px solid #000",
        boxSizing: "border-box",
        height: "150px",
        position: "relative"
    },
    tweetBox: {
        marginTop: "25px",
        width: "80%",
        height: "100px"
    },
    sendTweet: {
        position: "absolute",
        right: "10px",
        bottom: "10px"
    }
})

class SendTweetComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tweet: '',
            errorOpen: false,
            warning: '',
            succesOpen: false
        }
        this.handleTweet = this.handleTweet.bind(this)
        this.handleErrorClose= this.handleErrorClose.bind(this)
        this.handleSuccesClose= this.handleSuccesClose.bind(this)

    }
    render() {
        const { classes } = this.props;
        return <div id="tweetComponent" className={classes.tweetComponent}>
        <TextField autoFocus={true} aria-label="tweet" placeholder="What's on your mind" value={this.state.tweet} onChange={this.handleChange} className={classes.tweetBox} multiline/>
            <Button variant="contained" color="primary" className={classes.sendTweet} onClick={this.handleTweet}>
                Send Tweet
            </Button>
            <Snackbar open={this.state.errorOpen} autoHideDuration={3000} onClose={this.handleErrorClose}>
                <Alert onClose={this.handleErrorClose} severity="error">
                    {this.state.warning}
                </Alert>
            </Snackbar>
            <Snackbar open={this.state.succesOpen} autoHideDuration={3000} onClose={this.handleSuccesClose}>
                <Alert onClose={this.handleSuccesClose} severity="success">
                Tweet has been sent
                </Alert>
        </Snackbar>
        </div>
    }

    handleChange = e => {
        this.setState({ tweet: e.target.value });
    }

    async handleTweet (e)  {
    var user_id = 0;
    e.preventDefault();
    await axios.get("https://kwetter-accountservice.azurewebsites.net/user/me", { headers: { Authorization:  'Bearer ' + sessionStorage.getItem('token') } 
    })
    .then(response => {
        user_id = response.data.id;
    })
    .catch(error => {
        sessionStorage.removeItem('token')
        this.setState({warning: "You do not have the permissions to send this tweet"})
        this.handleErrorOpen();
        return;
    })
    axios.post('https://kwetter-gateway1.azurewebsites.net/tweet', {
      user_id: user_id,
      message: this.state.tweet
    })
    .then(response => {
        this.handleSuccesOpen()
        this.setState({
          tweet: ""
        })
    })
    .catch(error => {
        this.setState({warning: "You do not have the permissions to send this tweet"})
        this.handleErrorOpen();
        return;
      })

    }

    handleErrorOpen() {
        this.setState({
            errorOpen: true});
      }
    handleErrorClose() {
        this.setState({
            errorOpen: false});
        window.location.replace('')
      }
    
    handleSuccesOpen() {
        this.setState({
            succesOpen: true});
      }
    handleSuccesClose() {
        this.setState({
            succesOpen: false});
      }
}

export default withStyles(useStyles)(SendTweetComponent)