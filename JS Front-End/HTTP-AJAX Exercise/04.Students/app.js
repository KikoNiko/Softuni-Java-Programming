function attachEvents() {
  const baseUrl = 'http://localhost:3030/jsonstore/collections/students';

  const tbodyEl = document.querySelector('table#results tbody');
  
  const inputs = document.querySelectorAll('.inputs input[type="text"]');

  document.addEventListener('DOMContentLoaded', loadContent);
  
  const submitBtn = document.querySelector('button#submit');

  submitBtn.addEventListener('click', (e) => {
    e.preventDefault();

    const [firstName, lastName, facultyNumber, grade] = [...inputs].map(field => field.value);
    const studentData = {firstName, lastName, facultyNumber, grade};

    if ( !firstName || !lastName || !facultyNumber || !grade )  {
      return;
    };
    
    fetch(baseUrl, {
      method: 'POST',
      body: JSON.stringify(studentData)
    })
    .then(response => response.json())
    .then(loadContent)
    .catch(error => console.error(error));
    
  });

  function loadContent(e) {
    tbodyEl.innerHTML = '';
    fetch(baseUrl)
            .then(response => response.json())
            .then(students => {
                Object.values(students).forEach(student => {
                  const studentDataEl = document.createElement('tr');
                  const firstNameEl = document.createElement('td');
                  firstNameEl.textContent = student.firstName;
                  const lastNameEl = document.createElement('td');
                  lastNameEl.textContent = student.lastName;
                  const facultyNumberEl = document.createElement('td');
                  facultyNumberEl.textContent = student.facultyNumber;
                  const gradeEl = document.createElement('td');
                  gradeEl.textContent = student.grade;

                  studentDataEl.append(firstNameEl, lastNameEl, facultyNumberEl, gradeEl);
                  tbodyEl.append(studentDataEl);
                });
            })
            .catch(error => console.error(error));
  }
}

attachEvents();