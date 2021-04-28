import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import axios from 'axios';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';


function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function FormDialog() {

  const [open, setOpen] = React.useState(false);

  const [details, setDetails] = useState({
    username: '',
    password: '',
    email: ''
  });
  const [errorOpen, seterrorOpen] = useState(false);
  const [error, setError] = useState({
    warning: ''
  })
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleErrorOpen = () => {
    seterrorOpen(true);
  }
  const handleErrorClose = () => {
    seterrorOpen(false);
  }

  const handleChange = name => e => {
    setDetails({ ...details, [name]: e.target.value });
};
const buttonStyle = {
  position: "absolute",
  top: "50%",
  transform: "translateY(-50%)",
  height: "50px",
  width: "100px",
  right: "25px"
}


  const handleLogin = async (e) => {
    e.preventDefault();
    const {password, email } = details;

    axios.post('http://localhost:8083/user/signin?&username=' + email +"&password=" + password, {
      username: email,
      password: password,
    })
    .then(response => {
      console.log(response)
      localStorage.setItem('token', response.data.token)
      var user_id = response.data.user_id
      axios.get('http://localhost:8079/profile/getById/'+user_id)
      .then(response => {
        localStorage.setItem('username', response.data.username)
        handleClose();
        window.location.replace('');
      })
    })
    .catch(error => {
      if (typeof error.response !== "undefined") {
        setError({warning: error.response.data.error})
      }
      else {
        setError({warning: "Connection to service refused"})
      }
      handleErrorOpen();
    })
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    
    const { username, password, email } = details;
    console.log(username)
    if (username === "") {
      setError({warning: "Fill in a username"})
      handleErrorOpen();
      return;
    }
    await axios.post('http://localhost:8083/user/signup', {
      username: email,
      password: password,
      roles: [
        "ROLE_CLIENT"
      ]
    })
    .then(response => {
      localStorage.setItem('token', response.data)
      console.log(response.status)
      if (response.status === 200)
      {
        axios.post('http://localhost:8079/profile', {
          username: username
        })
        .then(response => {
          localStorage.setItem('username', username)
          handleClose();
          window.location.replace('')
        })
        .catch(error => {
          setError({warning: error.response.data.error})
          handleErrorOpen();
          })
      }
    })
    .catch(error => {
      setError({warning: error.response.data.error})
      handleErrorOpen();
      })

    
  };

  return (
    <div>
      <Button variant="contained" style={buttonStyle} onClick={handleClickOpen}>
        Login
      </Button>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Login</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Login to your Kwetter account
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="username"
            label="Username"
            name="username"
            type="text"
            value={details.username}
            onChange={handleChange('username')}
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            id="email"
            label="Email"
            name="email"
            type="email"
            value={details.email}
            onChange={handleChange('email')}
            fullWidth
          />
          <TextField
            margin="dense"
            id="password"
            name="password"
            label="Password"
            value={details.password}
            type="password"
            onChange={handleChange('password')}
            fullWidth
          />
          <Snackbar open={errorOpen} autoHideDuration={3000} onClose={handleErrorClose}>
            <Alert onClose={handleErrorClose} severity="error">
              {error.warning}
            </Alert>
          </Snackbar>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleRegister} color="primary">
            Register
          </Button>
          <Button onClick={handleLogin} color="primary">
            Login
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}