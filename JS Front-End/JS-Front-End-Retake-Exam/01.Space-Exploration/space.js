function solve(input) {

    const astronautCount = input.shift();
    const astronatInput = input.splice(0, astronautCount);

    const astronauts = astronatInput.reduce((astronauts, entry) => {
        let [ name, section, skills ] = entry.split(' ');
        skills = skills.split(',');

        astronauts[name] = { section, skills }
        return astronauts;
    }, {});

    input.forEach(line => {
        const parts = line.split(' / ');
        const command = parts.shift();
        const astronautName = parts.shift();
        const currentAstronaut = astronauts[astronautName];

        switch (command) {
            case 'Perform':
                const spacecraftSection = parts.shift();
                const skill = parts.shift();
                if (currentAstronaut.section === spacecraftSection && currentAstronaut.skills.includes(skill)) {
                    console.log(`${astronautName} has successfully performed the skill: ${skill}!`);
                } else {
                    console.log(`${astronautName} cannot perform the skill: ${skill}.`);
                }
                break;
            case 'Learn Skill':
                const newSkill = parts.shift();
                if (!currentAstronaut.skills.includes(newSkill)) {
                    currentAstronaut.skills.push(newSkill);
                    console.log(`${astronautName} has learned a new skill: ${newSkill}.`);
                } else {
                    console.log(`${astronautName} already knows the skill: ${newSkill}.`);     
                }
                break;
            case 'Transfer':
                const newSection = parts.shift();
                currentAstronaut.section = newSection;
                console.log(`${astronautName} has been transferred to: ${newSection}`);
                break;
        }
    });

    let output = '';

    Object.keys(astronauts).forEach(astroName => {
        const skills = astronauts[astroName].skills.sort();
        output += `Astronaut: ${astroName}, Section: ${astronauts[astroName].section}, Skills: ${skills.join(', ')}\n`
    });
    
    console.log(output);
}

solve([ 
   "2", 
   "Alice command_module piloting,communications",
   "Bob engineering_bay repair,maintenance",
   "Perform / Alice / command_module / piloting",
   "Perform / Bob / command_module / repair",
   "Learn Skill / Alice / navigation",
   "Perform / Alice / command_module / navigation",
   "Transfer / Bob / command_module", 
   "Perform / Bob / command_module / maintenance",
   "End"
])

/**Alice has successfully performed the skill: piloting! 
Bob cannot perform the skill: repair. 
Alice has learned a new skill: navigation. 
Alice has successfully performed the skill: navigation! 
Bob has been transferred to: command_module 
Bob has successfully performed the skill: maintenance! 
Astronaut: Alice, Section: command_module, Skills: communications, navigation, piloting 
Astronaut: Bob, Section: command_module, Skills: maintenance, repair  */