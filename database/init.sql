# PRODUCTION DB --------------------------------------------------------------------------------------------
DROP DATABASE IF EXISTS simple_api_db;
CREATE DATABASE simple_api_db;
USE simple_api_db;

CREATE TABLE cat(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
weight DOUBLE		# kg
);
ALTER TABLE cat MODIFY COLUMN id BIGINT AUTO_INCREMENT;

INSERT INTO cat (name, weight)
VALUES 
('Siamese', 4.5),
('Persian', 5),
('Maine Coon', 7),
('Bengal', 5),
('Ragdoll', 6.5),
('British Shorthair', 6),
('Sphynx', 3.5),
('Scottish Fold', 4),
('Norwegian Forest Cat', 7.5),
('Abyssinian', 3.75);

CREATE TABLE cat_price(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
cat_id BIGINT,
price DOUBLE		# kg
);
ALTER TABLE cat_price MODIFY COLUMN id BIGINT AUTO_INCREMENT;
ALTER TABLE cat_price 
	ADD CONSTRAINT fk_cat_price_cat
	FOREIGN KEY (cat_id) REFERENCES cat(id) 
	ON DELETE CASCADE;

INSERT INTO cat_price (cat_id, price)
VALUES 
(1, 12),
(2, 10),
(3, 8),
(4, 8),
(5, 14),
(6, 5),
(7, 8.5),
(8, 11),
(9, 2.5),
(10, 11);

# creating combined view
CREATE VIEW cat_with_price AS
SELECT 
    c.id AS cat_id,
    c.name AS cat_name,
    c.weight,
    cp.price
FROM cat c
INNER JOIN 
	cat_price cp 
    ON 
    c.id = cp.cat_id;
    
# SELECT * FROM cat_with_price;
# TEST DB --------------------------------------------------------------------------------------------
DROP DATABASE IF EXISTS simple_api_test_db;
CREATE DATABASE simple_api_test_db;
USE simple_api_test_db;

CREATE TABLE cat(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
weight DOUBLE								# kg
);
ALTER TABLE cat MODIFY COLUMN id BIGINT AUTO_INCREMENT;

INSERT INTO cat (name, weight)
VALUES 
('test0', 1),
('test1', 2);

CREATE TABLE cat_price(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
cat_id BIGINT,
price DOUBLE		# kg
);
ALTER TABLE cat_price MODIFY COLUMN id BIGINT AUTO_INCREMENT;
ALTER TABLE cat_price 
	ADD CONSTRAINT fk_cat_price_cat
	FOREIGN KEY (cat_id) REFERENCES cat(id) 
	ON DELETE CASCADE;
    
INSERT INTO cat_price (cat_id, price)
VALUES 
(1, 12),
(2, 10);

# creating combined view
CREATE VIEW cat_with_price AS
SELECT 
    c.id AS cat_id,
    c.name AS cat_name,
    c.weight,
    cp.price
FROM cat c
INNER JOIN 
	cat_price cp 
    ON 
    c.id = cp.cat_id;
# SELECT * FROM cat_with_price;

CREATE USER IF NOT EXISTS 'myuser'@'%' IDENTIFIED BY 'Light80s!';
GRANT ALL PRIVILEGES ON simple_api_db.* TO 'myuser'@'%';
GRANT ALL PRIVILEGES ON simple_api_test_db.* TO 'myuser'@'%';
FLUSH PRIVILEGES;
COMMIT;


