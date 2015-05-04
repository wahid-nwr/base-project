DROP TABLE if EXISTS householdmMembervisit;
DROP TABLE if EXISTS familyplanninginfo;
DROP TABLE if EXISTS householdchildvisit;
DROP TABLE if EXISTS householdmothervisit;
DROP TABLE if EXISTS householdmember;
DROP TABLE if EXISTS household;

DROP TABLE if EXISTS household;
CREATE TABLE household
(
	componentId bigint  default '0',
	householdId varchar (100),
	houseNo varchar (100) ,
	ssId varchar (100) ,
	chiefName varchar (100),
	financialType varchar (100),
	sanitationType varchar (100),
	totalMember int  default '0',
	tubewell boolean ,
	visitId bigint NOT NULL default '0',
	branch bigint NOT NULL default '0',
	isFamilyOrMess varchar(20),
	isFamilyPresent int,
	
	KEY `fk_Household_VisitInfo_rf` (`visitId`),
	UNIQUE KEY (`houseNo`),
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS householdmember;
CREATE TABLE householdmember
(
	componentId bigint default '0',
	householdId varchar (100),
	beneficiaryId varchar (200) ,
	memberPicture varchar (100) ,
	memberName varchar (100) ,
	memberType varchar (100) ,
	mobileNo varchar (100),
	gender varchar (100),
	pregnancyCondition varchar (100) ,
	husbandName varchar (100),
	fpMethod	varchar(300),
	occupation varchar (100) ,
	age int,
	otherInfo boolean,
	memberNameStr varchar(600),
	occupationStr varchar(600),
	otherMemVoice varchar(600),
	
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS householdmothervisit;
CREATE TABLE householdmothervisit
(
	componentId bigint default '0',
	householdMemberId bigint default '0',
	visitId bigint NOT NULL default '0',
	totalChildren int  default '0',
	memberVisitType int  default '0',
	ageOfYoungerChild int  default '0',
	causeOfAbsence varchar (200) ,
	presenceOfMother int ,
	newRegistration int ,
	KEY `fk_HouseholdMotherVisit_VisitInfo_rf` (`visitId`),
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS householdchildvisit;
CREATE TABLE householdchildvisit
(
	componentId bigint  default '0',
	householdMemberId bigint  default '0',
	visitId bigint NOT NULL default '0',
	memberVisitType int  default '0',
	KEY `fk_HouseholdChildVisit_VisitInfo_rf` (`visitId`),
	PRIMARY KEY (componentId)
);


DROP TABLE if EXISTS familyplanninginfo;
CREATE TABLE familyplanninginfo
(
	componentId bigint default '0',
	householdMotherId bigint  default '0',
	daysOfPeriod int  default '0',
	birthControlKit varchar (200) ,
	causeOfChange varchar (200),
	comments varchar (200),
	stripCheck int,
	iudCheck int,
	pillDate datetime default NULL,
	norplantDate datetime default NULL,
	deadlineOfNorplant datetime default NULL,
	iudDate datetime default NULL,
	deadlineOfIUD datetime default NULL,
	dateOfChange datetime default NULL,
	
	PRIMARY KEY (componentId)
);



DROP TABLE if EXISTS householdmembervisit;
CREATE TABLE householdmembervisit
(
	componentId bigint default '0',	
	`householdMemberId` bigint(20) default '0',
	PRIMARY KEY (componentId)
);  

DROP TABLE if EXISTS sshousehold;
CREATE TABLE sshousehold
(
	
	ssId bigint NOT NULL,
	household bigint NOT NULL,
	householdOrder  int NOT NULL,
	
	constraint	fk_shasthoShebica_ssid foreign key (ssId) references	shasthoshebica( componentId ), 	
	constraint	fk_household foreign key (household) references 	household(componentId),
	constraint	uk_ssHousehold_ssId unique key (ssId,household)
);

alter table householdmember add memberNameStr varchar(600);
alter table householdmember add occupationStr varchar(600);

// alter script
alter table familyplanninginfo modify stripCheck int;
alter table familyplanninginfo modify iudCheck int;

alter table householdmothervisit modify presenceOfMother int;
alter table householdmothervisit modify newRegistration int;

alter table householdmember add otherMemVoice varchar(600); 
