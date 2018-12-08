-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: leavedb
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activityId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activityName` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`activityId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (0,'none'),(1,'Planning'),(2,'Execution'),(3,'Reporting'),(4,'Followup');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attributerating`
--

DROP TABLE IF EXISTS `attributerating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attributerating` (
  `attributeIdRatingId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `attributeId` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  `score` int(10) unsigned NOT NULL DEFAULT '0',
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `level` int(10) unsigned NOT NULL DEFAULT '0',
  `rating` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`attributeIdRatingId`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attributerating`
--

LOCK TABLES `attributerating` WRITE;
/*!40000 ALTER TABLE `attributerating` DISABLE KEYS */;
/*!40000 ALTER TABLE `attributerating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Hyphen'),(2,'Test'),(3,'t1Company');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `countryId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'US'),(2,'UK'),(3,'Other Euorope'),(4,'Middle East'),(5,'Australia');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domains`
--

DROP TABLE IF EXISTS `domains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domains` (
  `domainId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `lineofServiceId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`domainId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domains`
--

LOCK TABLES `domains` WRITE;
/*!40000 ALTER TABLE `domains` DISABLE KEYS */;
INSERT INTO `domains` VALUES (1,'Audit',1),(2,'Audit Management',1),(3,'Consulting',1),(4,'IAD Management',1),(5,'Unassigned',2);
/*!40000 ALTER TABLE `domains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `jobId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lineofServiceId` int(10) unsigned NOT NULL DEFAULT '0',
  `domainId` int(10) unsigned NOT NULL DEFAULT '0',
  `jobName` varchar(45) NOT NULL DEFAULT '',
  `countryId` int(10) unsigned DEFAULT NULL,
  `allocation` int(10) unsigned NOT NULL DEFAULT '0',
  `segment` int(10) unsigned NOT NULL DEFAULT '0',
  `nature` int(10) unsigned NOT NULL DEFAULT '0',
  `company` int(10) unsigned NOT NULL DEFAULT '0',
  `status` varchar(45) DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (202,1,1,'Test overseas',1,1,1,1,1,'Active',0),(203,2,2,'Business Meetups',1,2,5,6,2,'office',2),(204,1,1,'Team Meetings',1,2,1,1,1,'office',1),(205,1,1,'Casual Leaves',1,2,1,1,1,'office',1),(206,1,1,'Sick Leave',1,2,1,1,1,'office',1),(207,1,1,'Orientation and Management',1,2,1,1,1,'office',1),(208,1,1,'Training and Development',1,2,1,1,1,'office',1),(209,1,1,'Tools and Softwares',1,2,1,1,1,'office',1),(210,2,1,'Procurement',1,1,1,1,1,'Active',1);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobactivity`
--

DROP TABLE IF EXISTS `jobactivity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobactivity` (
  `jobactivityId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobId` int(10) unsigned DEFAULT NULL,
  `reporting` int(10) unsigned NOT NULL DEFAULT '0',
  `planning` int(10) unsigned NOT NULL DEFAULT '0',
  `execution` int(10) unsigned NOT NULL DEFAULT '0',
  `followup` int(10) unsigned NOT NULL DEFAULT '0',
  `designation` int(10) unsigned NOT NULL DEFAULT '0',
  `totalhours` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`jobactivityId`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobactivity`
--

LOCK TABLES `jobactivity` WRITE;
/*!40000 ALTER TABLE `jobactivity` DISABLE KEYS */;
INSERT INTO `jobactivity` VALUES (37,202,0,1,0,0,0,1,1),(38,202,1,0,1,0,0,2,30),(39,202,0,0,0,0,0,0,1),(40,202,1,1,1,1,0,4,30),(41,203,2,1,3,1,0,7,1),(42,203,2,1,3,3,0,9,30),(43,203,2,2,2,2,0,8,31),(44,203,0,0,0,0,0,0,32),(45,203,0,0,0,0,0,0,33),(46,204,0,0,0,0,0,0,1),(47,204,0,0,0,0,0,0,30),(48,204,0,0,0,0,0,0,31),(49,204,0,0,0,0,0,0,32),(50,204,0,0,0,0,0,0,33),(51,205,0,0,0,0,0,0,1),(52,205,0,0,0,0,0,0,30),(53,205,0,0,0,0,0,0,31),(54,205,0,0,0,0,0,0,32),(55,205,0,0,0,0,0,0,33),(56,206,0,0,0,0,0,0,1),(57,206,0,0,0,0,0,0,30),(58,206,0,0,0,0,0,0,31),(59,206,0,0,0,0,0,0,32),(60,206,0,0,0,0,0,0,33),(61,207,0,0,0,0,0,0,1),(62,207,0,0,0,0,0,0,30),(63,207,0,0,0,0,0,0,31),(64,207,0,0,0,0,0,0,32),(65,207,0,0,0,0,0,0,33),(66,208,0,0,0,0,0,0,1),(67,208,0,0,0,0,0,0,30),(68,208,0,0,0,0,0,0,31),(69,208,0,0,0,0,0,0,32),(70,208,0,0,0,0,0,0,33),(71,209,0,0,0,0,0,0,1),(72,209,0,0,0,0,0,0,30),(73,209,0,0,0,0,0,0,31),(74,209,0,0,0,0,0,0,32),(75,209,0,0,0,0,0,0,33),(76,210,0,5,0,0,0,5,1),(77,210,0,5,0,0,0,5,30),(78,210,0,0,0,0,0,0,31),(79,210,0,0,0,0,0,0,32),(80,210,0,0,0,0,0,0,33);
/*!40000 ALTER TABLE `jobactivity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobactivityrelation`
--

DROP TABLE IF EXISTS `jobactivityrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobactivityrelation` (
  `jobactivityrelationid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobid` int(10) unsigned NOT NULL DEFAULT '0',
  `activityid` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`jobactivityrelationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobactivityrelation`
--

LOCK TABLES `jobactivityrelation` WRITE;
/*!40000 ALTER TABLE `jobactivityrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobactivityrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobattributes`
--

DROP TABLE IF EXISTS `jobattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobattributes` (
  `jobAttributeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `level` int(10) unsigned NOT NULL DEFAULT '0',
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `rating` varchar(45) DEFAULT NULL,
  `score` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`jobAttributeId`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobattributes`
--

LOCK TABLES `jobattributes` WRITE;
/*!40000 ALTER TABLE `jobattributes` DISABLE KEYS */;
INSERT INTO `jobattributes` VALUES (6,'dd',0,21,NULL,0,0),(7,'Detailed research',2,32,'0.90',3,0),(8,'Excellent writing',1,32,'0.60',3,17),(9,'On time delivery',1,32,NULL,0,0),(10,'Professional presentation',1,32,NULL,0,0),(11,'Coherent and insightful',0,32,NULL,0,0),(12,'Structured approach and planning',1,34,NULL,0,0),(13,'Completeness',1,34,NULL,0,0),(14,'Detailed and comprehensive research',3,34,NULL,0,0),(15,'Strong presentation',1,34,NULL,0,0),(16,'Detailed research',1,36,'0.4',2,0),(17,'Excellent writing',1,36,'0.2',1,0),(18,'On time delivery',1,36,'0.4',2,0),(20,'Thorough research',2,44,'0.2',2,15),(21,'Structured presentation and writing',4,44,'0.5',1,17),(22,'Timely delivery',1,44,'0.3',3,17),(23,'Detailed research',3,38,NULL,0,0),(24,'Analytical approach',2,38,NULL,0,0),(25,'Strcutured presentation',2,38,NULL,0,0),(26,'Detailed research',4,39,NULL,0,0),(27,'Excellent writing and presentation',4,39,NULL,0,0),(28,'Detailed research',3,38,NULL,0,0),(29,'Analytical approach',2,38,NULL,0,0),(30,'Strcutured presentation',2,38,NULL,0,0),(31,'Detailed research',3,38,NULL,0,0),(32,'Detailed research',3,38,NULL,0,0),(33,'Analytical approach',2,38,NULL,0,0),(34,'Strcutured presentation',2,38,NULL,0,0),(35,'Detailed research',3,38,NULL,0,0),(36,'Structured presentation',2,38,NULL,0,0),(37,'Analytical skills',2,38,NULL,0,0),(38,'Detailed research',2,48,NULL,0,0),(39,'Analytical skills',3,48,NULL,0,0),(40,'Strcutured presentation',2,48,NULL,0,0),(41,'Research',4,49,NULL,0,0),(42,'Research',4,49,NULL,0,0),(43,'Excellent wriritng',4,49,NULL,0,0),(44,'Organized approach',2,51,NULL,0,0),(45,'Complete documentation',2,51,NULL,0,0),(46,'Efficiency',3,51,NULL,0,0),(57,'Comprehensive and relevant architecture',2,59,NULL,0,0),(58,'Accurate and thorough',1,59,NULL,0,0),(59,'Well written',1,59,NULL,0,0),(60,'Effective cahnnelization',2,59,NULL,0,0),(61,'Well strcutured and with wow factor',4,60,NULL,0,0),(62,'Capture relevant points',4,60,NULL,0,0),(64,'Professional presentation',1,36,NULL,0,0),(65,'Professional presentation',1,36,NULL,0,0),(67,'Detailed and relevant research',4,66,NULL,0,0),(68,'Timely completion',2,66,NULL,0,0),(69,'Effective presentaion',1,66,NULL,0,0),(70,'Structured documentation',2,50,NULL,0,0),(71,'Research',3,50,NULL,0,0),(72,'Well written',2,50,NULL,0,0),(75,'Timley completion',1,67,NULL,0,0),(76,'Clear and understandable model structuring',2,67,NULL,0,0),(77,'Complete capture of all the aspects',2,67,NULL,0,0),(78,'Complete conceptual understanding',1,67,NULL,0,0),(79,'Well planned and structured',2,68,NULL,0,0),(80,'Accuracy and completeness',4,68,NULL,0,0),(81,'Timeliness',1,68,NULL,0,0),(82,'Comprehensive and clear',4,69,NULL,0,0),(83,'Well presented',4,69,NULL,0,0),(84,'Business understanding',2,76,NULL,0,0),(85,'Effective and relevant write up',2,76,NULL,0,0),(86,'Efficient delivery',1,76,NULL,0,0),(87,'Timely completion',4,75,NULL,0,0),(88,'Financial understanding',4,75,NULL,0,0),(89,'Accuracy',2,77,NULL,0,0),(90,'Quick Books understanding',1,77,NULL,0,0),(91,'Effective client management',2,77,NULL,0,0),(92,'Execution as per audit methodology',2,70,NULL,0,0),(93,'Complete and clear documentation',2,70,NULL,0,0),(94,'Identification of weaknesses and issues',3,70,NULL,0,0),(95,'Financial understanding',1,76,NULL,0,0),(96,'Financial understanding',1,76,NULL,0,0),(97,'Financial understanding',1,76,NULL,0,0),(98,'Financial understanding',1,76,NULL,0,0),(99,'Financial understanding',1,76,NULL,0,0),(100,'Business understanding',2,85,NULL,0,0),(101,'Application of auditing standards',1,85,NULL,0,0),(102,'Timeliness',1,85,NULL,0,0),(103,'Develop for more services',2,87,NULL,0,0),(104,'Efficient execution',1,87,NULL,0,0),(105,'Timely communication',4,87,NULL,0,0);
/*!40000 ALTER TABLE `jobattributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobemployee`
--

DROP TABLE IF EXISTS `jobemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobemployee` (
  `jobEmployeeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `employeeId` int(10) unsigned NOT NULL DEFAULT '0',
  `noOfDays` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`jobEmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=877 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobemployee`
--

LOCK TABLES `jobemployee` WRITE;
/*!40000 ALTER TABLE `jobemployee` DISABLE KEYS */;
INSERT INTO `jobemployee` VALUES (835,202,1,0),(836,202,30,0),(837,203,1,0),(838,203,30,0),(839,203,31,0),(840,203,32,0),(841,203,33,0),(842,204,1,0),(843,204,30,0),(844,204,31,0),(845,204,32,0),(846,204,33,0),(847,202,31,0),(848,202,32,0),(849,202,33,0),(850,205,1,0),(851,205,30,0),(852,205,31,0),(853,205,32,0),(854,205,33,0),(855,206,30,0),(856,206,31,0),(857,206,32,0),(858,206,33,0),(859,207,30,0),(860,207,31,0),(861,207,32,0),(862,207,33,0),(863,208,30,0),(864,208,31,0),(865,208,32,0),(866,208,33,0),(867,209,31,0),(868,209,32,0),(869,209,33,0),(870,209,30,0),(871,206,1,0),(872,207,1,0),(873,208,1,0),(874,209,1,0),(875,210,1,0),(876,210,30,0);
/*!40000 ALTER TABLE `jobemployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaverecord`
--

DROP TABLE IF EXISTS `leaverecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaverecord` (
  `leaveRecordId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned DEFAULT NULL,
  `leaveTypeId` int(10) unsigned DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `endDate` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`leaveRecordId`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaverecord`
--

LOCK TABLES `leaverecord` WRITE;
/*!40000 ALTER TABLE `leaverecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `leaverecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leavetypes`
--

DROP TABLE IF EXISTS `leavetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leavetypes` (
  `leaveTypeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `leaveTypeName` varchar(45) DEFAULT NULL,
  `allowed` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`leaveTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leavetypes`
--

LOCK TABLES `leavetypes` WRITE;
/*!40000 ALTER TABLE `leavetypes` DISABLE KEYS */;
INSERT INTO `leavetypes` VALUES (1,'casual leave',8),(2,'annual leave',20),(3,'sick leave',8),(4,'exam leave',10),(5,'unpaid leave',1000);
/*!40000 ALTER TABLE `leavetypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineofservice`
--

DROP TABLE IF EXISTS `lineofservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineofservice` (
  `lineofServiceId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`lineofServiceId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineofservice`
--

LOCK TABLES `lineofservice` WRITE;
/*!40000 ALTER TABLE `lineofservice` DISABLE KEYS */;
INSERT INTO `lineofservice` VALUES (1,'Audit'),(2,'Business Process Reengineering'),(3,'Cash Count'),(4,'Corporate Governance'),(6,'Due Diligence'),(7,'Forensic Review'),(8,'IA Representation in Committes / Meetings'),(9,'Inventory Count'),(10,'Limited Review'),(11,'N/A'),(12,'Pre Audit / Vouching'),(13,'Risk Management'),(14,'Special Audit'),(15,'Subject Matter Advice'),(16,'Unassigned');
/*!40000 ALTER TABLE `lineofservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phases`
--

DROP TABLE IF EXISTS `phases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phases` (
  `phaseId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phaseName` varchar(45) NOT NULL DEFAULT '',
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `startDate` datetime DEFAULT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `submissionDate` datetime DEFAULT NULL,
  PRIMARY KEY (`phaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phases`
--

LOCK TABLES `phases` WRITE;
/*!40000 ALTER TABLE `phases` DISABLE KEYS */;
/*!40000 ALTER TABLE `phases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) DEFAULT NULL,
  `chargeRate` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'Senior Auditor',750),(3,'Auditor',1500),(5,'Group Chief Audit Executive',500);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sublineofservice`
--

DROP TABLE IF EXISTS `sublineofservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sublineofservice` (
  `subLineofServiceId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `domainId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`subLineofServiceId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sublineofservice`
--

LOCK TABLES `sublineofservice` WRITE;
/*!40000 ALTER TABLE `sublineofservice` DISABLE KEYS */;
INSERT INTO `sublineofservice` VALUES (1,'Business Plan',1),(2,'Pricing',1),(3,'Financial Modeling',1),(4,'Enterprice Risk',2),(5,'Business Continuity',2),(6,'Internal Control',2),(7,'Fraud Management',2),(8,'Internal Audit',2),(9,'Business Process Reengineering',3),(10,'Finance Functional Excellence',3),(11,'Operating Models',3),(12,'PFM General',4),(13,'PEFA assessment',4),(14,'Fuduciary risk',4),(15,'Pre award assessment',4),(16,'Statutory Audit',5),(17,'Non Operator Audit',5),(18,'Third Part validation',5),(19,'Accounting ',6),(20,'Payroll',6),(21,'Vendor Management',6),(22,'Fixed Asset Management',6),(23,'Abilite management',7),(24,'Functional Development',8),(25,'Learning & Development',9),(26,'Software',10);
/*!40000 ALTER TABLE `sublineofservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timesheet` (
  `timeSheetId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `month` int(10) unsigned NOT NULL DEFAULT '0',
  `day` int(10) unsigned NOT NULL DEFAULT '0',
  `hours` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `activityId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`timeSheetId`)
) ENGINE=InnoDB AUTO_INCREMENT=1238 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (1234,202,12,1,1,1,0,1),(1235,202,12,10,2,1,0,2),(1236,202,12,23,3,1,0,3),(1237,202,12,29,5,1,0,4);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `examLeaves` int(10) unsigned DEFAULT NULL,
  `reportingTo` int(10) unsigned DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `bacnkAcNo` varchar(45) DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  `joiningDate` datetime DEFAULT NULL,
  `companyId` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nauman','nauman','nauman@email.com',10,1,'111','1111',5,'2018-12-08 00:00:00',1,'active'),(30,'saeed','saeed','saeed@email.com',10,1,'111','111',2,'2018-12-14 00:00:00',1,'active'),(31,'SamiUllah Mukhtar','samiullah','samiullah@email.com',10,1,'0000','0000',2,'2018-12-07 00:00:00',1,'active'),(32,'Ahmed Ghufran','ahmed','ahmed@email.com',10,1,'0000','0000',3,'2018-12-07 00:00:00',1,'active'),(33,'Asif Shah','asif','asif@email.com',10,1,'00001','00001',3,'2018-12-07 00:00:00',1,'active');
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

-- Dump completed on 2018-12-08 12:08:41
