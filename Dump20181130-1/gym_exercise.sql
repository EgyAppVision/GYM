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
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise` (
  `exercise_id` int(11) NOT NULL,
  `exercise_desc` varchar(2000) DEFAULT NULL,
  `exercise_video_id` int(11) DEFAULT NULL,
  `exercise_type_id` int(11) DEFAULT NULL,
  `exercise_sub_muscle` int(11) DEFAULT NULL,
  PRIMARY KEY (`exercise_id`),
  KEY `exercise_video_FK_idx` (`exercise_video_id`),
  KEY `exercise_type_id_idx` (`exercise_type_id`),
  KEY `exercise_main_muscle_idx` (`exercise_sub_muscle`),
  CONSTRAINT `exercise_main_muscle` FOREIGN KEY (`exercise_sub_muscle`) REFERENCES `muscle` (`muscle_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `exercise_type_id` FOREIGN KEY (`exercise_type_id`) REFERENCES `exercise_type` (`exercise_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `exercise_video_FK` FOREIGN KEY (`exercise_video_id`) REFERENCES `video_lookup` (`video_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'curl',1,1,1),(2,'Rope Cable Curl',1,1,1),(3,'Close Gripe EZ Bar Curl',1,1,1),(4,'Wide Grip BarBell Bench Press',1,1,3),(5,'Flat Bench Cable Flyes',1,1,3),(6,'Alternate DumbBell Bench Press',1,1,3);
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:09:48
