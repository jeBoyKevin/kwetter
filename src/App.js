import './App.css';
import ProfilePage from "./components/profile/ProfilePage";
import LoginForm from "./components/auth/loginForm";
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {

  return (
    <div className="app">
      <Router>
      <LoginForm></LoginForm>
        <Route path="/profile/:id" component={ProfilePage}></Route>
      </Router>
    </div>
  );
}

export default App;
