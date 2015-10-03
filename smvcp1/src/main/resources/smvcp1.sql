-- MySQL dump 10.13  Distrib 5.6.23, for osx10.8 (x86_64)
--
-- Host: localhost    Database: smvcp1
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_df8c9872ad284bb8a647e2c29f0` (`user_id`),
  CONSTRAINT `FK_df8c9872ad284bb8a647e2c29f0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES 
(3,'ROLE_USER','pep@smvcp.com',3),
(4,'ROLE_ADMIN','pep@smvcp.com',3),
(5,'ROLE_USER','manel@smvcp.com',4),
(6,'ROLE_USER','vanessa@smvcp.com',5),
(8,'ROLE_USER','ninel@smvcp.com',6),
(9,'ROLE_ADMIN','ninel@smvcp.com',6),
(10,'ROLE_ROOT','ninel@smvcp.com',6);
/*(11,'ROLE_USER','chris@smvc.com',7),
(12,'ROLE_ADMIN','chris@smvc.com',7),
(13,'ROLE_USER','xavi@smvc.com',8),
(14,'ROLE_USER','anna@smvc.com',9),
(15,'ROLE_USER','amed@smvc.com',10),
(16,'ROLE_USER','abel@smvc.com',11),
(17,'ROLE_USER','abir@smvc.com',12),
(18,'ROLE_USER','maria@smvc.com',13),
(19,'ROLE_ADMIN','maria@smvc.com',13),
(20,'ROLE_USER','susana@smvc.com',14),
(21,'ROLE_USER','esther@smvc.com',15),
(22,'ROLE_USER','ruth@smvc.com',16),
(23,'ROLE_USER','andres@smvc.com',17),
(24,'ROLE_USER','juan@smvc.com',18),
(25,'ROLE_USER','isma@smvc.com',19);*/
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(64) DEFAULT NULL,
  `timeIn` datetime DEFAULT NULL,
  `timeOut` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f2f11777f7dc4aa68289477aa4c` (`user_id`),
  CONSTRAINT `FK_f2f11777f7dc4aa68289477aa4c` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection`
--

LOCK TABLES `connection` WRITE;
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(32) NOT NULL,
  `date` datetime NOT NULL,
  `ip` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `profession` varchar(32) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  -- UNIQUE KEY `UK_baf146a9c69749c985536f810ec` (`user_id`),
  KEY `FK_dfa972e3693844b3b7ae4b077fe` (`user_id`),
  CONSTRAINT `FK_dfa972e3693844b3b7ae4b077fe` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (2,'terrassa','2015-09-16 21:11:17','127.0.0.1','pep','0000000000','estudiant',3),(4,'terrassa','2015-09-15 22:08:55','127.0.0.1','josep','8888888888','estudiante',4),(5,'a','2015-09-15 22:32:49','127.0.0.1','a','0000000000','a',5);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f80ad6c47538465fbf8957f51f5` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES 
(3,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','pep@smvcp.com'),
(4,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','manel@smvcp.com'),
(5,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','vanessa@smvcp.com'),
(6,0,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','ninel@smvcp.com');
/*(7,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','chris@smvcp.com'),
(8,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','xavi@smvcp.com'),
(9,0,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','anna@smvcp.com'),
(10,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','amed@smvcp.com'),
(11,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','abel@smvcp.com'),
(12,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','abir@smvcp.com'),
(13,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','maria@smvcp.com'),
(14,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','susana@smvcp.com'),
(15,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','esther@smvcp.com'),
(16,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','ruth@smvcp.com'),
(17,0,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','andres@smvcp.com'),
(18,0,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','juan@smvcp.com'),
(19,1,'$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','isma@smvcp.com');*/
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-27 14:03:28
