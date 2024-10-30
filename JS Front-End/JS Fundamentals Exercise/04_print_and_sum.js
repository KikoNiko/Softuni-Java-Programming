function solve(start, end) {
    let sum = 0;
    let log = '';
    for (let i = start; i <= end; i++) {
        log += i + ' ';
        sum += i;
    }
    console.log(`${log.trim()}\nSum: ${sum}`);
}

solve(5, 10);
solve(0, 26);