import { useState, useEffect } from "react";
import { BrowserRouter as Router, Redirect, Route, Switch } from "react-router-dom";
import Heading from './Heading';
import ConfirmDelete from './ConfirmDelete.js';
import Login from './Login.js';
import CardList from './CardList.js';
import NotFound from './NotFound.js';
import AuthContext from './contexts/AuthContext';
import jwt_decode from 'jwt-decode';



function App() {

  const [user, setUser] = useState({ username: "" });
  const [initialized, setInitialized] = useState(false);

  const login = (token) => {

    const decodedToken = jwt_decode(token);

    const nextUser = { ...user };
    nextUser.username = decodedToken.sub;
    nextUser.roles = decodedToken.authorities.split(",");
    nextUser.token = token;
    nextUser.isAdmin = function () {
      return this.roles?.includes("ROLE_ADMIN");
    };

    localStorage.setItem('token', token);

    setUser(nextUser);
  }

  const logout = () => {
    localStorage.removeItem('token');
    setUser({ username: "" });
  };

  useEffect(() => {
    // may need adjust this logic to map onto the back end
    const token = localStorage.getItem('token');
    if (token) {
      login(token);
    }
    setInitialized(true);
  }, []);

  const auth = {
    user,
    login,
    logout
  };

  if (!initialized) {
    return null;
  }

  return (
    <div className="container">
      <AuthContext.Provider value={auth}>
        <Router>
          <Heading />
          <Switch>
            <Route path={["/edit/:id", "/add"]}>
              {auth.user.username ? <nbaCardForm /> : <Redirect to="/login" />}
            </Route>
            <Route path="/delete/:gameId/">
              {auth.user.username && auth.user.isAdmin() ? <ConfirmDelete /> : <Redirect to="/login" />}
            </Route>
            <Route path="/login">
              <Login />
            </Route>
            <Route exact path="/">
              <CardList />
            </Route>
            <Route>
              <NotFound />
            </Route>
          </Switch>
        </Router>

      </AuthContext.Provider>

    </div>

  )


}

export default App;
