
DROP TABLE if EXISTS childbeneficiary;
CREATE TABLE childbeneficiary 
(           
    componentId bigint(20) NOT NULL,        
    childId varchar(50) default NULL,       
    motherId varchar(50) default NULL,      
    nameFile varchar(200) default NULL,     
    pictureFile varchar(200) default NULL,  
    sex varchar(50) default NULL, 
	isMtrChild varchar(50) default NULL, 
    regDate datetime default NULL,      
    dateOfBirth datetime default NULL,      
    birthWieght double default NULL,        
    name varchar(200) default NULL,         
    firstVisitId bigint(20) default NULL,   
    PRIMARY KEY  (componentId)              
);

DROP TABLE if EXISTS childcomplicacyrecord;
CREATE TABLE childcomplicacyrecord 
(                 
	componentId bigint(20) NOT NULL,                   
	isChildDiarrhea int(11) default NULL,              
	isTreated int(11) default NULL,                    
	placeOfTreatment varchar(50) default NULL,         
	communityAssistant varchar(50) default NULL,       
	treatmentProcedure varchar(50) default NULL,       
	isNumonia int(11) default NULL,                    
	otherChildComplication varchar(300) default NULL,  
	isNumoniaTreated int(11) default NULL,             
	isOtherProblemTreated int(11) default NULL, 
	isDiarrRefered int default -1,
 	isNumnRefered int default -1,
 	diarrResult  VARCHAR(100),
 	numnResult  VARCHAR(100),
 	numnComAsst  VARCHAR(100),
	numnTreatPlc  VARCHAR(100),
	otherChildComplication  VARCHAR(300),
	
	PRIMARY KEY  (componentId)                         
);

DROP TABLE if EXISTS childfirstvisitrecord;
CREATE TABLE childfirstvisitrecord 
(               
	componentId bigint(20) NOT NULL,                 
	isBreastFedFirst int(11) default NULL,           
	isBrestFedWithinOneHour int(11) default NULL,    
	isBirthAsphyxiaIdentified int(11) default NULL,  
	isAsphyxiaRevived int(11) default NULL,          
	helpingAssistant varchar(50) default NULL,       
	deliveryResult varchar(50) default NULL,         
	reasonOfDeath varchar(50) default NULL,          
	dateOfMotherDeath datetime default NULL,         
	placeOfMotherDeath varchar(50) default NULL,     
	isChildAlive int(11) default NULL,               
	reasonOfChildDeath varchar(50) default NULL,     
	dateOfChildDeath datetime default NULL,          
	placeOfChildDeath varchar(50) default NULL,      
	isPlasantaTied int(11) default NULL,             
	isBodyCoverd int(11) default NULL,               
	isBirthDefect int(11) default NULL, 
	visitId bigint(20) not NULL,
	refferalId bigint(20) default NULL,
	neoNatalVisitInfoId bigint(20) default NULL,       
	neoNatalComplicacyId bigint(20) default NULL,
	timeOfBirth datetime default NULL,
 
	PRIMARY KEY  (componentId)                       
);


DROP TABLE if EXISTS childvisitrecord;
CREATE TABLE childvisitrecord (                 
    componentId bigint(20) NOT NULL,              
    isChildTtTakenAsPerAge int(11) default NULL,  
    childId bigint(20) default NULL,  
    visitId bigint(20) not NULL, 
    refferalId bigint(20) default NULL,          
    childComplicacyId bigint(20) default NULL,
    isVitATaken int default -1,
    
    PRIMARY KEY  (componentId)                    
);
  
DROP TABLE if EXISTS neonatalcomplicacyrecord;
CREATE TABLE neonatalcomplicacyrecord 
(                     
	componentId bigint(20) NOT NULL,                        
	isAbnormalTempareture int(11) default NULL,             
	isChildBreastFeedNormal int(11) default NULL,           
	isRapidBreathing int(11) default NULL,                  
	isChildChestGoDownWhileBreathing int(11) default NULL,  
	isInfectedBellyButton int(11) default NULL,             
	isEnlargeAbdomen int(11) default NULL,                  
	isRapidBomiting int(11) default NULL,                   
	isDiarrhea int(11) default NULL,                        
	isFaint int(11) default NULL,                           
	isChildConvultion int(11) default NULL,                 
	isMoreThanTen int(11) default NULL,                     
	otherInfantComplication varchar(300) default NULL,       
	isTreatedImmidiately int(11) default NULL,              
	treatmentResult varchar(400) default NULL, 
	treatPlace VARCHAR (100),
	treatResult  VARCHAR(100),
	
	PRIMARY KEY  (componentId)                              
);

