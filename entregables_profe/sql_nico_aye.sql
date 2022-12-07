-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: iw3Final
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.20.04.1

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
-- Table structure for table `alerta`
--

DROP TABLE IF EXISTS `alerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` int DEFAULT NULL,
  `fecha_de_aceptacion` datetime(6) DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `usr_aceptador` varchar(100) DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd5tm777gyb3op5clv12rkqf4v` (`orden_id`),
  CONSTRAINT `FKd5tm777gyb3op5clv12rkqf4v` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta`
--

LOCK TABLES `alerta` WRITE;
/*!40000 ALTER TABLE `alerta` DISABLE KEYS */;
INSERT INTO `alerta` VALUES (1,2,'2022-12-07 18:18:09.325000','back-end','nico',1),(2,2,'2022-12-07 18:32:57.543000','Alarma con producto A','nico',7);
/*!40000 ALTER TABLE `alerta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alerta_datos`
--

DROP TABLE IF EXISTS `alerta_datos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerta_datos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail` varchar(100) DEFAULT NULL,
  `temperatura_max` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta_datos`
--

LOCK TABLES `alerta_datos` WRITE;
/*!40000 ALTER TABLE `alerta_datos` DISABLE KEYS */;
INSERT INTO `alerta_datos` VALUES (1,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(2,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(3,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(4,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(5,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(6,'[ayecano98@gmail.com,nbrambilla024@alumnos.iua.edu.ar]',43),(7,'[nicolas.brambilla8@gmail.com]',54);
/*!40000 ALTER TABLE `alerta_datos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_token`
--

DROP TABLE IF EXISTS `auth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_token` (
  `series` varchar(255) NOT NULL,
  `desde` datetime DEFAULT NULL,
  `last_used` datetime DEFAULT NULL,
  `request_count` int NOT NULL,
  `request_limit` int NOT NULL,
  `hasta` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `validity_seconds` int NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_token`
--

LOCK TABLES `auth_token` WRITE;
/*!40000 ALTER TABLE `auth_token` DISABLE KEYS */;
INSERT INTO `auth_token` VALUES ('5xOIvPDGpfJm1on2rSJtqw==',NULL,'2022-12-07 18:18:15',18,0,NULL,'4JkkqOJ15Fc7wYyR5xatow==','DEFAULT','nico',1240000),('EPU+z8HbWV3gxjUx3T83hA==',NULL,'2022-12-07 18:34:21',25,0,NULL,'711gXWokZXRqYebgiT5fCA==','DEFAULT','nico',1240000);
/*!40000 ALTER TABLE `auth_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camiones`
--

DROP TABLE IF EXISTS `camiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camiones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cisternado` tinyblob NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `patente` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camiones`
--

LOCK TABLES `camiones` WRITE;
/*!40000 ALTER TABLE `camiones` DISABLE KEYS */;
INSERT INTO `camiones` VALUES (1,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0\0\0\0(\0\0\0\"',NULL,'343-CSS-372'),(2,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\02\0\0\0P\0\0\06',NULL,'574-USB-684'),(3,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0\0\0\0\0\0\08',NULL,'22-THX-32'),(4,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0\0\0\0J\0\0\0#',NULL,'643-SMS-302'),(5,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0\\\0\0\0#\0\0\0&',NULL,'877-RSS-402'),(6,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0Y\0\0\02\0\0\0',NULL,'730-RAM-859'),(7,_binary '¬\í\0ur\0[IMº`&vê²¥\0\0xp\0\0\0\0\0\0\0\0\0 \0\0\0+','DescrCamion','54h3h4');
/*!40000 ALTER TABLE `camiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choferes`
--

DROP TABLE IF EXISTS `choferes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choferes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(100) NOT NULL,
  `documento` double NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choferes`
--

LOCK TABLES `choferes` WRITE;
/*!40000 ALTER TABLE `choferes` DISABLE KEYS */;
INSERT INTO `choferes` VALUES (1,'Williamson',65487353,'Ryann'),(2,'Thompson',11108474,'Joe'),(3,'Bayer',17423030,'Madyson'),(4,'Hills',32591669,'Norberto'),(5,'Block',35416590,'Shanon'),(6,'Kerluke',48970428,'Vinnie'),(7,'Brambilla',36605823,'Nicola');
/*!40000 ALTER TABLE `choferes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contacto` varchar(100) NOT NULL,
  `razon_social` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'83','Cheese'),(2,'255','Shoes'),(3,'729','Pizza'),(4,'794','Bike'),(5,'774','Ball'),(6,'619','Tuna'),(7,'35496523','RazonTest');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dato_carga`
--

DROP TABLE IF EXISTS `dato_carga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dato_carga` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `caudal` double DEFAULT NULL,
  `densidad_producto` double DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `masa_acumulada` double DEFAULT NULL,
  `temperatura_producto` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dato_carga`
--

LOCK TABLES `dato_carga` WRITE;
/*!40000 ALTER TABLE `dato_carga` DISABLE KEYS */;
INSERT INTO `dato_carga` VALUES (1,14,0.5,'2022-12-07 18:14:38.658000',100,70),(2,14,0.5,'2022-12-07 18:16:17.256000',100,70),(3,14,0.4,'2022-12-07 18:30:05.611000',100,60),(4,14,0.4,'2022-12-07 18:33:30.336000',100,60);
/*!40000 ALTER TABLE `dato_carga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_detalle`
--

DROP TABLE IF EXISTS `orden_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_detalle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `caudal` double DEFAULT NULL,
  `densidad_producto` double DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `masa_acumulada` double DEFAULT NULL,
  `temperatura_producto` double DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodj1h9fho4h5yjdaswuufgb2` (`orden_id`),
  CONSTRAINT `FKodj1h9fho4h5yjdaswuufgb2` FOREIGN KEY (`orden_id`) REFERENCES `ordenes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_detalle`
--

LOCK TABLES `orden_detalle` WRITE;
/*!40000 ALTER TABLE `orden_detalle` DISABLE KEYS */;
INSERT INTO `orden_detalle` VALUES (1,14,0.5,'2022-12-07 18:14:38.658000',100,70,1),(2,14,0.4,'2022-12-07 18:30:05.611000',100,60,7);
/*!40000 ALTER TABLE `orden_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes`
--

DROP TABLE IF EXISTS `ordenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_externo` varchar(50) DEFAULT NULL,
  `estado` int DEFAULT NULL,
  `fecha_fin_carga` datetime(6) DEFAULT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `fecha_recepcion_pesaje_final` datetime(6) DEFAULT NULL,
  `fecha_recepcion_pesaje_inicial` datetime(6) DEFAULT NULL,
  `fecuencia` bigint DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `peso_final` double DEFAULT NULL,
  `peso_inicial` double DEFAULT NULL,
  `preset` int DEFAULT NULL,
  `alerta_datos_id` bigint DEFAULT NULL,
  `camion_id` bigint DEFAULT NULL,
  `chofer_id` bigint DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `producto_id` bigint DEFAULT NULL,
  `promedio_datos_carga_id` bigint DEFAULT NULL,
  `ultimos_datos_carga_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iqe4ufscndb7pe1q1isyb8bpo` (`codigo_externo`),
  KEY `FK3wbtsu00ob5o1j68m20n2cmju` (`alerta_datos_id`),
  KEY `FKoor180pstu8sjohc6b2ke83p3` (`camion_id`),
  KEY `FK2f78gmjo0j9fuc1pg501qps0h` (`chofer_id`),
  KEY `FKmftalwehlpu8ntrikv005falt` (`cliente_id`),
  KEY `FKghtsna0co218vud8ic1b6scix` (`producto_id`),
  KEY `FKoqn6iofxbtuny65lx1pn6sy28` (`promedio_datos_carga_id`),
  KEY `FK8uv7rhi5p9lu45bogwr9cl64d` (`ultimos_datos_carga_id`),
  CONSTRAINT `FK2f78gmjo0j9fuc1pg501qps0h` FOREIGN KEY (`chofer_id`) REFERENCES `choferes` (`id`),
  CONSTRAINT `FK3wbtsu00ob5o1j68m20n2cmju` FOREIGN KEY (`alerta_datos_id`) REFERENCES `alerta_datos` (`id`),
  CONSTRAINT `FK8uv7rhi5p9lu45bogwr9cl64d` FOREIGN KEY (`ultimos_datos_carga_id`) REFERENCES `ultimos_datos_carga` (`id`),
  CONSTRAINT `FKghtsna0co218vud8ic1b6scix` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKmftalwehlpu8ntrikv005falt` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKoor180pstu8sjohc6b2ke83p3` FOREIGN KEY (`camion_id`) REFERENCES `camiones` (`id`),
  CONSTRAINT `FKoqn6iofxbtuny65lx1pn6sy28` FOREIGN KEY (`promedio_datos_carga_id`) REFERENCES `promedio_datos_carga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes`
--

LOCK TABLES `ordenes` WRITE;
/*!40000 ALTER TABLE `ordenes` DISABLE KEYS */;
INSERT INTO `ordenes` VALUES (1,'284',4,'2022-12-07 18:16:17.253000','2022-11-18 00:00:00.000000','2022-12-07 18:16:58.394000','2022-12-07 18:09:09.495000',10,'96268',3345,1002,104,1,1,1,1,1,2,1),(2,'901',1,NULL,'2022-11-18 00:00:00.000000',NULL,NULL,10,NULL,NULL,NULL,184,2,2,2,2,2,NULL,NULL),(3,'763',1,NULL,'2022-11-18 00:00:00.000000',NULL,NULL,10,NULL,NULL,NULL,85,3,3,3,3,3,NULL,NULL),(4,'793',1,NULL,'2022-11-18 00:00:00.000000',NULL,NULL,10,NULL,NULL,NULL,115,4,4,4,4,4,NULL,NULL),(5,'497',1,NULL,'2022-11-18 00:00:00.000000',NULL,NULL,10,NULL,NULL,NULL,165,5,5,5,5,5,NULL,NULL),(6,'700',1,NULL,'2022-11-18 00:00:00.000000',NULL,NULL,10,NULL,NULL,NULL,160,6,6,6,6,6,NULL,NULL),(7,'120',4,'2022-12-07 18:33:30.334000','2022-12-07 00:00:00.000000','2022-12-07 18:34:02.019000','2022-12-07 18:28:31.102000',10,'95694',777,569,98,7,7,7,7,7,4,3);
/*!40000 ALTER TABLE `ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,NULL,'Incredible Metal Shirt'),(2,NULL,'Licensed Frozen Chair'),(3,NULL,'Generic Cotton Computer'),(4,NULL,'Tasty Soft Soap'),(5,NULL,'Incredible Rubber Mouse'),(6,NULL,'Intelligent Plastic Towels'),(7,'DescrTest','ProdTest');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promedio_datos_carga`
--

DROP TABLE IF EXISTS `promedio_datos_carga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promedio_datos_carga` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKp38cmm9d1rj44460fbh2u67iw` FOREIGN KEY (`id`) REFERENCES `dato_carga` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promedio_datos_carga`
--

LOCK TABLES `promedio_datos_carga` WRITE;
/*!40000 ALTER TABLE `promedio_datos_carga` DISABLE KEYS */;
INSERT INTO `promedio_datos_carga` VALUES (2),(4);
/*!40000 ALTER TABLE `promedio_datos_carga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) DEFAULT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g00thobnv3twutok8x6furkx1` (`rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN','ADMIN'),(2,'USER','USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ultimos_datos_carga`
--

DROP TABLE IF EXISTS `ultimos_datos_carga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ultimos_datos_carga` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK6h0apbqus7ynov14hif9w5buw` FOREIGN KEY (`id`) REFERENCES `dato_carga` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ultimos_datos_carga`
--

LOCK TABLES `ultimos_datos_carga` WRITE;
/*!40000 ALTER TABLE `ultimos_datos_carga` DISABLE KEYS */;
INSERT INTO `ultimos_datos_carga` VALUES (1),(3);
/*!40000 ALTER TABLE `ultimos_datos_carga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_non_expired` tinyint DEFAULT '1',
  `account_non_locked` tinyint DEFAULT '1',
  `apellido` varchar(80) NOT NULL,
  `credentials_non_expired` tinyint DEFAULT '1',
  `duracion_token` int NOT NULL,
  `email` varchar(300) NOT NULL,
  `enabled` tinyint DEFAULT '1',
  `intentos_fallidos` int NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `password` varchar(150) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `id_rol_principal` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKagx3xbxthdpi4gc0hyfggwxn0` (`id_rol_principal`),
  CONSTRAINT `FKagx3xbxthdpi4gc0hyfggwxn0` FOREIGN KEY (`id_rol_principal`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,1,'Brambilla',1,1240000,'nicolas.brambilla82@gmail.com',1,1,'Nico','1234','nico',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `id_user` int NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_rol`),
  KEY `FKaulyi2lejh5cckb2y8e2mlpud` (`id_rol`),
  CONSTRAINT `FK6ywr92flw5416dup8uc2egb83` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKaulyi2lejh5cckb2y8e2mlpud` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-07 15:45:48
