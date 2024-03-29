CREATE DATABASE `online_store`;
USE online_store;

# 1
CREATE TABLE brands (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews (
	id INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT,
    rating DECIMAL(10, 2) NOT NULL,
    picture_url VARCHAR(80) NOT NULL,
    published_at DATETIME NOT NULL 
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    quantity_in_stock INT,
    `description` TEXT,
    brand_id INT NOT NULL,
    category_id INT NOT NULL,
    review_id INT,
    CONSTRAINT fk_products_brands
    FOREIGN KEY (brand_id) REFERENCES brands(id),
    CONSTRAINT fk_products_categories
    FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_products_reviews
    FOREIGN KEY (review_id) REFERENCES reviews(id)
);

CREATE TABLE customers (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE,
    address VARCHAR(60) NOT NULL,
    discount_card BIT(1) NOT NULL
);

CREATE TABLE orders (
	id INT PRIMARY KEY AUTO_INCREMENT,
    order_datetime DATETIME NOT NULL,
    customer_id INT NOT NULL,
    CONSTRAINT fk_orders_customers
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE orders_products (
	order_id INT,
    product_id INT,
    CONSTRAINT fk_op_orders
    FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_op_products
    FOREIGN KEY (product_id) REFERENCES products(id)
);


# 2
INSERT INTO reviews (content, picture_url, published_at, rating)
(
	SELECT SUBSTRING(description, 1, 15), REVERSE(`name`), '2010-10-10', price / 8
	FROM products WHERE id >= 5
);


# 3
UPDATE products SET quantity_in_stock = quantity_in_stock - 5
WHERE quantity_in_stock BETWEEN 60 AND 70;


# 4
DELETE c FROM customers AS c 
LEFT JOIN orders AS o ON c.id = o.customer_id
WHERE customer_id IS NULL;


# 5
SELECT * FROM categories ORDER BY name DESC;


# 6
SELECT id, brand_id, name, quantity_in_stock
FROM products
WHERE price > 1000 AND quantity_in_stock < 30
ORDER BY quantity_in_stock, id;


# 7
SELECT * FROM reviews
WHERE content LIKE 'My%'
AND length(content) > 61
ORDER BY rating DESC;


# 8
SELECT 
	CONCAT(first_name, ' ', last_name) AS 'full_name', 
	address, 
	o.order_datetime AS 'order_date'
FROM customers c 
JOIN orders o ON c.id = o.customer_id
WHERE YEAR(o.order_datetime) <= 2018
ORDER BY full_name DESC;


# 9 
SELECT COUNT(*) AS 'items_count', c.name, SUM(p.quantity_in_stock) AS 'total_quantity'
FROM products p 
JOIN categories c ON p.category_id = c.id
GROUP BY p.category_id
ORDER BY items_count DESC, total_quantity
LIMIT 5;


# 10
DELIMITER $$
CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN 
	RETURN
    (
		SELECT COUNT(op.product_id) FROM orders_products op
		JOIN orders o ON op.order_id = o.id
		JOIN customers c ON o.customer_id = c.id
		WHERE c.first_name = name
    );
END$$


# 11
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
	UPDATE products p
    JOIN categories c ON p.category_id = c.id
    JOIN reviews r ON p.review_id = r.id
    SET price = price * 0.7
    WHERE c.name = category_name AND r.rating < 4;
END $$

DELIMITER ;