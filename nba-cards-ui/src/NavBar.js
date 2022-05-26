import {useContext} from "react";
import {Link} from "react-router-dom";
import AuthContext from "./contexts/AuthContext";

function NavBar() {

    const auth = useContext(AuthContext);

    return (
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                {auth.user.username !== "" ? (
                    <li>Hello {auth.user.username}! <button onClick={() => auth.logout}>Logout</button></li>
                ) : (
                    <li><Link to="/login">Login</Link></li>
                 )}
            </ul>
        </nav>

    );


}

export default NavBar;