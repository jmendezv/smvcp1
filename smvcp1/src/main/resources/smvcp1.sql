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

-- trigger users_hist
-- procedure user_auth
-- procedure count_users

--  show triggers;
-- show procedure status;
-- show create procedure user_auth;

-- drop procedure if exists user_auth;
-- delimiter // 
-- create procedure user_auth(in id long) begin select u.id, u.username, u.password, a.authority from users u inner join authorities a on u.id = a.user_id and u.id = id; 
-- end //  
-- delimiter ;

 -- delimiter //
 -- mysql> create trigger users_hist before delete on users for each row begin insert into users_old values (null, old.username); end;//
 -- mysql> delimiter ;
 
-- delimiter //
-- create procedure user_auth(in id long) begin select u.id, u.username, u.password, a.authority from users u inner join authorities a on u.id = a.user_id and u.id = id; end //
  -- select u.id, u.username, u.password , a.authority from users u, authorities a where u.id = id and u.id = a.user_id;
  -- end //
  -- delimiter ;

 -- select u.id, u.username, a.authority from users u inner join authorities a on u.id = a.user_id and u.id = 1;
 
--  delimiter //
-- create procedure count_users (out n long) begin select count(*) into n from users; end //
-- delimiter ;

-- call count_users(@t);

-- select @t;
--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(64) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s21btj9mlob1djhm3amivbe5e` (`user_id`),
  CONSTRAINT `FK_s21btj9mlob1djhm3amivbe5e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_USER','pep@smartcoding.es',1),(2,'ROLE_ADMIN','pep@smartcoding.es',1),(3,'ROLE_USER','ana@smartcoding.es',3);
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connections`
--

DROP TABLE IF EXISTS `connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(64) DEFAULT NULL,
  `timeIn` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bifjq7gtcpt2q922i3wvgbja9` (`user_id`),
  CONSTRAINT `FK_bifjq7gtcpt2q922i3wvgbja9` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connections`
--

LOCK TABLES `connections` WRITE;
/*!40000 ALTER TABLE `connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `datecontact` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `username` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iy89i58xo7oepohxdhtenhgel` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(32) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `profession` varchar(32) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4ixsj6aqve5pxrbw2u0oyk8bb` (`user_id`),
  CONSTRAINT `FK_4ixsj6aqve5pxrbw2u0oyk8bb` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resets`
--

DROP TABLE IF EXISTS `resets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateExpiry` datetime DEFAULT NULL,
  `resetCode` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pabh43dxq8nxlmyy6jisxg72d` (`user_id`),
  CONSTRAINT `FK_pabh43dxq8nxlmyy6jisxg72d` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resets`
--

LOCK TABLES `resets` WRITE;
/*!40000 ALTER TABLE `resets` DISABLE KEYS */;
/*!40000 ALTER TABLE `resets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','pep@smartcoding.es'),(3,'','$2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje','ana@smartcoding.es');
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

-- Dump completed on 2015-11-10 15:16:36
