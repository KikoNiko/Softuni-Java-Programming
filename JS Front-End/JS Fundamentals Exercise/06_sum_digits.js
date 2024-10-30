function solve(num) {
    let sum = 0;
    let stringNum = num.toString();
    for (let i = 0; i < stringNum.length; i++) {
        sum += Number(stringNum[i]);    
    }
    console.log(sum);
}

solve(245678);
solve(97561);
solve(543);
