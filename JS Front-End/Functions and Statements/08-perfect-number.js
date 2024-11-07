function isPerfect(num) {
    const perfectMessage = "We have a perfect number!";
    const notPerfectMessage = "It's not so perfect.";
    const divisors = [1];

    if (num < 6 || !Number.isInteger(num)) {
        console.log(notPerfectMessage);
        return;
    }

    for (let i = 2; i <= num / 2; i++) {
        if (num % i === 0) {
            divisors.push(i);
        }
    }

    const sumDivisors = divisors.reduce((x, y) => x + y, 0);

    if (sumDivisors === num) {
        console.log(perfectMessage);
    } else {
        console.log(notPerfectMessage);
        
    }
}

isPerfect(28);
isPerfect(8128);