DROP TABLE if EXISTS infantvisitrecord;
CREATE TABLE infantvisitrecord 
(                          
	componentId bigint(20) NOT NULL,                        
	isBreastFedOnly int(11) default NULL,                   
	isSupplymentaryFoodAfterSixMonth int(11) default NULL,  
	isTtTakenAsPerAge int(11) default NULL,                 
	isVitaminACompleted int(11) default NULL,               
	childId bigint(20) default NULL,  
	visitId bigint(20) not NULL, 
	refferalId bigint(20) default NULL,                     
	childComplicacyId bigint(20) default NULL,             
	PRIMARY KEY  (componentId)                              
);
   
DROP TABLE if EXISTS neonatalvisitinfo;   
CREATE TABLE neonatalvisitinfo 
(                
	componentId bigint(20) default NULL,          
	isChildSkinNormal int(11) default NULL,       
	isChildEyeNormal int(11) default NULL,        
	isChildBreathingNormal int(11) default NULL,  
	isChildMovementNormal int(11) default NULL,   
	isChildPlacementNormal int(11) default NULL,  
	childBodyTempareture float default NULL,      
	otherBabyFood varchar(50) default NULL,       
	isBabyAbleToBreastFeed int(11) default NULL,  
	isOnlyBreastFed int(11) default NULL,         
	isChildHairCut int(11) default NULL,          
	hasChildGivenBath int(11) default NULL,       
	isChildGivenTt int(11) default NULL,
	
	PRIMARY KEY  (componentId)  
);

DROP TABLE if EXISTS neonatalvisitrecord;     
CREATE TABLE neonatalvisitrecord 
(                   
	componentId bigint(20) NOT NULL,                   
	isUmbilicalCordTiedProperly int(11) default NULL,  
	isUmbilicalCordTakenCareOf int(11) default NULL,   
	isBreastFed int(11) default NULL,                  
	isLowBirthWeight int(11) default NULL,             
	isBabyWarmed int(11) default NULL,                 
	wayOfWarming varchar(100) default NULL,            
	visitType int(11) default NULL,                  
	childId bigint(20) default NULL, 
	isUmbilicalCordDried int(11),
	wieghtOfChild double,
	childFollowUpWieght double,      
	visitId bigint(20) not NULL,  
	refferalId bigint(20) default NULL,        
	neoNatalVisitInfoId bigint(20) default NULL,       
	neoNatalComplicacyId bigint(20) default NULL,
	hasSepsis int default -1,
	sepsisTrtmntPlc varchar(100),
	sepsisResult VARCHAR(100),
	isRefered int default -1,
	referResult VARCHAR(100),
	deathCause  VARCHAR(500),
	deathTime datetime default NULL, 
	deathPlace  VARCHAR(500),
	
	PRIMARY KEY  (componentId)                         
);

DROP TABLE if EXISTS childreferralrecord;
CREATE TABLE childreferralrecord
(
	componentId bigint NOT NULL default '0',
	uniqueCode varchar(50),
	isReferral  int,
	dateOfReferral datetime default NULL,
	timeOfRefferal datetime default NULL,
	referredFrom varchar(600),
	placeOfReferral  varchar(600),
	reasonOfRefferance varchar(600),
	otherRefferCause varchar(600),
	otherRefferCauseRecord varchar(600),
	otherRefferCauseRecordFile varchar(600),
	pregRecordId bigint default NULL,
	visitId bigint, 
	beneficiaryId varchar(50),
	PRIMARY KEY (componentId)
);

alter table childbeneficiary add ismtrchild    varchar(300);

# alter table script for newly question added
alter table neonatalcomplicacyrecord add treatPlace VARCHAR (100);
alter table neonatalcomplicacyrecord add treatResult  VARCHAR(100);

# child complicacy
alter table  childcomplicacyrecord add isDiarrRefered int default -1;
alter table  childcomplicacyrecord add isNumnRefered int default -1;
alter table  childcomplicacyrecord add diarrResult  VARCHAR(100);
alter table  childcomplicacyrecord add numnResult  VARCHAR(100);
alter table  childcomplicacyrecord add numnComAsst  VARCHAR(100);
alter table  childcomplicacyrecord add numnTreatPlc  VARCHAR(100);
alter table  childcomplicacyrecord add otherChildComplication  VARCHAR(300);

# neonatal visit info
alter table  neonatalvisitinfo add hasSepsis int default -1;
alter table  neonatalvisitinfo add sepsisTrtmntPlc varchar(100);
alter table  neonatalvisitinfo add sepsisResult VARCHAR(100);
alter table  neonatalvisitinfo add isRefered int default -1;
alter table  neonatalvisitinfo add referResult VARCHAR(100);
alter table  neonatalvisitinfo add deathCause  VARCHAR(500);
alter table  neonatalvisitinfo add deathTime datetime default NULL; 
alter table  neonatalvisitinfo add deathPlace  VARCHAR(500);

# alterScript
alter table childfirstvisitrecord add timeOfBirth datetime default NULL;


# alter script for vitamin a
alter table  childvisitrecord add isVitATaken int default -1;
