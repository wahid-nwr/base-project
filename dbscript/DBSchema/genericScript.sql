
# MySQL-Front Dump 2.5
# Host: localhost   Database: mhealth
# Server version  5.0


create database mhealth_test CHARACTER SET utf8 COLLATE utf8_bin;  
use mhealth_test;


DROP TABLE if EXISTS role;
CREATE TABLE role (
  componentId bigint NOT NULL default '0',
  uniqueCode varchar(50) default NULL,
  accesslevel int default NULL,
  status int NOT NULL default '0',
  description varchar(250) default NULL,
  updateddate datetime default NULL,
  updatedby BIGINT default null,
  PRIMARY KEY  (componentId)
) ;


DROP TABLE if EXISTS groups;
CREATE TABLE groups (
   componentId BIGINT not null default '0',
    uniqueCode VARCHAR(100),
    groupName VARCHAR(100),
    description VARCHAR(100),
    parentGroupId bigint(20) default NULL,
    status INT not null,
    updateDate datetime default NULL,
    updatedby BIGINT default null,
    PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS geolocation;
CREATE TABLE geolocation
( 
	componentId bigint NOT NULL default '0',
	name varchar(50),
	code varchar(20),
	status int,
	geoType int,
	description varchar(500),
	
	 geoNumber varchar(20),
	 address varchar(500),
	 location varchar(50),
	 officePhoneNumber varchar(20),
	 contactPerson varchar(100),
	 contactNumber varchar(20),
	
	parentArea  bigint,
		
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS users;
CREATE TABLE users (
  componentId bigint NOT NULL default '0',
  uniqueCode varchar(50) NOT NULL default '',
  firstName varchar(50) NOT NULL default '',
  lastName varchar(50) NOT NULL default '',
  password varchar(1024) NOT NULL default '',
  roleId bigint default NULL,
  email varchar(255) default NULL,
  groupId bigint default NULL,
  status int NOT NULL default '0',
  updateddate datetime default NULL,
  updatedby BIGINT default null, 
  userArea bigint,
  areaType int NOT NULL default '0',
  PRIMARY KEY  (componentId),
  constraint fk_group_user foreign key  (groupId) references groups(componentId), 
  constraint fk_role_user foreign key  (roleId) references role(componentId),
  constraint fk_user_area foreign key (userArea) references geoLocation(componentId)  
  
);


DROP TABLE if EXISTS functiontype;
CREATE TABLE functiontype(
  componentId bigint NOT NULL default '0',
  uniqueCode varchar(50),
  typeId int NULL default NULL,
  typeName  varchar(200),
  PRIMARY KEY  (typeId)
) ;

DROP TABLE if EXISTS functions;
CREATE TABLE functions(
  componentId bigint NOT NULL default '0',
  uniqueCode varchar(50),
  functionName varchar(100),
  functionId   int NOT NULL default '0',
  typeId int NULL default NULL,
  displayName  varchar(200),
  PRIMARY KEY  (componentId),
  constraint fk_FunctionType_functions  foreign key (typeId) references functiontype(typeId)

) ;


DROP TABLE if EXISTS rolefunction;
create table rolefunction
(	
	role bigint	not null,
	functions bigint not null,

	constraint	fk_role_rf foreign key (role) references	role( componentId ), 	
	constraint	fk_functions_rf	foreign key (functions) references 	functions(componentId),
	constraint	uk_role_function unique key (role, functions)													
);

########## Create Question related Table- Algorithm and algorithm Question also goes this section #########
########## As the Algorithm uses the question/questionnaire #################
DROP TABLE if EXISTS questionnairestatus;
CREATE TABLE questionnairestatus
(
  componentId bigint NOT NULL default '0',
  statusName varchar(100),
  statusId int NULL default NULL,
  PRIMARY KEY (componentId) 
 );
 
DROP TABLE if EXISTS questioncategory;
CREATE TABLE questioncategory
(
  componentId bigint NOT NULL default '0',
  categoryName varchar(100),
  categoryId int NULL default NULL,
  PRIMARY KEY (componentId) 
  );

DROP TABLE if EXISTS questionquestionnaire;
CREATE TABLE questionquestionnaire
(
	componentId bigint NOT NULL default '0',	
	PRIMARY KEY (componentId)
);  
 
 DROP TABLE if EXISTS answertype;
CREATE TABLE answertype
(
  componentId bigint NOT NULL default '0',
  answerTypeId int NULL default NULL,
  answerTypeName varchar(200),
  PRIMARY KEY (componentId)
); 
DROP TABLE if EXISTS question;
CREATE TABLE question
(
	componentId bigint NOT NULL default '0',
	questionName varchar (2000),
	qqType int NOT NULL default '110',
	questionId varchar (100) NOT NULL,
	categoryId  bigint NULL default NULL,
	answerTypeId bigint NULL default NULL,
	constraint	uk_questionId unique key (questionId),
	constraint	fk_AnswerType_rf foreign key (answerTypeId) references	answertype( componentId ), 	
	constraint	fk_QuestionCategory_rf	foreign key (categoryId) references 	questioncategory(componentId),
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS questionnaire;
CREATE TABLE questionnaire
(
  componentId bigint NOT NULL default '0',
  questionnaireName  varchar(800),
  qqType int NOT NULL default '120',
  questionnaireVersion  varchar(50),
  questionnaireStatus  bigint NOT NULL default '0',
  questionnaireId  varchar (100) NOT NULL,
  questionnaireTimestamp  varchar(100),
  questionnaireDescription  varchar(1500),
  numberOfQuestion int NULL default NULL,
  questionnaireTypeFlag int,
  constraint	uk_questionnaireId unique key (questionnaireId),
  constraint	fk_QuestionnaireStatus_rf	foreign key (questionnaireStatus) references questionnairestatus(componentId),
  PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS questionmap;
CREATE TABLE questionmap
(
	
	questionnaireId  bigint NOT NULL,
	question  bigint NOT NULL,
	questionOrder  int NOT NULL,
	
	constraint	fk_questionnaire_qm foreign key (questionnaireId) references	questionnaire( componentId ), 	
	constraint	fk_question_qm	foreign key (question) references 	question(componentId),
	constraint	uk_question_questionairre unique key (questionnaireId, question)
);

DROP TABLE if EXISTS mcqoption;
CREATE TABLE mcqoption
(
	componentId bigint NOT NULL default '0',
	name varchar (1000),
	value varchar (200),
	questionId  bigint NOT NULL default '0',
	questionOrder  int,
	
	PRIMARY KEY (componentId)	
);

DROP TABLE if EXISTS algquestion;
CREATE TABLE algquestion
(
	componentId bigint NOT NULL default '0',
	name varchar (600),
	lastQuestionFlag int,
	description varchar (500),
	qdto  bigint NOT NULL default '0',
	branchAlu varchar(200),
	constraint	fk_AlgQ_Qdto	foreign key (qdto) references 	questionquestionnaire(componentId),
	 	
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS algquestionbranch;
CREATE TABLE algquestionbranch
(
	componentId bigint NOT NULL default '0',
	algQuestionId  bigint NOT NULL default '0',
	nextAlgQuestionId  bigint NOT NULL default '0',
	branchOrder  int,
	
	constraint	fk_AlgQuestionAlg_Branch_rf foreign key (nextAlgQuestionId) references	algquestion( componentId ), 	
	
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS algorithm;
CREATE TABLE algorithm
(
	componentId bigint NOT NULL default '0',
	name varchar (400),
	version varchar (200),
	description varchar (500),
	status int default 0,
	algId varchar(200),
	firstAlgQuestion bigint NULL default NULL,
	constraint	fk_AlgQuestionAlg_rf foreign key (firstAlgQuestion) references	algquestion( componentId ), 	
	PRIMARY KEY (componentId)
);


DROP TABLE if EXISTS alganswer;
CREATE TABLE alganswer
(
	componentId bigint NOT NULL default '0',
	answer1 varchar (600),
	answer2 varchar (600),
	questionId bigint NOT NULL default '0',
	answerTypeId bigint NOT NULL default '0',
	algorithmId bigint NOT NULL default '0',
	
	algBranchId  bigint NOT NULL default '0',
	answerOrder  int,
	
	constraint	fk_AlgBranc_AlgAns	foreign key (algBranchId) references 	algquestionbranch(componentId),
	constraint	fk_Question_AlgAns	foreign key (questionId) references 	question(componentId),
	constraint	fk_AnswerType_AlgAns	foreign key (answerTypeId) references 	answertype(componentId),
 	constraint	fk_Algorithm_AlgAns	foreign key (algorithmId) references 	algorithm(componentId),

	PRIMARY KEY (componentId)
);

################ Algorithm related Table ##########

DROP TABLE if EXISTS answer;
CREATE TABLE answer
(
  componentId bigint NOT NULL default '0',
  answerName varchar(1500),
  answerId int NULL default NULL,
  answerTypeId bigint NOT NULL default '0',
  questionId   bigint NOT NULL default '0',
  constraint	fk_QuestionAns_rf	foreign key (questionId ) references question(componentId),
  constraint	fk_AnswerTypeAns_rf	foreign key (answerTypeId) references answertype(componentId),
  PRIMARY KEY (componentId)
);



DROP TABLE if EXISTS visitinfo;
CREATE TABLE visitinfo
( 
	componentId bigint NOT NULL default '0',
	visitDate datetime default NULL,
	dataArrivingTime datetime default NULL,
	visitDuration float,
	differanceBetweenTime varchar(50),
	userId varchar(600),
	startTimeStamp datetime default NULL,
	endTimeStamp datetime default NULL,
	visitPicTimeStamp datetime default NULL,
	diffBetweenvisitPicTime varchar(50),
	diffBetweenvisitPicAndQEndTime varchar(50),
	visitPic varchar(200),
	visitType int,
	PRIMARY KEY (componentId)
);

## For schedule table
DROP TABLE if EXISTS visitschedule;
CREATE TABLE visitschedule
( 
	componentId bigint NOT NULL default '0',
	
	visitItemId varchar(20),	
	scheduleDate datetime NULL, 
	executionDate datetime NULL, 
	state int default '0',
	
	scheduleBy varchar(100),
	remarks varchar(500),
	visitItemType int,
	
	user bigint NOT NULL,
	visitId bigint,
	constraint	fk_visitSchedule_VisitInfo_rf foreign key (visitId) references visitinfo( componentId ),
	constraint	fk_schedule_user_rf foreign key (user) references users( componentId ),
	PRIMARY KEY (componentId)
);

DROP TABLE if EXISTS alert;
CREATE TABLE alert
( 
	componentId bigint NOT NULL default '0',
	alertId varchar(50),	
	name  varchar(200),
	description varchar(50),
	gender  varchar(50),
	status int,
	updatedDate datetime,
	updatedby int,
	header varchar(200),
	body varchar(500),
	recieverId int,
	alertType int,
	alertDate datetime,
	sendBy varchar(50),
	beneficiaryId varchar(50),
	PRIMARY KEY (componentId)
);


DROP TABLE if EXISTS shasthoshebica;
CREATE TABLE shasthoshebica
( 
	componentId bigint NOT NULL default '0',
	ssId varchar(50),
	skId varchar(50),
	branch bigint,
	PRIMARY KEY (componentId)
);



CREATE TABLE `subalgorithm` (                         
    `componentId` bigint(20) NOT NULL default '0',      
    `name` varchar(400) default NULL,                   
    `version` varchar(200) default NULL,                
    `description` varchar(500) default NULL,            
    `status` int(11) default '0',                       
    `algId` varchar(200) default NULL,                  
    `algorithmId` bigint(20) default NULL,              
    `firstAlgQuestion` bigint(20) default NULL,         
    `nextAlgQuestion` bigint(20) default NULL,          
    `prevAlgQuestion` bigint(20) default NULL,          
    PRIMARY KEY  (`componentId`),                       
    KEY `fk_AlgQuestionSubAlg_rf` (`firstAlgQuestion`)  
);

CREATE TABLE `medicaladvicetemplate` (                                 
                 `componentId` bigint(20) NOT NULL default '0',               
                 `creatorId` bigint(20) NOT NULL default '0',                 
                 `advice` varchar(2000) collate utf8_bin default NULL,        
                 `medicine` varchar(350) collate utf8_bin default NULL,       
                 `refer` varchar(250) collate utf8_bin default NULL,          
                 `creationDate` datetime default NULL,                          
                 `adviceId` varchar(100) collate utf8_bin default NULL,                            
                 `status` int(11) default NULL,                               
                 PRIMARY KEY  (`componentId`)                                
);       
/*Table structure for table `validation` */

DROP TABLE IF EXISTS `validation`;

CREATE TABLE `validation` (
  `componentId` bigint(20) NOT NULL default '0',
  `qId` varchar(200) collate utf8_bin default NULL,
  `validationOrder` int(11) default NULL,
  `ansType` varchar(50) collate utf8_bin default NULL,
  `ansTypeCode` bigint(20) NOT NULL default '0',
  `validationType` int(11) default NULL,
  `validationName` varchar(50) collate utf8_bin default NULL,
  `validationValue` varchar(50) collate utf8_bin default NULL,
  `secondValidationType` int(11) default NULL,
  `secondValidationName` varchar(50) collate utf8_bin default NULL,
  `secondValidationValue` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`componentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

