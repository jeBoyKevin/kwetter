import './App.css';
import ProfilePage from "./components/profile/ProfilePage";
import LoginForm from "./components/auth/loginForm";
import MainPage from "./components/main/MainPage";
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {

  return (
    <div className="app">
      <Router>
      <Route path="/main"
        render={(props) =>
            localStorage.getItem('token') ? (
                <MainPage></MainPage>
            ) : (
                    <LoginForm></LoginForm>
                )}
      />
        <Route path="/profile/:id" component={ProfilePage}></Route>
      </Router>
    </div>
  );
}

export default App;
