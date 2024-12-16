function attachEvents() {

    const baseUrl = 'http://localhost:3030/jsonstore/messenger';

    const textareaEl = document.querySelector('textarea#messages');

    const inputs = document.querySelectorAll('#controls input[name]')

    const buttonRefresh = document.querySelector('#refresh');
    buttonRefresh.addEventListener('click', viewMessages);

    document.querySelector('#submit').addEventListener('click', handleSendMessage);

    function viewMessages(e) {
        fetch(baseUrl)
            .then(response => response.json())
            .then(messages => {
                textareaEl.textContent = '';
                Object.values(messages).forEach(message => {
                    textareaEl.textContent += `${message.author}: ${message.content}\n`;
               });
            })
            .catch(error => console.error(error));
    }

    function handleSendMessage(e) {
        const [author, content] = [...inputs].map(field => field.value);
        if ( !author || !content ) return;

        const body = {author, content};
        
        fetch(baseUrl, {
            method : 'POST',
            body : JSON.stringify(body)
        })
            .then(response => response.json())
            .then(result => {
                buttonRefresh.click();
            })
            .catch(error => console.error(error));
    }
}
attachEvents();