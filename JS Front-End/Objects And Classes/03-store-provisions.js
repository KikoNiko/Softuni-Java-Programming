function storeProvision(inStockProd, orderedProd) {
    function generateObjectData(input) {
        return new Array(input.length / 2).fill({}).reduce((acc, curr, i) => {
            const product = input[i + i];
            const quantity = Number(input[i + i + 1]);

            if (!curr[product]) {
                curr[product] = 0;
            }
            
            curr[product] += quantity;

            return Object.assign(acc, curr);
        }, {});
    }

    const inStock = generateObjectData(inStockProd);
    const ordered = generateObjectData(orderedProd);

    const allProd = { ...inStock };

    Object.keys(ordered).forEach((productName) => {
        if (!allProd[productName]) {
            allProd[productName] = 0;
        }
        allProd[productName] += ordered[productName];
    });

    Object.entries(allProd).forEach(([k , v]) => {
        console.log(`${k} -> ${v}`);
        
    })
}

storeProvision([
    'Chips', '5', 'CocaCola', '9', 'Bananas','14', 'Pasta', '4', 'Beer', '2'
    ],
    [
    'Flour', '44', 'Oil', '12', 'Pasta', '7',
    'Tomatoes', '70', 'Bananas', '30'
    ])