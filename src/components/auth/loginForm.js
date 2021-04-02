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
    password: ''
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


  const handleLogin = async (e) => {
    e.preventDefault();
    const { username, password } = details;

    axios.post('http://localhost:8083/account/signin', {
      username: username,
      password: password
    })
    .then(response => {
      localStorage.setItem('userid', response.data.id)
      handleClose();
    })
    .catch(error => {
      setError({warning: error.response.data.error})
      handleErrorOpen();
    })
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    const { username, password } = details;

    axios.post('http://localhost:8083/user/signup', {
      username: username,
      password: password,
      roles: [
        "ROLE_CLIENT"
      ]
    })
    .then(response => {
      localStorage.setItem('userid', response.data)
      handleClose();
    })
    .catch(error => {
      setError({warning: error.response.data.error})
      handleErrorOpen();
      })
  };

  return (
    <div>
      <Button variant="outlined" color="primary" onClick={handleClickOpen}>
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