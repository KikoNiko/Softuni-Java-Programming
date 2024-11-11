function solve(input) {
    input.forEach((name) => {
        console.log(`Name: ${name} -- Personal Number: ${name.length}`);
    })  
}

solve([
    'Silas Butler',
    'Adnaan Buckley',
    'Juan Peterson',
    'Brendan Villarreal'
]);