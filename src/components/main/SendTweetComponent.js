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
const handleErrorOpen = () => {
    this.setState({
        errorOpen: true});
  }
  const handleErrorClose = () => {
    this.setState({
        errorOpen: false});
  }
class SendTweetComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tweet: '',
            errorOpen: false,
            warning: ''
        }
        this.handleTweet = this.handleTweet.bind(this)
    }
    render() {
        const { classes } = this.props;
        return <div id="tweetComponent" className={classes.tweetComponent}>
            <TextField autoFocus={true} aria-label="tweet" placeholder="What's on your mind" value={this.state.tweet} onChange={this.handleChange} className={classes.tweetBox} multiline/>
            <Button variant="contained" color="primary" className={classes.sendTweet} onClick={this.handleTweet}>
                Send Tweet
            </Button>
            <Snackbar open={this.state.errorOpen} autoHideDuration={3000} onClose={handleErrorClose}>
            <Alert onClose={handleErrorClose} severity="error">
              {this.state.warning}
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
    console.log('Bearer ' + localStorage.getItem('token'))
    await axios.get("http://localhost:8083/user/me", { headers: { Authorization:  'Bearer ' + localStorage.getItem('token') } 
    })
    .then(response => {
        user_id = response.data.id;
    })
    axios.post('http://localhost:8079/tweet', {
      user_id: user_id,
      message: this.state.tweet
    })
    .then(response => {
      console.log(response);
    })
    .catch(error => {
      this.setState({warning: error.response.data.error})
      handleErrorOpen();
      })
    }
}

export default withStyles(useStyles)(SendTweetComponent)