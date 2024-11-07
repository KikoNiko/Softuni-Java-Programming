function printLoadingBar(n) {
    const loadingBarArr = ['%','%','%','%','%','%','%','%','%','%'];
    const numOfPercentageSigns = (100 - n) / 10;
    const defaultMsg = "Still loading...";

    if (n === 100) {
        console.log('100% Complete!');
        console.log(`[${loadingBarArr.join('')}]`);
        return;
    }

    for (let i = 0; i < numOfPercentageSigns; i++) {
        loadingBarArr.push('.');
        loadingBarArr.shift();
    }

    console.log(`${n}% [${loadingBarArr.join('')}]`);
    console.log(defaultMsg);
}

printLoadingBar(100);
printLoadingBar(10);
printLoadingBar(70);