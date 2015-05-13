#--------------------------------------------------------#
### Copy this section into the defaultscript.sql file ###
#--------------------------------------------------------#


drop table if exists shippinginfo ;
create table shippinginfo
(
	 componentId BIGINT not null,
	 uniqueCode VARCHAR(50),
	 customercode varchar(50),
	 customername VARCHAR (250),
	 address varchar(500),
	 countryid bigint,
	 city bigint,
	 attentionname VARCHAR (250),
	paymenttype bigint,
	creditterms bigint,
	ircno varchar(50),
	currencyid bigint,
	aramount double,
	telephone varchar(50),
	fax varchar(50),
	email varchar(50),
	mobileno varchar(50),
	creationdate datetime,
	salesperson varchar(50),
	creditlimit varchar(50),
	 status INT default 0,
	 description VARCHAR(250),
	 updateddate datetime default NULL,
	 updatedby BIGINT default null,
	 primary key (componentId)
);