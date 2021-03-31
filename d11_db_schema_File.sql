CREATE DATABASE D11_DB;
create table D11_DB.users(
id int NOT NULL AUTO_INCREMENT primary key,
first_name varchar(50) NOT NULL,
last_name varchar(50) NOT NUll);

ALTER TABLE `d11_db`.`users` 
ADD COLUMN `username` VARCHAR(45) NULL AFTER `last_name`,
ADD COLUMN `password` VARCHAR(45) NULL AFTER `email`;


CREATE TABLE `d11_db`.`team` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
