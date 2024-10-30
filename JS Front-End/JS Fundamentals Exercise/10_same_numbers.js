function solve(num) {
    let index = 0;
    let result = true;
    let numToString = num.toString();
    let sum = Number(numToString[index]);
    for (let i = 1; i < numToString.length; i++) {
        if (numToString[i] !== numToString[index]) {
            result = false;
        }
        index = i;
        sum += Number(numToString[i]);
    }

    console.log(result);
    console.log(sum);
}

solve(2222222);
solve(1234);