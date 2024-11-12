function oddOccurrences(input) {
    const words = input.toLowerCase().split(" ").reduce((acc, curr) => {
        
        if (!acc.hasOwnProperty(curr)) {
            acc[curr] = 0;
        }
        acc[curr] += 1;
        return acc;
    }, {});

    console.log(Object.keys(words).filter((key) => words[key] % 2 !== 0).join(" "));
}

oddOccurrences('Cake IS SWEET is Soft CAKE sweet Food');