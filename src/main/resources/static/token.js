function saveToken() {
    let xmlHttp = new XMLHttpRequest();
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    let token;

    xmlHttp.open("POST", "http://localhost:1337/api/v1/auth", false);
    xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xmlHttp.send(JSON.stringify({"username": username, "password": password}));

    token = JSON.parse(xmlHttp.responseText);
    sessionStorage.removeItem('token');
    sessionStorage.setItem('token', token.jwt);
}

function sendToken(token) {
    let url = '/api/v1/auth';

    let h = new Headers();
    h.append('Authorization', `Bearer ${token}`);

}