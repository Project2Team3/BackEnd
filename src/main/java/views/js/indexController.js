let user = {
    username: "Samuel",
    password: "Liew",
};

const changeToRegister = () => {
    let loginForm = document.getElementsByClassName("loginFormContainer")[0];
    loginForm.classList.add("inactive");

    let registerForm = document.getElementsByClassName(
        "registerFormContainer"
    )[0];
    registerForm.classList.remove("inactive");
};

const changeToLogin = () => {
    let registerForm = document.getElementsByClassName(
        "registerFormContainer"
    )[0];
    registerForm.classList.add("inactive");

    let loginForm = document.getElementsByClassName("loginFormContainer")[0];
    loginForm.classList.remove("inactive");
};

let changeToLoginButton = document.getElementById("login");
let changeToRegisterButton = document.getElementById("register");
let loginForm = document.getElementById("loginForm");

loginForm.onsubmit = (e) => {
    e.preventDefault();
    let username = e.target[0].value;
    let password = e.target[1].value;
    if (username === user.username && password === user.password) {
        window.location.replace(
            "http://127.0.0.1:5500/src/main/java/views/userHomepage.html"
        );
    }
};

changeToLoginButton.addEventListener("click", changeToLogin);
changeToRegisterButton.addEventListener("click", changeToRegister);
