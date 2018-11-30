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
-- Table structure for table `ActualExcerciseProperty`
--

DROP TABLE IF EXISTS `ActualExcerciseProperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActualExcerciseProperty` (
  `idActualExcerciseProperty` int(11) NOT NULL,
  `excerciseId` int(11) DEFAULT NULL,
  `keyName` varchar(45) DEFAULT NULL,
  `keyValue` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`idActualExcerciseProperty`),
  KEY `excerciseId_idx` (`excerciseId`),
  CONSTRAINT `actualexcerciseId` FOREIGN KEY (`excerciseId`) REFERENCES `request_workout_exercies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActualExcerciseProperty`
--

LOCK TABLES `ActualExcerciseProperty` WRITE;
/*!40000 ALTER TABLE `ActualExcerciseProperty` DISABLE KEYS */;
INSERT INTO `ActualExcerciseProperty` VALUES (0,67,'Sets','2',NULL),(1,67,'Reps','0,0,',NULL),(2,67,'Weights','0,0,',NULL),(3,68,'Sets','2',NULL),(4,68,'Reps','0,0',NULL),(5,68,'Weights','0,0',NULL),(6,71,'Sets','2',NULL),(7,71,'Reps','0,0',NULL),(8,71,'Weights','0,0',NULL),(9,72,'Sets','2',NULL),(10,72,'Reps','0,0',NULL),(11,72,'Weights','0,0',NULL),(12,73,'Sets','2',NULL),(13,73,'Reps','9,8','2018-11-15 23:08:55'),(14,73,'Weights','9,8','2018-11-15 23:08:55'),(15,74,'Sets','2',NULL),(16,74,'Reps','8,5','2018-11-18 21:59:40'),(17,74,'Weights','9,7','2018-11-18 21:59:40'),(18,75,'Sets','2',NULL),(19,75,'Reps','9,0','2018-11-18 18:51:38'),(20,75,'Weights','4,0','2018-11-18 18:51:38'),(21,76,'Sets','3',NULL),(22,76,'Reps','10,18,25','2018-11-18 21:50:12'),(23,76,'Weights','5,5,6','2018-11-18 21:50:12'),(24,77,'Sets','2',NULL),(25,77,'Reps','0,0',NULL),(26,77,'Weights','0,0',NULL),(27,78,'Sets','1',NULL),(28,78,'Reps','0',NULL),(29,78,'Weights','0',NULL),(30,79,'Sets','1',NULL),(31,79,'Reps','0',NULL),(32,79,'Weights','0',NULL),(33,80,'Sets','2',NULL),(34,80,'Reps','0,0',NULL),(35,80,'Weights','0,0',NULL),(36,81,'Sets','1',NULL),(37,81,'Reps','0',NULL),(38,81,'Weights','0',NULL),(39,82,'Sets','2',NULL),(40,82,'Reps','10,0','2018-11-16 23:08:38'),(41,82,'Weights','5,0','2018-11-16 23:08:38'),(42,83,'Sets','3',NULL),(43,83,'Reps','5,5,0','2018-11-18 16:31:33'),(44,83,'Weights','10,10,0','2018-11-18 16:31:33'),(45,84,'Sets','3',NULL),(46,84,'Reps','6,6,6','2018-11-18 16:35:27'),(47,84,'Weights','20,20,25','2018-11-18 16:35:27'),(48,85,'Sets','3',NULL),(49,85,'Reps','0,0,0',NULL),(50,85,'Weights','0,0,0',NULL),(51,86,'Sets','2',NULL),(52,86,'Reps','0,0',NULL),(53,86,'Weights','0,0',NULL),(54,87,'Sets','1',NULL),(55,87,'Reps','0',NULL),(56,87,'Weights','0',NULL),(57,88,'Sets','4',NULL),(58,88,'Reps','0,0,0,0',NULL),(59,88,'Weights','0,0,0,0',NULL),(60,89,'Sets','3',NULL),(61,89,'Reps','0,0,0',NULL),(62,89,'Weights','0,0,0',NULL),(63,90,'Sets','2',NULL),(64,90,'Reps','0,0',NULL),(65,90,'Weights','0,0',NULL),(66,91,'Sets','1',NULL),(67,91,'Reps','0',NULL),(68,91,'Weights','0',NULL),(69,92,'Sets','2',NULL),(70,92,'Reps','0,0',NULL),(71,92,'Weights','0,0',NULL),(72,93,'Sets','3',NULL),(73,93,'Reps','0,0,0',NULL),(74,93,'Weights','0,0,0',NULL),(75,94,'Sets','3',NULL),(76,94,'Reps','0,0,0',NULL),(77,94,'Weights','0,0,0',NULL),(78,95,'Sets','2',NULL),(79,95,'Reps','0,0',NULL),(80,95,'Weights','0,0',NULL);
/*!40000 ALTER TABLE `ActualExcerciseProperty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 15:07:44
