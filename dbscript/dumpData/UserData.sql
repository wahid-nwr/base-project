/*Data for the table `geolocation` */
insert  into `geolocation`(`componentId`,`name`,`code`,`status`,`geoType`,`description`,`geoNumber`,`address`,`location`,`officePhoneNumber`,`contactPerson`,`contactNumber`,`parentArea`) values (2,'Dhaka','1',1,5,NULL,'','','','','',NULL,NULL),(3,'Dhanmondi','DHA',1,10,NULL,'','','','','',NULL,2),(4,'Mohammadpur','D2',1,15,NULL,'','','','','',NULL,3),(6,'Kunipara ','G3',1,15,NULL,'','','','','',NULL,8),(8,'Gulshan','GUL',1,10,NULL,'','','','','',NULL,2),(22,'Badda','G4',1,15,NULL,'','','','','',NULL,8);

/*Data for functiontype*/
insert  into `functiontype`(`componentId`,`uniqueCode`,`typeId`,`typeName`) values (1,NULL,1,'Web Service'),(2,NULL,2,'Web tier'),(3,NULL,3,'Link');

/*Data for the table `functions` */
insert  into `functions`(`componentId`,`uniqueCode`,`functionName`,`functionId`,`typeId`,`displayName`) values (1,NULL,'View Lists',1,3,'View List'),(2,NULL,'View Beneficiary Info',2,3,'View Beneficiary Info'),(3,NULL,'View Household Info',3,3,'View Household Info'),(4,NULL,'View Work Schedules',4,3,'View Work Schedules'),(5,NULL,'View Reports',5,3,'View Reports'),(6,NULL,'Alerts',6,3,'Alerts'),(7,NULL,'Search',7,3,'Search'),(8,NULL,'Logout',8,3,'Logout'),(9,NULL,'Manage Questionnaire',9,3,'Manage Questionnaire'),(10,NULL,'Manage Algorithms',10,3,'Manage Algorithms'),(11,NULL,'Manage Alerts',11,3,'Manage Alerts'),(12,NULL,'Manage Users',12,3,'Manager Users'),(13,NULL,'Backup',13,3,'Backup'),(14,NULL,'Export Data',14,3,'Export Data'),(15,NULL,'Manage Question',15,3,'Manage Question'),(16,NULL,'Manage Role Function',16,3,'Manage Role Function'),(17,NULL,'View Household',17,3,'Manage Role Function'),(20,NULL,'Mother Death Record',20,3,'Mother Death Record'),(21,NULL,'Child Death Record',21,3,'Child Death Record'),(25,NULL,'Export Geographycal Information',25,3,'Export Geographycal Information'),(26,NULL,'Geographical Location',26,3,'Geographical Location'),(27,NULL,'MPR Report',27,3,'MPR Report'),(28,NULL,'Medical Advice Template',28,3,'Medical Advice Template'),(22,NULL,'Manage Algorithm',22,3,'Manage Algorithm');


/*Data for the table `role` */
insert  into `role`(`componentId`,`uniqueCode`,`accesslevel`,`status`,`description`,`updateddate`,`updatedby`) values (1,'System Admin',1,1,'System Admin Role',NULL,NULL),(2,'Branch Manager',2,1,'Branch Manager Role',NULL,NULL),(3,'Manoshi Admin',3,1,'System Admin Role',NULL,NULL),(4,'Group Admin',4,1,'Group Admin Role',NULL,NULL),(5,'Hotline Agent',5,1,'Hotline Agent Role',NULL,NULL),(6,'User',6,1,'User Role',NULL,NULL),(7,'SK',7,1,'SK',NULL,NULL),(8,'Doctor',8,1,'Docotr',NULL,NULL),(9,'Regional Manager',9,1,'Regional Manager',NULL,NULL);


/*Data for the table `rolefunction` */
insert  into `rolefunction`(`role`,`functions`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,20),(1,21),(1,22),(1,25),(1,26),(1,27),(1,28),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,21),(3,3),(3,7),(4,4),(4,5),(4,8),(5,2),(5,3),(5,27),(5,28),(6,7),(6,8),(6,13),(6,14),(7,2),(8,2),(8,20),(8,21),(8,28),(9,2),(9,8),(9,12);


