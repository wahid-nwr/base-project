
DROP TABLE if EXISTS motherbeninfo;
CREATE TABLE motherbeninfo (
	componentId		bigint NOT NULL default '0',
	mobileNo		varchar(50),
	nameFile		varchar(300),
	nameStr			varchar(300),
	pictureFile		varchar(300),
	age			int,
	isMigrated		varchar(50),
	hasAncCard		varchar(50),
	ancCardNo		int,
	hasRcvPregServ		varchar(50),
	pregServFrom		varchar(50),
	PRIMARY KEY  (componentId)
) ;
DROP TABLE if EXISTS preghistory;
CREATE TABLE preghistory (
	componentId		bigint NOT NULL default '0',
	PregCount		int,
	childCount		int,
	lastChildAge		int,
	isStillBirth		varchar(50),
	stillBirthCount	int,
	dthNewBorn		varchar(50),
	dthNBCount		int,
	rsnDthNBorn		varchar(300),
	childDead		varchar(50),
	chDCount		int,
	rsnDthChild		varchar(300),
	isCsr			varchar(50),
	csrCount		int,
	isMisCrg		varchar(50),
	misCrgCount		int,
	isMisCrgComp		varchar(50),
	isMisCrgAFirst		varchar(50),
	misCrgACount	int,
	isEpcoTomy		varchar(50),
	isTwin			varchar(50),
	twinCount		int,	
	isCsrTwin		varchar(50),
	csrTwinCount		int,
	isBldHistory		varchar(50),
	isBldAnc		varchar(50),
	isBldDelivery		varchar(50),
	isBldPnc		varchar(50),
	isObsLabour		varchar(50),
	isCnvHist		varchar(50),
	isEarlyDel		varchar(50),
	isLBWChild		varchar(50),
	isLBWFine		varchar(50),
	isBirthDfct		varchar(50),
	isFistura		varchar(50),
	isOtherProbHist		varchar(50),
	PRIMARY KEY  (componentId)
) ;

DROP TABLE if EXISTS healthrecord;
CREATE TABLE healthrecord (
	componentId		bigint NOT NULL default '0',
	isDiabetes		varchar(50),
	isHighBP		varchar(50),
	isBPTreated		varchar(50),
	isHeartDis		varchar(50),
	isJondis		varchar(50),
	isRespMain		varchar(50),
	isAsma			varchar(50),
	isAsmaTreated		varchar(50),
	isRespProb		varchar(50),
	isTB			varchar(50),
	isTBTreated		varchar(50),
	isTBtrtCmpd		varchar(50),
	isOtherRespProb		varchar(50),
	isTaken5TT		varchar(50),
	isTTComplt		varchar(50),
	isOtherAbdOp		varchar(50),
	isOP			varchar(50),
	isRelCnvl		varchar(50),
	PRIMARY KEY  (componentId)
) ;

DROP TABLE if EXISTS pregrecord;
CREATE TABLE pregrecord (
	componentId 		bigint NOT NULL default '0',
	pregNo			int,
	hhMemberId		varchar (50),
	lmpDt			datetime,
	prgIdentiMthd		varchar(50),
	updateddate 		datetime default NULL,
	prgSymptom		varchar(300),	
	painInBrst		int,
	weakNess		int,
	exUrin			int,
	vomTndc			int,
	childMvMnt		int,
	sympNone		int,
	trimstar		int,
	followupId		bigint NOT NULL default '0',
	pregHistId		bigint NOT NULL default '0',
	benefId			bigint NOT NULL default '0',
	hltRcdId		bigint NOT NULL default '0',
	visitId			bigint NOT NULL default '0',
	dlvRecordId		bigint,
	PRIMARY KEY  (componentId),
	constraint fk_prg_rcrd_prg_hist foreign key  (pregHistId) references preghistory(componentId),
	constraint fk_prg_rcrd_ben_inf foreign key  (benefId) references motherbeninfo(componentId),
	constraint fk_prg_rcrd_hlth_rcrd foreign key  (hltRcdId) references healthrecord(componentId),
	constraint fk_prg_rcrd_visit_info foreign key  (visitId) references visitinfo(componentId)
);

