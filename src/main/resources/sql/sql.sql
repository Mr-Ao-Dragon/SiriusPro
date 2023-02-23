/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.30 : Database - siriuspro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `passwd` varchar(64) NOT NULL,
  `power` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `robot` */

DROP TABLE IF EXISTS `robot`;

CREATE TABLE `robot` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `bot_type` int NOT NULL,
                         `sand_box` int NOT NULL,
                         `bot_id` varchar(20) NOT NULL,
                         `token` varchar(64) NOT NULL,
                         `auto_login` int NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `bot_id` (`bot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `intent` */

DROP TABLE IF EXISTS `intent`;

CREATE TABLE `intent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `robot_id` varchar(20) NOT NULL,
  `intents_type` int NOT NULL,
  `intents_name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `robot_id` (`robot_id`),
  CONSTRAINT `intent_ibfk_1` FOREIGN KEY (`robot_id`) REFERENCES `robot` (`bot_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



/*Table structure for table `server_config` */

DROP TABLE IF EXISTS `server_config`;

CREATE TABLE `server_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `key` varchar(512) NOT NULL,
  `val` varchar(4096) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
