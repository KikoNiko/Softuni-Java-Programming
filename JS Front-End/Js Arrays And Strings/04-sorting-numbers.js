function solve(input) {
    input.sort((a, b) => a - b);
    
    const counter = [...new Array(input.length).keys()];

    return counter.reduce(function(result, i) {
        if (i % 2 === 0) {
            return [...result, input.shift()];
        } else {
            return [...result, input.pop()];
        }
    }, []);
}

console.log(solve([1, 65, 3, 52, 48, 63, 31, -3, 18, 56]));