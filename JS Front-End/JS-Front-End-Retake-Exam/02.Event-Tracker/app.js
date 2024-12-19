window.addEventListener("load", solve);

function solve(){

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

    const formEl = document.querySelector('.event-content');

    const inputFields = [...document.querySelectorAll('#event, #note, #date')];
    const saveBtn = document.querySelector('#save');
    saveBtn.addEventListener('click', saveEventHandler);

    const upcomingListEl = document.querySelector('#upcoming-list');
    const eventListEl = document.querySelector('#events-list');

    function createEntry({ event, note, date }) {
    const eventItemEl = createElement('li', {className: 'event-item', dataset: { event, note, date }}, upcomingListEl);
    const containerEl = createElement('div', {className: 'event-container'}, eventItemEl)
    const articleEl = createElement('article', {}, containerEl);
    createElement('p', { textContent: `Name: ${event}` }, articleEl);
    createElement('p', { textContent: `Note: ${note}` }, articleEl);
    createElement('p', { textContent: `Date: ${date}` }, articleEl);
    const buttonsDivEl = createElement('div', {className: 'buttons'}, containerEl)
    createElement('button', { className: 'btn edit', textContent: 'Edit', onclick: editHandler }, buttonsDivEl);
    createElement('button', { className: 'btn done', textContent: 'Done', onclick: doneHandler }, buttonsDivEl);
  }

  function saveEventHandler(e) {
    e.preventDefault();
    const [ event, note, date ] = inputFields.map(field => field.value);
        if ( !event || !note || !date ) return;
        createEntry({ event, note, date });
        formEl.reset();
  }

  function editHandler(e) {
    const entryEl = e.target.closest('li');
    entryEl.remove();
    
    const values = [ entryEl.dataset.event, entryEl.dataset.note, entryEl.dataset.date ];

    inputFields.forEach((field, index) => field.value = values[index]);
  }

  function doneHandler(e) {
    const entryEl = e.target.closest('li');
    const [event, note, date] = [...Object.values(entryEl.dataset)];
    
    entryEl.remove();
    
    const eventItemEl = createElement('li', {className: 'event-item', dataset: { event, note, date }}, eventListEl);
    const articleEl = createElement('article', {}, eventItemEl);
    createElement('p', { textContent: `Name: ${event}` }, articleEl);
    createElement('p', { textContent: `Note: ${note}` }, articleEl);
    createElement('p', { textContent: `Date: ${date}` }, articleEl);
  }

  document.querySelector('.btn.delete').addEventListener('click', (e) => {
    document.querySelector('#events-list').innerHTML = '';    
  })
}

