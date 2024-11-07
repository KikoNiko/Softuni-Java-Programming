function printMatrix(n) {
    const getNumberNTimes = (num, separator = ' ') => {
        return `${num}${separator}`.repeat(num).trim();
    };
    for (let i = 0; i < n; i++) {
        console.log(getNumberNTimes(n));
    }
}

printMatrix(3);