DROP TABLE if EXISTS ancfollowup;
CREATE TABLE ancfollowup (
	componentId		bigint NOT NULL default '0',
	pregId			bigint NOT NULL default '0',
	trimstar		int,
	followupNo		int,
	visitId			bigint NOT NULL default '0',
	hhmemberId		varchar(50),
	isMrngSick		varchar(50),
	isFreqVomating		varchar(50),
	isLoosingWgh		varchar(50),
	isPrgFever		varchar(50),
	isFeverNow		varchar(50),
	isFevTreated		varchar(50),
	fevTreatVce		varchar(300),
	isPrngFine		varchar(50),
	isPrgnProbVce		varchar(300),
	isFeelingWell		varchar(50),
	isUnFatigue		varchar(50),
	isHeadAche		varchar(50),
	isBlurVsn		varchar(50),
	isConvulsion		varchar(50),
	isIncThirst		varchar(50),
	isPianWUrin		varchar(50),
	isFever			varchar(50),
	isBleeding		varchar(50),
	isLessBld		varchar(50),
	isExcBld		varchar(50),
	hasJondis		varchar(50),
	canFlCMvmnt		varchar(50),
	feelPrsInAbd		varchar(50),
	isPRcdASleep		varchar(50),
	isLDischarge		varchar(50),
	isTakingIron		varchar(50),
	hasTakenTT		varchar(50),
	isHlmnthDrug		varchar(50),
	hlmnthDrugVce		varchar(300),
	hasRcvdANCSrvc		varchar(50),
	isRptAtoANC		varchar(50),
	Hgb			varchar(50),
	bldPLow			int,
	bldPHigh		int,
	BloodType		varchar(50),
	Rh			varchar(50),
	VDRL			varchar(50),
	HepB			varchar(50),
	isOtherProb		varchar(50),
	otherProbVce		varchar(300),
	hasSavings		varchar(50),
	savingsAmt		int,
	arrgndVehicle		varchar(50),
	dlvPlace		varchar(50),
	dlvAccVce		varchar(300),
	dlvHome			varchar(50),
	dlvHospital		varchar(50),
	eddMeeting		varchar(50),
	otherNGOSrvc		varchar(50),
	ngoSrvcVce		varchar(300),
	hasTestAuth		varchar(50),
	highFever		varchar(50),
	animia			varchar(50),
	animiaProb		varchar(50),
	jondis			varchar(50),
	exBPLow			int,
	exBPHigh		int,
	eclampsia		varchar(50),
	highBP			varchar(50),
	utHeight		int,
	utHghtChng2cm		varchar(50),
	mfetalPrg		varchar(50),
	utsTest			varchar(50),
	isChildPNormal		varchar(50),
	emrgncyCntct		varchar(50),
	descVstPurs		varchar(50),
	comments		varchar(300),
	visitCmt		varchar(500),
	nameFile		varchar(300),
	nameStr			varchar(300),
	pictureFile		varchar(300),
	temperature		float,
	ancOrder		int,
	isPrngFine int,
	isPrgnProbVce varchar(300),

	PRIMARY KEY  (componentId),
	key fk_anc_prg_rcrd (pregId) references pregrecord(componentId)
) ;

DROP TABLE if EXISTS pncfollowup;
CREATE TABLE pncfollowup (
	componentId		bigint NOT NULL default '0',
	pregId			bigint NOT NULL default '0',
	followupNo		int,
	hhmemberId		varchar(50),
	ironPil			varchar(50),
	vitACap			varchar(50),
	pExBld			varchar(50),
	pBTemp			int,
	pncBPL			int,
	pncBPH			int,
	highFever		varchar(50),
	abdPain			varchar(50),
	odrDischarge		varchar(50),
	pncConv			varchar(50),
	edema			varchar(50),
	tornPrnm		varchar(50),
	prbOnBreast		varchar(50),
	pOtherProb		varchar(300),
	pCompActn		varchar(300),
	pComActnRslt		varchar(50),
	stertedVisit		varchar(50),
	fpStarted		varchar(50),
	finalSevereHeadache VARCHAR( 50 ),
	visitId			bigint NOT NULL default '0',
	pncOrder 		int,
	PRIMARY KEY  (componentId),
	constraint fk_pnc_prg_rcrd foreign key  (pregId) references pregrecord(componentId)
) ;

DROP TABLE if EXISTS miscarriage;
CREATE TABLE miscarriage (
	componentId		bigint NOT NULL default '0',
	pregId			bigint NOT NULL default '0',
	hhmemberId		varchar(50),
	typeOfAbrtn		varchar(50),
	helperOfAbrtn		varchar(50),
	dateOfAbrtn		datetime,
	compOfAbrtn		varchar(50),
	servOfAbrtn		varchar(50),
	visitId			bigint NOT NULL default '0',
	PRIMARY KEY  (componentId),
	constraint fk_misc_prg_rcrd foreign key  (pregId) references pregrecord(componentId)
) ;

