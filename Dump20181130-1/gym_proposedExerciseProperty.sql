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
-- Table structure for table `proposedExerciseProperty`
--

DROP TABLE IF EXISTS `proposedExerciseProperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proposedExerciseProperty` (
  `idproposedExerciseProperty` int(11) NOT NULL AUTO_INCREMENT,
  `excerciseId` int(11) DEFAULT NULL,
  `keyName` varchar(45) DEFAULT NULL,
  `keyValue` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproposedExerciseProperty`),
  KEY `excerciseId_idx` (`excerciseId`),
  CONSTRAINT `proposedExcerciseid` FOREIGN KEY (`excerciseId`) REFERENCES `request_workout_exercies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposedExerciseProperty`
--

LOCK TABLES `proposedExerciseProperty` WRITE;
/*!40000 ALTER TABLE `proposedExerciseProperty` DISABLE KEYS */;
INSERT INTO `proposedExerciseProperty` VALUES (69,60,'Sets','2'),(70,60,'Reps','8,5'),(71,60,'Weights','9,7'),(72,61,'Sets','2'),(73,61,'Reps','8,5'),(74,61,'Weights','9,7'),(75,62,'Sets','3'),(76,62,'Reps','9,4,7'),(77,62,'Weights','10,14,3'),(78,63,'Sets','2'),(79,63,'Reps','5,5'),(80,63,'Weights',',7'),(81,67,'Sets','2'),(82,67,'Reps','8,5'),(83,67,'Weights','9,7'),(84,68,'Sets','2'),(85,68,'Reps','8,5'),(86,68,'Weights','9,7'),(87,71,'Sets','2'),(88,71,'Reps','8,5'),(89,71,'Weights','9,7'),(90,72,'Sets','2'),(91,72,'Reps','8,5'),(92,72,'Weights','9,7'),(93,73,'Sets','2'),(94,73,'Reps','8,5'),(95,73,'Weights','9,7'),(96,74,'Sets','2'),(97,74,'Reps','8,5'),(98,74,'Weights','9,7'),(99,75,'Sets','2'),(100,75,'Reps','10,20'),(101,75,'Weights','4,4'),(102,76,'Sets','3'),(103,76,'Reps','10,20,30'),(104,76,'Weights','0,5,6'),(105,77,'Sets','2'),(106,77,'Reps','10,10'),(107,77,'Weights','4,4'),(108,78,'Sets','1'),(109,78,'Reps','30'),(110,78,'Weights','2'),(111,79,'Sets','1'),(112,79,'Reps','15'),(113,79,'Weights','5'),(114,80,'Sets','2'),(115,80,'Reps','8,5'),(116,80,'Weights','9,7'),(117,81,'Sets','1'),(118,81,'Reps','10'),(119,81,'Weights','5'),(120,82,'Sets','2'),(121,82,'Reps','15,20'),(122,82,'Weights','5,8'),(123,83,'Sets','3'),(124,83,'Reps','10,10,10'),(125,83,'Weights','50,50,50'),(126,84,'Sets','3'),(127,84,'Reps','8,8,8'),(128,84,'Weights','40,40,40'),(129,85,'Sets','3'),(130,85,'Reps','5,6,7'),(131,85,'Weights','30,25,20'),(132,86,'Sets','2'),(133,86,'Reps','15,28'),(134,86,'Weights','30,58'),(135,87,'Sets','1'),(136,87,'Reps','10'),(137,87,'Weights','20'),(138,88,'Sets','4'),(139,88,'Reps','10,8,6,4'),(140,88,'Weights','25,30,35,40'),(141,89,'Sets','3'),(142,89,'Reps','10,12,14'),(143,89,'Weights','25,20,15'),(144,90,'Sets','2'),(145,90,'Reps','20,20'),(146,90,'Weights','15,15'),(147,91,'Sets','1'),(148,91,'Reps','2'),(149,91,'Weights','5'),(150,92,'Sets','2'),(151,92,'Reps','13,12'),(152,92,'Weights','20,35'),(153,93,'Sets','3'),(154,93,'Reps','10,10,8'),(155,93,'Weights','35,35,40'),(156,94,'Sets','3'),(157,94,'Reps','20,20,20'),(158,94,'Weights','20,15,10'),(159,95,'Sets','2'),(160,95,'Reps','10,5'),(161,95,'Weights','50,70');
/*!40000 ALTER TABLE `proposedExerciseProperty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:08:41
