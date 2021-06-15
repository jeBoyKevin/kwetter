import React, {Component} from 'react';
import {withStyles } from '@material-ui/core';
import LoginForm from "../auth/loginForm";
import LogoutForm from "../auth/logoutForm";
import ProfileMenu from "../main/ProfileMenu";
import Button from '@material-ui/core/Button';


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
    },
    profileButton: {
        textDecoration: "none",
        background: '#E0E0E0',
        color: '#fff',
        float: 'right',
        marginRight: '95px',
        marginTop: '5px'
    }
})
class Banner extends Component {
    render() {
        const { classes } = this.props;
        return <div id="banner" className={classes.banner}>
            <p className={classes.pstyle}>Welcome to Kwetter, {sessionStorage.getItem('username')}</p>
            {sessionStorage.getItem('token') ? (
                <div>
                <Button variant="contained" className={classes.profileButton} onClick={() => {window.location.href="../profile/" + sessionStorage.getItem('username')}}>
                    Go to profile
                </Button>
                <Button variant="contained" className={classes.profileButton} onClick={() => {window.location.href="../main"}}>
                    Go to main page
                </Button>
                <LogoutForm ></LogoutForm>
                <ProfileMenu></ProfileMenu>
                </div>
            ) : (
                <LoginForm></LoginForm>
            )}
        </div>
    }
}

export default withStyles(useStyles)(Banner)