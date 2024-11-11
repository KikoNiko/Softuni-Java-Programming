function getMovieData(input) {
    const mdb = [];

    input.forEach((command) => {
        if (command.startsWith("addMovie")) {
            const [name] = command.split("addMovie ").filter(Boolean);
            mdb.push({name});
        } else if (command.includes("directedBy")) {
            const [name, director] = command.split(" directedBy ").filter(Boolean);
            const movie = mdb.find((x) => x?.name === name);
            
            if (movie?.name) {
                movie.director = director;
            }
        } else if (command.includes("onDate")) {
            const [name, date] = command.split(" onDate ").filter(Boolean);
            const movie = mdb.find((x) => x?.name === name);
            
            if (movie?.name) {
                movie.date = date;
            }
        }
    });

    mdb
    .filter((movie) => movie.name && movie.director && movie.date)
    .forEach((movie) => console.log(JSON.stringify(movie)));
}
