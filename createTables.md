
CREATE DATABASE bank;

USE bank;

CREATE TABLE user (
  userid INT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(255) DEFAULT NULL,
  lastname VARCHAR(255) DEFAULT NULL,
  email VARCHAR(255) DEFAULT NULL UNIQUE,
  password VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8m EmpID VARCHAR(15) GENERATED ALWAYS AS (CONCAT('emp_', ID)) STORED,b4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE curr_account (
  currid int PRIMARY KEY,
  curr_account_number VARCHAR(15) GENERATED ALWAYS AS (CONCAT('C',currid)) STORED,
  balance DOUBLE DEFAULT NULL,
  userid INT DEFAULT NULL UNIQUE,
  CONSTRAINT fk_curruserid FOREIGN KEY (userid) REFERENCES user (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DELIMITER $$
CREATE TRIGGER tg_curr_account_insert
BEFORE INSERT ON curr_account
FOR EACH ROW
BEGIN
  SET NEW.curr_account_number = CONCAT('C', NEW.currid);
END$$
DELIMITER ;

CREATE TABLE sav_account (
  savid int PRIMARY KEY,
  sav_account_number VARCHAR(15) GENERATED ALWAYS AS (CONCAT('S',savid)) STORED,
  balance DOUBLE DEFAULT NULL,
  userid INT DEFAULT NULL,
  CONSTRAINT fk_savuserid FOREIGN KEY (userid) REFERENCES user (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DELIMITER $$
CREATE TRIGGER tg_sav_account_insert
BEFORE INSERT ON sav_account
FOR EACH ROW
BEGIN
  SET NEW.sav_account_number = CONCAT('S', NEW.savid);
END$$
DELIMITER ;

CREATE TABLE transaction(
transactionid int PRIMARY KEY AUTO_INCREMENT,
account_number varchar(255) NOT NULL,
transactiontype varchar(255) NOT NULL,
amount double NOT NULL,
newbalance double NOT NULL,
date_time datetime NOT NULL
);



