function solve(year) {
    let result = 'no';
    if (year % 4 === 0 && year % 100 != 0 || year % 400 === 0) {
        result = 'yes';
    }
    console.log(result);
}
solve(1984);
solve(2003);
solve(4);