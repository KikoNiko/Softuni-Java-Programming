document.addEventListener('DOMContentLoaded', solve);

function solve() {
    document.querySelector('#encode').addEventListener('submit', (e) => {
        e.preventDefault();
        const inputEl = e.target.querySelector('textarea');
        const textMsg = inputEl.value;
        const encodedText = textMsg.split('').map(ch => String.fromCharCode(ch.charCodeAt() + 1)).join('');

        document.querySelector('#decode textarea').value = encodedText;
        inputEl.value = '';
    });

    document.querySelector('#decode').addEventListener('submit', (e) => {
        e.preventDefault();
        const outputEl = e.target.querySelector('textarea');
        const textMsg = outputEl.value;
        const decodedText = textMsg.split('').map(ch => String.fromCharCode(ch.charCodeAt() - 1)).join('');
        outputEl.value = decodedText;
    });
}