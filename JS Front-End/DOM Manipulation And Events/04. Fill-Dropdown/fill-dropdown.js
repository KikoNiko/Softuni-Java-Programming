document.addEventListener('DOMContentLoaded', solve);

function solve() {
    const dropdownSelect = document.querySelector('#menu');

    document.querySelector('form').addEventListener('submit', (e) => {
        e.preventDefault();

        const textInputContent = e.target.querySelector('#newItemText').value;
        const valueInputContent = e.target.querySelector('#newItemValue').value;

        if ( textInputContent == '' || valueInputContent == '' ) return;

        const optionEl = document.createElement('option');

        optionEl.textContent = textInputContent;
        optionEl.setAttribute('value', valueInputContent);

        dropdownSelect.append(optionEl);

        e.target.reset();
    });
}