/*Data for the table `shasthoshebica` */
insert  into `shasthoshebica`(`componentId`,`ssId`,`skId`,`branch`) values (1,'01','299',4),(2,'02','299',4),(3,'03','299',4),(4,'04','299',4),(5,'05','299',4),(6,'06','299',4),(7,'07','299',4),(9,'1','35',22),(10,'2','35',22),(11,'3','35',22),(12,'4','35',22),(13,'5','35',22),(14,'6','35',22),(15,'7','35',22),(17,'8','35',22),(18,'9','35',22),(19,'10','35',22),(20,'11','35',22),(21,'1','36',22),(22,'2','36',22),(23,'3','36',22),(24,'4','36',22),(25,'5','36',22),(26,'6','36',22),(27,'7','36',22),(28,'8','36',22),(29,'9','36',22),(30,'10','36',22),(31,'1','37',22),(32,'2','37',22),(33,'3','37',22),(34,'4','37',22),(35,'5','37',22),(36,'6','37',22),(37,'7','37',22),(38,'8','37',22),(39,'1','38',22),(40,'2','38',22),(41,'3','38',22),(42,'4','38',22),(43,'5','38',22),(44,'6','38',22),(45,'7','38',22),(46,'1','39',22),(47,'2','39',22),(48,'3','39',22),(49,'4','39',22),(50,'5','39',22),(51,'6','39',22),(52,'1','40',22),(53,'2','40',22),(54,'3','40',22),(55,'4','40',22),(56,'5','40',22),(57,'6','40',22),(58,'7','40',22),(59,'8','40',22),(60,'1','41',22),(61,'2','41',22),(62,'3','41',22),(63,'4','41',22),(64,'5','41',22),(65,'6','41',22),(66,'7','41',22),(67,'8','41',22),(68,'9','41',22),(69,'1','42',22),(70,'2','42',22),(71,'3','42',22),(72,'4','42',22),(73,'5','42',22),(74,'6','42',22),(75,'7','42',22),(76,'1','43',22),(77,'2','43',22),(78,'3','43',22),(79,'4','43',22),(80,'5','43',22),(81,'6','43',22),(82,'7','43',22),(83,'8','43',22),(84,'1','44',22),(85,'2','44',22),(86,'3','44',22),(87,'4','44',22),(88,'5','44',22),(89,'6','44',22),(90,'7','44',22),(91,'8','44',22),(92,'9','44',22),(93,'1','45',22),(94,'2','45',22),(95,'3','45',22),(96,'4','45',22),(97,'5','45',22),(98,'6','45',22),(99,'7','45',22),(100,'8','45',22),(101,'1','46',22),(102,'2','46',22),(103,'3','46',22),(104,'4','46',22),(105,'5','46',22),(106,'6','46',22),(107,'1','47',22),(108,'2','47',22),(109,'3','47',22),(110,'4','47',22),(111,'5','47',22),(112,'6','47',22),(113,'1','48',22),(114,'2','48',22),(115,'3','48',22),(116,'4','48',22),(117,'5','48',22),(118,'1','49',22),(119,'2','49',22),(120,'3','49',22),(121,'4','49',22),(122,'5','49',22),(123,'6','49',22),(124,'7','49',22),(125,'1','27',8),(126,'2','27',8),(127,'3','27',8),(128,'4','27',8),(129,'5','27',8),(131,'6','27',8),(132,'7','27',8),(133,'1','28',8),(134,'2','28',8),(135,'3','28',8),(136,'4','28',8),(137,'5','28',8),(138,'6','28',8),(139,'7','28',8),(140,'1','29',8),(141,'2','29',8),(142,'3','29',8),(143,'4','29',8),(144,'1','30',8),(145,'2','30',8),(146,'3','30',8),(147,'4','30',8),(148,'5','30',8),(149,'1','31',8),(150,'2','31',8),(151,'3','31',8),(152,'4','31',8),(153,'5','31',8),(154,'6','31',8),(155,'01','32',8),(156,'02','32',8),(157,'03','32',8),(158,'04','32',8),(159,'1','33',8),(160,'2','33',8),(161,'3','33',8),(162,'4','33',8),(163,'5','33',8),(164,'1','34',8),(165,'2','34',8),(166,'3','34',8),(167,'4','34',8),(168,'5','34',8),(170,'01','299',26),(171,'02','299',26),(172,'03','299',26),(173,'04','299',26),(174,'05','299',26),(175,'06','299',26),(176,'07','299',26);



