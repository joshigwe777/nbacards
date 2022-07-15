import React from "react";
function NbaCard({nbaCard}) {
    return <div className="card">
        <img className="card-image" src={nbaCard.imgUrl} alt="card image"/>
        <div className="card-body">
            <h5 className="card-title">{nbaCard.name}</h5>
            <p>{nbaCard.teamId}</p>
            <p>{nbaCard.position}</p>
            <p>{nbaCard.ppg}</p>
            <p>{nbaCard.apg}</p>
            <p>{nbaCard.rpg}</p>
        </div>

    
    </div>
}
export default NbaCard;