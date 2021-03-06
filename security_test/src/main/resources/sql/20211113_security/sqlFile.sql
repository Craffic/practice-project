CREATE TABLE IF NOT EXISTS `USER` (
    `ID` INT (11) UNSIGNED AUTO_INCREMENT,
    `user_name` VARCHAR (32),
    `password` VARCHAR (255),
    `enabled` TINYINT (1),
    `locked` TINYINT (1),
    `create_by` VARCHAR (32),
    `create_time` DATE,
    `UPDATE_BY` VARCHAR (32),
    `UPDATE_TIME` DATE,
    PRIMARY KEY (`ID`)
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `ROLE` (
    `ID` INT (11) UNSIGNED AUTO_INCREMENT,
    `NAME` VARCHAR (32),
    `NAME_ZH` VARCHAR (32),
    PRIMARY KEY (`ID`)
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `USER_ROLE` (
    `ID` INT (11) UNSIGNED AUTO_INCREMENT,
    `UID` INT (11),
    `RID` INT (11),
    PRIMARY KEY (`ID`)
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;

--资源表
CREATE TABLE IF NOT EXISTS `MENU` (
    `ID` INT (11) UNSIGNED AUTO_INCREMENT,
    `PATTERN` VARCHAR (32),
    PRIMARY KEY (`ID`)
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `MENU_ROLE` (
    `ID` INT (11) UNSIGNED AUTO_INCREMENT,
    `MID` INT (11),
    `RID` INT (11),
    PRIMARY KEY (`ID`)
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;