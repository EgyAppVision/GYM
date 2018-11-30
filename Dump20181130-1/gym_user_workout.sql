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
-- Table structure for table `user_workout`
--

DROP TABLE IF EXISTS `user_workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_workout` (
  `user_workout_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_workout_desc` varchar(300) NOT NULL,
  `user_workout_trainer` int(11) DEFAULT NULL,
  `user_workout_trainee` int(11) NOT NULL,
  `user_workout_request` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_workout_id`),
  KEY `user_workout_trainer_fk_idx` (`user_workout_trainer`),
  KEY `user_workout_trainee_fk_idx` (`user_workout_trainee`),
  KEY `user_workout_request_fk_idx` (`user_workout_request`),
  CONSTRAINT `user_workout_request_fk` FOREIGN KEY (`user_workout_request`) REFERENCES `request_Trainer` (`request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_workout_trainee_fk` FOREIGN KEY (`user_workout_trainee`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_workout_trainer_fk` FOREIGN KEY (`user_workout_trainer`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_workout`
--

LOCK TABLES `user_workout` WRITE;
/*!40000 ALTER TABLE `user_workout` DISABLE KEYS */;
INSERT INTO `user_workout` VALUES (124,'from local',58,57,NULL),(125,'add from server  ',58,57,NULL),(126,'Hard Workout',58,57,NULL),(127,'Hard Workout',58,57,NULL),(128,'Hard Workout',58,57,NULL),(129,'Hard Workout',58,57,NULL),(130,'test workout',58,57,NULL),(131,'test workout',58,57,NULL),(132,'test workout',58,57,NULL),(133,'Day one',58,57,NULL),(134,'Day one FromServer',58,57,NULL),(135,'Day one FromServer',58,57,NULL),(136,'Day two FromServer',58,57,NULL),(137,'hi',NULL,57,NULL),(138,'Day 3',58,57,NULL),(139,'day one',58,60,NULL),(142,'Day four FromServer',58,57,NULL),(143,'day one',58,57,NULL),(144,'Bieceps ',62,61,NULL),(145,'Bieceps v2',62,61,NULL),(146,'test',58,57,NULL),(147,'Bieceps v3',62,61,NULL),(148,'Bieceps ',62,61,NULL),(149,'Biceps ',62,61,NULL),(150,'Chest',62,61,NULL);
/*!40000 ALTER TABLE `user_workout` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:07:25
