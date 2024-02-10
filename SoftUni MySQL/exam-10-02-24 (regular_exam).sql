CREATE DATABASE preserves_db;
USE preserves_db;

# 1
CREATE TABLE continents (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE countries (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE,
    country_code VARCHAR(10) NOT NULL UNIQUE,
    continent_id INT NOT NULL,
    CONSTRAINT fk_countries_continents
    FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE preserves (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    latitude DECIMAL(9, 6),
    longitude DECIMAL(9, 6),
    `area` INT,
    `type` VARCHAR(20),
    established_on DATE
);

CREATE TABLE positions (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE,
    `description` TEXT,
    is_dangerous TINYINT(1) NOT NULL
);

CREATE TABLE workers (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    age INT,
    personal_number VARCHAR(20) NOT NULL UNIQUE,
    salary DECIMAL(19, 2),
    is_armed TINYINT(1) NOT NULL,
    start_date DATE,
    preserve_id INT,
    position_id INT,
    CONSTRAINT fk_workers_preserves
    FOREIGN KEY (preserve_id) REFERENCES preserves(id),
    CONSTRAINT fk_workers_position
    FOREIGN KEY (position_id) REFERENCES positions(id)
);

CREATE TABLE countries_preserves (
    country_id INT,
    preserve_id INT,
    CONSTRAINT fk_cp_countries
    FOREIGN KEY (country_id) REFERENCES countries(id),
    CONSTRAINT fk_cp_preserves
    FOREIGN KEY (preserve_id) REFERENCES preserves(id)
);


# 2
INSERT INTO preserves (`name`, `latitude`, `longitude`, `area`, `type`, `established_on`)
(
	SELECT  CONCAT(`name`, ' is in South Hemisphere'),
			`latitude`,
            `longitude`,
			`area` * id,
			LOWER(`type`),
            `established_on`
	FROM preserves
	WHERE latitude < 0
);


# 3
UPDATE workers
SET salary = salary + 500
WHERE position_id IN (5, 8, 11, 13);


# 4
DELETE FROM `preserves`
WHERE `established_on` IS NULL;


# 5
SELECT  CONCAT(first_name, ' ', last_name) AS 'full_name', 
		DATEDIFF("2024-01-01", start_date) AS 'days_of_experience'
FROM workers
HAVING days_of_experience > 1826
ORDER BY days_of_experience DESC
LIMIT 10;


# 6
SELECT w.id, w.first_name, w.last_name , p.name AS 'preserve_name', c.country_code 
FROM workers w
	JOIN preserves p ON w.preserve_id = p.id
	JOIN countries_preserves cp ON p.id = cp.preserve_id
	JOIN countries c ON cp.country_id = c.id
WHERE w.salary > 5000 AND w.age < 50
ORDER BY c.country_code;


# 7
SELECT  DISTINCT(p.name) AS 'name',  
		COUNT(w.id) AS 'armed_workers'
FROM workers AS w
JOIN preserves p ON w.preserve_id = p.id
WHERE w.preserve_id IS NOT NULL AND w.is_armed = 1
GROUP BY p.id
ORDER BY armed_workers DESC, p.name;


# 8
SELECT 	p.name AS 'name', 
		c.country_code AS 'country_code', 	
        YEAR(p.established_on) AS 'founded_in'
FROM preserves p
JOIN countries_preserves cp ON p.id = cp.preserve_id
JOIN countries c ON cp.country_id = c.id
WHERE MONTH(p.established_on) = '05'
ORDER BY p.established_on
LIMIT 5;


# 9
SELECT  id,
		name,
CASE 	WHEN area <= 100 THEN 'very small'
		WHEN area <= 1000 THEN 'small'
		WHEN area <= 10000 THEN 'medium'
        WHEN area <= 50000 THEN 'large'
        ELSE 'very large'
END AS 'category'
FROM preserves
ORDER BY area DESC;


# 10
DELIMITER $$
CREATE FUNCTION udf_average_salary_by_position_name (name VARCHAR(40)) 
RETURNS DECIMAL(19, 2)
DETERMINISTIC
BEGIN
	RETURN (SELECT AVG(w.salary) 
			FROM workers w
			JOIN positions p ON w.position_id = p.id
			WHERE p.name = name);
END $$


# 11
CREATE PROCEDURE udp_increase_salaries_by_country(country_name VARCHAR(40))
BEGIN
	UPDATE workers w 
		JOIN preserves p ON w.preserve_id = p.id
		JOIN countries_preserves cp ON p.id = cp.preserve_id
		JOIN countries c ON cp.country_id = c.id
    SET w.salary = w.salary * 1.05
	WHERE c.name = country_name;
END $$