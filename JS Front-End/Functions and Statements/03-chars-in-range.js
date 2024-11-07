function printCharsInRange(c1, c2) {
    const firstCharCode = c1.charCodeAt(0);
    const secondCharCode = c2.charCodeAt(0);
    const smallerChar = Math.min(firstCharCode, secondCharCode) + 1;
    const length = Math.abs(firstCharCode - secondCharCode) - 1;
    
    const charsArr = new Array(length).fill(0).map((_, i) => String.fromCharCode(smallerChar + i));
    console.log(charsArr.join(' '));
}

printCharsInRange('a', 'd');