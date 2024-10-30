function solve(numOfPeople, type, day) {
    let basePrice = 0;
    let totalPrice = 0;
    let discountCoef = 1;

    switch (type) {
        case 'Students' :
            switch (day) {
                case 'Friday':
                    basePrice = 8.45;
                    break;
                case 'Saturday':
                    basePrice = 9.80;
                    break;
                case 'Sunday':
                    basePrice = 10.46;
                    break;
            }
            if (numOfPeople >= 30) discountCoef = 0.85;
            break;
        case 'Business':
            switch (day) {
                case 'Friday':
                    basePrice = 10.90;
                    break;
                case 'Saturday':
                    basePrice = 15.60;
                    break;
                case 'Sunday':
                    basePrice = 16;
                    break;
            }
            if (numOfPeople >= 100) numOfPeople -= 10;
            break;
        case 'Regular':
            switch (day) {
                case 'Friday':
                    basePrice = 15;
                    break;
                case 'Saturday':
                    basePrice = 20;
                    break;
                case 'Sunday':
                    basePrice = 22.50;
                    break;
            }
            if (numOfPeople >= 10 && numOfPeople <= 20) discountCoef = 0.95;
            break;
    }

    totalPrice = numOfPeople * basePrice * discountCoef;

    console.log(`Total price: ${totalPrice.toFixed(2)}`);
}
solve(30, "Students", "Sunday");
solve(40, "Regular", "Saturday");