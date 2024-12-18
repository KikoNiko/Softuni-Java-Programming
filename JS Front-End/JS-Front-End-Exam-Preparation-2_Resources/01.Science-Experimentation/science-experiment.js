function solve(input) {

    const chemicalCount = input.shift();
    const chemicalDetails = input.splice(0, chemicalCount);

    const chemicals = chemicalDetails.reduce((chemicals, entry) => {
        let [ name, quantity ] = entry.split(' # ');
    
        chemicals[name] = { quantity: Number(quantity) }
        return chemicals;
    }, {});
    
    input.forEach(line => {
        line = line.split(' # ');
        const command = line.shift();
        
        switch(command) {
            case 'Mix':
                let [ firstChemical, secondChemical, quantity ] = line;
                quantity = Number(quantity);
                if (chemicals[firstChemical].quantity - quantity < 0 || chemicals[secondChemical].quantity - quantity < 0) {
                    console.log(`Insufficient quantity of ${firstChemical}/${secondChemical} to mix.`);
                } else {
                    chemicals[firstChemical].quantity -= quantity;
                    chemicals[secondChemical].quantity -= quantity;
                    console.log(`${firstChemical} and ${secondChemical} have been mixed. ${quantity} units of each were used.`);
                }
                
                break;
            case 'Replenish':    
                let [chemical, amountToAdd] = line;
                amountToAdd = Number(amountToAdd);

                if (Object.keys(chemicals).includes(chemical)) {
                    const initialQuantity = chemicals[chemical].quantity;
                    const result = initialQuantity + amountToAdd;
                    if (result < 500) {
                        chemicals[chemical].quantity += amountToAdd;
                        console.log(`${chemical} quantity increased by ${amountToAdd} units!`);
                    } else {
                        const remainder = result - 500;
                        const addedQuantity = amountToAdd - remainder;
                        chemicals[chemical].quantity = 500;
                        console.log(`${chemical} quantity increased by ${addedQuantity} units, reaching maximum capacity of 500 units!`);  
                    }
                } else {
                    console.log(`The Chemical ${chemical} is not available in the lab.`);
                }
                break;
            case 'Add Formula':
                let [chemicalName, formula] = line;

                if (!Object.keys(chemicals).includes(chemicalName)) {
                    console.log(`The Chemical ${chemicalName} is not available in the lab.`);
                    break;
                }
                chemicals[chemicalName]['formula'] = formula;
                console.log(`${chemicalName} has been assigned the formula ${formula}.`);
                break;
        }  
    });

    let output = '';
    Object.keys(chemicals).forEach(chemicalName => {
        if (Object.keys(chemicals[chemicalName]).includes('formula')) {
            output += `Chemical: ${chemicalName}, Quantity: ${chemicals[chemicalName].quantity}, Formula: ${chemicals[chemicalName].formula}\n`;
        } else {
            output += `Chemical: ${chemicalName}, Quantity: ${chemicals[chemicalName].quantity}\n`;
        }
    });
    console.log(output);
}

solve([ '4',
    'Water # 200',  
    'Salt # 100', 
    'Acid # 50',
    'Base # 80',
    'Mix # Water # Salt # 50',
    'Replenish # Salt # 150',
    'Add Formula # Acid # H2SO4',
    'End']);

//Water and Salt have been mixed. 50 units of each were used. 
// Salt quantity increased by 150 units! 
// Acid has been assigned the formula H2SO4. 
// Chemical: Water, Quantity: 150 
// Chemical: Salt, Quantity: 200 
// Chemical: Acid, Quantity: 50, Formula: H2SO4 
// Chemical: Base, Quantity: 8