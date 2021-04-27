
use tiki;

create table MBR(
MBR_ID varchar(20) primary key,
MBR_PWD varbinary(60) NOT NULL,
MBR_NM varchar(20) NOT NULL,
MBR_BDAY varchar(20) NOT NULL,
MBR_ADDR varchar(30) NOT NULL,
MBR_ROLE boolean NOT NULL default false,
MBR_POINTS INT NOT NULL default 0,
MBR_GRADE varchar(20),
MBR_EMAIL varchar(30) NOT NULL unique,
MBR_PHONE varchar(20) NOT NULL unique 

);


CREATE table CAT(
CAT_NM VARCHAR(20) NOT NULL,
CAT_NO INT primary key
);

create TABLE PROD(
PROD_NO INT auto_increment,
PROD_NM varchar(20) NOT NULL,
PROD_PRC INT NOT NULL,
CAT_NO INT,
SEL_ID varchar(20),
WAY VARCHAR(15) NOT NULL,
NEGO INT default 0,
WDAY DATE NOT NULL,
PROD_CO varchar(60),
PROD_VIEW int default 0,
primary key(PROD_NO),
foreign key(CAT_NO) references CAT(CAT_NO) on update cascade on delete CASCADE,
foreign key(SEL_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
);



CREATE TABLE MANAGER(
MAN_ID varchar(20) primary KEY,
MAN_PWD varchar(60) NOT NULL,
MAN_NM varchar(20) not null,
MAN_EMAIL varchar(30) NOT NULL,
MAN_PNUM varchar(20) NOT NULL
);

CREATE table COMP(
COMP_IDX INT primary KEY auto_increment,
COMP_NM VARCHAR(40) NOT NULL,
COMP_CON text,
SPT varchar(20),
REPO varchar(20),
foreign key(SPT) references MBR(MBR_ID) on update cascade on delete CASCADE,
foreign key(REPO) references MBR(MBR_ID) on update cascade on delete CASCADE
);

create TABLE BLACKLIST(
BLIST_ID varchar(20),
foreign key(BLIST_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
);

CREATE TABLE BUYLIST(
PROD_NO INT,
BUYER_ID varchar(20),
foreign key(PROD_NO) references PROD(PROD_NO) on update cascade on delete CASCADE,
foreign key(BUYER_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
);


