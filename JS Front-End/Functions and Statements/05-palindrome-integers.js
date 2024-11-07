function printIsPalindrome(numbers) {
    function isPalindrome(n) {
        return n.toString() === n.toString().split('').reverse().join('');
    }
    for (let n of numbers) {
        console.log(isPalindrome(n));
    }
}

printIsPalindrome([123,323,421,121]);