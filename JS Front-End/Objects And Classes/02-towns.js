function printTowns(input) {
    const towns = input.reduce((acc, cur) => {
        const [town, latitude, longitude] = cur.split(" | ");
        acc.push({
            town,
            latitude: Number(latitude).toFixed(2),
            longitude: Number(longitude).toFixed(2)
        });
        
        return acc;
    }, []);
    towns.forEach((town) => {
        console.log(town);
    })
}

printTowns(['Sofia | 42.696552 | 23.32601', 'Beijing | 39.913818 | 116.363625']);