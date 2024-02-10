# 1
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT first_name, last_name FROM employees
    WHERE salary > 35000
    ORDER BY first_name, last_name, employee_id;
END$$

# 2
CREATE PROCEDURE usp_get_employees_salary_above(target_salary DECIMAL(19, 4))
BEGIN
	SELECT first_name, last_name FROM employees
    WHERE salary >= target_salary
    ORDER BY first_name, last_name, employee_id;
END$$

# 3
CREATE PROCEDURE usp_get_towns_starting_with(prefix VARCHAR(50))
BEGIN
	SELECT `name` AS 'town_name' FROM towns
    WHERE `name` LIKE CONCAT(prefix, '%')
    ORDER BY `town_name`;
END$$

# 4
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT first_name, last_name FROM employees AS e
    JOIN addresses AS a ON e.address_id = a.address_id
    JOIN towns AS t ON a.town_id = t.town_id
    WHERE t.`name` = town_name
    ORDER BY first_name, last_name, employee_id;
END$$

# 5
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19, 4))
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(10);
    IF salary < 30000 THEN SET salary_level := 'Low';
    ELSEIF salary BETWEEN 30000 AND 50000 THEN SET salary_level := 'Average';
    ELSE SET salary_level := 'High';
    END IF;
    RETURN salary_level;
END$$

# 6
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(10))
BEGIN
	SELECT first_name, last_name FROM employees AS e
    WHERE (SELECT ufn_get_salary_level(e.salary)) = salary_level
    ORDER BY first_name DESC, last_name DESC;
END$$

# 7
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN word REGEXP (CONCAT('^[', set_of_letters, ']+$'));
END$$

# 8
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT CONCAT(first_name, ' ', last_name) AS 'full_name' FROM account_holders
    ORDER BY full_name, id;
END$$

# 9
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(amount DECIMAL(19, 4))
BEGIN
	SELECT ah.first_name, ah.last_name FROM account_holders AS ah
    LEFT JOIN accounts AS a ON ah.id = a.account_holder_id
    GROUP BY ah.first_name, ah.last_name
    HAVING SUM(a.balance) > amount;
END$$

# 10
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19, 4), yearly_interest_rate DOUBLE , years INT)
RETURNS DECIMAL(19, 4)
DETERMINISTIC
BEGIN
	DECLARE future_value DECIMAL(19, 4);
    SET future_value := initial_sum * POW(1 + yearly_interest_rate, years);
    RETURN future_value;
END$$

# 11
CREATE PROCEDURE usp_calculate_future_value_for_account(id INT, interest DECIMAL(19, 4))
BEGIN
	SELECT 
		a.id AS 'account_id', 
		ah.first_name, 
		ah.last_name, 
		a.balance AS 'current_balance', 
		ufn_calculate_future_value(a.balance, interest, 5) AS 'balance_in_5_years'
    FROM account_holders AS ah
    JOIN accounts AS a ON ah.id = a.account_holder_id
    WHERE a.id = id;
END$$

# 12
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    IF (money_amount <= 0) THEN ROLLBACK;
    ELSE # може и без else
		UPDATE accounts SET balance = balance + money_amount
        WHERE id = account_id;
        # тук може да има commit, но не е нужно
	END IF;
END$$

# 13
CREATE PROCEDURE usp_withdraw_money(id int, money_amount decimal(19,4))
BEGIN
    START TRANSACTION;
    IF (money_amount <= 0 OR (SELECT balance FROM accounts AS a WHERE a.id = id) < money_amount) THEN
    ROLLBACK;
    ELSE
        UPDATE accounts as ac SET ac.balance = ac.balance - money_amount
        WHERE ac.id = id;
        COMMIT;
    END IF; 
END$$

# 14
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	IF 
		amount <= 0 
        OR (SELECT balance FROM accounts WHERE id = from_account_id) < amount
		OR from_account_id = to_account_id
		OR (SELECT COUNT(id) FROM accounts WHERE id = from_account_id) <> 1
		OR (SELECT COUNT(id) FROM accounts WHERE id = to_account_id) <> 1
    THEN ROLLBACK;
    ELSE
    UPDATE accounts SET balance = balance - amount
        WHERE id = from_account_id;
        UPDATE accounts SET balance = balance + amount
        WHERE id = to_account_id;
        COMMIT;
    END IF; 
END$$

# 15
CREATE TABLE `logs` (
	log_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    old_sum DECIMAL(19, 4) NOT NULL,
    new_sum DECIMAL(19, 4) NOT NULL
);

CREATE TRIGGER tr_change_balance
AFTER UPDATE ON accounts
FOR EACH ROW
BEGIN
    INSERT INTO logs(account_id, old_sum, new_sum) 
    VALUES (OLD.id, OLD.balance, NEW.balance);
END$$