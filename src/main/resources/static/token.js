function saveToken() {
    sessionStorage.setItem('token', 'dfsdfg')
}

function getToken() {
    let url = '/api/v1/auth';
    let token = sessionStorage.getItem('token');

    let h = new Headers();
    h.append('Authorization', `Bearer ${token}`);

    let req = new Request(url, {
        method: 'POST',
        mode: 'cors',
        headers: h
    });
    fetch(req)
        .then(resp => resp.json())
        .then(data => {
            console.log(data[0]);
        })
        .catch(err => {
            console.error(err.message);
        })
}