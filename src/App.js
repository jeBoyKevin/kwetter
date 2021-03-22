import './App.css';
import ProfilePage from "./components/profile/ProfilePage"
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {

  return (
    <div className="app">
      <Router>
        <Route path="/profile/:id" component={ProfilePage}></Route>
      </Router>
    </div>
  );
}

export default App;
