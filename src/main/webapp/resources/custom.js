document.addEventListener("DOMContentLoaded", function () {refreshData()});

function refreshData() {
    const grid = document.getElementById("grid");
    grid.replaceChildren();

    const xhr = new XMLHttpRequest();
    xhr.open("GET", DATA_URL);
    xhr.send();
    xhr.responseType = "json";
    xhr.onload = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let appData = xhr.response;
            if (Array.isArray(appData)) {
                appData.forEach(
                    element => element['deleteIcon'] = '<img src="resources/common/favicon/cross-small.png" onclick="removeAppItem(' + element.id + ')"/>');
            }
            populateGrid(appData);
        } else {
            console.log(`Error: ${xhr.status}`);
        }
    }
}

function removeAppItem(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", DATA_URL + id);
    xhr.send();
    xhr.onload = () => {
        if (xhr.readyState == 4 && xhr.status == 204) {
            refreshData();
        } else {
            console.log(xhr);
            alert("Deleting that element is not allowed!");
        }
    }
}

function createAppItem(url) {
    const form = document.getElementById("form");
    const objs = Object.fromEntries(new FormData(form).entries());
    let body = JSON.stringify(objs);

    if (IS_SETS) {
        body = addKPacsToBody(body);
    }
    console.log(body);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(body);
    xhr.onload = () => {
        if (xhr.readyState == 4 && xhr.status == 201) {
            form.reset();
            refreshData();
        } else {
            alert("Error creating element!");
        }
    }
}

function addKPacsToBody(body) {
    const grid = document.getElementById("modal-grid");
    const inputs = grid.getElementsByTagName("input");
    const kpacs = [];
    for (let input of inputs) {
        console.log(input.checked);
        if (input.checked) {
            let kpac = {
                id: parseInt(input.id.replace(CHK_PREFIX, ""))
            };
            kpacs.push(kpac);
        }
    }
    if (kpacs.length === 0) {
        return body;
    }
    let tmp = JSON.parse(body);
    tmp.kpacs = kpacs;
    body = JSON.stringify(tmp);
    return body;
}