const url = `${window.NBA_CARD_GAME_API_URL}/nbacard`;

export async function fetchAll() {
    const response = await fetch(url);
    if (response.status!==200) {
        return Promise.reject("Could not fetch cards");
    }
    return await response.json();
};

export async function fetchById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 404) {
        throw new Error(response.statusText);
    }
    if (response.status!==200) {
        return Promise.reject("Could not fetch card with that id");
    }
    return await response.json();
}

export async function save(card, token) {

    const init = {
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(card)
    };

    if(card.id > 0) {
        init.method = "PUT";
        const response = await fetch(`${url}/${card.id}`, init);
        if (response.status == 400) {
            const errors = await response.json();
            return Promise.reject(errors);
        }
        if(response.status !== 204) {
            return Promise.reject("Could not update card!");
        }
    } else {
        init.method = "POST";
        const response = await fetch(url, init);
        if (response.status === 400) {
            const errors = await response.json();
            return Promise.reject(errors);
        } else if (response.status === 201) {
            return await response.json();
        }
        return Promise.reject("Card not created");
    }

}

export async function deleteById(id, token) {
    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/${id}`, init);
    if (response.status !== 204) {
        return Promise.reject(`Could not delete game: ${id}`);
    }

}