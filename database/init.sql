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

CREATE USER IF NOT EXISTS 'myuser'@'%' IDENTIFIED BY 'Light80s!';
GRANT ALL PRIVILEGES ON simple_api_db.* TO 'myuser'@'%';
GRANT ALL PRIVILEGES ON simple_api_test_db.* TO 'myuser'@'%';
FLUSH PRIVILEGES;
COMMIT;
