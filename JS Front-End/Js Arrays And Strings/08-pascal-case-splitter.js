function solve (input) {
    console.log(input.match(/[A-Z][a-z]*/g).join(', '));
}

solve('SplitMeIfYouCanHaHaYouCantOrYouCan');