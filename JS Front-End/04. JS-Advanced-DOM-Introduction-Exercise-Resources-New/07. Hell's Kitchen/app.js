function solve() {
   document.querySelector('#btnSend').addEventListener('click', onClick);

   const inputTextArea = document.querySelector('#inputs textarea');
   const bestRestaurantEl = document.querySelector('#outputs #bestRestaurant p');
   const workersEl = document.querySelector('#outputs #workers p');

   function onClick () {
      const restaurants = JSON.parse(inputTextArea.value).reduce((acc, data) => {
         const [restName, workersData] = data.split(' - ');

         const workers = workersData.split(', ').map((wData) => {
            const [name, salary] = wData.split(' ');
            return {
               name,
               salary: Number(salary)
            }
         });

         if (!acc.hasOwnProperty(restName)) {
            acc[restName] = {
               workers: []
            }
         }

         acc[restName].workers.push(...workers);

         return acc;
      }, {});
      
      function getAvgSalary(restData) {
         return restData.workers.reduce((a, b) => a + b.salary, 0) / restData.workers.length;
      }

      const [bestRestaurantKey] = Object.keys(restaurants)
         .sort((a, b) => getAvgSalary(restaurants[b]) - getAvgSalary(restaurants[a]) || -1);
      
      const bestWorkers = restaurants[bestRestaurantKey].workers
         .slice()
         .sort((a, b) => b.salary - a.salary);
      
      bestRestaurantEl.textContent = 
      `Name: ${bestRestaurantKey} Average Salary: ${getAvgSalary(
         restaurants[bestRestaurantKey]
      ).toFixed(2)} Best Salary: ${bestWorkers[0].salary.toFixed(2)}`;

      workersEl.textContent = 
      bestWorkers.map((x) => `Name: ${x.name} With Salary: ${x.salary}`).join(' ');
   }
}