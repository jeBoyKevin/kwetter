import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';
import SendTweetComponent from './SendTweetComponent';

const useStyles = (theme) => ({
    mainPage: {
        width: "100%",
        padding: "0 5%",
        margin: "50px 0 0 0",
        boxSizing: "border-box"
    }
})
class MainPage extends Component {
    render() {
        const { classes } = this.props;
        return <div id="profilePage" className={classes.mainPage}>
            <SendTweetComponent></SendTweetComponent>
        </div>
    }
}

export default withStyles(useStyles)(MainPage)