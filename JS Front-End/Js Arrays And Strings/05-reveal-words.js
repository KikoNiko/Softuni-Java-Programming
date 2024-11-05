function solve(words, sentence) {

    words = words.split(', ');

    for (let w of words) {
        sentence = sentence.replace('*'.repeat(w.length), w);
    }

    return sentence;
}

console.log(solve('great', 'softuni is ***** place for learning new programming languages'));
