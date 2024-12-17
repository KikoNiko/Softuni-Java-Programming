function solve(input) {

    const heroesCount = input.shift();
    const heroesInput = input.splice(0, heroesCount);

    const heroes = heroesInput.reduce((heroes, hero) => {
        let [ name, powers, energy ] = hero.split('-');
        powers =  powers.split(',');
    
        heroes[name] = { powers, energy: Number(energy) }
        return heroes;
    }, {});

    input.forEach(entry => {
        const line = entry.split(' * ');
        const command = line.shift();
        const heroName = line.shift();
        const currentHero = heroes[heroName];

        switch(command) {
            case 'Use Power':
                let [ power, energy ] = line;
                energy = Number(energy);
                if (currentHero.powers.includes(power) && currentHero.energy - energy > 0) {
                    currentHero.energy -= energy;
                    console.log(`${heroName} has used ${power} and now has ${currentHero.energy} energy!`);
                } else {
                    console.log(`${heroName} is unable to use ${power} or lacks energy!`);
                }
                break;
            case 'Train':
                let trainingEnergy = Number(line);

                if (currentHero.energy < 100) {
                    currentHero.energy += trainingEnergy;

                    if (currentHero.energy > 100) {
                        const remainder = currentHero.energy - 100;
                        trainingEnergy -= remainder;
                        currentHero.energy = 100;
                    }
                    console.log(`${heroName} has trained and gained ${trainingEnergy} energy!`);
                } else {
                    console.log(`${heroName} is already at full energy!`);
                }
                break;
            case 'Learn':
                let [ powerToLearn ] = line;
                if (!currentHero.powers.includes(powerToLearn)) {
                    currentHero.powers.push(powerToLearn);
                    console.log(`${heroName} has learned ${powerToLearn}!`);
                } else {
                    console.log(`${heroName} already knows ${powerToLearn}.`);
                }
                break;
        } 
    });
     
    let output = '';

    Object.keys(heroes).forEach(heroName => {
        output += `Superhero: ${heroName}
- Superpowers: ${heroes[heroName].powers.join(', ')}
- Energy: ${heroes[heroName].energy}\n`
    });
    
    console.log(output);
    
}

solve(([
    "3",
    "Iron Man-Repulsor Beams,Flight-80",
    "Thor-Lightning Strike,Hammer Throw-10",
    "Hulk-Super Strength-60",
    "Use Power * Iron Man * Flight * 30",
    "Train * Thor * 20",
    "Train * Hulk * 50",
    "Learn * Hulk * Thunderclap",
    "Use Power * Hulk * Thunderclap * 70",
    "Evil Defeated!"
]));

//Iron Man has used Flight and now has 50 energy! 
// Thor has trained and gained 20 energy! 
// Hulk has trained and gained 40 energy! 
// Hulk has learned Thunderclap! 
// Hulk has used Thunderclap and now has 30 energy! 
// Superhero: Iron Man 
// - Superpowers: Repulsor Beams, Flight 
// - Energy: 50 
// Superhero: Thor 
// - Superpowers: Lightning Strike, Hammer Throw 
// - Energy: 30 
// Superhero: Hulk 
// - Superpowers: Super Strength, Thunderclap 
// - Energy: 30