import React, { useEffect, useState, useContext } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { deleteById, fetchById } from "./services/NbaCardApi";
import AuthContext from "./contexts/AuthContext";
import NotFound from "./NotFound";


function ConfirmDelete() {

    const [nbaCard, setNbaCard] = useState({ name: "" });

    const auth = useContext(AuthContext);

    const { cardId } = useParams();

    const navigate = useNavigate();

    useEffect(() => {
        if (cardId) {
            fetchById(cardId)
                .then((card) => setNbaCard(card))
                .catch(() => navigate("/404"));
        }

    }, [cardId, navigate]);

    const handleDelete = () => {
        deleteById(cardId, auth.user.token)
            .then(() => {
                navigate("/");
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