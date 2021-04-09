import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';
import LoginForm from "../auth/loginForm";
import LogoutForm from "../auth/logoutForm";


const useStyles = (theme) => ({
    banner: {
        width: "100%",
        height: "100px",
        background: "#45b6fe",
        position: "relative"
    },
    button: {
        float: "right"
    },
    pstyle: {
        margin: "0 0 0 5%",
        lineHeight: "100px",
        fontSize: "32px"
    }
})
class Banner extends Component {
    render() {
        const { classes } = this.props;
        return <div id="banner" className={classes.banner}>
            <p className={classes.pstyle}>Welcome to Kwetter</p>
            {localStorage.getItem('token') ? (
                <LogoutForm ></LogoutForm>
            ) : (
                <LoginForm></LoginForm>
            )}
        </div>
    }
}

export default withStyles(useStyles)(Banner)