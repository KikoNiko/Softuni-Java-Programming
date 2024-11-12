function piccolo(input) {
    const parkingLot = input.reduce((acc, curr) => {
        const [direction, plateNumber] = curr.split(", ");
        
        switch (direction) {
            case "IN": {
                if (!acc.hasOwnProperty(plateNumber)) {
                    acc[plateNumber] = false;
                }
                acc[plateNumber] = true;
            }
            break;
            case "OUT": {
                if (acc.hasOwnProperty(plateNumber)) {
                    acc[plateNumber] = false;
                }
            }
            break;
        }

        return acc;
    }, {});

    if (Object.keys(parkingLot).filter((key) => parkingLot[key]).length === 0) {
        console.log("Parking Lot is Empty");
        return;
    }
    Object.keys(parkingLot)
    .filter((key) => parkingLot[key])
    .sort((a, b) => a.localeCompare(b))
    .forEach((plate) => console.log(plate));
}

piccolo(['IN, CA2844AA', 'IN, CA1234TA', 'OUT, CA2844AA', 'IN, CA9999TT', 'IN, CA2866HI', 'OUT, CA1234TA', 'IN, CA2844AA', 'OUT, CA2866HI', 'IN, CA9876HH', 'IN, CA2822UU']);

piccolo(['IN, CA2844AA', 'IN, CA1234TA', 'OUT, CA2844AA', 'OUT, CA1234TA']);