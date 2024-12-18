window.addEventListener("load", solve);

function solve() {

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

  const addForm = document.querySelector('.registerEvent');

  const inputFields = [...document.querySelectorAll('#email, #event, #location')];
  const nextBtn = document.querySelector('#next-btn');
  nextBtn.addEventListener('click', nextEventHandler);

  const previewListEl = document.querySelector('#preview-list');
  const eventListEl = document.querySelector('#event-list');

  function createEntry({ email, event, location }) {
    const entryEl = createElement('li', {className: 'application', dataset: { email, event, location }}, previewListEl);
    const articleEl = createElement('article', {}, entryEl);
    createElement('h4', { textContent: email }, articleEl);
    createElement('p', { innerHTML: `<strong>Event:</strong><br>${event}` }, articleEl);
    createElement('p', { innerHTML: `<strong>Location:</strong><br>${location}` }, articleEl);
    createElement('button', { className: 'action-btn edit', textContent: 'edit', onclick: editHandler }, entryEl);
    createElement('button', { className: 'action-btn apply', textContent: 'apply', onclick: applyHandler }, entryEl);
  }

  function nextEventHandler(e) {
    e.preventDefault();
    const [ email, event, location ] = inputFields.map(field => field.value);
    
        if ( !email || !event || !location ) return;

        createEntry({ email, event, location });

        nextBtn.disabled = true;
        
        addForm.reset();
  }

  function editHandler(e) {
      const entryEl = e.target.closest('li');
        entryEl.remove();

        const values = [ entryEl.dataset.email, entryEl.dataset.event, entryEl.dataset.location ];

        inputFields.forEach((field, index) => field.value = values[index]);
        nextBtn.disabled = false;
  }

  function applyHandler(e) {
      const entryEl = e.target.closest('li');
        const [email, event, location] = [...Object.values(entryEl.dataset)];
        
        entryEl.remove();

        const listEl = createElement('li', {className: 'application', dataset: { email, event, location }}, eventListEl);
        const articleEl = createElement('article', {}, listEl);
        createElement('h4', { textContent: email }, articleEl);
        createElement('p', { innerHTML: `<strong>Event:</strong><br>${event}` }, articleEl);
        createElement('p', { innerHTML: `<strong>Location:</strong><br>${location}` }, articleEl);

        nextBtn.disabled = false;
  }
}
