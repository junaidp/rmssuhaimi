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
INSERT INTO `activity` VALUES (0,'none'),(1,'planning'),(2,'Execution'),(3,'Followup'),(4,'Reportig');
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
INSERT INTO `attributerating` VALUES (14,20,17,2,44,2,'0.6'),(15,21,17,2,44,2,'1.0'),(16,20,15,3,44,0,'0.90'),(17,21,15,2,44,0,'1.0'),(18,22,15,1,44,0,'0.2'),(19,20,0,0,44,0,''),(20,26,8,2,39,2,'1'),(21,27,8,1,39,0,'0.5'),(22,38,8,2,48,0,'0.6'),(23,39,8,2,48,0,'0.8'),(24,40,8,1,48,0,'0.3'),(25,16,18,2,36,0,'0.4'),(26,17,18,1,36,0,'0.2'),(27,18,18,2,36,0,'0.4'),(28,19,18,1,36,0,'0.2'),(29,7,17,3,32,0,'0.89'),(30,8,17,1,32,0,'0.2'),(31,9,17,3,32,0,'0.60'),(32,10,17,3,32,0,'0.60'),(33,11,17,2,32,0,'0.2'),(34,22,17,2,44,0,'0.4'),(35,44,17,2,51,0,'0.6'),(36,45,17,2,51,0,'0.6'),(37,46,17,2,51,0,'0.8'),(38,16,5,2,36,2,'0.4'),(39,17,5,2,36,0,'0.4'),(40,18,5,3,36,0,'0.60'),(41,19,5,0,36,0,''),(42,64,5,3,36,0,'0.60'),(43,65,5,3,36,0,'0.60'),(44,67,5,2,66,0,'1'),(45,68,5,1,66,0,'0.3'),(46,69,5,2,66,0,'0.4'),(47,67,8,2,66,0,'1'),(48,68,8,3,66,0,'0.89'),(49,69,8,2,66,0,'0.4'),(50,70,5,2,50,0,'0.6'),(51,71,5,2,50,0,'0.8'),(52,72,5,1,50,0,'0.3'),(53,62,17,3,60,3,'1.5'),(54,61,17,3,60,3,'1.5'),(55,82,17,3,69,0,'1.5'),(56,83,17,3,69,0,'1.5'),(57,79,18,3,68,0,'0.89'),(58,80,18,2,68,0,'1'),(59,81,18,3,68,0,'0.60'),(60,79,15,2,68,2,'0.6'),(61,80,15,2,68,2,'1'),(62,81,15,3,68,3,'0.60'),(63,79,21,3,68,0,'0.89'),(64,80,21,2,68,0,'1'),(65,81,21,3,68,0,'0.60'),(66,75,8,2,67,0,'0.4'),(67,76,8,3,67,0,'0.89'),(68,77,8,3,67,0,'0.89'),(69,78,8,2,67,0,'0.4');
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
INSERT INTO `domains` VALUES (1,'Strategy',1),(2,'Risk',1),(3,'Transformation',1),(4,'PFM',1),(5,'Assurance',2),(6,'Outsourcing',3),(7,'Existing',4),(8,'New Application',5),(9,'Learning & Development',6),(10,'Software',7);
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
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (3,1,1,'j11',1,0,0,0,0),(4,2,2,'jobArs',1,0,0,0,0),(5,1,1,'t',1,0,0,0,0),(6,1,1,'testwithsupervis',1,0,0,0,0),(13,1,1,'kj',1,0,0,0,0),(14,1,1,'NewT',1,0,0,0,0),(15,1,1,'NewT',1,0,0,0,0),(16,1,1,'NewT',1,0,0,0,0),(17,1,1,'NewT',1,0,0,0,0),(18,1,1,'jj',1,0,0,0,0),(19,1,1,'k',1,0,0,0,0),(20,1,1,'Logistics',1,0,0,0,0),(21,1,1,'j11',1,0,0,0,0),(22,1,1,'jk',1,0,0,0,0),(23,6,9,'New test',1,0,0,0,0),(31,1,1,'j',1,0,0,0,0),(32,1,1,'Publishing',2,0,0,0,0),(33,1,1,'Publishing',2,0,0,0,0),(34,1,3,'Macro Economic forecasting model',2,0,0,0,0),(35,1,1,'BP Trucking',2,0,0,0,0),(36,1,1,'BP Trucking',2,0,0,0,0),(37,1,3,'Macro Economic Phase III',2,0,0,0,0),(38,1,3,'Macro Economic Phase III',2,0,0,0,0),(39,1,3,'Macro Economic Model Phase II',2,0,0,0,0),(40,1,3,'Macro Economic Model Phase II',2,0,0,0,0),(41,1,1,'Office',1,0,0,0,0),(42,1,1,'LND',1,0,0,0,0),(43,1,1,'Business Development',1,0,0,0,0),(44,5,8,'Proposal for MY  University',1,0,0,0,0),(45,1,3,'Macro Econominc Model PH III',2,0,0,0,0),(46,1,3,'Macro Econominc Model PH III',2,0,0,0,0),(47,1,1,'Macro Econominc Model PH III',2,0,0,0,0),(48,1,1,'Macro Econominc Model PH III',2,0,0,0,0),(49,1,4,'PFM System',2,0,0,0,0),(50,1,4,'PFM System - ADB',2,0,0,0,0),(51,1,1,'MY University - Audit',1,0,0,0,0),(52,1,1,'testjob',1,0,0,0,0),(53,1,1,'testjob',1,0,0,0,0),(54,1,1,'testjob',1,0,0,0,0),(55,1,1,'testjob',1,0,0,0,0),(56,5,1,'Proposal - University of Sargodha',1,0,0,0,0),(57,2,1,'Eni Final Report',1,0,0,0,0),(58,2,5,'Testing new',1,0,0,0,0),(59,5,8,'Doing business in Pakistan publication',1,0,0,0,0),(60,7,1,'abilite presenation',1,0,0,0,0),(61,5,8,'Incubator BP questionnare',1,0,0,0,0),(62,5,1,'Internal Audit Survey Report',1,0,0,0,0),(63,5,8,'Action steps and theme for CFO Breakfast',1,0,0,0,0),(64,5,8,'Action steps and theme for CEO interviews',1,0,0,0,0),(65,5,8,'Ideas for hyphen video',1,0,0,0,0),(66,1,1,'Agriculture research',5,0,0,0,0),(67,1,1,'Macro Economic Model Phase IV',2,0,0,0,0),(68,3,6,'SABA Accounts and bookkeeping',1,0,0,0,0),(69,5,8,'Training on Excel',1,0,0,0,0),(70,2,5,'SABA Trust Audit',1,0,0,0,0),(71,5,8,'Incubator',1,0,0,0,0),(72,5,8,'Incubator',1,0,0,0,0),(73,1,1,'University of Sargodha Workshop execution',1,0,0,0,0),(74,5,8,'Audit Proposal',1,0,0,0,0),(75,1,1,'Excel based financials',1,0,0,0,0),(76,1,1,'Business Plan and Pitch Bahamas',1,0,0,0,0),(77,3,6,'Accounting - Canada',1,0,0,0,0),(78,1,1,'Mcro Economic Mode Phase V',2,0,0,0,0),(79,3,6,'QB Conversion',1,0,0,0,0),(80,3,6,'QB Conversion',1,0,0,0,0),(81,3,6,'QB Conversion',1,0,0,0,0),(82,3,6,'Bookkeeping',1,0,0,0,0),(83,1,1,'Business Plan Sun Isp',1,0,0,0,0),(84,5,8,'abilite website content',1,0,0,0,0),(85,2,5,'Honda Avenue March 31 2016',1,0,0,0,0),(86,3,6,'Chris tremblay Accounting',1,0,0,0,0),(87,3,6,'Chris tremblay Accounting',1,0,0,0,0),(88,1,1,'Business Plan FlatProduction',1,0,0,0,0),(89,3,6,'Saba Trust April Ist Half',1,0,0,0,0),(90,3,6,'Saba Trust April Ist Half',1,0,0,0,0),(91,3,6,'Saba Trust April Ist Half',1,0,0,0,0),(92,1,3,'Saba Manuals',1,0,0,0,0),(93,5,8,'Incubation Proposal',1,0,0,0,0),(94,5,8,'Incubation Proposal',1,0,0,0,0),(95,5,8,'Summit videos',1,0,0,0,0),(96,5,8,'Sialkot industry research',1,0,0,0,0),(97,5,8,'INGO List',1,0,0,0,0),(98,1,2,'IT JOB - Contract review',4,0,0,0,0),(99,5,8,'Quality Assurance Framework',4,0,0,0,0),(100,5,1,'Quality Assurance Framework ',4,0,0,0,0),(101,1,1,'Business Plan Beauty app',4,0,0,0,0),(102,5,8,'Singapore Vistra Proposal',5,0,0,0,0),(103,5,1,'Internal Audit Club Launch',5,0,0,0,0),(104,5,8,'Proposal MMG',4,0,0,0,0),(105,5,8,'Proposal RAS',1,0,0,0,0),(106,1,2,'Internal Control Training',1,0,0,0,0),(107,5,8,'Meetings in Lahore',1,0,0,0,0),(108,5,8,'Strategy for overseas outsourcing',1,0,0,0,0),(109,5,8,'Meetings with NGOs\'',1,0,0,0,0),(110,5,1,'Honda for Internal Audit Outsourcing',1,0,0,0,0),(111,5,8,'Middel East VAT research',1,0,0,0,0),(112,5,8,'Research on SPO',1,0,0,0,0),(113,1,1,'Honda forecasting',1,0,0,0,0),(114,1,1,'Pvt vs. IPO',1,0,0,0,0),(115,3,6,'Saba Accounting 4th Qtr',1,0,0,0,0),(116,5,8,'Business Development CFO & IA',1,0,0,0,0),(117,1,3,'IFRS',4,0,0,0,0),(118,1,3,'IFRS Conversion',4,0,0,0,0),(119,5,1,'IFRS Videos',4,0,0,0,0),(120,3,6,'Vistra',5,0,0,0,0),(121,1,3,'Incorporation - TMS',2,0,0,0,0),(122,2,5,'Honda Accounts finalization',1,0,0,0,0),(123,2,1,'OMV Audit',1,0,0,0,0),(124,1,1,'Economic System - Gold',1,0,0,0,0),(125,1,1,'Training in KSA',4,0,0,0,0),(126,1,1,'Articles and videos for publication',4,0,0,0,0),(127,1,3,'IFRS for SMEs\' - SOCPA Guidance Review ',1,0,0,0,0),(128,2,5,'JUMA Agreement Review & Briefing',1,0,0,0,0),(129,5,8,'VAT research',1,0,0,0,0),(130,5,1,'IFRS GAP - Remaining',1,0,0,0,0),(131,5,8,'IFRS Capacity - Stock',1,0,0,0,0),(132,5,1,'IFRS Capacity - PPE',1,0,0,0,0),(133,3,6,'Vistra Dec',5,0,0,0,0),(134,1,1,'Presentation on Revenue, Employee Benefits ',1,0,0,0,0),(135,1,1,'Presentation on Leases and Equity Accounting ',1,0,0,0,0),(136,1,1,'Provision & Contingencies/Going Concern',1,0,0,0,0),(137,1,1,'Incorporation - Honda Avenue',1,0,0,0,0),(138,1,1,'Profit Centers\' Analysis',1,0,0,0,0),(139,5,8,'Vistra - Process documentation',1,0,0,0,0),(140,1,1,'Incoporation: Mabali (Private) Limited',1,0,0,0,0),(141,1,1,'Incoporation: Mabali ',1,0,0,0,0),(142,1,1,'Incorporation: Mabali',1,0,0,0,0),(143,1,1,'TMS - Sales Tax Registration',1,0,0,0,0),(144,1,1,'Coso Framework',1,0,0,0,0),(145,1,1,'Organic Poultry Form',1,0,0,0,0),(146,1,1,'Saudi Steel',1,0,0,0,0),(147,1,1,'Website for IFRS related material',1,0,0,0,0),(148,1,1,'Honda Avenue/Suzuki Motors Incorporation',1,0,0,0,0),(149,1,1,'Audit Program -  Honda Avenue',1,0,0,0,0),(150,1,1,'Honda Avenue - NTN & STRN Registration',1,0,0,0,0),(151,1,1,'Honda Avenue - Stock Count',1,0,0,0,0),(152,1,1,'Profit Centers\' Follow-up',1,0,0,0,0),(153,1,1,'Vistra Assignment',1,0,0,0,0),(154,1,1,'Honda Audit',1,0,0,0,0),(155,1,1,'Presentations on IFRS',1,0,0,0,0),(156,1,1,'Zamzama Non Operator Audit',1,0,0,0,0),(157,1,1,'Directors\' Agreement with TMS',1,0,0,0,0),(158,1,1,'OMV Report',1,0,0,0,0),(160,1,1,'hh',1,0,0,0,0),(165,1,1,'vas',1,0,0,0,0),(166,1,1,'ha',1,0,0,0,0);
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
  PRIMARY KEY (`jobactivityId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobactivity`
--

LOCK TABLES `jobactivity` WRITE;
/*!40000 ALTER TABLE `jobactivity` DISABLE KEYS */;
INSERT INTO `jobactivity` VALUES (1,NULL,3,2,4,7,0),(2,166,4,3,2,1,1);
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
INSERT INTO `jobattributes` VALUES (6,'dd',0,21,NULL,0,0),(7,'Detailed research',2,32,'0.90',3,0),(8,'Excellent writing',1,32,'0.60',3,17),(9,'On time delivery',1,32,NULL,0,0),(10,'Professional presentation',1,32,NULL,0,0),(11,'Coherent and insightful',0,32,NULL,0,0),(12,'Structured approach and planning',1,34,NULL,0,0),(13,'Completeness',1,34,NULL,0,0),(14,'Detailed and comprehensive research',3,34,NULL,0,0),(15,'Strong presentation',1,34,NULL,0,0),(16,'Detailed research',1,36,'0.4',2,0),(17,'Excellent writing',1,36,'0.2',1,0),(18,'On time delivery',1,36,'0.4',2,0),(20,'Thorough research',2,44,'0.2',2,15),(21,'Structured presentation and writing',4,44,'0.5',1,17),(22,'Timely delivery',1,44,'0.3',3,17),(23,'Detailed research',3,38,NULL,0,0),(24,'Analytical approach',2,38,NULL,0,0),(25,'Strcutured presentation',2,38,NULL,0,0),(26,'Detailed research',4,39,NULL,0,0),(27,'Excellent writing and presentation',4,39,NULL,0,0),(28,'Detailed research',3,38,NULL,0,0),(29,'Analytical approach',2,38,NULL,0,0),(30,'Strcutured presentation',2,38,NULL,0,0),(31,'Detailed research',3,38,NULL,0,0),(32,'Detailed research',3,38,NULL,0,0),(33,'Analytical approach',2,38,NULL,0,0),(34,'Strcutured presentation',2,38,NULL,0,0),(35,'Detailed research',3,38,NULL,0,0),(36,'Structured presentation',2,38,NULL,0,0),(37,'Analytical skills',2,38,NULL,0,0),(38,'Detailed research',2,48,NULL,0,0),(39,'Analytical skills',3,48,NULL,0,0),(40,'Strcutured presentation',2,48,NULL,0,0),(41,'Research',4,49,NULL,0,0),(42,'Research',4,49,NULL,0,0),(43,'Excellent wriritng',4,49,NULL,0,0),(44,'Organized approach',2,51,NULL,0,0),(45,'Complete documentation',2,51,NULL,0,0),(46,'Efficiency',3,51,NULL,0,0),(57,'Comprehensive and relevant architecture',2,59,NULL,0,0),(58,'Accurate and thorough',1,59,NULL,0,0),(59,'Well written',1,59,NULL,0,0),(60,'Effective cahnnelization',2,59,NULL,0,0),(61,'Well strcutured and with wow factor',4,60,NULL,0,0),(62,'Capture relevant points',4,60,NULL,0,0),(64,'Professional presentation',1,36,NULL,0,0),(65,'Professional presentation',1,36,NULL,0,0),(67,'Detailed and relevant research',4,66,NULL,0,0),(68,'Timely completion',2,66,NULL,0,0),(69,'Effective presentaion',1,66,NULL,0,0),(70,'Structured documentation',2,50,NULL,0,0),(71,'Research',3,50,NULL,0,0),(72,'Well written',2,50,NULL,0,0),(75,'Timley completion',1,67,NULL,0,0),(76,'Clear and understandable model structuring',2,67,NULL,0,0),(77,'Complete capture of all the aspects',2,67,NULL,0,0),(78,'Complete conceptual understanding',1,67,NULL,0,0),(79,'Well planned and structured',2,68,NULL,0,0),(80,'Accuracy and completeness',4,68,NULL,0,0),(81,'Timeliness',1,68,NULL,0,0),(82,'Comprehensive and clear',4,69,NULL,0,0),(83,'Well presented',4,69,NULL,0,0),(84,'Business understanding',2,76,NULL,0,0),(85,'Effective and relevant write up',2,76,NULL,0,0),(86,'Efficient delivery',1,76,NULL,0,0),(87,'Timely completion',4,75,NULL,0,0),(88,'Financial understanding',4,75,NULL,0,0),(89,'Accuracy',2,77,NULL,0,0),(90,'Quick Books understanding',1,77,NULL,0,0),(91,'Effective client management',2,77,NULL,0,0),(92,'Execution as per audit methodology',2,70,NULL,0,0),(93,'Complete and clear documentation',2,70,NULL,0,0),(94,'Identification of weaknesses and issues',3,70,NULL,0,0),(95,'Financial understanding',1,76,NULL,0,0),(96,'Financial understanding',1,76,NULL,0,0),(97,'Financial understanding',1,76,NULL,0,0),(98,'Financial understanding',1,76,NULL,0,0),(99,'Financial understanding',1,76,NULL,0,0),(100,'Business understanding',2,85,NULL,0,0),(101,'Application of auditing standards',1,85,NULL,0,0),(102,'Timeliness',1,85,NULL,0,0),(103,'Develop for more services',2,87,NULL,0,0),(104,'Efficient execution',1,87,NULL,0,0),(105,'Timely communication',4,87,NULL,0,0),(106,'',1,145,NULL,0,0),(107,'',1,145,NULL,0,0),(108,'',1,154,NULL,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=835 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobemployee`
--

LOCK TABLES `jobemployee` WRITE;
/*!40000 ALTER TABLE `jobemployee` DISABLE KEYS */;
INSERT INTO `jobemployee` VALUES (2,13,8,1),(4,13,15,4),(5,5,8,1),(6,5,8,1),(7,14,8,3),(8,14,9,2),(9,15,8,3),(10,15,9,1),(11,18,8,2),(12,19,8,2),(13,20,8,10),(14,21,8,2),(15,22,8,1),(16,23,8,12),(24,31,8,2),(25,33,17,40),(26,32,17,40),(27,34,8,80),(28,35,18,40),(29,36,18,60),(30,37,8,40),(31,38,8,40),(32,39,8,40),(33,40,8,40),(34,41,1,500),(35,41,5,500),(36,41,8,500),(37,41,9,500),(38,41,15,500),(39,41,17,500),(40,41,18,500),(41,42,1,500),(42,42,5,500),(43,42,8,500),(44,42,9,500),(45,42,15,500),(46,42,17,500),(47,42,18,500),(48,43,1,500),(49,43,5,500),(50,43,8,500),(51,43,9,500),(52,43,15,500),(53,43,17,500),(54,43,18,500),(55,44,15,12),(56,44,17,8),(57,45,8,80),(58,46,8,80),(59,47,8,80),(60,48,8,80),(61,49,1,50),(62,50,1,50),(63,51,8,24),(64,51,15,24),(65,51,5,4),(66,52,8,1),(67,53,8,1),(68,54,8,1),(69,55,8,1),(70,55,17,2),(71,51,17,24),(72,56,15,40),(73,56,17,40),(74,57,8,20),(75,36,5,20),(76,50,5,24),(77,58,8,1),(78,58,17,2),(79,58,1,3),(80,32,1,3),(81,36,1,2),(82,39,1,2),(83,44,1,0),(84,48,1,5),(85,50,1,0),(86,51,1,8),(87,56,1,2),(88,57,1,2),(89,59,18,24),(90,59,18,0),(91,59,1,0),(92,60,5,8),(94,60,17,16),(95,61,17,8),(96,61,17,0),(97,61,1,0),(98,62,18,12),(99,62,5,0),(101,62,5,12),(103,62,1,8),(104,63,17,12),(105,63,17,0),(106,63,1,2),(107,64,8,12),(108,64,17,0),(109,64,1,2),(110,64,17,12),(111,64,17,0),(112,64,1,2),(113,65,15,10),(114,65,15,0),(115,65,1,1),(116,66,8,8),(117,66,8,0),(118,66,1,0),(119,66,5,4),(120,66,8,0),(121,66,1,0),(122,67,8,64),(123,67,8,16),(124,67,1,0),(125,68,18,120),(126,68,5,40),(127,68,1,8),(128,68,15,120),(129,68,5,40),(130,68,1,8),(131,68,21,40),(132,68,5,40),(133,68,1,8),(134,69,17,16),(135,69,17,0),(136,69,1,0),(137,70,17,24),(138,70,17,0),(139,70,1,1),(140,60,17,0),(141,60,1,2),(142,60,17,0),(143,60,1,2),(144,71,17,40),(145,71,17,0),(146,71,1,4),(147,72,17,40),(148,72,17,0),(149,72,1,4),(150,73,17,60),(151,73,1,24),(152,73,1,0),(153,74,17,8),(154,74,17,0),(155,74,1,1),(156,62,5,0),(157,62,1,8),(158,62,5,0),(159,62,1,8),(160,62,5,0),(161,62,1,8),(162,62,5,0),(163,62,1,8),(164,62,5,0),(165,62,1,8),(166,62,5,0),(167,62,1,8),(168,62,17,16),(169,62,5,0),(170,62,1,8),(171,75,17,6),(172,75,17,0),(173,75,1,0),(174,76,17,60),(175,76,17,0),(176,76,1,10),(177,77,18,80),(178,77,18,0),(179,77,1,4),(180,78,8,30),(181,78,8,0),(182,78,1,2),(183,79,17,20),(184,79,17,0),(185,79,1,4),(186,79,18,20),(187,79,17,0),(188,79,1,4),(189,80,17,20),(190,80,17,0),(191,80,1,4),(192,80,18,20),(193,80,17,0),(194,80,1,4),(195,81,17,20),(196,81,17,0),(197,81,1,4),(198,81,18,20),(199,81,17,0),(200,81,1,4),(201,82,21,40),(202,82,17,5),(203,82,1,0),(204,83,17,40),(205,83,17,0),(206,83,1,8),(207,84,15,20),(208,84,17,4),(209,84,1,1),(210,85,21,80),(211,85,1,0),(212,85,1,10),(213,85,8,80),(214,85,1,0),(215,85,1,10),(216,86,15,180),(217,86,1,0),(218,86,1,0),(219,87,15,180),(220,87,1,0),(221,87,1,0),(222,88,8,8),(223,88,5,3),(224,88,1,1),(225,89,15,16),(226,89,5,0),(227,89,1,0),(228,90,15,16),(229,90,1,0),(230,90,1,0),(231,91,15,16),(232,91,1,0),(233,91,1,0),(234,92,15,40),(235,92,5,20),(236,92,1,4),(237,93,5,20),(238,93,5,0),(239,93,1,2),(240,94,5,20),(241,94,5,0),(242,94,1,2),(243,95,8,10),(244,95,1,0),(245,95,1,0),(246,96,15,10),(247,96,1,0),(248,96,1,1),(249,96,21,10),(250,96,1,0),(251,96,1,1),(252,97,22,10),(253,97,1,0),(254,97,1,1),(255,98,8,20),(256,98,1,0),(257,98,1,4),(258,99,5,40),(259,99,1,0),(260,99,1,4),(261,100,5,40),(262,100,15,40),(263,100,1,4),(264,101,5,60),(266,101,8,30),(267,102,21,20),(268,102,1,0),(269,102,1,20),(271,103,21,10),(273,104,1,0),(274,104,1,0),(275,104,1,12),(276,105,1,0),(277,105,1,0),(278,105,1,10),(279,101,1,0),(280,101,1,2),(281,101,1,0),(282,101,1,2),(283,103,1,0),(284,103,1,20),(285,106,1,40),(286,106,1,0),(287,106,1,0),(288,107,1,10),(289,107,1,0),(290,107,1,0),(291,108,1,10),(292,108,1,0),(293,108,1,0),(294,109,1,20),(295,109,1,0),(296,109,1,0),(297,110,1,5),(298,110,21,10),(299,110,8,1),(300,110,1,0),(301,110,1,0),(302,110,1,0),(303,110,1,0),(304,110,1,0),(305,110,1,0),(306,111,22,10),(307,111,1,0),(308,111,1,1),(309,112,21,10),(310,112,1,0),(311,112,1,2),(312,113,21,30),(313,113,1,0),(314,113,1,4),(315,114,8,20),(316,114,1,0),(317,114,1,3),(318,114,1,0),(319,114,1,3),(320,114,1,0),(321,114,1,3),(322,114,1,0),(323,114,1,3),(324,100,1,0),(325,100,1,4),(326,100,1,0),(327,100,1,4),(328,100,1,0),(329,100,1,4),(330,103,1,0),(331,103,1,20),(332,103,1,0),(333,103,1,20),(334,103,1,0),(335,103,1,20),(336,103,1,0),(337,103,1,20),(338,103,1,0),(339,103,1,0),(340,103,1,20),(341,103,1,20),(342,103,1,0),(343,103,1,20),(344,103,1,0),(345,103,1,20),(346,103,1,0),(347,103,1,0),(348,103,1,20),(349,103,1,20),(350,103,1,0),(351,103,1,20),(352,103,1,0),(353,103,1,20),(354,103,1,0),(355,103,1,0),(356,103,1,20),(357,103,1,20),(358,103,1,0),(359,103,1,20),(360,115,15,24),(361,115,1,0),(362,115,1,1),(363,115,22,24),(364,115,1,0),(365,115,1,1),(366,116,8,40),(367,116,1,0),(368,116,1,10),(369,116,22,40),(370,116,1,0),(371,116,1,10),(372,117,8,120),(373,117,24,120),(374,117,1,40),(375,117,21,120),(376,117,24,120),(377,117,1,40),(378,118,21,280),(379,118,24,240),(380,118,1,60),(381,118,8,240),(382,118,24,240),(383,118,1,60),(384,119,8,40),(385,119,24,4),(386,119,1,2),(387,120,21,40),(388,120,21,4),(389,120,1,0),(390,120,15,40),(391,120,21,4),(392,120,1,0),(393,121,8,10),(399,122,21,15),(400,122,1,0),(401,122,1,1),(408,124,26,40),(409,124,24,10),(410,124,1,0),(411,125,1,70),(412,125,24,0),(413,125,1,0),(414,125,24,100),(415,125,24,0),(416,125,1,0),(417,126,8,100),(418,126,24,0),(419,126,1,0),(420,127,21,8),(421,127,24,4),(422,127,1,2),(423,128,26,16),(424,128,24,4),(425,128,1,2),(426,129,26,10),(427,129,1,0),(428,129,1,0),(429,130,21,40),(431,130,1,2),(432,131,22,40),(433,131,24,5),(434,131,1,2),(438,133,21,40),(439,133,21,0),(440,133,1,0),(441,133,26,40),(442,133,21,0),(443,133,1,0),(444,133,22,40),(445,133,21,0),(446,133,1,0),(447,134,15,40),(448,134,24,1),(449,134,1,1),(450,135,1,40),(451,135,24,1),(452,135,1,1),(464,132,26,2),(465,138,8,40),(466,138,24,20),(467,138,1,5),(473,136,8,40),(474,136,24,1),(475,136,1,1),(478,132,24,5),(479,132,1,2),(480,124,24,10),(481,124,1,0),(482,124,24,10),(483,124,1,0),(484,124,24,10),(485,124,1,0),(486,130,24,5),(487,130,1,2),(488,130,24,5),(489,130,1,2),(490,121,24,6),(491,121,1,1),(492,134,24,1),(493,134,1,1),(494,134,24,1),(495,134,1,1),(496,134,24,1),(497,134,1,1),(498,134,24,1),(499,134,1,1),(500,134,24,1),(501,134,1,1),(502,134,24,1),(503,134,1,1),(504,134,24,1),(505,134,1,1),(506,134,24,1),(507,134,1,1),(508,134,24,1),(509,134,1,1),(510,134,24,1),(511,134,1,1),(512,134,24,1),(513,134,1,1),(514,134,24,1),(515,134,1,1),(516,134,24,1),(517,134,1,1),(518,134,24,1),(519,134,1,1),(520,134,24,1),(521,134,1,1),(522,134,24,1),(523,134,1,1),(524,134,24,1),(525,134,1,1),(526,134,24,1),(527,134,1,1),(528,134,24,1),(529,134,1,1),(530,134,24,1),(531,134,1,1),(532,134,24,1),(533,134,1,1),(534,134,24,1),(535,134,1,1),(536,134,24,1),(537,134,1,1),(538,134,24,1),(539,134,1,1),(540,134,24,1),(541,134,1,1),(542,134,24,1),(543,134,1,1),(544,134,24,1),(545,134,1,1),(546,134,24,1),(547,134,1,1),(548,134,24,1),(549,134,1,1),(550,134,24,1),(551,134,1,1),(552,134,24,1),(553,134,1,1),(554,134,24,1),(555,134,1,1),(556,134,24,1),(557,134,1,1),(558,134,24,1),(559,134,1,1),(560,134,24,1),(561,134,1,1),(562,134,24,1),(563,134,1,1),(564,134,24,1),(565,134,1,1),(566,134,24,1),(567,134,1,1),(568,134,24,1),(569,134,1,1),(576,134,24,1),(577,134,1,1),(578,134,24,1),(579,134,1,1),(580,134,24,1),(581,134,1,1),(582,134,24,1),(583,134,1,1),(584,134,24,1),(585,134,1,1),(586,134,24,1),(587,134,1,1),(588,134,24,1),(589,134,1,1),(590,134,24,1),(591,134,1,1),(592,134,24,1),(593,134,1,1),(594,134,24,1),(595,134,1,1),(596,134,24,1),(597,134,1,1),(598,134,24,1),(599,134,1,1),(600,134,24,1),(601,134,1,1),(602,134,24,1),(603,134,1,1),(604,134,24,1),(605,134,1,1),(606,134,24,1),(607,134,1,1),(608,134,24,1),(609,134,1,1),(610,134,24,1),(611,134,1,1),(612,134,24,1),(613,134,1,1),(614,134,24,1),(615,134,1,1),(616,134,24,1),(617,134,1,1),(618,134,24,1),(619,134,1,1),(620,134,24,1),(621,134,1,1),(622,134,24,1),(623,134,1,1),(624,134,24,1),(625,134,1,1),(626,134,24,1),(627,134,1,1),(628,134,24,1),(629,134,1,1),(630,134,24,1),(631,134,1,1),(632,134,24,1),(633,134,1,1),(634,134,24,1),(635,134,1,1),(636,134,24,1),(637,134,1,1),(638,134,24,1),(639,134,1,1),(640,134,24,1),(641,134,1,1),(642,134,24,1),(643,134,1,1),(644,134,24,1),(645,134,1,1),(646,134,24,1),(647,134,1,1),(648,134,24,1),(649,134,1,1),(650,134,24,1),(651,134,1,1),(652,134,24,1),(653,134,1,1),(654,134,24,1),(655,134,1,1),(656,134,24,1),(657,134,1,1),(658,134,24,1),(659,134,1,1),(660,134,24,1),(661,134,1,1),(662,134,24,1),(663,134,1,1),(664,134,24,1),(665,134,1,1),(666,134,24,1),(667,134,1,1),(668,134,24,1),(669,134,1,1),(670,134,24,1),(671,134,1,1),(672,134,24,1),(673,134,1,1),(674,134,24,1),(675,134,1,1),(676,134,24,1),(677,134,1,1),(678,134,24,1),(679,134,1,1),(680,134,24,1),(681,134,1,1),(682,134,24,1),(683,134,1,1),(684,134,24,1),(685,134,1,1),(686,134,24,1),(687,134,1,1),(688,134,24,1),(689,134,1,1),(690,134,24,1),(691,134,1,1),(692,134,24,1),(693,134,1,1),(694,134,24,1),(695,134,1,1),(696,134,24,1),(697,134,1,1),(698,134,24,1),(699,134,1,1),(700,134,24,1),(701,134,1,1),(702,134,24,1),(703,134,1,1),(704,134,24,1),(705,134,1,1),(706,134,24,1),(707,134,1,1),(708,134,24,1),(709,134,1,1),(710,134,24,1),(711,134,1,1),(712,134,24,1),(713,134,1,1),(714,134,24,1),(715,134,1,1),(716,134,24,1),(717,134,1,1),(718,134,24,1),(719,134,1,1),(720,134,24,1),(721,134,1,1),(722,134,24,1),(723,134,1,1),(724,134,24,1),(725,134,1,1),(726,134,24,1),(727,134,1,1),(728,134,24,1),(729,134,1,1),(730,134,24,1),(731,134,1,1),(732,134,24,1),(733,134,1,1),(734,134,24,1),(735,134,1,1),(736,134,24,1),(737,134,1,1),(738,136,24,1),(739,136,1,1),(740,136,24,1),(741,136,1,1),(742,136,24,1),(743,136,1,1),(744,119,24,4),(745,119,1,2),(746,119,24,4),(747,119,1,2),(748,119,24,4),(749,119,1,2),(750,130,24,5),(751,130,1,2),(752,130,24,5),(753,130,1,2),(754,130,24,5),(755,130,1,2),(756,130,24,5),(757,130,1,2),(758,130,24,5),(759,130,1,2),(760,130,24,5),(761,130,1,2),(762,139,21,40),(763,139,24,5),(764,139,1,1),(765,140,26,40),(766,140,24,25),(767,140,1,5),(768,141,26,40),(769,141,24,25),(770,141,1,5),(771,142,26,40),(772,142,24,40),(773,142,1,5),(774,143,15,20),(775,143,24,5),(776,143,1,5),(777,144,21,20),(778,144,24,1),(779,144,1,1),(780,145,22,40),(781,145,24,1),(782,145,1,1),(783,146,26,40),(784,146,24,3),(785,146,1,1),(786,147,24,20),(787,147,24,1),(788,147,1,20),(789,148,26,20),(790,148,24,2),(791,148,1,1),(792,149,15,5),(793,149,24,1),(794,149,1,1),(795,150,15,40),(796,150,24,5),(797,150,1,1),(798,151,22,5),(799,151,24,1),(800,151,1,1),(801,152,15,20),(802,152,24,10),(803,152,1,2),(804,153,21,15),(805,153,24,1),(806,153,1,1),(807,154,15,40),(808,154,24,2),(809,154,1,2),(810,155,27,40),(811,155,1,1),(812,155,1,1),(813,156,28,80),(814,156,24,20),(815,156,1,5),(816,157,24,2),(817,157,24,2),(818,157,1,1),(819,158,28,40),(820,158,24,10),(821,158,1,1),(822,160,1,9),(823,160,1,8),(824,160,1,7),(829,165,1,2),(830,165,1,2),(831,165,1,2),(832,166,1,23),(833,166,1,23),(834,166,1,2);
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
INSERT INTO `leaverecord` VALUES (14,3,1,'2015-04-01 00:00:00','2015-04-02 00:00:00','pending','','for cass'),(16,6,4,'2015-04-15 12:00:00','2015-04-17 12:00:00','Approved','okss','for exammm'),(17,10,1,'2015-04-17 09:00:00','2015-04-17 06:00:00','Declined','','Had to go to lahore.'),(18,7,1,'2015-03-25 09:00:00','2015-03-25 18:00:00','Approved','','I had to take my mother for a medical checkup'),(19,7,1,'2015-03-26 09:00:00','2015-03-25 18:00:00','Approved','','I had to take my mother for a medical checkup'),(20,7,4,'2015-05-01 09:00:00','2015-05-29 12:00:00','pending',NULL,'Exam preparation leaves'),(21,14,1,'2015-04-10 12:00:00','2015-04-13 12:00:00','Approved','','First leave'),(22,14,1,'2015-04-09 12:00:00','2015-04-13 12:00:00','Declined','','Leave first'),(23,14,1,'2015-04-09 12:00:00','2015-04-13 12:00:00','Declined','','Leave first'),(24,9,3,'2015-04-06 00:00:00','2015-04-06 00:00:00','Approved','','Not feeling well.'),(25,14,1,'2015-04-17 12:00:00','2015-04-17 12:00:00','pending',NULL,'m'),(26,14,1,'2015-04-17 12:00:00','2015-04-23 12:00:00','Declined','',''),(27,14,1,'2015-04-22 12:00:00','2015-04-29 12:00:00','Approved','','k'),(28,14,5,'2015-04-22 12:00:00','2015-04-29 12:00:00','Approved','','un'),(29,12,3,'2015-04-06 12:00:00','2015-04-06 12:00:00','Approved','',''),(30,12,3,'2015-04-09 12:00:00','2015-04-09 12:00:00','Approved','',''),(31,12,3,'2015-04-10 12:00:00','2015-04-10 12:00:00','Approved','',''),(32,12,3,'2015-04-13 12:00:00','2015-04-13 12:00:00','Approved','',''),(35,15,1,'2015-03-20 09:00:00','2015-03-20 09:00:00','Declined','','Had to go to Lahore.'),(36,15,1,'2015-03-20 09:00:00','2015-03-20 09:00:00','Declined','','Had to go to Lahore.'),(37,15,1,'2015-03-20 09:00:00','2015-03-20 09:00:00','Declined','',''),(39,15,1,'2015-03-20 12:00:00','2015-03-20 12:00:00','Declined','','Had to go to Lahore.'),(40,15,1,'2015-03-20 12:00:00','2015-03-20 12:00:00','Approved','','Had to go to Lahore.'),(41,15,1,'2015-03-20 12:00:00','2015-03-20 12:00:00','Declined','','Had to go to Lahore.'),(42,15,3,'2015-03-20 12:00:00','2015-03-20 12:00:00','Declined','','Had to go to Lahore.'),(43,2,1,'2015-04-15 12:00:00','2015-04-15 12:00:00','pending',NULL,''),(44,9,5,'2015-04-13 12:00:00','2015-04-30 12:00:00','Declined','','Due to some family issues.'),(45,20,1,'2015-04-21 00:00:00','2015-04-21 00:00:00','pending',NULL,'I have to do some chores.'),(46,20,1,'2015-04-21 00:00:00','2015-04-21 00:00:00','pending',NULL,'I have to do some chores.'),(47,20,1,'2015-04-21 00:00:00','2015-04-21 00:00:00','pending',NULL,'I have to do some chores.'),(48,20,1,'2015-04-21 12:00:00','2015-04-21 12:00:00','pending',NULL,'I have to do some chores.'),(49,17,1,'2015-04-23 12:00:00','2015-04-23 12:00:00','Approved','',''),(50,17,1,'2015-04-23 12:00:00','2015-04-23 12:00:00','Approved','',''),(51,17,1,'2015-04-23 12:00:00','2015-04-23 12:00:00','Approved','',''),(52,11,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','pending',NULL,''),(53,11,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','pending',NULL,'Preparation for ACCA papers: P4 and P7'),(54,11,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','pending',NULL,'Preparation for ACCA papers: P4 and P7'),(55,11,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','pending',NULL,''),(56,11,3,'2015-03-16 12:00:00','2015-03-17 12:00:00','pending',NULL,''),(57,11,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','pending',NULL,''),(58,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,''),(59,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,'Test'),(60,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,'Test'),(61,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,'Test'),(62,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,'Test'),(63,11,4,'2015-04-13 12:00:00','2015-04-14 12:00:00','pending',NULL,'Test'),(64,5,4,'2015-04-22 12:00:00','2015-04-25 12:00:00','Declined','',''),(65,15,1,'2015-03-20 12:00:00','2015-03-20 12:00:00','Approved','','Had to go to Lahore.'),(66,18,1,'2015-03-25 12:00:00','2015-03-27 12:00:00','Approved','','Had to take my parents for medical checkup'),(67,18,4,'2015-05-11 12:00:00','2015-06-05 12:00:00','Approved','','I have to prepare for my exams.'),(68,8,1,'2015-04-29 12:00:00','2015-04-29 12:00:00','Approved','','For mother\'s medical check-up'),(69,17,1,'2015-04-24 12:00:00','2015-04-24 12:00:00','Approved','',''),(70,5,4,'2015-04-13 12:00:00','2015-04-24 12:00:00','Approved','',''),(71,5,3,'2015-03-04 12:00:00','2015-03-13 12:00:00','Approved','',''),(72,18,1,'2015-05-05 12:00:00','2015-05-05 12:00:00','Approved','','Had to accompany mother to medical tests'),(73,8,4,'2015-05-11 12:00:00','2015-05-15 12:00:00','Approved','','exam leave'),(74,8,4,'2015-05-25 12:00:00','2015-06-12 12:00:00','Approved','','exam leave'),(75,15,3,'2015-05-08 12:00:00','2015-05-08 12:00:00','Approved','',''),(76,15,4,'2015-05-11 12:00:00','2015-06-05 12:00:00','Approved','',''),(77,17,3,'2015-05-07 12:00:00','2015-05-09 12:00:00','Approved','',''),(78,17,4,'2015-05-19 12:00:00','2015-06-01 12:00:00','Approved','Please also put in the leave of 2nd as casual',''),(79,17,3,'2015-06-03 12:00:00','2015-06-05 12:00:00','Approved','Get well soon :)',''),(80,5,4,'2015-05-18 12:00:00','2015-05-29 12:00:00','Approved','',''),(82,18,3,'2015-06-10 12:00:00','2015-06-10 12:00:00','Approved','','Pain in legs'),(84,15,3,'2015-06-26 12:00:00','2015-06-26 12:00:00','Approved','',''),(86,8,1,'2015-07-23 12:00:00','2015-07-24 12:00:00','Approved','',''),(88,5,5,'2016-04-08 00:00:00','2016-04-08 00:00:00','Declined','',''),(89,5,2,'2016-04-07 00:00:00','2016-04-07 00:00:00','Approved','',''),(90,5,2,'2016-04-18 00:00:00','2016-05-03 00:00:00','Approved','',''),(91,15,3,'2016-05-09 00:00:00','2016-05-10 00:00:00','Approved','','illness.'),(92,15,2,'2016-05-23 00:00:00','2016-06-09 00:00:00','Approved','','For examination purposes.'),(93,22,4,'2016-05-20 00:00:00','2016-06-12 00:00:00','Approved','','Exams leaves'),(94,15,1,'2016-05-20 00:00:00','2016-05-20 00:00:00','Approved','','Have to attend friends fathers funeral. '),(95,5,2,'2016-06-15 00:00:00','2016-06-15 00:00:00','Approved','',''),(96,8,2,'2016-05-20 00:00:00','2016-06-09 00:00:00','Approved','',''),(97,8,2,'2016-06-13 00:00:00','2016-06-16 00:00:00','Approved','',''),(98,22,4,'2016-06-13 00:00:00','2016-06-15 00:00:00','Approved','',''),(99,8,3,'2016-07-25 00:00:00','2016-07-25 00:00:00','Approved','',''),(100,5,2,'2016-07-11 00:00:00','2016-07-15 00:00:00','Approved','',''),(101,5,2,'2016-07-28 00:00:00','2016-07-28 00:00:00','Approved','',''),(102,5,1,'2016-08-15 00:00:00','2016-08-15 00:00:00','Approved','',''),(103,5,1,'2016-08-11 00:00:00','2016-08-11 00:00:00','Approved','',''),(104,5,1,'2016-08-02 00:00:00','2016-08-02 00:00:00','Approved','',''),(105,5,1,'2016-08-10 00:00:00','2016-08-10 00:00:00','Approved','',''),(106,8,3,'2016-09-30 00:00:00','2016-09-30 00:00:00','Approved','',''),(107,5,1,'2016-09-27 00:00:00','2016-09-29 00:00:00','Approved','',''),(108,22,3,'2016-10-10 00:00:00','2016-10-10 00:00:00','Approved','',''),(109,21,1,'2016-12-16 00:00:00','2016-12-16 00:00:00','Approved','',''),(110,15,3,'2016-12-26 00:00:00','2016-12-26 00:00:00','Approved','','Have to go to shifa hospital for some tests.'),(111,26,1,'2016-12-02 00:00:00','2016-12-02 00:00:00','Approved','','went to Lahore with family'),(112,26,3,'2016-12-08 00:00:00','2016-12-09 00:00:00','Approved','','food poisoning'),(113,8,1,'2016-11-27 02:00:00','2016-12-01 02:00:00','Approved','',''),(114,22,3,'2017-01-18 00:00:00','2017-01-18 00:00:00','Approved','','Due to fever'),(115,15,3,'2017-01-30 00:00:00','2017-01-30 00:00:00','Approved','','Flu and fever.'),(116,8,1,'2017-02-03 00:00:00','2017-02-03 00:00:00','Approved','','High fever'),(117,22,1,'2017-02-06 00:00:00','2017-02-06 00:00:00','Approved','','death of my friend'),(118,22,1,'2017-02-07 00:00:00','2017-02-09 00:00:00','Approved','','Engagement'),(119,15,3,NULL,NULL,'pending',NULL,''),(120,3,1,'2018-04-24 00:00:00','2018-04-26 00:00:00','pending',NULL,'need to spend some time with family'),(121,27,1,'2018-04-24 00:00:00','2018-04-25 00:00:00','Approved','','leave request');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineofservice`
--

LOCK TABLES `lineofservice` WRITE;
/*!40000 ALTER TABLE `lineofservice` DISABLE KEYS */;
INSERT INTO `lineofservice` VALUES (1,'Advisory'),(2,'Assurance'),(3,'Outsourcing'),(4,'Technology'),(5,'Internal Business Development'),(6,'Learning & Development'),(7,'Software');
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
INSERT INTO `phases` VALUES (1,'p',2,'2015-06-03 12:00:00','2015-06-03 12:00:00','2015-06-03 12:00:00'),(2,'p1',2,'2015-06-30 12:00:00','2015-06-30 12:00:00','2015-06-30 12:00:00'),(4,'p3',3,'2015-07-14 12:00:00','2015-07-15 12:00:00','2015-07-16 12:00:00'),(5,'p4',3,'2015-07-21 12:00:00','2015-07-22 12:00:00','2015-07-23 12:00:00'),(7,'pa',4,'2015-07-08 12:00:00','2015-07-15 12:00:00','2015-07-22 12:00:00'),(9,'p',5,'2015-07-07 12:00:00','2015-07-07 12:00:00','2015-07-07 12:00:00'),(10,'p',5,'2015-07-14 12:00:00','2015-07-15 12:00:00','2015-07-16 12:00:00'),(11,'phasesup',6,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(12,'j2',6,'2015-07-09 12:00:00','2015-07-09 12:00:00','2015-07-09 12:00:00'),(13,'jl',13,'2015-07-14 12:00:00','2015-07-15 12:00:00','2015-07-16 12:00:00'),(14,'Phh',14,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(15,'Phh',15,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(16,'Phh',16,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(17,'Phh',17,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(18,'ph222',18,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(19,'ll',19,'2015-07-16 12:00:00','2015-07-17 12:00:00','2015-07-18 12:00:00'),(20,'Draft',20,'2015-07-16 12:00:00','2015-07-16 12:00:00','2015-07-16 12:00:00'),(21,'lkk',21,'2015-07-28 12:00:00','2015-07-29 12:00:00','2015-07-30 12:00:00'),(22,'pp',22,'2015-07-29 12:00:00','2015-07-30 12:00:00','2015-07-31 12:00:00'),(23,'ph',23,'2015-07-21 12:00:00','2015-07-22 12:00:00','2015-07-23 12:00:00'),(24,'k',31,'2015-07-23 12:00:00','2015-07-24 12:00:00','2015-07-25 12:00:00'),(25,'Business Plan Submission',33,'2015-07-01 12:00:00','2015-08-14 12:00:00','2015-08-12 12:00:00'),(26,'Business Plan Submission',32,'2015-07-01 12:00:00','2015-08-14 12:00:00','2015-08-12 12:00:00'),(28,'Phase iii Research',34,'2015-07-27 12:00:00','2015-08-10 12:00:00','2015-08-07 12:00:00'),(29,'Draft BP',35,'2015-07-07 12:00:00','2015-08-15 12:00:00','2015-08-14 12:00:00'),(30,'Final Draft',35,'2015-07-16 12:00:00','2015-08-20 12:00:00','2015-08-19 12:00:00'),(31,'Draft BP',36,'2015-07-07 12:00:00','2015-08-15 12:00:00','2015-08-14 12:00:00'),(32,'Final Draft',36,'2015-07-16 12:00:00','2015-08-20 12:00:00','2015-08-19 12:00:00'),(33,'Pitch',36,'2015-07-20 12:00:00','2015-08-22 12:00:00','2015-08-22 12:00:00'),(34,'SOP',36,'2015-08-22 12:00:00','2015-08-24 12:00:00','2015-08-24 12:00:00'),(35,'Draft BP',37,'2015-07-07 12:00:00','2015-08-15 12:00:00','2015-08-14 12:00:00'),(36,'Final Draft',37,'2015-07-16 12:00:00','2015-08-20 12:00:00','2015-08-19 12:00:00'),(37,'Pitch',37,'2015-07-20 12:00:00','2015-08-22 12:00:00','2015-08-22 12:00:00'),(38,'SOP',37,'2015-08-22 12:00:00','2015-08-24 12:00:00','2015-08-24 12:00:00'),(39,'Draft BP',38,'2015-07-07 12:00:00','2015-08-15 12:00:00','2015-08-14 12:00:00'),(40,'Final Draft',38,'2015-07-16 12:00:00','2015-08-20 12:00:00','2015-08-19 12:00:00'),(41,'Pitch',38,'2015-07-20 12:00:00','2015-08-22 12:00:00','2015-08-22 12:00:00'),(42,'SOP',38,'2015-08-22 12:00:00','2015-08-24 12:00:00','2015-08-24 12:00:00'),(43,'Audit',85,'2016-04-01 00:00:00','2016-04-15 00:00:00','2016-04-13 00:00:00'),(44,'Audit',86,'2016-04-01 00:00:00','2016-04-15 00:00:00','2016-04-13 00:00:00'),(45,'Accounting',86,'2016-04-01 00:00:00','2016-12-31 00:00:00','2016-12-31 00:00:00'),(47,'Accounting',87,'2016-04-01 00:00:00','2016-12-31 00:00:00','2016-12-31 00:00:00'),(50,'Business Plan update changes',88,'2016-04-18 00:00:00','2016-04-21 00:00:00','2016-03-21 00:00:00'),(51,'Audit',89,'2016-04-01 00:00:00','2016-04-15 00:00:00','2016-04-13 00:00:00'),(52,'Accounting',89,'2016-04-01 00:00:00','2016-12-31 00:00:00','2016-12-31 00:00:00'),(53,'Business Plan update changes',89,'2016-04-18 00:00:00','2016-04-21 00:00:00','2016-03-21 00:00:00'),(54,'Audit',90,'2016-04-01 00:00:00','2016-04-15 00:00:00','2016-04-13 00:00:00'),(55,'Accounting',90,'2016-04-01 00:00:00','2016-12-31 00:00:00','2016-12-31 00:00:00'),(56,'Business Plan update changes',90,'2016-04-18 00:00:00','2016-04-21 00:00:00','2016-03-21 00:00:00'),(57,'April 1st Half',90,'2016-04-18 00:00:00','2016-04-20 00:00:00','2016-04-20 00:00:00'),(62,'April 1st Half',91,'2016-04-18 00:00:00','2016-04-20 00:00:00','2016-04-20 00:00:00'),(63,'Development',92,'2016-04-06 00:00:00','2016-04-15 00:00:00','2016-04-14 00:00:00'),(64,'Development',93,'2016-04-06 00:00:00','2016-04-15 00:00:00','2016-04-14 00:00:00'),(65,'FINANCIAL QUOTE',93,'2016-04-07 00:00:00','2016-04-15 00:00:00','2016-04-15 00:00:00'),(66,'Development',94,'2016-04-06 00:00:00','2016-04-15 00:00:00','2016-04-14 00:00:00'),(67,'FINANCIAL QUOTE',94,'2016-04-07 00:00:00','2016-04-15 00:00:00','2016-04-15 00:00:00'),(68,'Videos editing',95,'2016-06-20 00:00:00','2016-06-22 00:00:00','2016-06-22 00:00:00'),(69,'Videos editing',96,'2016-06-20 00:00:00','2016-06-22 00:00:00','2016-06-22 00:00:00'),(70,'Research report',96,'2016-06-21 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(71,'VAT Research',97,'2016-06-30 00:00:00','2016-06-30 00:00:00','2016-06-05 00:00:00'),(72,'Research report',97,'2016-06-21 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(73,'INGO List',97,'2016-06-20 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(74,'Videos editing',98,'2016-06-20 00:00:00','2016-06-22 00:00:00','2016-06-22 00:00:00'),(75,'Research report',98,'2016-06-21 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(76,'INGO List',98,'2016-06-20 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(77,'Report',98,'2016-06-22 00:00:00','2016-06-29 00:00:00','2016-06-28 00:00:00'),(78,'Videos editing',99,'2016-06-20 00:00:00','2016-06-22 00:00:00','2016-06-22 00:00:00'),(79,'Research report',99,'2016-06-21 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(80,'INGO List',99,'2016-06-20 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(81,'Report',99,'2016-06-22 00:00:00','2016-06-29 00:00:00','2016-06-28 00:00:00'),(82,'QA Framework',99,'2016-06-20 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(83,'Videos editing',100,'2016-06-20 00:00:00','2016-06-22 00:00:00','2016-06-22 00:00:00'),(84,'Research report',100,'2016-06-21 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(85,'INGO List',100,'2016-06-20 00:00:00','2016-06-23 00:00:00','2016-06-23 00:00:00'),(86,'Report',100,'2016-06-22 00:00:00','2016-06-29 00:00:00','2016-06-28 00:00:00'),(87,'QA Framework',100,'2016-06-20 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(88,'Business Plan',101,'2016-06-25 00:00:00','2016-06-30 00:00:00','2016-07-04 00:00:00'),(89,'Proposal & Review',102,'2016-07-01 00:00:00','2016-07-30 00:00:00','2016-07-30 00:00:00'),(92,'Proposals',104,'2016-07-11 00:00:00','2016-07-16 00:00:00','2016-07-16 00:00:00'),(93,'Proposal MMG',104,'2016-06-07 00:00:00','2016-07-13 00:00:00','2016-07-13 00:00:00'),(94,'Proposal',105,'2016-07-11 00:00:00','2016-07-16 00:00:00','2016-07-16 00:00:00'),(95,'Training matrerial',106,'2016-07-11 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(96,'Training matrerial',107,'2016-07-11 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(97,'Meetings',107,'2016-07-19 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(98,'Training matrerial',108,'2016-07-11 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(99,'Meetings',108,'2016-07-19 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(100,'Strategy',108,'2016-07-12 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(101,'Training matrerial',109,'2016-07-11 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(102,'Meetings',109,'2016-07-19 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(103,'Strategy',109,'2016-07-12 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(104,'NGOs\'',109,'2016-07-12 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(105,'Training matrerial',110,'2016-07-11 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(106,'Meetings',110,'2016-07-19 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(107,'Strategy',110,'2016-07-12 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(108,'NGOs\'',110,'2016-07-12 00:00:00','2016-07-29 00:00:00','2016-07-29 00:00:00'),(109,'Honda meeting',110,'2016-07-12 00:00:00','2016-07-13 00:00:00','2016-07-13 00:00:00'),(110,'Research',111,'2016-07-06 00:00:00','2016-07-12 00:00:00','2016-07-12 00:00:00'),(111,'RESEARCH',112,'2016-07-15 00:00:00','2016-07-18 00:00:00','2016-07-18 00:00:00'),(112,'RESEARCH',113,'2016-07-15 00:00:00','2016-07-18 00:00:00','2016-07-18 00:00:00'),(113,'Model strucutre',113,'2016-07-18 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(114,'RESEARCH',114,'2016-07-15 00:00:00','2016-07-18 00:00:00','2016-07-18 00:00:00'),(115,'Model strucutre',114,'2016-07-18 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(116,'Analysis and conclusion',114,'2016-07-18 00:00:00','2016-07-22 00:00:00','2016-07-22 00:00:00'),(117,'Launch',103,'2016-07-01 00:00:00','2016-07-15 00:00:00','2016-07-15 00:00:00'),(118,'Accounting',115,'2016-07-23 00:00:00','2016-07-20 00:00:00','2016-07-20 00:00:00'),(119,'Accounting',116,'2016-07-23 00:00:00','2016-07-20 00:00:00','2016-07-20 00:00:00'),(120,'Research',116,'2016-07-19 00:00:00','2016-07-31 00:00:00','2016-07-31 00:00:00'),(121,'Diagnosis',117,'2016-10-10 00:00:00','2016-10-27 00:00:00','2016-10-27 00:00:00'),(122,'Conversion',118,'2016-11-01 00:00:00','2017-01-19 00:00:00','2017-01-19 00:00:00'),(123,'Conversion',119,'2016-11-01 00:00:00','2017-01-19 00:00:00','2017-01-19 00:00:00'),(124,'Video Making IFRS 15',119,'2016-12-15 00:00:00','2017-02-17 00:00:00','2017-02-17 00:00:00'),(125,'Conversion',120,'2016-12-23 00:00:00','2017-01-19 00:00:00','2017-01-19 00:00:00'),(126,'Video Making IFRS 15',120,'2016-12-15 00:00:00','2016-12-26 00:00:00','2016-12-23 00:00:00'),(127,'Portfolio',120,'2016-12-23 00:00:00','2017-01-06 00:00:00','2017-01-06 00:00:00'),(128,'Company Incorporation',121,'2016-12-21 00:00:00','2017-02-15 00:00:00','2017-02-15 00:00:00'),(129,'Accounts Finalization',122,'2016-12-20 00:00:00','2016-12-23 00:00:00','2016-12-23 00:00:00'),(131,'NOA',123,'2017-02-27 00:00:00','2017-03-10 00:00:00','2017-03-10 00:00:00'),(132,'Accounts Finalization',124,'2016-12-20 00:00:00','2017-02-15 00:00:00','2017-02-15 00:00:00'),(135,'phase',125,'2016-12-26 00:00:00','2016-12-26 00:00:00','2016-12-26 00:00:00'),(136,'Start',126,'2016-12-26 00:00:00','2016-12-26 00:00:00','2016-12-26 00:00:00'),(137,'Review',127,'2017-01-18 00:00:00','2017-01-19 00:00:00','2017-01-19 00:00:00'),(138,'Review',128,'2017-01-18 00:00:00','2017-01-19 00:00:00','2017-01-19 00:00:00'),(139,'Review',128,'2017-01-18 00:00:00','2017-02-06 00:00:00','2017-02-06 00:00:00'),(140,'VAT',129,'2017-01-19 00:00:00','2017-01-20 00:00:00','2017-01-20 00:00:00'),(141,'VAT',130,'2017-01-19 00:00:00','2017-02-22 00:00:00','2017-02-22 00:00:00'),(145,'Stocks',131,'2017-01-19 00:00:00','2017-02-07 00:00:00','2017-02-07 00:00:00'),(146,'VAT',132,'2017-01-19 00:00:00','2017-02-16 00:00:00','2017-02-16 00:00:00'),(150,'Dec',133,'2017-01-26 00:00:00','2017-02-03 00:00:00','2017-02-02 00:00:00'),(151,'d',134,'2017-01-25 00:00:00','2017-02-22 00:00:00','2017-02-22 00:00:00'),(152,'d',135,'2017-01-25 00:00:00','2017-02-09 00:00:00','2017-02-09 00:00:00'),(156,'d',138,'2017-02-01 00:00:00','2017-02-17 00:00:00','2017-02-17 00:00:00'),(157,'d',136,'2017-01-31 00:00:00','2017-02-17 00:00:00','2017-02-17 00:00:00'),(158,'',137,'2017-01-30 00:00:00','2017-02-24 00:00:00','2017-02-24 00:00:00'),(159,'',139,'2017-02-13 00:00:00','2017-02-16 00:00:00','2017-02-16 00:00:00'),(160,'',140,'2017-02-14 00:00:00','2017-02-24 00:00:00','2017-02-24 00:00:00'),(161,'',141,'2017-02-14 00:00:00','2017-02-24 00:00:00','2017-02-24 00:00:00'),(162,'Start',142,'2017-02-14 00:00:00','2017-02-24 00:00:00','2017-02-24 00:00:00'),(163,'Start',143,'2017-03-22 00:00:00','2017-04-07 00:00:00','2017-04-07 00:00:00'),(164,'Start',144,'2017-03-22 00:00:00','2017-03-29 00:00:00','2017-03-29 00:00:00'),(166,'Start',146,'2017-03-22 00:00:00','2017-03-30 00:00:00','2017-03-30 00:00:00'),(167,'Start',147,'2017-03-27 00:00:00','2017-03-31 00:00:00','2017-03-31 00:00:00'),(168,'Final',148,'2017-03-22 00:00:00','2017-03-27 00:00:00','2017-03-27 00:00:00'),(169,'Start',149,'2017-03-22 00:00:00','2017-03-22 00:00:00','2017-03-24 00:00:00'),(170,'Start ',150,'2017-03-27 00:00:00','2017-04-05 00:00:00','2017-04-05 00:00:00'),(171,'Start',151,'2017-03-31 00:00:00','2017-03-31 00:00:00','2017-03-31 00:00:00'),(172,'Beginning',152,'2017-04-03 00:00:00','2017-04-14 00:00:00','2017-04-07 00:00:00'),(173,'Completion',153,'2017-03-27 00:00:00','2017-04-03 00:00:00','2017-04-03 00:00:00'),(174,'Start',154,'2017-04-05 00:00:00','2017-04-14 00:00:00','2017-04-14 00:00:00'),(175,'Start',155,'2017-03-20 00:00:00','2017-04-28 00:00:00','2017-04-28 00:00:00'),(176,'Start',156,'2017-04-10 00:00:00','2017-04-21 00:00:00','2017-04-21 00:00:00'),(177,'Start',157,'2017-03-30 00:00:00','2017-04-03 00:00:00','2017-04-03 00:00:00'),(178,'Completion',158,'2017-03-14 00:00:00','2017-04-06 00:00:00','2017-04-06 00:00:00'),(179,'phas',160,'2018-11-29 00:00:00','2018-11-21 00:00:00','2018-11-14 00:00:00'),(180,'asd',165,'2018-11-29 00:00:00','2018-12-07 00:00:00','2018-11-21 00:00:00'),(181,'ha',166,'2018-11-15 00:00:00','2018-11-01 00:00:00','2018-11-30 00:00:00');
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
INSERT INTO `roles` VALUES (1,'Trainee Business Analyst',500),(2,'Business Analyst',750),(3,'Senior Business Analyst',1500),(4,'Manager',3500),(5,'Principal Consultant',7500);
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
) ENGINE=InnoDB AUTO_INCREMENT=1213 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (149,20,1,0,6,1,0,0),(150,20,1,1,4,1,0,0),(151,20,1,2,3,1,0,0),(152,20,1,3,4,1,0,0),(153,20,1,4,9,1,0,0),(154,20,1,5,8,1,0,0),(155,20,1,6,2,1,0,0),(156,20,1,7,2,1,0,0),(157,20,1,8,5,1,0,0),(158,20,1,9,12,1,0,0),(159,20,1,10,1,1,0,0),(160,20,1,11,2,1,0,0),(161,20,1,12,1,1,0,0),(162,20,1,13,1,1,0,0),(163,85,4,1,5,8,0,0),(164,41,4,1,0,1,0,0),(165,41,4,2,0,1,0,0),(166,41,4,3,0,1,0,0),(167,41,4,5,2,1,0,0),(168,41,4,6,8,1,0,0),(169,41,4,7,5,1,0,0),(170,41,4,8,2,1,0,0),(171,41,4,9,0,1,0,0),(172,41,4,10,0,1,0,0),(173,41,4,11,5,1,0,0),(174,41,4,12,8,1,0,0),(175,41,4,13,3,1,0,0),(176,43,4,4,4,1,0,0),(177,43,4,5,4,1,0,0),(178,43,4,7,3,1,0,0),(179,43,4,11,1,1,0,0),(180,43,4,13,2,1,0,0),(181,43,4,14,7,1,0,0),(182,83,4,4,3,1,0,0),(183,83,4,5,2,1,0,0),(184,85,4,4,1,1,0,0),(185,85,4,8,2,1,0,0),(186,85,4,11,2,1,0,0),(187,85,4,13,2,1,0,0),(188,85,4,14,1,1,0,0),(189,92,4,8,2,1,0,0),(190,93,4,13,1,1,0,0),(227,41,4,6,8,22,0,0),(228,41,4,7,8,22,0,0),(229,41,4,8,8,22,0,0),(230,43,4,9,0,22,0,0),(231,43,4,10,0,22,0,0),(232,87,4,4,8,22,0,0),(233,87,4,5,8,22,0,0),(234,91,4,1,8,22,0,0),(235,91,4,2,0,22,0,0),(236,91,4,3,0,22,0,0),(237,92,4,11,8,22,0,0),(238,92,4,12,8,22,0,0),(239,92,4,13,8,22,0,0),(240,92,4,14,8,22,0,0),(241,92,4,15,8,22,0,0),(242,92,4,16,0,22,0,0),(243,92,4,17,0,22,0,0),(244,92,4,18,8,22,0,0),(266,85,4,1,7,21,0,0),(267,85,4,4,8,21,0,0),(268,85,4,5,8,21,0,0),(269,85,4,6,8,21,0,0),(270,85,4,7,8,21,0,0),(271,85,4,8,7,21,0,0),(272,85,4,11,8,21,0,0),(273,85,4,12,8,21,0,0),(274,85,4,13,8,21,0,0),(275,85,4,14,8,21,0,0),(276,85,4,15,7,21,0,0),(277,85,4,18,8,21,0,0),(278,85,4,19,8,21,0,0),(279,85,4,20,8,21,0,0),(280,85,4,21,8,21,0,0),(281,85,4,22,7,21,0,0),(282,85,4,25,8,21,0,0),(283,85,4,26,8,21,0,0),(284,85,4,27,8,21,0,0),(285,85,4,28,8,21,0,0),(286,85,4,29,7,21,0,0),(289,85,5,2,8,21,0,0),(290,85,5,3,8,21,0,0),(291,85,5,4,8,21,0,0),(292,85,5,5,8,21,0,0),(293,85,5,6,7,21,0,0),(294,85,5,9,8,21,0,0),(295,85,5,10,8,21,0,0),(296,85,5,11,8,21,0,0),(297,85,5,12,8,21,0,0),(298,85,5,13,7,21,0,0),(299,85,5,16,8,21,0,0),(300,85,5,17,8,21,0,0),(301,85,5,18,8,21,0,0),(302,85,5,19,8,21,0,0),(303,85,5,20,7,21,0,0),(304,85,5,23,8,21,0,0),(305,85,5,24,8,21,0,0),(306,85,5,25,8,21,0,0),(307,85,5,26,8,21,0,0),(308,85,5,27,7,21,0,0),(309,85,5,30,8,21,0,0),(310,85,5,31,8,21,0,0),(355,92,4,14,8,15,0,0),(356,92,4,15,8,15,0,0),(357,92,4,18,8,15,0,0),(358,92,4,19,8,15,0,0),(359,92,4,20,8,15,0,0),(360,92,4,21,8,15,0,0),(361,92,4,22,8,15,0,0),(362,92,4,25,8,15,0,0),(363,92,4,26,8,15,0,0),(364,92,4,27,8,15,0,0),(365,92,4,28,8,15,0,0),(366,92,4,29,8,15,0,0),(367,43,5,2,8,15,0,0),(368,43,5,3,8,15,0,0),(369,43,5,4,8,15,0,0),(370,43,5,5,8,15,0,0),(371,43,5,6,8,15,0,0),(372,43,5,9,8,15,0,0),(373,43,5,10,8,15,0,0),(374,43,5,11,8,15,0,0),(375,43,5,12,8,15,0,0),(376,43,5,13,8,15,0,0),(377,43,5,16,8,15,0,0),(378,43,5,17,8,15,0,0),(379,43,5,18,8,15,0,0),(380,43,5,19,8,15,0,0),(381,43,5,20,8,15,0,0),(419,97,5,20,0,22,0,0),(420,97,5,21,0,22,0,0),(421,97,5,22,0,22,0,0),(422,97,5,23,0,22,0,0),(423,97,5,24,0,22,0,0),(424,97,5,25,0,22,0,0),(425,97,5,26,0,22,0,0),(426,97,5,27,0,22,0,0),(427,97,5,28,0,22,0,0),(428,97,5,29,0,22,0,0),(429,97,5,30,0,22,0,0),(430,97,5,31,0,22,0,0),(431,42,5,13,5,22,0,0),(432,42,5,14,5,22,0,0),(433,42,5,15,5,22,0,0),(434,42,5,16,5,22,0,0),(435,42,5,17,0,22,0,0),(436,42,5,18,5,22,0,0),(437,87,5,18,8,22,0,0),(438,87,5,19,8,22,0,0),(439,92,5,1,0,22,0,0),(440,92,5,2,8,22,0,0),(441,92,5,3,8,22,0,0),(442,92,5,4,8,22,0,0),(443,92,5,5,8,22,0,0),(444,92,5,6,8,22,0,0),(445,92,5,7,0,22,0,0),(446,92,5,8,0,22,0,0),(447,92,5,9,8,22,0,0),(448,92,5,10,8,22,0,0),(449,92,5,11,8,22,0,0),(450,92,5,12,8,22,0,0),(451,92,5,13,8,22,0,0),(452,92,5,14,0,22,0,0),(453,92,5,15,0,22,0,0),(454,92,5,16,8,22,0,0),(455,92,5,17,8,22,0,0),(497,97,6,20,5,22,0,0),(498,97,6,21,5,22,0,0),(499,97,6,22,5,22,0,0),(500,97,6,23,5,22,0,0),(501,97,6,24,0,22,0,0),(502,97,6,25,5,22,0,0),(503,97,6,26,0,22,0,0),(504,97,6,27,5,22,0,0),(505,97,6,28,5,22,0,0),(506,97,6,29,5,22,0,0),(507,97,6,30,5,22,0,0),(860,41,6,6,3,1,0,0),(861,41,6,7,3,1,0,0),(862,41,6,15,1,1,0,0),(863,41,6,20,2,1,0,0),(864,41,6,21,5,1,0,0),(865,41,6,22,2,1,0,0),(866,41,6,25,100,1,0,0),(867,42,6,3,0,1,0,0),(868,42,6,5,0,1,0,0),(869,42,6,10,0,1,0,0),(870,42,6,12,0,1,0,0),(871,42,6,17,0,1,0,0),(872,42,6,19,0,1,0,0),(873,42,6,24,0,1,0,0),(874,42,6,26,0,1,0,0),(875,42,6,29,0,1,0,0),(876,43,6,1,5,1,0,0),(877,43,6,2,5,1,0,0),(878,43,6,4,5,1,0,0),(879,43,6,6,2,1,0,0),(880,43,6,18,5,1,0,0),(881,43,6,20,3,1,0,0),(882,43,6,22,3,1,0,0),(883,43,6,23,1,1,0,0),(884,43,6,30,3,1,0,0),(885,85,6,7,2,1,0,0),(886,85,6,8,1,1,0,0),(887,85,6,9,5,1,0,0),(888,88,6,13,1,1,0,0),(889,98,6,23,2,1,0,0),(890,98,6,27,3,1,0,0),(891,98,6,28,3,1,0,0),(892,99,6,15,2,1,0,0),(893,99,6,16,2,1,0,0),(894,101,6,27,2,1,0,0),(895,101,6,30,2,1,0,0),(896,102,6,23,2,1,0,0),(897,102,6,25,5,1,0,0),(898,102,6,28,3,1,0,0),(899,103,6,11,2,1,0,0),(900,103,6,13,2,1,0,0),(901,103,6,14,2,1,0,0),(902,104,6,10,4,1,0,0),(903,104,6,11,3,1,0,0),(904,104,6,13,2,1,0,0),(905,105,6,14,3,1,0,0),(906,105,6,15,3,1,0,0),(907,105,6,16,1,1,0,0),(932,41,6,2,5,8,0,0),(933,41,6,4,5,8,0,0),(934,41,6,5,5,8,0,0),(935,41,6,6,5,8,0,0),(936,41,6,7,5,8,0,0),(937,41,6,9,5,8,0,0),(938,41,6,11,5,8,0,0),(939,41,6,12,5,8,0,0),(940,41,6,13,5,8,0,0),(941,41,6,14,5,8,0,0),(942,41,6,16,5,8,0,0),(943,41,6,20,5,8,0,0),(944,41,6,21,5,8,0,0),(945,42,6,18,5,8,0,0),(946,43,6,21,5,8,0,0),(947,43,6,22,5,8,0,0),(948,95,6,22,5,8,0,0),(949,95,6,23,5,8,0,0),(950,95,6,29,3,8,0,0),(951,98,6,24,5,8,0,0),(952,98,6,25,5,8,0,0),(953,98,6,26,5,8,0,0),(954,98,6,27,5,8,0,0),(955,98,6,28,5,8,0,0),(956,91,6,13,5,15,0,0),(957,91,6,14,5,15,0,0),(958,91,6,15,5,15,0,0),(959,91,6,16,5,15,0,0),(960,96,6,22,5,15,0,0),(961,96,6,23,5,15,0,0),(962,96,6,27,5,15,0,0),(963,96,6,28,5,15,0,0),(964,96,6,29,5,15,0,0),(987,85,6,1,5,21,0,0),(988,85,6,2,5,21,0,0),(989,85,6,4,5,21,0,0),(990,85,6,6,5,21,0,0),(991,85,6,7,5,21,0,0),(992,85,6,8,5,21,0,0),(993,85,6,9,5,21,0,0),(994,85,6,11,5,21,0,0),(995,85,6,13,5,21,0,0),(996,96,6,14,5,21,0,0),(997,96,6,15,5,21,0,0),(998,96,6,16,5,21,0,0),(999,96,6,18,5,21,0,0),(1000,102,6,20,5,21,0,0),(1001,102,6,21,5,21,0,0),(1002,102,6,22,5,21,0,0),(1003,102,6,23,5,21,0,0),(1004,102,6,25,5,21,0,0),(1005,102,6,27,5,21,0,0),(1006,102,6,28,5,21,0,0),(1007,102,6,29,5,21,0,0),(1008,102,6,30,5,21,0,0),(1016,100,7,1,8,15,0,0),(1017,100,7,11,8,15,0,0),(1018,100,7,12,8,15,0,0),(1019,100,7,13,8,15,0,0),(1020,100,7,14,8,15,0,0),(1021,100,7,15,8,15,0,0),(1022,115,7,16,8,15,0,0),(1023,95,7,2,8,8,0,0),(1024,95,7,4,8,8,0,0),(1025,101,7,11,8,8,0,0),(1026,101,7,12,8,8,0,0),(1027,101,7,13,8,8,0,0),(1028,101,7,14,8,8,0,0),(1029,101,7,15,8,8,0,0),(1030,101,7,18,12,8,0,0),(1031,116,7,19,6,8,0,0),(1153,111,7,1,0,22,0,0),(1154,111,7,4,5,22,0,0),(1155,111,7,2,5,22,0,0),(1156,111,7,5,0,22,0,0),(1157,111,7,3,0,22,0,0),(1158,111,7,6,0,22,0,0),(1159,111,7,4,5,22,0,0),(1160,111,7,7,0,22,0,0),(1161,111,7,5,0,22,0,0),(1162,111,7,8,0,22,0,0),(1163,111,7,6,0,22,0,0),(1164,111,7,9,0,22,0,0),(1165,111,7,7,0,22,0,0),(1166,111,7,10,0,22,0,0),(1167,111,7,8,0,22,0,0),(1168,111,7,11,8,22,0,0),(1169,111,7,9,0,22,0,0),(1170,111,7,12,0,22,0,0),(1171,111,7,10,0,22,0,0),(1172,111,7,13,5,22,0,0),(1173,111,7,11,8,22,0,0),(1174,111,7,14,0,22,0,0),(1175,111,7,12,0,22,0,0),(1176,111,7,15,8,22,0,0),(1177,111,7,13,5,22,0,0),(1178,111,7,16,0,22,0,0),(1179,111,7,14,0,22,0,0),(1180,111,7,17,0,22,0,0),(1181,111,7,15,8,22,0,0),(1182,115,7,18,8,22,0,0),(1183,111,7,16,0,22,0,0),(1184,116,7,19,4,22,0,0),(1185,111,7,17,0,22,0,0),(1186,115,7,18,8,22,0,0),(1187,116,7,19,4,22,0,0),(1188,111,7,1,0,22,0,0),(1189,111,7,2,5,22,0,0),(1190,111,7,3,0,22,0,0),(1191,111,7,4,5,22,0,0),(1192,111,7,5,0,22,0,0),(1193,111,7,6,0,22,0,0),(1194,111,7,7,0,22,0,0),(1195,111,7,8,0,22,0,0),(1196,111,7,9,0,22,0,0),(1197,111,7,10,0,22,0,0),(1198,111,7,11,8,22,0,0),(1199,111,7,12,0,22,0,0),(1200,111,7,13,5,22,0,0),(1201,111,7,14,0,22,0,0),(1202,111,7,15,8,22,0,0),(1203,111,7,16,0,22,0,0),(1204,111,7,17,0,22,0,0),(1205,115,7,18,8,22,0,0),(1206,116,7,19,4,22,0,0),(1207,96,7,2,5,21,0,0),(1208,96,7,4,5,21,0,0),(1209,113,7,12,4,21,0,0),(1210,113,7,18,4,21,0,0),(1211,113,7,19,4,21,0,0),(1212,113,7,20,4,21,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'faheem','iunais12','mfaheempiracha@gmail.com',0,5,'1','1',5,'2015-01-09 00:00:00',1,'active'),(2,'khurram','khurram','khurram',20,5,'1','1',3,'2015-01-09 00:00:00',1,'inActive'),(3,'test','test','tt@tt.com',14,5,'1','1',3,'2015-01-09 00:00:00',1,'active'),(5,'sohaib','sohaib','sohaibnouman@gmail.com',20,1,'11','1',3,'2015-01-23 12:00:00',1,'active'),(6,'test1','test1','junaidp2@hotmail.com',13,4,'1','1',1,'2015-01-09 00:00:00',2,'active'),(7,'Rafey ','rafey','rafeyqidwai@gmail.com',20,1,'1','1',2,'2015-01-09 00:00:00',1,'inActive'),(8,'Arslan','arsalan','arsalanehsanisb@gmail.com',20,24,'1','5000595836',3,'2014-11-20 00:00:00',1,'active'),(9,'Fawad','fawad','accafawad@gmail.com',20,1,'1','1',1,'2015-01-30 12:00:00',1,'active'),(11,'sohaib','sohaib','sohaibnouman@gmail.com',20,1,'3','3',3,'2015-01-22 12:00:00',1,'inActive'),(12,'mujtaba','mujtaba','mujtabakhann@gmail.com',10,1,'1','1',1,'2015-01-09 00:00:00',1,'inActive'),(13,'t1','t1','juanidp2@hotmail.com',0,13,'0','0',5,'2015-04-09 12:24:40',3,'active'),(14,'t11','t11','juanidp@gmail.com',1,13,'11111','22',1,'2015-04-08 12:00:00',3,'active'),(15,'zain','zain','zainhamadani@gmail.com',20,24,'0518314598','55685000596344',2,'2015-03-20 00:00:00',1,'active'),(16,'mujtaba','mujtaba','mujtabakhann@gmail.com',10,1,'1','1',1,'2015-01-09 00:00:00',1,'inActive'),(17,'mujtaba','mujtaba','mujtabakhann@gmail.com',10,1,'1','1',2,'2015-01-09 00:00:00',1,'active'),(18,'Rafey ','rafey','rafeyqidwai@gmail.com',20,1,'1','1',2,'2015-01-09 00:00:00',1,'inActive'),(19,'Rafey ','rafey','rafeyqidwai@gmail.com',20,1,'1','1',2,'2015-01-09 00:00:00',1,'inActive'),(20,'Rafey ','rafey','rafeyqidwai@gmail.com',20,1,'1','1',2,'2015-01-09 00:00:00',1,'inActive'),(21,'abdul rafey','abdulrafey','abdurrafe7211@gmail.com',1,24,'898','5000698656',2,'2015-12-15 00:00:00',1,'active'),(22,'Kaleem','kaleem','kamichughtai@hotmail.com',20,1,'03358617759','5000693024',2,'2015-05-11 00:00:00',1,'active'),(23,'Nouman','nouman1290','nouman057@gmail.com',20,1,'03005771311','',1,'2016-04-04 00:00:00',1,'active'),(24,'Ezaz','EZAZ1','mezazsahibzada@gmail.com',0,1,'','',5,'2017-02-01 00:00:00',1,'active'),(25,'zohaib','zohaib','zohaib@gmail.com',1,1,'111','111',1,'2016-12-23 00:00:00',1,'inActive'),(26,'zohaib','zohaib1','zohaib1112@hotmail.com',1,24,'','5000793274',2,'2016-08-15 00:00:00',1,'active'),(27,'salman','salman','salman@email.com',1,1,'11111','1111',1,'2017-03-24 00:00:00',1,'active'),(28,'hassan','hassan12','hassanarif.isb@gmail.com',1,24,'11','11',3,'2017-03-24 00:00:00',1,'active');
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

-- Dump completed on 2018-11-30  8:39:08
