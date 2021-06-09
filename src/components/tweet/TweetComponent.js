
import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';
import axios from 'axios';
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';

const useStyles = (theme) => ({
    tweetText: {
        margin: "0"
    },
    tweetDate: {
        margin: "15px 0 0 0",
        fontStyle: "italic",
        fontSize: "0.750rem"
    },
    tweetLikes: {
        margin: "0",
        display: "inline-block"
    },
    tweetDelete: {
        '&:hover': {
            cursor: "pointer",
            fontWeight: "bold"
        },
        margin: "0",
        display: "inline-block",
        float: "right",
        color: "#1c4966"
    }
})
class TweetComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            errorOpen: false,
            errorText: ""
        }
        this.handleErrorClose = this.handleErrorClose.bind(this)
        this.handleDelete = this.handleDelete.bind(this)

    }
    render() {
        const { classes } = this.props;
        return <div id="tweetComponent">
            <p className={classes.tweetText}>{this.props.message}</p>
            <p className={classes.tweetDate}>{this.props.date}</p>
            <p className={classes.tweetLikes}>{this.props.likes} likes</p>
            <p onClick={this.handleDelete} className={classes.tweetDelete}>Delete tweet</p>
                <Snackbar open={this.state.errorOpen} autoHideDuration={8000} onClose={this.handleErrorClose}>
                    <Alert onClose={this.handleErrorClose} severity="error">
                        {this.state.errorText}
                    </Alert>
                </Snackbar>
        </div>
    }
    handleErrorClose() {
        this.setState({
            errorOpen: false
        })
    }
    async handleDelete() {
        var user_id = "0";
        await axios.get("https://kwetter-accountservice.azurewebsites.net/user/me", { headers: { Authorization:  'Bearer ' + localStorage.getItem('token') } 
        })
        .then(response => {
            user_id = response.data.id;
        })
        .catch(response => {
            localStorage.removeItem('token');
            return;
        })
        if (user_id === this.props.user_id) {
            await axios({
                method: 'DELETE',
                url: `https://kwetter-gateway1.azurewebsites.net/tweet/${this.props.tweet_id}`,
            });
            window.location.replace('');
            }       
        else {
            this.setState({
                errorText: "You do not have the permissions to remove this tweet",
                errorOpen: true
            })
        } 
    }
}

export default withStyles(useStyles)(TweetComponent)