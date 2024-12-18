//Helper functions
function loadResources(baseUrl, onSuccess) {
    fetch(baseUrl)
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function createResource(baseUrl, resource, onSuccess) {
    fetch(baseUrl, {
        method: 'POST',
        body: JSON.stringify(resource)
    })
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function updateResource(baseUrl, resource, onSuccess) {
    fetch(baseUrl + resource._id, {
        method: 'PUT',
        body: JSON.stringify(resource)
    })
        .then(response => response.json())
        .then(onSuccess)
        .catch(error => console.error(error));
}

function deleteResource(baseUrl, id, onSuccess) {
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

//Main function
function init() {
    const baseUrl = 'http://localhost:3030/jsonstore/workout/';

    const formEl = document.querySelector('#form form');

    const fields = [...formEl.querySelectorAll('input[name]')];

    const listEl = document.querySelector('#list');

    const addBtn = document.querySelector('#add-workout');
    addBtn.addEventListener('click', addHandler);
    const editBtn = document.querySelector('#edit-workout');
    editBtn.addEventListener('click', updateHandler);

    document.querySelector('#load-workout').addEventListener('click', loadEntries);

    function loadEntries() {
        listEl.innerHTML = '';
        loadResources(baseUrl, (result) => {
            Object.values(result).forEach(createEntry);
        });
    }

    function createEntry({ workout, location, date, _id }) {
        const entryEl = createElement('div', { className: 'container', dataset: { workout, location, date, _id }}, listEl);
        createElement('h2', { textContent: workout }, entryEl);
        createElement('h3', { textContent: date }, entryEl);
        createElement('h3', { textContent: location, id: 'location'}, entryEl);
        const buttonsEl = createElement('div', { id: 'buttons-container'}, entryEl);
        createElement('button', { className: 'change-btn', textContent: 'Change', onclick: editHandler }, buttonsEl);
        createElement('button', { className: 'delete-btn', textContent: 'Done', onclick: deleteHandler }, buttonsEl);
    }

    function deleteEntry(_id) {
        listEl.querySelector(`div.container[data-_id='${_id}']`).remove();
    }

    function addHandler(e) {
        e.preventDefault();
        const [ workout, location, date ] = fields.map(field => field.value);
        
        if (!workout || !location || !date) return;

        const data = { workout, location, date };
        
        createResource(baseUrl, data, (result) => {
            createEntry(result);
        });

        formEl.reset();
    }
    function editHandler(e) {
        const entryEl = e.target.closest('div.container');
        const datasetValues = Object.values(entryEl.dataset);

        entryEl.classList.add('active');

        fields.forEach((field, index) => {
            field.value = datasetValues[index];
        });

        addBtn.disabled = true;
        editBtn.disabled = false;
    }

    function updateHandler(e) {
        e.preventDefault();
        const [ workout, location, date ] = fields.map(field => field.value);
       
        if (!workout || !location || !date) return;
    
        const entryEl = listEl.querySelector('div.container.active');

        const data = { workout, location, date, _id: entryEl.dataset._id };

        updateResource(baseUrl, data, (result) => {
            loadEntries();
            fields.forEach(field => field.value = '');
            addBtn.disabled = false;
            editBtn.disabled = true;
        });
    }

    function deleteHandler(e) {
        const entryEl = e.target.closest('div.container');
        const id = entryEl.dataset._id;
        deleteResource(baseUrl, id, (result) => {
            deleteEntry(result._id);
        });
    }
}

document.addEventListener('DOMContentLoaded', init);