DROP TABLE if EXISTS deliveryrecord;
CREATE TABLE deliveryrecord (	
	componentId		bigint NOT NULL default '0',
	pregId			bigint NOT NULL default '0',
	noOfDlvChild		int,
	noOfLChild		int,
	noOfDChild		int,
	noOfMDthChild		int,
	dtOfDlv			datetime,
	tmOfDlv			time,
	typeOfDlv		varchar(50),
	otherTypeOfDlv		varchar(300),
	dlvAsst			varchar(300),
	otherDlvAsst		varchar(300),
	misoprostol		varchar(50),
	usedDlvKit		varchar(50),
	ssPrsnt			varchar(50),
	isDlvComp		varchar(500),
	
	
	isMisoprostolUsed varchar(300),
	dlvPlace 		varchar(300),
	misoprostolGivenBy varchar(300),
	dlvCompOther varchar(300),
	
	dlvHighBP		int,
	dlvExBld		int,
	dlvCnv			int,
	dlvProLabour		int,
	dlvObsLabour		int,
	dlvUmbcNR		int,
	dlvHeadNF		int,
	dlvMFetal		int,
	dlvJondis		int,
	dlvAnimia		int,
	dlvPrevCsr		int,
	dlvBldGNeg		int,
	compActnVce		varchar(300),
	compActnRslt		varchar(50),
	dlvCompOther varchar (300),
	isMisoprostolUsed VARCHAR( 20 ) ,
	misoprostolGivenBy VARCHAR( 50 ) ,
	dlvPlace VARCHAR( 50 ),	

	visitId			bigint NOT NULL default '0',
	PRIMARY KEY  (componentId),
	constraint fk_dlv_prg_rcrd foreign key  (pregId) references pregrecord(componentId)
) ;

DROP TABLE if EXISTS referralrecord;
CREATE TABLE referralrecord (	
	componentId		bigint NOT NULL default '0',
	pregId			bigint NOT NULL default '0',
	visitType		varchar(50),
	followupRefIF		bigint NOT NULL default '0',
	hhmemberId		varchar(50),
	isPtRfd			varchar(50),
	ptCndtnDR		varchar(50),
	trymstOfRfrl		varchar(500),
	dateOfRfrl		datetime,
	timeOfRfrl		time,
	rfrlPlace		varchar(50),
	rsltOfRfrl		varchar(50),
	nRsltOfRfrlVce		varchar(300),
	ptDied			varchar(50),
	otherRsnVce		varchar(300),
	
	
	rfrlHighBP		int,
	rfrlBld			int,
	rfrlCnv			int,
	rfrlAnimia		int,
	rfrlDiabetes	int,
	rfrlJondis		int,
	rfrlHDisease	int,
	rfrlAbdPain		int,
	rfrlDiarrhea	int,
	rfrlProLabr		int,
	rfrlObsLabr		int,
	rfrlUmbcNR		int,
	rfrlUmbcFirst	int,
	rfrlLimbFirst	int,
	rfrlOvrFirst	int,
	rfrlTwin		int,
	rfrlExMtrChild	int,
	rfrlPrevCsr		int,
	rfrlBDGNeg		int,
	rfrlADlvInfc	int,
	rfrlTornPrnm	int,
	rfrlInfcStich	int,
	rfrlAsma        int default 0,
	referralOrder int,
	rfrlOther VARCHAR( 50 ),

	PRIMARY KEY  (componentId),
	constraint fk_rfrl_prg_rcrd foreign key  (pregId) references pregrecord(componentId)
) ;



-- new table for household and population data storage
DROP TABLE if EXISTS appsettings;
CREATE TABLE appsettings
(
  `uniqueCode` varchar(100),
  `name`  varchar(255),
  `totalCount` bigint ,
   PRIMARY KEY  (`uniqueCode`)
) ;



DROP TABLE if EXISTS deathrecord;
CREATE TABLE deathrecord
( 
	componentId bigint NOT NULL default '0',
	beneficiaryId varchar(100),
	beneficiaryType int,
	deathDate datetime default NULL,
	deathTime varchar(11),
	deathMonth int,
	placeOfDeath varchar(200),
	deathCause varchar(200),
	villageOrg varchar(200),
	economicCondition varchar(200),
	gravidOfMother int,
	ageOfMother int,	
	childAge double,	
	pregnancyStage varchar(200),
	childGestLife varchar(50),
	visitId bigint,
	
	constraint	fk_deathrecord_VisitInfo_rf foreign key (visitId) references visitInfo( componentId ),
	PRIMARY KEY (componentId)
);

insert into appsettings value ('total_household','Household',100);
insert into appsettings value ('total_population','Population',1000);


alter table ancfollowup add column nameFile varchar(300);
alter table ancfollowup add column nameStr varchar(300);
alter table ancfollowup add column pictureFile varchar(300);
alter table ancfollowup add column isPrngFine int;
alter table ancfollowup add column isPrgnProbVce varchar(300);

# alter table 
alter table deliveryrecord add column dlvCompOther varchar (300);
alter table deliveryrecord add column isMisoprostolUsed VARCHAR( 20 ) ;
alter table deliveryrecord add column misoprostolGivenBy VARCHAR( 50 ) ;
alter table deliveryrecord add column dlvPlace VARCHAR( 50 ) ;

alter table pncfollowup add column finalSevereHeadache VARCHAR( 50 );

alter table referralrecord add column referralOrder int;
alter table referralrecord add column rfrlAsma int default 0;
alter table referralrecord add column rfrlOther VARCHAR( 50 ) ;

