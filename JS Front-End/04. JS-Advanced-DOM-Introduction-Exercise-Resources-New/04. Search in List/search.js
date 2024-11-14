function search() {
   const towns = document.querySelectorAll("#towns li");

   function getMatchedElements(input) {
      return [...towns].filter(
         (x) => x.textContent.toLowerCase().includes(input.toLowerCase())
      );
   }

   function resetState() {
      towns.forEach((el) => {
         el.style.fontWeight = 'normal';
         el.style.textDecoration = 'none';
      })
   }

   resetState();

   const [inputEl] = document.getElementsByTagName('input');

   const matchedElements = getMatchedElements(inputEl.value);

   matchedElements.forEach((el) => {
      el.style.fontWeight = 'bold';
      el.style.textDecoration = 'underline';
   })

   document.getElementById('result').textContent = `${matchedElements.length} matches found`;

}
