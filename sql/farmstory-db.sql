-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SET @@session.restrict_fk_on_non_standard_key=OFF;
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Farmstory
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Farmstory
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Farmstory` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema farmstory
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema farmstory
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `farmstory` DEFAULT CHARACTER SET utf8mb3 ;
USE `Farmstory` ;

-- -----------------------------------------------------
-- Table `farmstory`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`user` (
  `id` VARCHAR(10) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `nickname` VARCHAR(8) NOT NULL,
  `point` INT NULL DEFAULT '0',
  `level` TINYINT NULL DEFAULT '0',
  `email` VARCHAR(254) NOT NULL,
  `phone_num` CHAR(13) NOT NULL,
  `zip` CHAR(5) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `address_detail` VARCHAR(50) NOT NULL,
  `register_date` DATETIME NULL DEFAULT NULL,
  `leave_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_num_UNIQUE` (`phone_num` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(10) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `author` VARCHAR(8) NOT NULL,
  `content` TEXT NOT NULL,
  `file_id` INT NULL DEFAULT NULL,
  `comment_number` INT NULL DEFAULT '0',
  `view_number` INT NULL DEFAULT '0',
  `register_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `farmstory`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`article_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`article_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_file_article1_idx` (`article_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_file_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `farmstory`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `author` VARCHAR(20) NOT NULL,
  `content` VARCHAR(300) NOT NULL,
  `register_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_article1_idx` (`article_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `farmstory`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(30) NOT NULL,
  `manager_name` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(20) NOT NULL,
  `addr` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`product_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `thumbnail_location` VARCHAR(100) NULL DEFAULT NULL,
  `info_location` VARCHAR(100) NULL DEFAULT NULL,
  `detail_location` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `category` VARCHAR(10) NOT NULL,
  `price` INT NOT NULL,
  `point` INT NULL DEFAULT NULL,
  `discount_rate` FLOAT NULL DEFAULT NULL,
  `delivery_fee` INT NOT NULL DEFAULT '0',
  `stock` INT NOT NULL DEFAULT '0',
  `image_id` INT NOT NULL,
  `register_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `company_id`),
  INDEX `fk_product_company1_idx` (`company_id` ASC) VISIBLE,
  INDEX `fk_product_product_image1_idx` (`image_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `farmstory`.`company` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_product_image1`
    FOREIGN KEY (`image_id`)
    REFERENCES `farmstory`.`product_image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(10) NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NULL DEFAULT NULL,
  `placed_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_order_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `farmstory`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `farmstory`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`point_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`point_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(10) NOT NULL,
  `amount` INT NULL DEFAULT NULL,
  `description` VARCHAR(50) NULL DEFAULT NULL,
  `earn_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_point_history_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_point_history_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `farmstory`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`term`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`term` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmstory`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmstory`.`wishlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(10) NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `fk_wishlist_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_wishlist_user1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_wishlist_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `farmstory`.`product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wishlist_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `farmstory`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
