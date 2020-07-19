-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: cms_shopping_cart
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `slug` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(45) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (7,'Apples','apples','Nice Apples','apples.jpg',1.99,6,'2020-07-17 12:03:44','2020-07-17 12:03:44'),(8,'Bananas','bananas','Tasty Bananas','bananas.jpg',1.59,6,'2020-07-17 12:04:18','2020-07-17 12:04:18'),(9,'Black shirt','black-shirt','A black shirt','black shirt.jpg',5.99,1,'2020-07-17 12:04:47','2020-07-17 12:04:47'),(10,'Blue shirt','blue-shirt','A blue shirt','blue shirt.jpg',6.99,1,'2020-07-17 12:05:14','2020-07-17 12:05:14'),(11,'Grapefruit','grapefruit','Juicy Grapefruit','grapefruit.jpg',3.99,6,'2020-07-17 12:07:25','2020-07-17 12:07:25'),(12,'Grapes','grapes','Great grapes','grapes.jpg',2.45,6,'2020-07-17 12:07:48','2020-07-17 12:07:48'),(13,'Grey shirt','grey-shirt','A grey shirt','grey shirt.jpg',10.99,1,'2020-07-17 12:08:10','2020-07-17 12:08:10'),(14,'Kiwi','kiwi','A fresh kiwi','kiwi.jpg',1.69,6,'2020-07-17 12:08:40','2020-07-17 12:08:40'),(15,'Pink shirt','pink-shirt','A pink shirt','pink shirt.jpg',7.99,1,'2020-07-17 12:08:57','2020-07-17 12:08:57'),(16,'Watermelon','watermelon','A tasty watermelon ','watermelon.jpg',5.99,6,'2020-07-17 12:09:19','2020-07-17 12:09:19'),(17,'White shirt','white-shirt','A white shirt','white shirt.jpg',4.50,1,'2020-07-17 12:09:45','2020-07-17 12:09:45'),(18,'Yellow shirt','yellow-shirt','A yellow shirt','yellow shirt.jpg',14.50,1,'2020-07-17 12:10:06','2020-07-17 12:10:06');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-19 18:00:05
