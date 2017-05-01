/**
 * Created by Duque on 01/05/2017.
 */
function initLogin() {
    appenDivToContent(createLoginBox());
}

function createLoginBox() {
    let badgeDiv = document.createElement('div');
    badgeDiv.className = 'login_box';

    //Badge Header
    badgeDiv.appendChild(createRowDiv('login_head', "Login"));

    //Badge Body
    let usernameInput = createInputRowDiv("usernameInput", 'text', "Username:");
    badgeDiv.appendChild(usernameInput);

    let passInput = createInputRowDiv('passInput', 'password', "Password:");
    badgeDiv.appendChild(passInput);

    let submitButton = createRowDiv('login_button', "Login");
    submitButton.onclick = function () {
        let usernameVal = document.getElementById('usernameInput').value;
        let passVal = document.getElementById('passInput').value;

        console.log(usernameVal);
        console.log(passVal);

        callLoginServlet(usernameVal, passVal);
    };

    badgeDiv.appendChild(submitButton);

    return badgeDiv;
}

function createInputRowDiv(id, type, name) {
    let containerDiv = document.createElement('div');
    containerDiv.className = 'login_input_container';
    containerDiv.appendChild(createTxtDiv(name, 'login_input_txt'));
    containerDiv.appendChild(createInput(id, type, name, 'login_input'));

    return containerDiv;
}

function createInput(id, type, name, className) {
    let input = document.createElement('input');
    input.className = className ? className : 'login_input';
    input.type = type;
    input.id = id;
    input.name = name;

    return input;
}

