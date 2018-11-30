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
-- Table structure for table `user_workout_exercies`
--

DROP TABLE IF EXISTS `user_workout_exercies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_workout_exercies` (
  `user_workout_exercies_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_workout_exercies_workout` int(11) NOT NULL,
  `user_workout_exercies_exercies` int(11) NOT NULL,
  `user_workout_exercies_trainer_comment` varchar(500) DEFAULT NULL,
  `user_workout_exercies_trainee_comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_workout_exercies_id`),
  KEY `user_workout_exercies_workout_fk_idx` (`user_workout_exercies_workout`),
  KEY `user_workout_exercies_exercies_fk_idx` (`user_workout_exercies_exercies`),
  CONSTRAINT `user_workout_exercies_exercies_fk` FOREIGN KEY (`user_workout_exercies_exercies`) REFERENCES `exercise` (`exercise_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_workout_exercies_workout_fk` FOREIGN KEY (`user_workout_exercies_workout`) REFERENCES `user_workout` (`user_workout_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_workout_exercies`
--

LOCK TABLES `user_workout_exercies` WRITE;
/*!40000 ALTER TABLE `user_workout_exercies` DISABLE KEYS */;
INSERT INTO `user_workout_exercies` VALUES (96,124,2,'good luck',NULL),(97,125,3,'hoppa',NULL),(98,125,1,'hoppa',NULL),(99,125,2,'hoppa',NULL),(100,126,2,'do them all',NULL),(101,127,2,'do them all',NULL),(102,128,2,'do them all',NULL),(103,129,2,'do them all',NULL),(104,130,2,'do as much as you can',NULL),(105,131,2,'do as much as you can',NULL),(106,132,2,'do as much as you can',NULL),(107,133,2,'do as much as you can',NULL),(108,134,2,'do as much as you can',NULL),(109,135,2,'do as much as you can',NULL),(110,136,2,'good luck',NULL),(111,137,1,NULL,'exercise 1 is 5 Reps with 10kg for '),(112,137,2,NULL,'exercise 2 is 10 Reps with 6kg for '),(113,138,2,'higher level',NULL),(114,138,3,'higher level',NULL),(115,139,1,'drink water',NULL),(116,139,3,'drink water',NULL),(117,139,2,'drink water',NULL),(118,142,2,'do as much as you can',NULL),(119,143,2,'don\'t take breaks',NULL),(120,143,3,'don\'t take breaks',NULL),(121,144,1,'Dhshjs',NULL),(122,144,2,'Dhshjs',NULL),(123,145,3,'This is for the 2nd round ',NULL),(124,145,2,'This is for the 2nd round ',NULL),(125,146,2,'gl',NULL),(126,147,3,'The 3rd round',NULL),(127,147,1,'The 3rd round',NULL),(128,147,2,'The 3rd round',NULL),(129,148,2,'Play hard',NULL),(130,148,1,'Play hard',NULL),(131,149,1,'Try this ',NULL),(132,149,2,'Try this ',NULL),(133,150,6,'Just test',NULL);
/*!40000 ALTER TABLE `user_workout_exercies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:06:53