insert  into `users`(`componentId`,`uniqueCode`,`firstName`,`lastName`,`password`,`roleId`,`email`,`groupId`,`status`,`updateddate`,`updatedby`,`userArea`,`areaType`) 
values (0,'','','','',NULL,NULL,NULL,0,NULL,NULL,NULL,0),
(1,'admin','admin','admin','343443653337515441464473584252427754356363513d3d',1,'admin@clickdiagnostics.com',NULL,0,NULL,NULL,NULL,0),
(2,'allWeb','allWeb','allWeb','343443653337515441464473584252427754356363513d3d',6,'admin@clickdiagnostics.com',NULL,0,NULL,NULL,NULL,0),
(3,'allMobile','allMobile','allMobile','343443653337515441464473584252427754356363513d3d',6,'admin@clickdiagnostics.com',NULL,0,NULL,NULL,NULL,0),
(4,'allSK','allSK','allSK','343443653337515441464473584252427754356363513d3d',6,'sk@clickdiagnostics.com',NULL,0,NULL,NULL,NULL,0),
(35,'111','111','111','111',6,'111',NULL,0,NULL,NULL,7,15),
(37,'35','Maleka ','Khatun','',7,'',NULL,0,NULL,NULL,22,15),
(38,'36','Hasina ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(39,'37','Khadija ','Begum','',7,'',NULL,0,NULL,NULL,22,15),
(40,'38','Lakhy ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(41,'39','Mahinur ','Sikder','',7,'',NULL,0,NULL,NULL,22,15),
(42,'40','Selina ','Khatun','',7,'',NULL,0,NULL,NULL,22,15),
(43,'41','Nurunnahar ','Rekha','',7,'',NULL,0,NULL,NULL,22,15),
(46,'44','Reshma ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(47,'45','Aklima ','Khatun','',7,'',NULL,0,NULL,NULL,22,15),
(48,'46','Hasina ','Begum','',7,'',NULL,0,NULL,NULL,22,15),
(49,'47','Sufia ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(50,'48','Hosneara ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(51,'49','Mahmuda ','Khanam','',7,'',NULL,0,NULL,NULL,22,15),
(52,'27','Rehena ','Begum','',7,'',NULL,0,NULL,NULL,6,15),
(53,'28','Feroja ','Begum','',7,'',NULL,0,NULL,NULL,6,15),
(82,'007','ME','TEST','354c6f366c45626f4e57566d7843764c36786f5571773d3d',7,'',NULL,0,NULL,NULL,4,15),
(54,'29','Marina ','Akter','',7,'',NULL,0,NULL,NULL,6,15),
(55,'30','Siri ','Khatun','',7,'',NULL,0,NULL,NULL,6,15),
(56,'31','Shahanaj ','Akter','',7,'',NULL,0,NULL,NULL,6,15),
(57,'45','Aklima ','Khatun','6f786373525867384b366f6b612b7278376c736b4e513d3d',7,'',NULL,0,NULL,NULL,22,15),
(58,'33','Sahida ','Akter','',7,'',NULL,0,NULL,NULL,6,15),
(85,'43','Sakina ','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(59,'34','Shahinur ','Akter','',7,'',NULL,0,NULL,NULL,6,15),
(60,'299','Sharmin','Akter','299',7,'',NULL,0,NULL,NULL,4,15),
(61,'300','Ruksana','Ruksana','300',7,'',NULL,0,NULL,NULL,4,15),
(62,'301','Ms','Sheuli','301',7,'',NULL,0,NULL,NULL,4,15),
(63,'302','Ms','Jannat','302',7,'',NULL,0,NULL,NULL,4,15),
(64,'303','Ms','Sahina','303',7,'',NULL,0,NULL,NULL,4,15),
(65,'304','Ms','Nazma','304',7,'',NULL,0,NULL,NULL,4,15),
(66,'305','Ms','Fahima','305',7,'',NULL,0,NULL,NULL,4,15),
(67,'306','Ms','Forida','306',7,'',NULL,0,NULL,NULL,4,15),
(68,'307','Ms','Jesmin','307',7,'',NULL,0,NULL,NULL,4,15),
(69,'308','Ms','Sima','308',7,'',NULL,0,NULL,NULL,4,15),
(70,'309','Ms','Amena','309',7,'',NULL,0,NULL,NULL,4,15),
(71,'12345','Test','me','123',7,'',NULL,0,NULL,NULL,7,15),
(73,'rmraj','Tamim','Iqbal','776b4f4a7a643044597a36533961726e5a73526a54673d3d',9,'',NULL,0,NULL,NULL,15,5),
(74,'99999','Test ','Q Download','6f6755437073466d307a30324651376f716c6e5645413d3d',7,'',NULL,0,NULL,NULL,7,15),
(75,'00123','Raj','Test','123',7,'',NULL,0,NULL,NULL,17,15),
(77,'234','xyz','xyz','234',7,'xyz@xyz',NULL,0,NULL,NULL,2,5),
(78,'doc','doc','mamun','65373244717a52383142696c664e496b3975424745773d3d',8,'',NULL,0,NULL,NULL,NULL,0),
(79,'doctor','Mamun','Palash','757455306a762f2f57346a43614e6d785977666848773d3d',8,'',NULL,0,NULL,NULL,NULL,-1),
(80,'click','click','click','43306745546474476c686e682b42626c556e79762f773d3d',5,'',NULL,0,NULL,NULL,NULL,-1),
(81,'8997','Anwarul','Mamun','123',7,'',NULL,0,NULL,NULL,4,15),
(84,'42','Shahida','Akter','',7,'',NULL,0,NULL,NULL,22,15),
(83,'32','Kajol ','Akhter','',7,'',NULL,0,NULL,NULL,6,15);