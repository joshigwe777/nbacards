const url = `http://localhost:8080/team`;

export async function fetchAll() {
    const response = await fetch(url);
    if (response.status!==200) {
        return Promise.reject("Could not fetch cards");
    }
    return await response.json();
};