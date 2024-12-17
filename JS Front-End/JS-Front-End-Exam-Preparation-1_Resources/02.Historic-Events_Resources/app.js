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

  const addEventForm = document.querySelector('#add-event form');
  addEventForm.addEventListener('submit', handleAddEvent);

  const inputFields = [...document.querySelectorAll('#name, #time, #description')];
  const btnAddEl = document.querySelector('#add-btn');

  const listPreviewEl = document.querySelector('#preview-list');
  const listArchiveEl = document.querySelector('#archive-list');

  function createEntry({ name, time, desc }) {
    const entryEl = createElement('li', { dataset: { name, time, desc }}, listPreviewEl);
    const articleEl = createElement('article', {}, entryEl);
    createElement('p', { textContent: name }, articleEl);
    createElement('p', { textContent: time }, articleEl);
    createElement('p', { textContent: desc }, articleEl);
    const buttonsEl = createElement('div', { className: 'buttons'}, entryEl);
    createElement('button', { className: 'edit-btn', textContent: 'Edit', onclick: handleEditEvent }, buttonsEl);
    createElement('button', { className: 'next-btn', textContent: 'Next', onclick: handleNextEvent }, buttonsEl);
}

  function handleAddEvent(e) {
    e.preventDefault();
    const [ name, time, desc ] = inputFields.map(field => field.value);

        if ( ! name || ! time || ! desc ) return;

        createEntry({ name, time, desc });

        btnAddEl.disabled = true;
        
        addEventForm.reset();
  }

  function handleEditEvent(e) {
    const entryEl = e.target.closest('li');
        entryEl.remove();

        const values = [ entryEl.dataset.name, entryEl.dataset.time, entryEl.dataset.desc ];

        inputFields.forEach((field, index) => field.value = values[index]);
        btnAddEl.disabled = false;
  }

  function handleNextEvent(e) {
    const entryEl = e.target.closest('li');
        entryEl.remove();

        entryEl.querySelector('div.buttons').remove();
        createElement('button', { className: 'archive-btn', textContent: 'Archive', onclick: handleArchiveEvent }, entryEl);

        listArchiveEl.append(entryEl);
  }

  function handleArchiveEvent(e) {
    const entryEl = e.target.closest('li');
    entryEl.remove();

    btnAddEl.disabled = false;
  }

}