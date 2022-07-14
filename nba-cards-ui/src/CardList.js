import React, {useContext, useState, useEffect} from "react";
import {Link} from "react-router-dom";
import {fetchAll} from "./services/nbaCardApi";
import AuthContext from "./contexts/AuthContext";
import NbaCard from "./NbaCard";

function NbaCardList() {

    const [nbaCards, setNbaCards] = useState([]);

    const auth = useContext(AuthContext);

    useEffect(() => {
        //this runs once
        fetchAll()
            .then(json => setNbaCards[json])
            .catch(errors => console.log(errors));

    },[]);

    console.log(auth);

    return <>

    {auth.user.username && (
        <div>
            <Link to="add" className="btn btn-info">Add Card</Link>
        </div>
    )}

    {nbaCards.map(c => <NbaCard key={c.id} nbaCard={c} />)}
    </>

}
export default NbaCardList;