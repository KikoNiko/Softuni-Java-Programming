function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   const tableRowEls = document.querySelectorAll("table.container tbody tr");
   const searchFieldEl = document.getElementById('searchField');

   function getMatchedElements(input) {
      return [...tableRowEls].filter((el) => 
         el.textContent.toLocaleLowerCase().includes(input.toLowerCase())
      );
   }

   function clearState() {
      [...tableRowEls].forEach((el) => {
         el.classList.remove('select')
      });
   }

   function onClick() {

      clearState();

      getMatchedElements(searchFieldEl.value).forEach((row) => {
         row.classList.add('select');
      });

      searchFieldEl.value = "";
   }
}