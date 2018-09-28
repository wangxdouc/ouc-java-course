# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: musicserver
# Generation Time: 2017-11-17 05:57:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table music
# ------------------------------------------------------------

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `md5value` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `singer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;

INSERT INTO `music` (`id`, `md5value`, `name`, `singer`)
VALUES
	(70,'cdc8b7a47b61ce5c0eef1b050f6ce41c','AMANI.mp3',NULL),
	(71,'0bcb24befd73757f6a769b43656789d6','无声的告别 - Beyond.mp3',NULL),
	(72,'9c87194b62c39b35de640764d7b53d0f','命运是你家 - Beyond.mp3',NULL),
	(73,'1e659b0eefb3e1bb796e93cfe0710a9c','长城.mp3',NULL);

/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table musicsheet
# ------------------------------------------------------------

DROP TABLE IF EXISTS `musicsheet`;

CREATE TABLE `musicsheet` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT NULL,
  `creatorId` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `dateCreated` varchar(50) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `musicsheet` WRITE;
/*!40000 ALTER TABLE `musicsheet` DISABLE KEYS */;

INSERT INTO `musicsheet` (`id`, `uuid`, `name`, `creatorId`, `creator`, `dateCreated`, `picture`)
VALUES
	(40,'f4d15f962976458d93975ab99562870e','Forever Beyond Band','2011022','Wang Xiaodong','2017-11-17 10:57:35','f4d15f962976458d93975ab99562870e.png');

/*!40000 ALTER TABLE `musicsheet` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table musicsheet_music
# ------------------------------------------------------------

DROP TABLE IF EXISTS `musicsheet_music`;

CREATE TABLE `musicsheet_music` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `musicsheetId` int(11) unsigned NOT NULL,
  `musicId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MUSICSHEET` (`musicsheetId`),
  KEY `FK_MUSIC` (`musicId`),
  CONSTRAINT `FK_MUSIC` FOREIGN KEY (`musicId`) REFERENCES `music` (`id`),
  CONSTRAINT `FK_MUSICSHEET` FOREIGN KEY (`musicsheetId`) REFERENCES `musicsheet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `musicsheet_music` WRITE;
/*!40000 ALTER TABLE `musicsheet_music` DISABLE KEYS */;

INSERT INTO `musicsheet_music` (`id`, `musicsheetId`, `musicId`)
VALUES
	(66,40,70),
	(67,40,71),
	(68,40,72),
	(69,40,73);

/*!40000 ALTER TABLE `musicsheet_music` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
