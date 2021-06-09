import Avatar from '@material-ui/core/Avatar';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import IconButton from '@material-ui/core/IconButton';
import Collapse from '@material-ui/core/Collapse';
import Badge from '@material-ui/core/Badge';
import CloseIcon from '@material-ui/icons/Close';
import { Alert, AlertTitle } from '@material-ui/lab';

const buttonStyle = {
    position: "absolute",
    top: "50%",
    transform: "translateY(-50%)",
    right: "200px"
}

const alertStyle = {
    float: "right",
    marginRight: "150px",
    marginTop: "25px"
}



export default function FormDialog() {
    const [open, setOpen] = React.useState(false);

    const [Notifications, setNotifications] = useState([]);
    const [amountofNots, setAmountofNots] = useState(0);
 
    useEffect(() => {
        async function fetchData() {
          var user_id = 0
          await axios.get("https://kwetter-accountservice.azurewebsites.net/user/me", { headers: { Authorization:  'Bearer ' + sessionStorage.getItem('token') } 
        })
        .then(response => {
            user_id = response.data.id;
        })
        .catch(error => {
            sessionStorage.removeItem('token')
            sessionStorage.removeItem('username')
            return;
        })
        await axios.get('https://kwetter-gateway1.azurewebsites.net/notification/' + user_id, {
        })
        .then(response => {
            Notifications.length = 0
            for(let i = 0; i < response.data.notifications.length; i++) {
                Notifications.push(response.data.notifications[i]);
                setNotifications(Notifications)
            }
            setAmountofNots(Notifications.length)
        })
        .catch(error => {
            return;
          })
        }
        fetchData();
      }, []); 

    async function clearNotifications() {
        setAmountofNots(0);
        setNotifications([])
        var user_id = 0
          await axios.get("https://kwetter-accountservice.azurewebsites.net/user/me", { headers: { Authorization:  'Bearer ' + sessionStorage.getItem('token') } 
        })
        .then(response => {
            user_id = response.data.id;
        })
        .catch(error => {
            sessionStorage.removeItem('token')
            sessionStorage.removeItem('username')
            return;
        })
        await axios.put('https://kwetter-gateway1.azurewebsites.net/notification/' + user_id, {
        })
        .then(response => {
            
        })
        .catch(error => {
            return;
          })
        }


    
  return (
    <div>
        <Badge style={buttonStyle} color="primary" badgeContent={amountofNots}>
            <Avatar aria-controls="simple-menu" aria-haspopup="true" onClick={() => {
                    setOpen(true);
                }} src="../images/bell.png"></Avatar>
        </Badge>

          
        <div>
            
        {Notifications.map((notification, index) =>
            <Collapse in={open}>
            <Alert style ={alertStyle}
            severity="info"
            action={
                <IconButton
                aria-label="close"
                color="inherit"
                size="small"
                onClick={() => {
                    setOpen(false);
                    clearNotifications();
                }}
                >
                <CloseIcon fontSize="inherit" />
                </IconButton>
            }
        
            >
            {notification.message}
            </Alert>
        </Collapse>
        )}
        {Notifications.length > 0 ? console.log("Reading notifications..") :  <Collapse in={open}>
            <Alert severity="info"
            style = {alertStyle}
            action={
                <IconButton
                aria-label="close"
                color="inherit"
                size="small"
                onClick={() => {
                    setOpen(false);
                    clearNotifications();
                }}
                >
                <CloseIcon fontSize="inherit" />
                </IconButton>
            }
        
            >
            You have no new notifications at this time
            </Alert>
        </Collapse>}
        
        </div>
    </div>
  );
}