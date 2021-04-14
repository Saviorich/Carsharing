DROP DATABASE IF EXISTS carsharing;
CREATE DATABASE IF NOT EXISTS `carsharing` DEFAULT CHARACTER SET utf8;
USE `carsharing`;

CREATE TABLE IF NOT EXISTS `role`
(
    `id`   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `role` VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `users`
(
    `id`            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `email`         VARCHAR(255) NOT NULL UNIQUE,
    `user_password` VARCHAR(255) NOT NULL,
    `role`          INT          NOT NULL,

    FOREIGN KEY (`role`) REFERENCES `role` (`id`)
);

CREATE TABLE IF NOT EXISTS `passport`
(
    `passport_number`       VARCHAR(9)   NOT NULL PRIMARY KEY,
    `identification_number` VARCHAR(14)  NOT NULL UNIQUE,
    `issue_date`            TIMESTAMP(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS `cars`
(
    `id`                INT                              NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `brand`             VARCHAR(50)                      NOT NULL,
    `model`             VARCHAR(100)                     NOT NULL,
    `color`             VARCHAR(50)                      NOT NULL,
    `mileage`           INT                              NOT NULL,
    `gearbox`           ENUM ('automatic', 'manual')     NOT NULL,
    `manufactured_year` YEAR(4)                     	 NOT NULL,
    `engine_type`       ENUM ('petrol', 'diesel', 'gas') NOT NULL,
    `price_per_day`     DECIMAL(10, 2)                   NOT NULL,
    `vin`               VARCHAR(17)                      NOT NULL UNIQUE,
    `plate`             VARCHAR(10)                      NULL,
    `class`             VARCHAR(30)                      NULL,
    `image_path`        VARCHAR(255)                     NULL
);

CREATE TABLE IF NOT EXISTS `status`
(
    `id`           INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `status_group` VARCHAR(50) NOT NULL,
    `status_name`  VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `orders`
(
    `id`                INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`           INT          NOT NULL,
    `car_id`            INT          NOT NULL,
    `status_id`         INT          NOT NULL,
    `start_date`        TIMESTAMP(6) NOT NULL,
    `end_date`          TIMESTAMP(6) NOT NULL,
    `rejection_comment` VARCHAR(500) NULL,
    `return_comment`    VARCHAR(500) NULL,

    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
    FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
);

CREATE TABLE IF NOT EXISTS `user_details`
(
    `id`              INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`         INT          NOT NULL,
    `phone_number`    VARCHAR(13)  NOT NULL,
    `first_name`      VARCHAR(100) NOT NULL,
    `second_name`     VARCHAR(100) NOT NULL,
    `middle_name`     VARCHAR(100) NULL,
    `passport_number` VARCHAR(9)   NOT NULL,

    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`passport_number`) REFERENCES `passport` (`passport_number`)
);

CREATE TABLE IF NOT EXISTS `carsharing_news`
(
    `id`               INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`          INT          NOT NULL,
    `header`           VARCHAR(100) NOT NULL,
    `content`          VARCHAR(500) NOT NULL,
    `publication_date` TIMESTAMP(6) NOT NULL,
    `image_path`       VARCHAR(255) NULL,

    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE IF NOT EXISTS `payment`
(
    `id`           INT            NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_id`     INT            NOT NULL,
    `status_id`    INT            NOT NULL,
    `total_price`  DECIMAL(10, 2) NOT NULL,
    `payment_date` TIMESTAMP(6)   NOT NULL,

    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
);

CREATE TABLE IF NOT EXISTS `car_comment`
(
    `id`       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`  INT          NOT NULL,
    `car_id`   INT          NOT NULL,
    `order_id` INT          NOT NULL,
    `content`  VARCHAR(200) NOT NULL,

    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);