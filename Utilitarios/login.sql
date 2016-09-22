-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: login
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

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
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `moduloA` tinyint(1) NOT NULL DEFAULT '0',
  `moduloB` tinyint(1) NOT NULL DEFAULT '0',
  `moduloC` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'adm','9a787d4d6ab18460da19d0df1aeabeec',1,0,0),(2,'ana','8c984ec2ea45f25d859fa90e18036553',0,0,1),(3,'marcos','dab4f16a0433e978c6392d4c5567ea77',0,1,0),(4,'lucas','d8578edf8458ce06fbc5bb76a58c5ca4',0,1,0),(5,'bruna','027b0723fd36d6b51cac67c689a105a3',0,1,1),(6,'joão','8c32e5048bc4fbfc5dc53c89a36c0812',0,1,1),(7,'amanda','7095efff1b96db48182229149a499510',0,0,1),(8,'cristiane','51237933d5d448b50b64f690d91e3706',0,1,0),(9,'pedro','74edec67c202e89f29114bdca031fb87',0,1,0),(10,'maria','a89d9e36227e953d4fbc3acc9e35d843',0,0,1),(11,'joana','388eea813f0a01a6ff2c6b5f1b609603',0,0,1),(12,'carlos','ce8fa0b74f3d6a8a9225624f2efce638',0,1,0),(13,'bruno','d531ef516ffd5b1e22ed5ccd9a020487',0,0,1),(14,'josé','6701065aff0d767920f79beed96bc79a',0,1,0);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-22  1:31:11
