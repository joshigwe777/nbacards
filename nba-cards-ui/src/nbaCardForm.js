import React, { useEffect, useState, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { fetchAll } from "./services/TeamApi";
import AuthContext from "./contexts/AuthContext";
import ErrorSummary from "./ErrorSummary";
import { save } from "./services/NbaCardApi";


const EMPTY_CARD = {
    "id": 0,
    "name": "",
    "teamId": "",
    "ppg": 0,
    "apg": 0,
    "rpg": 0


};


function NbaCardForm() {

    const [nbaCard, setNbaCard] = useState(EMPTY_CARD);
    const [teams, setTeams] = useState([]);
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);

    const handleSubmit = (evt) => {
        evt.preventDevault();
        save(nbaCard, auth.user.token)
        .then(() => {
        //   history.push("/");
        })
        .catch(setErrors);
    }
    useEffect(() => {
        fetchAll()
          .then(setTeams)
          .catch(console.log);
      }, []);
    /*
        fetchAll and fetch by id will go here
    */

    const handleChange = (evt) => {
        //this method should set the nbaCard to the next card that is entered in the form
        const nextNbaCard = { ...nbaCard };
        let nextValue = evt.target.value;
        nextNbaCard[evt.target.name] = nextValue;
        setNbaCard(nextNbaCard);

    }

    return <>
        <h2>{nbaCard.id === 0 ? "New NBA card" : `Edit ${nbaCard.name}`}</h2>
        <form onSubmit={handleSubmit}>
            <div>
                <label className="form-label">Name</label>
                <input className="form-control" type="text" value={nbaCard.name} name="name" onChange={handleChange} />
            </div>
            <div>
                <label className="form-label">Team</label>
                <select className='form-control' value={nbaCard.teamId} name="teamId" onChange={handleChange} >
                    <option value="0">-- Choose team --</option>
                    {teams.map(t => <option key={t.id} value={t.teamName}>{t.teamName}</option>)}
                </select>
            </div>
            <label className="form-label">Points Per Game</label>
            <input className="form-control" type="text" value={nbaCard.ppg} name="ppg" onChange={handleChange} />
            <div>
                <label className="form-label">Assists Per game</label>
                <input className="form-control" type="text" value={nbaCard.apg} name="apg" onChange={handleChange} />

            </div>
            <div>
                <label className="form-label">Rebounds Per Game</label>
                <input className="form-control" type="text" value={nbaCard.rpg} name="rpg" onChange={handleChange} />

            </div>

            <button type="submit" className="btn btn-primary">Save</button>
            <Link to="/" className="btn btn-secondary">Cancel</Link>
        </form>
        <ErrorSummary errors={errors}/>

    </>



}
export default NbaCardForm;