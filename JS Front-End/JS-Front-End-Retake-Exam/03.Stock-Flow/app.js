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
    const baseUrl = 'http://localhost:3030/jsonstore/orders/';

    const formEl = document.querySelector('#form form');
    const fields = [...formEl.querySelectorAll('form input[name]')];

    const listEl = document.querySelector('#list');

    const orderBtn = document.querySelector('#order-btn');
    orderBtn.addEventListener('click', addOrderHandler);
    const editBtn = document.querySelector('#edit-order');
    editBtn.addEventListener('click', updateHandler);

    document.querySelector('#load-orders').addEventListener('click', loadEntries);

    function loadEntries() {
        listEl.innerHTML = '';
        loadResources(baseUrl, (result) => {
            Object.values(result).forEach(createEntry);
        });
    }

    function createEntry({ name, quantity, date, _id }) {
        const entryEl = createElement('div', { className: 'container', dataset: { name, quantity, date, _id }}, listEl);
        createElement('h2', { textContent: name }, entryEl);
        createElement('h3', { textContent: date }, entryEl);
        createElement('h3', { textContent: quantity}, entryEl);
        createElement('button', { className: 'change-btn', textContent: 'Change', onclick: editHandler }, entryEl);
        createElement('button', { className: 'done-btn', textContent: 'Done', onclick: deleteHandler }, entryEl);
    }

    function addOrderHandler(e) {
        e.preventDefault();
        const [ name, quantity, date ] = fields.map(field => field.value);
        
        if (!name || !quantity || !date) return;

        const data = { name, quantity, date };
        
        createResource(baseUrl, data, (result) => {
            loadEntries();
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

        orderBtn.disabled = true;
        editBtn.disabled = false;
    }

    function updateHandler(e) {
        e.preventDefault();
        const [ name, quantity, date ] = fields.map(field => field.value);
       
        if (!name || !quantity || !date) return;
    
        const entryEl = listEl.querySelector('div.container.active');

        const data = { name, quantity, date, _id: entryEl.dataset._id };

        updateResource(baseUrl, data, (result) => {
            loadEntries();
            fields.forEach(field => field.value = '');
            orderBtn.disabled = false;
            editBtn.disabled = true;
        });
    }

    function deleteEntry(_id) {
        listEl.querySelector(`div.container[data-_id='${_id}']`).remove();
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