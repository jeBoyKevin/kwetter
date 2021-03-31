import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import axios from 'axios'

export default function FormDialog() {

  const [open, setOpen] = React.useState(false);
  const [details, setDetails] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState({
    warning: ''
  });
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = name => e => {
    setDetails({ ...details, [name]: e.target.value });
};


  const handleLogin = async (e) => {
    e.preventDefault();
    const { username, password } = details;

    axios.post('http://localhost:8079/account/login', {
      username: username,
      password: password
    })
    .then(response => {
      setError({error: ""})
      localStorage.setItem('userid', response.data.id)
    })
    .catch(error => {
      setError({warning: error.response.data.error})
    })
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    const { username, password } = details;

    axios.post('http://localhost:8079/account/register', {
      username: username,
      password: password
    })
    .then(response => {
      setError({error: ""})
      localStorage.setItem('userid', response.data.id)
    })
    .catch(error => {
        setError({warning: error.response.data.error})
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
          <DialogContentText>
            {error.warning}
          </DialogContentText>
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