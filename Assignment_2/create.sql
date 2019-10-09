-- Create person developer user
CREATE TABLE `cs5200_fall2019_yang`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`developer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developer_key` VARCHAR(45) NULL,
  `person` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization`
    FOREIGN KEY (`person`)
    REFERENCES `cs5200_fall2019_yang`.`person` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_agreement` TINYINT NULL,
  `person` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization`
    FOREIGN KEY (`person`)
    REFERENCES `cs5200_fall2019_yang`.`person` (`id`)
);

-- Create website page widget
CREATE TABLE `cs5200_fall2019_yang`.`website` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  -- `developer` INT NOT NULL,
  PRIMARY KEY (`id`)
  -- FOREIGN KEY (`developer`)
  --   REFERENCES `cs5200_fall2019_yang`.`developer` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `views` INT NULL,
  `website` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`website`)
    REFERENCES `cs5200_fall2019_yang`.`website` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `order` INT NULL,
  `dtype` VARCHAR(45) NULL,
  `youtube_url` VARCHAR(45) NULL,
  `youtube_shareble` TINYINT NULL,
  `youtube_expandable` TINYINT NULL,
  `image_src` VARCHAR(45) NULL,
  `heading` INT DEFAULT 2,
  `html` VARCHAR(45) NULL,
  `page` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`page`)
    REFERENCES `cs5200_fall2019_yang`.`page` (`id`)
);

-- Create phone and address
CREATE TABLE `cs5200_fall2019_yang`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `primary` TINYINT NULL,
  `person` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`person`)
    REFERENCES `cs5200_fall2019_yang`.`person` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `primary` TINYINT NULL,
  `person` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`person`)
    REFERENCES `cs5200_fall2019_yang`.`person` (`id`)
);


-- Create privilege and roles
CREATE TABLE `cs5200_fall2019_yang`.`privilege` (
  `name` INT NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `cs5200_fall2019_yang`.`role` (
  `name` INT NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `cs5200_fall2019_yang`.`website_privilege` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `privilege` VARCHAR(45) NOT NULL,
  `developer` INT NOT NULL,
  `website` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`developer`)
    REFERENCES `cs5200_fall2019_yang`.`developer` (`id`),
  FOREIGN KEY (`website`)
    REFERENCES `cs5200_fall2019_yang`.`website` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`page_privilege` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `privilege` VARCHAR(45) NOT NULL,
  `developer` INT NOT NULL,
  `page` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`developer`)
    REFERENCES `cs5200_fall2019_yang`.`developer` (`id`),
  FOREIGN KEY (`page`)
    REFERENCES `cs5200_fall2019_yang`.`page` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`page_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  `developer` INT NOT NULL,
  `page` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`developer`)
    REFERENCES `cs5200_fall2019_yang`.`developer` (`id`),
  FOREIGN KEY (`page`)
    REFERENCES `cs5200_fall2019_yang`.`page` (`id`)
);

CREATE TABLE `cs5200_fall2019_yang`.`website_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  `developer` INT NOT NULL,
  `website` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`developer`)
    REFERENCES `cs5200_fall2019_yang`.`developer` (`id`),
  FOREIGN KEY (`website`)
    REFERENCES `cs5200_fall2019_yang`.`website` (`id`)
);
