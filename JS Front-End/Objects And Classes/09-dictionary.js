function createDictionary(inputArr) {
    const dictionary = {};

    for (const json of inputArr) {
        const obj = JSON.parse(json);

        for (const key of Object.keys(obj)) {
            dictionary[key] = obj[key];
        }
    }
    const sortedDictKeys = Object.keys(dictionary).sort((a, b) => a.localeCompare(b));

    for (const term of sortedDictKeys) {
        console.log(`Term: ${term} => Definition: ${dictionary[term]}`);
        
    }
}