import Button from '@material-ui/core/Button';


const buttonStyle = {
    position: "absolute",
    top: "50%",
    transform: "translateY(-50%)",
    height: "50px",
    width: "100px",
    right: "25px"
}

export default function FormDialog() {
    const handleLogout = () => {
        localStorage.removeItem('token');
        window.location.replace('');
    }
    
  return (
    <div>
      <Button variant="contained" style={buttonStyle} onClick={handleLogout}>
        Logout
      </Button>
    </div>
  );
}