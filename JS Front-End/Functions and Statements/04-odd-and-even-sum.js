function printOddAndEvenSum(num) {
    const numArr = num.toString().split('').map(Number);
    const oddSum = numArr.filter(n => n % 2).reduce((x, y) => x + y, 0);
    const evenSum = numArr.filter(n => n % 2 === 0).reduce((x, y) => x + y, 0);;

    console.log(`Odd sum = ${oddSum}, Even sum = ${evenSum}`);
}

printOddAndEvenSum(3495892137259234);