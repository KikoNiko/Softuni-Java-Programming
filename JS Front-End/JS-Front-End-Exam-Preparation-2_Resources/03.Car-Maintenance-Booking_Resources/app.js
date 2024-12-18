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

function init() {
    const baseUrl = 'http://localhost:3030/jsonstore/appointments/';

    const formEl = document.querySelector('#form form');

    const modelInput = document.querySelector('#car-model');
    const serviceInput = document.querySelector('#car-service');
    const dateInput = document.querySelector('#date');

    const fields = [modelInput, serviceInput, dateInput];

    const listEl = document.querySelector('#appointments-list');

    const addBtn = document.querySelector('#add-appointment');
    addBtn.addEventListener('click', addHandler);
    const editBtn = document.querySelector('#edit-appointment');
    editBtn.addEventListener('click', updateHandler);

    function loadEntries() {
        loadResources(baseUrl, (result) => {
            Object.values(result).forEach(createEntry);
        });
    }

    function createEntry({ model, service, date, _id }) {
        const entryEl = createElement('li', { className: 'appointment', dataset: { model, service, date, _id }}, listEl);
        createElement('h2', { textContent: model }, entryEl);
        createElement('h3', { textContent: date }, entryEl);
        createElement('h3', { textContent: service }, entryEl);
        const buttonsEl = createElement('div', { className: 'buttons-appointment'}, entryEl);
        createElement('button', { className: 'change-btn', textContent: 'Change', onclick: editHandler }, buttonsEl);
        createElement('button', { className: 'delete-btn', textContent: 'Delete', onclick: deleteHandler }, buttonsEl);
    }

    function deleteEntry(_id) {
        listEl.querySelector(`li[data-_id='${_id}']`).remove();
    }

    function addHandler(e) {
        e.preventDefault();
        const [ model, service, date ] = fields.map(field => field.value);
        
        if (!model || !service || !date) return;

        const data = { model, service, date };
        
        createResource(baseUrl, data, (result) => {
            createEntry(result);
        });

        formEl.reset();
    }
    function editHandler(e) {
        const entryEl = e.target.closest('li');
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
        const [ model, service, date ] = fields.map(field => field.value);
       
        if (!model || !service || !date) return;
    
        const entryEl = listEl.querySelector('li.active');

        const data = { model, service, date, _id: entryEl.dataset._id };

        updateResource(baseUrl, data, (result) => {
            loadEntries();
            fields.forEach(field => field.value = '');
            addBtn.disabled = false;
            editBtn.disabled = true;
        });
    }

    function deleteHandler(e) {
        const entryEl = e.target.closest('li');
        const id = entryEl.dataset._id;
        deleteResource(baseUrl, id, (result) => {
            deleteEntry(result._id);
        });
    }

    document.querySelector('#load-appointments').addEventListener('click', loadEntries);
    
}

document.addEventListener('DOMContentLoaded', init);