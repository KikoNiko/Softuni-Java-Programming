document.addEventListener('DOMContentLoaded', solve);

function solve() {
   const formEl = document.getElementById('task-input');
   const contentDiv = document.getElementById('content');
   
   formEl.addEventListener('submit', (e) => {
      e.preventDefault();

      const sections = formEl.elements[0].value;
      sections.split(',').forEach(el => {
         const innerDivEl = document.createElement('div');
         const newPEl = document.createElement('p');

         newPEl.textContent = el;
         newPEl.style.display = 'none';

         innerDivEl.appendChild(newPEl)
         innerDivEl.addEventListener('click', (e) => {
            e.target.querySelector('p').style.display = 'block';
         });

         contentDiv.appendChild(innerDivEl);
      });
   });
   
}