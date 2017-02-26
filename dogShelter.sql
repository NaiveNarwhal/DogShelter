-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: dog_shelter
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `adopted_dogs`
--

DROP TABLE IF EXISTS `adopted_dogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adopted_dogs` (
  `dog_id` int(11) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `name` varchar(15) NOT NULL,
  `breed` varchar(15) NOT NULL,
  `adoption_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adopted_dogs`
--

LOCK TABLES `adopted_dogs` WRITE;
/*!40000 ALTER TABLE `adopted_dogs` DISABLE KEYS */;
INSERT INTO `adopted_dogs` VALUES (8,'male','Milo','mix','2017-01-18 20:18:59'),(9,'male','Coffee','york','2017-01-18 20:45:07'),(10,'female','MisaMInnie','mix','2017-01-18 20:55:02');
/*!40000 ALTER TABLE `adopted_dogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog_arrival`
--

DROP TABLE IF EXISTS `dog_arrival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dog_arrival` (
  `dog_id` int(11) NOT NULL,
  `date_of_arrival` date NOT NULL,
  `reason` varchar(45) NOT NULL,
  PRIMARY KEY (`dog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog_arrival`
--

LOCK TABLES `dog_arrival` WRITE;
/*!40000 ALTER TABLE `dog_arrival` DISABLE KEYS */;
INSERT INTO `dog_arrival` VALUES (1,'2016-10-15','found on the street'),(2,'2016-10-30','wandered in the wood'),(3,'2016-11-21','owners rid off'),(4,'2016-11-03','left by owners'),(5,'2016-11-02','owners death'),(6,'2016-10-25','owners private problems');
/*!40000 ALTER TABLE `dog_arrival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dogs`
--

DROP TABLE IF EXISTS `dogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dogs` (
  `dog_id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` varchar(6) NOT NULL,
  `name` varchar(15) NOT NULL,
  `date_birth` date DEFAULT NULL,
  `breed` varchar(15) NOT NULL,
  `size` varchar(8) NOT NULL,
  `medical_id` int(3) NOT NULL,
  PRIMARY KEY (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dogs`
--

LOCK TABLES `dogs` WRITE;
/*!40000 ALTER TABLE `dogs` DISABLE KEYS */;
INSERT INTO `dogs` VALUES (1,'male','Ajax','2014-05-03','german_shepherd','big',1),(2,'male','Sky','2012-04-28','border_collie','medium',2),(3,'female','Coco','2015-05-28','mix','small',3),(4,'male','Ramzes','2011-06-20','rhodesian','big',4),(5,'female','Minnie','2003-03-20','york','small',5),(6,'male','Jack','2009-08-20','mix','small',6);
/*!40000 ALTER TABLE `dogs` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger after_delete_dogs
  after DELETE on dogs
    for each row
      BEGIN
        set @dog_id = old.dog_id;
        set @name = old.name;
        set @gender = old.gender;
        set @breed = old.breed;
 
 
        INSERT INTO dog_shelter.adopted_dogs (dog_id ,name,gender,breed) VALUES (@dog_id,@name,@gender, @breed);
 
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger after_delete_dogs2
  after DELETE on dogs
    for each row
      BEGIN
    set @dog_id = old.dog_id;
 
        DELETE FROM dog_shelter.dogs_location WHERE dog_id = @dog_id;
 
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger after_delete_dogs3
  after DELETE on dogs
    for each row
      BEGIN
    set @dog_id = old.dog_id;
set @med_id= old.medical_id;
 
        DELETE FROM dog_shelter.dog_arrival WHERE dog_id = @dog_id;
DELETE FROM dog_shelter.medical_story WHERE med_id = @med_id;
 
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `dogs_location`
--

DROP TABLE IF EXISTS `dogs_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dogs_location` (
  `dog_id` int(11) NOT NULL,
  `cage_number` int(3) NOT NULL,
  PRIMARY KEY (`dog_id`,`cage_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dogs_location`
--

LOCK TABLES `dogs_location` WRITE;
/*!40000 ALTER TABLE `dogs_location` DISABLE KEYS */;
INSERT INTO `dogs_location` VALUES (1,5),(2,3),(3,4),(4,1),(5,10),(6,7);
/*!40000 ALTER TABLE `dogs_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `employee_id` int(3) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `phone_number` int(9) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `salary` int(5) NOT NULL,
  `task_id` int(3) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'John','Green',728456185,'55 Washington Street',2885,1),(2,'Greg','Willson',702184785,'45 Bakery Street',2900,2),(4,'Jennell','Warman',752485963,'23 Sunshine Street',3600,7),(5,'Verna','Botello',845741254,'12 Valley Street',2850,3),(6,'Mike','Fulp',785454128,'23 Cinner Street',2850,5),(7,'Zenia','Lavin',785412548,'21 Shrowen Street',4550,1),(8,'Nanci','Dunworthh',658125478,'89 Millenium Street',2485,6);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_story`
--

DROP TABLE IF EXISTS `medical_story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_story` (
  `diseases` varchar(45) DEFAULT NULL,
  `sterilization` tinyint(1) DEFAULT NULL,
  `veterinarian` varchar(45) DEFAULT NULL,
  `med_id` int(3) NOT NULL,
  PRIMARY KEY (`med_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_story`
--

LOCK TABLES `medical_story` WRITE;
/*!40000 ALTER TABLE `medical_story` DISABLE KEYS */;
INSERT INTO `medical_story` VALUES ('none',1,'John Greens',1),('fles, ears problems',0,'Amelia Gold',2),('hip dysplasia',1,'Amelia Gold',3),('heart diseases',0,'John Greens',4),('none',1,'John Greens',5),('atopy',1,'Amelia Gold',6);
/*!40000 ALTER TABLE `medical_story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` int(3) NOT NULL AUTO_INCREMENT,
  `type_of_task` varchar(45) NOT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'feeding'),(2,'washing dogs'),(3,'walking'),(5,'training'),(6,'cleaning cages'),(7,'combing');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('anna23','1234'),('john68','city56'),('klaudia','shelter');
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

-- Dump completed on 2017-01-18 22:25:24
