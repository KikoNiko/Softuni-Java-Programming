function solve(input) {
    let pattern = /#[A-Za-z]+/g;
    const words = input.match(pattern);

    words.forEach((el) => console.log(el.substring(1)));
}

solve('Nowadays everyone uses # to tag a #special word in #socialMedia');