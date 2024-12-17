function loadMatches(baseUrl, onSuccess) {
    fetch(baseUrl)
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function createMatch(baseUrl, match, onSuccess) {
    fetch(baseUrl, {
        method: 'POST',
        body: JSON.stringify(match)
    })
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function updateMatch(baseUrl, match, onSuccess) {
    fetch(baseUrl + match._id, {
        method: 'PUT',
        body: JSON.stringify(match)
    })
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function deleteMatch(baseUrl, id, onSuccess) {
    fetch(baseUrl + id, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function createElement(tag, properties, container) {
    const element = document.createElement(tag);

    Object.keys(properties).forEach(key => {
        if ( typeof properties[key] === 'object' ) {
            Object.assign(element[key], properties[key]);
        } else {
            element[key] = properties[key];
        }
    });

    if ( container ) container.append(element);

    return element;
}


function init() {
    const baseUrl = 'http://localhost:3030/jsonstore/matches/';

    const fields = [...document.querySelectorAll('#form form input[type="text"')];
    const addMatchBtn = document.querySelector('#add-match');
    const editMatchBtn = document.querySelector('#edit-match');
    const listEl = document.querySelector('#list');
    
    addMatchBtn.addEventListener('click', createHandler);
    editMatchBtn.addEventListener('click', updateHandler);

    function loadEntries() {
        listEl.innerHTML = '';
        loadMatches(baseUrl, (result) => {
            Object.values(result).forEach(createEntry);
        });
    }

    function createEntry({ host, score, guest, _id }) {
        const entryEl = createElement('li', { className: 'match', dataset: { host, score, guest, _id }}, listEl);
        const infoEl = createElement('div', { className: 'info' }, entryEl);
        createElement('p', { textContent: host }, infoEl);
        createElement('p', { textContent: score }, infoEl);
        createElement('p', { textContent: guest }, infoEl);
        const buttonsEl = createElement('div', { className: 'btn-wrapper'}, entryEl);
        createElement('button', { className: 'change-btn', textContent: 'Change', onclick: editHandler }, buttonsEl);
        createElement('button', { className: 'delete-btn', textContent: 'Delete', onclick: deleteHandler }, buttonsEl);
    }

    function deleteEntry(_id) {
        listEl.querySelector(`li[data-_id='${_id}']`).remove();
    }

    function createHandler(e) {
        e.preventDefault();
        const [ host, score, guest ] = fields.map(field => field.value);
        if (!host || !score || !guest) return;
        const matchData = { host, score, guest };
        createMatch(baseUrl, matchData, (result) => {
            createEntry(result);
        });

        fields.forEach(field => field.value = '');
    }

    function editHandler(e) {
        const entryEl = e.target.closest('li');
        const datasetValues = Object.values(entryEl.dataset);

        entryEl.classList.add('active');

        fields.forEach((field, index) => {
            field.value = datasetValues[index];
        });

        addMatchBtn.disabled = true;
        editMatchBtn.disabled = false;
    }

    function updateHandler(e) {
        e.preventDefault();
        const [ host, score, guest ] = fields.map(field => field.value);
        if (!host || !score || !guest) return;
        const entryEl = listEl.querySelector('li.active');
        const matchData = { host, score, guest, _id: entryEl.dataset._id };

        updateMatch(baseUrl, matchData, (result) => {
            loadEntries();
            fields.forEach(field => field.value = '');
            addMatchBtn.disabled = false;
            editMatchBtn.disabled = true;
        });
    }

    function deleteHandler(e) {
        const entryEl = e.target.closest('li');
        const id = entryEl.dataset._id;
        deleteMatch(baseUrl, id, (result) => {
            deleteEntry(result._id);
        });
    }

    document.querySelector('#load-matches').addEventListener('click', loadEntries);
}

document.addEventListener('DOMContentLoaded', init);