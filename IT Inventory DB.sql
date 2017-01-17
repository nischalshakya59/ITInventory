-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: inventorymgmt
-- ------------------------------------------------------
-- Server version	5.5.47

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
-- Table structure for table `brandtbl`
--

DROP TABLE IF EXISTS `brandtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brandtbl` (
  `brandid` int(11) NOT NULL AUTO_INCREMENT,
  `brandentrydate` varchar(45) DEFAULT NULL,
  `brandname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`brandid`),
  UNIQUE KEY `companyname_UNIQUE` (`brandname`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brandtbl`
--

LOCK TABLES `brandtbl` WRITE;
/*!40000 ALTER TABLE `brandtbl` DISABLE KEYS */;
INSERT INTO `brandtbl` VALUES (1,'May 28, 2016','Dell '),(2,'May 28, 2016','Acer'),(3,'May 28, 2016','Lenovo'),(4,'May 28, 2016','HP'),(5,'May 28, 2016','MSI'),(6,'May 28, 2016','ATI'),(7,'May 28, 2016','Nvidia'),(8,'May 28, 2016','DRN'),(9,'Jun 6 2016','Toshiba'),(10,'Jun 7 2016','Intel');
/*!40000 ALTER TABLE `brandtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorytbl`
--

DROP TABLE IF EXISTS `categorytbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorytbl` (
  `catid` int(11) NOT NULL AUTO_INCREMENT,
  `catentrydate` varchar(45) DEFAULT NULL,
  `catname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`catid`),
  UNIQUE KEY `catname_UNIQUE` (`catname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorytbl`
--

LOCK TABLES `categorytbl` WRITE;
/*!40000 ALTER TABLE `categorytbl` DISABLE KEYS */;
INSERT INTO `categorytbl` VALUES (1,'May 28 2016','Mobile\r'),(2,'May 28 2016','Headphone\r'),(3,'May 28 2016','Speaker\r'),(4,'May 28 2016','Motherboard\r'),(5,'May 28 2016','Laptop\r'),(6,'May 28 2016','Desktop\r'),(7,'May 28 2016','Power Supply\r'),(8,'May 28 2016','Graphic Card\r');
/*!40000 ALTER TABLE `categorytbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetbl`
--

DROP TABLE IF EXISTS `employeetbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeetbl` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `employeename` varchar(45) DEFAULT NULL,
  `employeelname` varchar(45) DEFAULT NULL,
  `employeecontactno` varchar(45) DEFAULT NULL,
  `employeeaddress` varchar(45) DEFAULT NULL,
  `employeepost` varchar(45) DEFAULT NULL,
  `employeejoindate` varchar(45) DEFAULT NULL,
  `employeesalary` double DEFAULT NULL,
  `employeeworkingyears` varchar(45) DEFAULT NULL,
  `employeeleavedate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetbl`
--

LOCK TABLES `employeetbl` WRITE;
/*!40000 ALTER TABLE `employeetbl` DISABLE KEYS */;
INSERT INTO `employeetbl` VALUES (2,'Rashik','Shakya','9849648900','Nayabazar','Guard','May 18 2016',3000,NULL,NULL),(3,'Nischal ','Shakya','Dallu','9841808707','Manager','May 31 2016',20000,'1-Year','');
/*!40000 ALTER TABLE `employeetbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logintbl`
--

DROP TABLE IF EXISTS `logintbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logintbl` (
  `userid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `employeetbl` (`employeeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logintbl`
--

LOCK TABLES `logintbl` WRITE;
/*!40000 ALTER TABLE `logintbl` DISABLE KEYS */;
INSERT INTO `logintbl` VALUES (2,'nischal','nischal','user'),(3,'admin','admin','admin');
/*!40000 ALTER TABLE `logintbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producttbl`
--

DROP TABLE IF EXISTS `producttbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producttbl` (
  `productid` int(11) NOT NULL,
  `productentrydate` varchar(45) DEFAULT NULL,
  `productsuppliername` varchar(45) DEFAULT NULL,
  `productbrand` varchar(45) DEFAULT NULL,
  `productmodel` varchar(45) DEFAULT NULL,
  `productcategory` varchar(45) DEFAULT NULL,
  `productqty` int(11) DEFAULT NULL,
  `productsellingprice` double DEFAULT NULL,
  `productcostprice` double DEFAULT NULL,
  `productprofit` double DEFAULT NULL,
  `productwarranty` varchar(45) DEFAULT NULL,
  `productspecification` longtext,
  PRIMARY KEY (`productid`),
  UNIQUE KEY `productmodel_UNIQUE` (`productmodel`),
  KEY `brandname_idx` (`productbrand`),
  KEY `suppliername_idx` (`productsuppliername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producttbl`
--

LOCK TABLES `producttbl` WRITE;
/*!40000 ALTER TABLE `producttbl` DISABLE KEYS */;
INSERT INTO `producttbl` VALUES (2,'May 28 2016','Microland','ATI','210','Motherboard\r',19,5000,2000,3000,'1-Month','Video Memory : \r1GB\nPort : HDMI, DIVX, VGA\n'),(3,'May 29 2016','AITE','Acer','5455','Laptop',45,90000,80000,10000,'1-Year',''),(4,'May 29 2016','Microland','Dell ','3430','Desktop',34,40000,25000,15000,'1-Year',''),(5,'Jun 6 2016','Topaz','DRN','67','Laptop',90,90000,50000,40000,'1-Year',''),(6,'Jun 7 2016','Digital Technologies','Intel','GSA','Graphic Card\r',90,50000,45000,5000,'2-Year','');
/*!40000 ALTER TABLE `producttbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saletbl`
--

DROP TABLE IF EXISTS `saletbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saletbl` (
  `saleid` int(11) NOT NULL,
  `saledate` varchar(45) DEFAULT NULL,
  `salecustomername` varchar(45) DEFAULT NULL,
  `salecustomeraddress` varchar(45) DEFAULT NULL,
  `salecustomercontact` varchar(45) DEFAULT NULL,
  `salesupplier` varchar(45) DEFAULT NULL,
  `salebrand` varchar(45) DEFAULT NULL,
  `saleproduct` varchar(45) DEFAULT NULL,
  `saleqty` double DEFAULT NULL,
  `saleamtperpiece` double DEFAULT NULL,
  `saletotal` double DEFAULT NULL,
  PRIMARY KEY (`saleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saletbl`
--

LOCK TABLES `saletbl` WRITE;
/*!40000 ALTER TABLE `saletbl` DISABLE KEYS */;
INSERT INTO `saletbl` VALUES (1,'May 31 2016','Nischal','Dallu','9841808707','Microland','ATI','210',1,5000,5000),(2,'May 31 2016','Rashik','New Road','9867990088','Microland','Dell ','3430',1,40000,40000),(3,'May 31 2016','Rashik','New Road','9867990088','Microland','Dell ','3430',1,40000,40000),(4,'May 31 2016','Nischal','Dallu','9876500990','Microland','ATI','210',1,5000,5000),(5,'May 31 2016','Nischal','Dallu','9876500990','AITE','Acer','5455',1,90000,90000),(6,'May 31 2016','Nischal','Dallu','9876500990','Microland','Dell ','3430',1,40000,40000),(7,'May 31 2016','Nischal','Dallu','9876500990','Microland','HP','5467',2,10000,20000),(15,'Jun 6 2016','H','H','0876','Microland','ATI','210',1,5000,5000);
/*!40000 ALTER TABLE `saletbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliertbl`
--

DROP TABLE IF EXISTS `suppliertbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliertbl` (
  `supplierid` int(11) NOT NULL AUTO_INCREMENT,
  `supplierentrydate` varchar(45) DEFAULT NULL,
  `suppliername` varchar(45) DEFAULT NULL,
  `suppliercontactno` varchar(45) DEFAULT NULL,
  `supplieraddress` varchar(45) DEFAULT NULL,
  `supplieremailaddress` varchar(45) DEFAULT NULL,
  `supplierdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierid`),
  KEY `suppliername` (`suppliername`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliertbl`
--

LOCK TABLES `suppliertbl` WRITE;
/*!40000 ALTER TABLE `suppliertbl` DISABLE KEYS */;
INSERT INTO `suppliertbl` VALUES (1,'May 28 2016','Microland','9856909080','Jwagal','microland15@gmail.com',''),(2,'May 28 2016','AITE','9876908790','Jumal','',''),(3,'May 28 2016','Topaz','01-558890','Kupondole','',''),(4,'Jun 7 2016','Digital Technologies','01-4233445','New Road','digital_technologies@hotmail.com','');
/*!40000 ALTER TABLE `suppliertbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-07  4:38:59
