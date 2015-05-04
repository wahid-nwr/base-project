DROP TABLE IF EXISTS `usertoken`;

CREATE TABLE `usertoken` 
(
  `componentId` bigint(20) NOT NULL default '0',
  
  `userId` varchar(50),
  `branch` varchar(50),
  tokenLowerLimit int,
  tokenUpperLimit int,
  
  `issueDate` datetime default NULL,  
 
  PRIMARY KEY  (`componentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `datatoken`;

CREATE TABLE `datatoken` 
(
  `componentId` bigint(20) NOT NULL default '0',
  
  `userId` varchar(600) collate utf8_bin default NULL,
  `phoneNo` varchar(50)  default NULL,
  `imeiNo` varchar(50)  default NULL,
  `reqTokenId` varchar(50)  default NULL,
  `respTokenId` varchar(50)  default NULL,
  `visitItemId` varchar(50)  default NULL,
  
  `visitDate` datetime default NULL,
  `tokenDate` datetime default NULL,
 
  PRIMARY KEY  (`componentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE users
ADD COLUMN `utoken` bigint(20) default NULL,
ADD CONSTRAINT `fk_userToken_user` FOREIGN KEY (`utoken`) REFERENCES `usertoken` (`componentId`)

