CREATE DATABASE  IF NOT EXISTS `beer` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `beer`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: beer
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beer`
--

DROP TABLE IF EXISTS `beer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beer` (
  `id_beer` int(11) NOT NULL AUTO_INCREMENT,
  `name_beer` varchar(20) DEFAULT NULL,
  `type_beer` varchar(20) DEFAULT NULL,
  `ai` varchar(20) DEFAULT NULL,
  `manufacturer` varchar(20) DEFAULT NULL,
  `id_ingredients` int(11) DEFAULT NULL,
  `id_chars` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_beer`),
  KEY `id_ingredients` (`id_ingredients`),
  KEY `id_chars` (`id_chars`),
  CONSTRAINT `beer_ibfk_1` FOREIGN KEY (`id_ingredients`) REFERENCES `ingredients` (`id_ingredients`),
  CONSTRAINT `beer_ibfk_2` FOREIGN KEY (`id_chars`) REFERENCES `chars` (`id_chars`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chars`
--

DROP TABLE IF EXISTS `chars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chars` (
  `id_chars` int(11) NOT NULL AUTO_INCREMENT,
  `volume_alcohol_fraction` double DEFAULT NULL,
  `clearness` varchar(30) DEFAULT NULL,
  `filtered` varchar(30) DEFAULT NULL,
  `nutritional_value` varchar(30) DEFAULT NULL,
  `id_spill_method` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_chars`),
  KEY `id_spill_method` (`id_spill_method`),
  CONSTRAINT `chars_ibfk_1` FOREIGN KEY (`id_spill_method`) REFERENCES `spill_method` (`id_spill_method`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `id_ingredients` int(11) NOT NULL AUTO_INCREMENT,
  `water` int(11) DEFAULT NULL,
  `malt` int(11) DEFAULT NULL,
  `hop` int(11) DEFAULT NULL,
  `sugar` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ingredients`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spill_method`
--

DROP TABLE IF EXISTS `spill_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spill_method` (
  `id_spill_method` int(11) NOT NULL AUTO_INCREMENT,
  `size_beer` double DEFAULT NULL,
  `matherial` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_spill_method`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'beer'
--
--
-- Dumping routines for database 'beer'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-18  0:46:10
