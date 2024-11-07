function validatePassword(password) {
    const regex1 = /^[A-Za-z0-9]*$/g;
    const regex2 = /(\D*\d){2,}/g;
    let isValid = true;
    if (password.length < 6 || password.length > 10) {
        console.log("Password must be between 6 and 10 characters");
        isValid = false;
    } 
    if (!regex1.test(password)) {
        console.log("Password must consist only of letters and digits");
        isValid = false;
    } 
    if (!regex2.test(password)) {
        console.log("Password must have at least 2 digits");
        isValid = false;
    }
    if (isValid) {
        console.log('Password is valid');
        
    }
}

validatePassword('Pass11');