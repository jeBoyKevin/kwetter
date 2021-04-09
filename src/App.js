import './App.css';
import ProfilePage from "./components/profile/ProfilePage";
import MainPage from "./components/main/MainPage";
import Banner from "./components/main/Banner";
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {

  return (
    <div className="app">
      <Banner></Banner>
      <Router>
      <Route path="/main"
        render={() =>
            localStorage.getItem('token') ? (
                <MainPage></MainPage>
            ) : (
                    console.log("Not logged in")
                )}
      />
        <Route path="/profile/:id" component={ProfilePage}></Route>
      </Router>
    </div>
  );
}

export default App;
