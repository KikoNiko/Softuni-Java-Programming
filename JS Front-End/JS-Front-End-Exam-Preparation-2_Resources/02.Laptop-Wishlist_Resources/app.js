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

  const addForm = document.querySelector('.laptop-info');

  const inputFields = [...document.querySelectorAll('#laptop-model, #storage, #price')];
  const btnAddEl = document.querySelector('#add-btn');
  btnAddEl.addEventListener('click', addHandler);

  const checkListEl = document.querySelector('#check-list');
  const wishListEl = document.querySelector('#laptops-list');

  document.querySelector('.btn.clear').addEventListener('click', (e) => {
    e.preventDefault();
    window.location.reload();
  })
  

  function createEntry({ model, storage, price }) {
    const entryEl = createElement('li', {className: 'laptop-item', dataset: { model, storage, price }}, checkListEl);
    const articleEl = createElement('article', {}, entryEl);
    createElement('p', { textContent: model }, articleEl);
    createElement('p', { textContent: `Memory: ${storage} TB` }, articleEl);
    createElement('p', { textContent: `Price: ${price}$` }, articleEl);
    createElement('button', { className: 'btn edit', textContent: 'edit', onclick: editHandler }, entryEl);
    createElement('button', { className: 'btn ok', textContent: 'ok', onclick: okHandler }, entryEl);
}

  function addHandler(e) {
    e.preventDefault();
    const [ model, storage, price ] = inputFields.map(field => field.value);
    
        if ( ! model || ! storage || ! price ) return;

        createEntry({ model, storage, price });

        btnAddEl.disabled = true;
        
        addForm.reset();
  }

  function editHandler(e) {
      const entryEl = e.target.closest('li');
        entryEl.remove();

        const values = [ entryEl.dataset.model, entryEl.dataset.storage, entryEl.dataset.price ];

        inputFields.forEach((field, index) => field.value = values[index]);
        btnAddEl.disabled = false;
  }

  function okHandler(e) {
      const entryEl = e.target.closest('li');
        const [model, storage, price] = [...Object.values(entryEl.dataset)];
        
        entryEl.remove();

        const itemEl = createElement('li', {className: 'laptop-item', dataset: { model, storage, price }}, wishListEl);
        const articleEl = createElement('article', {}, itemEl);
        createElement('p', { textContent: model }, articleEl);
        createElement('p', { textContent: `Memory: ${storage} TB` }, articleEl);
        createElement('p', { textContent: `Price: ${price}$` }, articleEl);

        wishListEl.append(itemEl);

        btnAddEl.disabled = false;
  }
}
  