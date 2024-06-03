-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 82.208.22.205    Database: fir_pj_2024
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `uq_course_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Programski jezici','2024-05-31 09:55:48',NULL,NULL),(2,'Razvoj softvera','2024-05-31 09:55:48',NULL,NULL),(3,'Strukture podataka i algorimi','2024-06-03 09:26:07',NULL,NULL),(4,'Cloud computing','2024-06-03 09:26:07',NULL,NULL),(5,'Internet technology and Web services','2024-06-03 09:26:07',NULL,NULL),(6,'Security aspcts of software and data engeneering','2024-06-03 09:26:07',NULL,NULL),(7,'Praktikum sistemi e-posloivanja','2024-06-03 09:26:07',NULL,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `surname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `indeks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `study_programme_id` int unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `uq_student_indeks` (`indeks`),
  KEY `fk_student_study_programme_idx` (`study_programme_id`),
  CONSTRAINT `fk_student_study_programme` FOREIGN KEY (`study_programme_id`) REFERENCES `study_programme` (`study_programme_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Petar','Kresoja','2019200948',1,'2024-05-17 10:13:30',NULL,NULL),(2,'Petar','Kresoja','2023410087',12,'2024-05-17 10:16:33','2024-05-31 09:52:05',NULL),(3,'Pero P','Nikic','2024123456',1,'2024-05-29 09:27:58','2024-05-29 09:28:04',NULL),(4,'Petar','Petrovic','2013123456',9,'2024-05-31 09:51:53','2024-05-31 09:51:59',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `student_course_id` int unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int unsigned NOT NULL,
  `course_id` int unsigned NOT NULL,
  PRIMARY KEY (`student_course_id`),
  KEY `fk_student_course_student_idx` (`student_id`),
  KEY `fk_student_course_course_idx` (`course_id`),
  CONSTRAINT `fk_student_course_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_course_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (3,3,2),(9,1,1),(10,1,2),(11,1,5),(12,1,7),(22,2,3);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_programme`
--

DROP TABLE IF EXISTS `study_programme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_programme` (
  `study_programme_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`study_programme_id`),
  UNIQUE KEY `uq_sp_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_programme`
--

LOCK TABLES `study_programme` WRITE;
/*!40000 ALTER TABLE `study_programme` DISABLE KEYS */;
INSERT INTO `study_programme` VALUES (1,'Рачунарске науке','2024-05-29 09:35:11','2024-05-29 10:18:31',NULL),(2,'Информационе технологије','2024-05-29 09:35:11',NULL,NULL),(3,'Софтверско и информационо инжењерство','2024-05-29 09:35:12',NULL,NULL),(4,'Англистика','2024-05-29 09:37:04',NULL,NULL),(5,'Менаџмент у туризму и хотелијерству','2024-05-29 09:37:04',NULL,NULL),(6,'Пословна економија','2024-05-29 09:37:04',NULL,NULL),(7,'Вештачка интелигенција','2024-05-29 09:37:04',NULL,NULL),(8,'Фармација','2024-05-29 09:37:04',NULL,NULL),(9,'Менаџмент у спорту','2024-05-29 09:37:04',NULL,NULL),(10,'Физичко васпитање и спорт','2024-05-29 09:37:04',NULL,NULL),(11,'Психлогија','2024-05-29 09:37:05',NULL,'2024-05-29 10:18:37'),(12,'Савремене информационе технологије','2024-05-29 10:11:28',NULL,NULL);
/*!40000 ALTER TABLE `study_programme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03 10:37:07
