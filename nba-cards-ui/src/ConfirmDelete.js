import { useEffect, useState, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, fetchById } from "./services/nbaCardApi";
import AuthContext from "./contexts/AuthContext";


function ConfirmDelete() {

    const [nbaCard, setNbaCard] = useState({ name: "" });

    const auth = useContext(AuthContext);

    const { cardId } = useParams();

    const history = useHistory();

    useEffect(() => {
        if (cardId) {
            fetchById(cardId)
                .then((card) => setNbaCard(card))
                .catch(() => history.push("/404"));
        }

    }, [cardId, history]);

    const handleDelete = () => {
        deleteById(cardId, auth.user.token)
            .then(() => {
                history.push("/");
            })
            .catch(console.log);

    }
    return <div>
        <h2>Delete {nbaCard.name}?</h2>
        <div>This cannot be undone</div>
        <div>
            <button className="btn btn-danger" onClick={handleDelete}>Delete Forever</button>
            <Link to="/" className="btn btn-secondary">Cancel</Link>
        </div>
    </div>;



}
export default ConfirmDelete;