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



export default function FormDialog() {
    const [open, setOpen] = React.useState(false);

    const [Notifications, setNotifications] = useState([]);
 
    useEffect(() => {
        async function fetchData() {
          var user_id = 0
          await axios.get("http://localhost:8083/user/me", { headers: { Authorization:  'Bearer ' + sessionStorage.getItem('token') } 
        })
        .then(response => {
            user_id = response.data.id;
        })
        .catch(error => {
            sessionStorage.removeItem('token')
            sessionStorage.removeItem('username')
            return;
        })
        axios.get('http://localhost:8079/notification/' + user_id, {
        })
        .then(response => {
            Notifications.length = 0
            for(let i = 0; i < response.data.notifications.length; i++) {
                Notifications.push(response.data.notifications[i]);
                setNotifications(Notifications)
            }
            console.log(Notifications)
        })
        .catch(error => {
            return;
          })
        }
        fetchData();
      }, []); 

    
  return (
    <div>
        <Badge style={buttonStyle} color="primary" badgeContent={Notifications.length}>
            <Avatar aria-controls="simple-menu" aria-haspopup="true" onClick={() => {
                    setOpen(true);
                }} src="./resources/images/bell.png"></Avatar>
        </Badge>

          
        <div>
            
        {Notifications.map((notification, index) =>
            <Collapse in={open}>
            <Alert severity="info"
            action={
                <IconButton
                aria-label="close"
                color="inherit"
                size="small"
                onClick={() => {
                    setOpen(false);
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
        
        </div>
    </div>
  );
}