-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 66.23.230.93    Database: gym
-- ------------------------------------------------------
-- Server version	5.6.41

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `user_mobile` varchar(20) DEFAULT NULL,
  `user_email` varchar(200) NOT NULL,
  `user_first_name` varchar(50) NOT NULL,
  `user_last_name` varchar(50) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_birth_date` date NOT NULL,
  `user_gender` int(11) DEFAULT NULL,
  `user_weight` int(11) DEFAULT NULL,
  `user_tall` int(11) DEFAULT NULL,
  `user_prefered_activity` int(11) DEFAULT NULL,
  `user_prefered_place` int(11) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_prefered_place_fk_idx` (`user_prefered_place`),
  KEY `user_prefered_activity_fk_idx` (`user_prefered_activity`),
  KEY `user_gender_idx` (`user_gender`),
  KEY `user_type_fk_idx` (`user_type`),
  CONSTRAINT `user_gender_fk` FOREIGN KEY (`user_gender`) REFERENCES `user_gender` (`user_gender_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_prefered_activity_fk` FOREIGN KEY (`user_prefered_activity`) REFERENCES `prefered_activity` (`prefered_activity_id`),
  CONSTRAINT `user_prefered_place_fk` FOREIGN KEY (`user_prefered_place`) REFERENCES `prefered_place` (`idprefered_place_id`),
  CONSTRAINT `user_type_fk` FOREIGN KEY (`user_type`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (57,NULL,'01017616099','player@one.com','Radwa','Alaa','12345678','1991-05-27',2,60,165,1,6,1),(58,NULL,'01017616099','trainer@one.com','Trainer','one','12345678','1980-01-01',1,80,180,1,6,2),(59,NULL,'01273776565','player@two.com','Radwa','Player','12345678','1991-01-01',2,60,165,1,6,2),(60,NULL,'01062418849','player@three.com','radwa','player2','123456789','1990-01-01',2,66,160,1,6,1),(61,NULL,'01001967947','wagdynegm@gmail.com','Wagdy ','Negm','1234','1984-08-08',1,76,175,1,6,1),(62,NULL,'01221255933','Wagdynegm@yahoo.com','Firsttrain','Lasttrain','1234','1980-01-01',1,75,174,1,6,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:09:58
