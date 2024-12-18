function solve(input) {
    const farmersCount = input.shift();
    const farmersData = input.splice(0, farmersCount);
    
    const farmers = farmersData.reduce((farmers, entry) => {
        let [ name, workArea, tasks ] = entry.split(' ');
        tasks = tasks.split(',');

        farmers[name] = { workArea, tasks };
        return farmers;
    }, {});
    
    input.forEach(line => {
        const parts = line.split(' / ');
        const command = parts.shift();
        const farmerName = parts.shift();
        const currentFarmer = farmers[farmerName];
        
        switch(command) {
            case 'Execute':
                const area = parts.shift();
                const task = parts.shift();                
                if (!currentFarmer.workArea.includes(area) || !currentFarmer.tasks.includes(task)) {
                    console.log(`${farmerName} cannot execute the task: ${task}.`);
                } else {
                    console.log(`${farmerName} has executed the task: ${task}!`);
                }
                break;
            case 'Learn Task':
                const newTask = parts.shift();
                if (currentFarmer.tasks.includes(newTask)) {
                    console.log(`${farmerName} already knows how to perform ${newTask}.`);
                } else {
                    currentFarmer.tasks.push(newTask);
                    console.log(`${farmerName} has learned a new task: ${newTask}.`);
                }
                break;
            case 'Change Area':
                const newArea = parts.shift();
                currentFarmer.workArea = newArea;
                console.log(`${farmerName} has changed their work area to: ${newArea}`);
                break;
        }
    });

    let output = '';

    Object.keys(farmers).forEach(farmerName => {
        const tasks = farmers[farmerName].tasks.sort();
        output += `Farmer: ${farmerName}, Area: ${farmers[farmerName].workArea}, Tasks: ${tasks.join(', ')}\n`
    });
    
    console.log(output);
}


solve([ 
    "3", 
    "Alex apiary harvesting,honeycomb", 
    "Emma barn milking,cleaning", 
    "Chris garden planting,weeding", 
    "Execute / Alex / apiary / harvesting", 
    "Learn Task / Alex / beeswax", 
    "Execute / Alex / apiary / beeswax", 
    "Change Area / Emma / apiary", 
    "Execute / Emma / apiary / milking", 
    "Execute / Chris / garden / watering", 
    "Learn Task / Chris / pruning", 
    "Execute / Chris / garden / pruning", 
    "End" 
]);

/* Alex has executed the task: harvesting! 

Alex has learned a new task: beeswax. 

Alex has executed the task: beeswax! 

Emma has changed their work area to: apiary 

Emma has executed the task: milking! 

Chris cannot execute the task: watering. 

Chris has learned a new task: pruning. 

Chris has executed the task: pruning! 

Farmer: Alex, Area: apiary, Tasks: beeswax, harvesting, honeycomb 

Farmer: Emma, Area: apiary, Tasks: cleaning, milking 

Farmer: Chris, Area: garden, Tasks: planting, pruning, weeding  */