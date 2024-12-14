document.addEventListener('DOMContentLoaded', solve);

function solve() {
    const values = { seconds: 1, minutes: 60, hours: 3600, days: 86400 };
    const inputElDays = document.querySelector('#days-input');
    const inputElHours = document.querySelector('#hours-input');
    const inputElMinutes = document.querySelector('#minutes-input');
    const inputElSeconds = document.querySelector('#seconds-input');

    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();

            const currentInputEl = e.target.querySelector('input[type="number"]');
            const currentVal = Number(currentInputEl.value);

            if (currentVal < 1) return;

            const key = currentInputEl.getAttribute('id').split('-input')[0];
            const multiplier = values[key];
            console.log(multiplier);

            convert(currentVal * multiplier);
            
        })
    });

    function convert(amountInSeconds) {
        inputElDays.value = Number(amountInSeconds / values.days).toFixed(2);
        inputElHours.value = Number(amountInSeconds / values.hours).toFixed(2);
        inputElMinutes.value = Number(amountInSeconds / values.minutes).toFixed(2);
        inputElSeconds.value = Number(amountInSeconds).toFixed(2);   
    }
}
