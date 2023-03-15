DROP DATABASE db;
CREATE DATABASE db;

-- DROP TABLE IF EXISTS `db`.`k_pac_set_k_pacs`;
-- DROP TABLE IF EXISTS `db`.`k_pac`;
-- DROP TABLE IF EXISTS `db`.`k_pac_set`;

CREATE TABLE `db`.`k_pac` (
`id` INT NOT NULL AUTO_INCREMENT,
`title` VARCHAR(250) NOT NULL,
`description` TEXT NULL,
`creation_date` DATE NOT NULL,
PRIMARY KEY (`id`));


CREATE TABLE `db`.`k_pac_set` (
`id` INT NOT NULL  AUTO_INCREMENT,
`title` VARCHAR(250) NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `db`.`k_pac_set_k_pacs` (
`id_k_pac_set` INT NOT NULL,
`id_k_pac` INT NOT NULL,
CONSTRAINT `k_pac_set_fk`
 FOREIGN KEY (`id_k_pac_set`)  REFERENCES `k_pac_set` (`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
CONSTRAINT `k_pac_fk`
 FOREIGN KEY (`id_k_pac`)  REFERENCES `k_pac` (`id`)
     ON DELETE RESTRICT
     ON UPDATE CASCADE,
CONSTRAINT `unq_multi_key` UNIQUE (`id_k_pac`,`id_k_pac_set`)
);
