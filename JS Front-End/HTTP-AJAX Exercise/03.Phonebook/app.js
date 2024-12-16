function attachEvents() {
    const baseUrl = 'http://localhost:3030/jsonstore/phonebook';

    const phonebookEl = document.querySelector('#phonebook');

    const personInput = document.querySelector('#person');
    const phoneInput = document.querySelector('#phone');

    const loadBtn = document.querySelector('#btnLoad');

    document.querySelector('#btnCreate').addEventListener('click', createNewContact);

    loadBtn.addEventListener('click', (e) => {
        fetch(baseUrl)
            .then(response => response.json())
            .then(data => {
                phonebookEl.innerHTML = '';
                Object.values(data).forEach(entry => {
                    const dataLiEl = document.createElement('li');
                    dataLiEl.textContent = `${entry.person}: ${entry.phone}`
                    const deleteBtn = document.createElement('button');
                    deleteBtn.textContent = 'Delete';
                    deleteBtn.addEventListener('click', deleteEntry)

                    Object.assign(dataLiEl.dataset, entry);

                    dataLiEl.append(deleteBtn);
                    phonebookEl.append(dataLiEl);
                    
                });
                
            })
            .catch(err => console.error(err));
    });

    function createNewContact(e) {

        if ( !personInput || !phoneInput ) return;
        const data = { person : personInput.value , phone : phoneInput.value };
        
        fetch(baseUrl, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(result => {
            loadBtn.click();
        })
        .catch(err => console.error(err));
    }

    function deleteEntry(e) {
        const entryEl = e.target.closest('li');
        const contact = Object.assign({}, entryEl.dataset);

        fetch(baseUrl + `/${contact._id}`, {
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(result)
        .catch(err => console.error(err));
    }
}

document.addEventListener('DOMContentLoaded', attachEvents);
