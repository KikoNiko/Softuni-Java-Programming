function factorialDivision(n1, n2) {

    const findFactorial = (n) => {
        if (n < 0) {
            return -1;
        }
        if (n === 0) {
            return 1;
        }
    
        return n * findFactorial(n - 1);
    };

    console.log((findFactorial(n1) / findFactorial(n2)).toFixed(2));
      
}

factorialDivision(6, 2);