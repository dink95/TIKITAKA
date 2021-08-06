
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
PROD_NM varchar(60) NOT NULL,
PROD_PRC INT NOT NULL,
CAT_NO INT,
SEL_ID varchar(20),
WAY VARCHAR(15) NOT NULL,
NEGO INT default 0,
CONTENT mediumtext,
WDAY DATETIME default CURRENT_TIMESTAMP,
PROD_CO varchar(60),
PROD_VIEW int default 0,
primary key(PROD_NO),
foreign key(CAT_NO) references CAT(CAT_NO) on update cascade on delete CASCADE,
foreign key(SEL_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
);

alter table prod add IMG_COUNT int default '0'; 
alter table prod add PROD_FINISH boolean default false;

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
/* insert문을 할 시, end time에 들어가게된다.*/
select date_add(current_timestamp(),interval 1 day);

create table AUCTION(
AUC_START_TIME DATETIME default CURRENT_TIMESTAMP,
AUC_END_TIME datetime,
STARTING_BID int default 0,
END_BID int default 0,
PROD_NO INT,
MBR_ID varchar(20),
foreign key(PROD_NO) references PROD(PROD_NO) on update cascade on delete CASCADE,
foreign key(MBR_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
); 

CREATE TABLE CHAT(
CHAT_IDX INT primary KEY auto_increment,
ROOM_NO INT,
PROD_NO INT,
SEND_ID varchar(20),
RECIPIENT_ID varchar(20),
CONTENT TEXT,
SENDTIME DATETIME default CURRENT_TIMESTAMP,
foreign key(PROD_NO) references PROD(PROD_NO) on update cascade on delete CASCADE,
foreign key(SEND_ID) references MBR(MBR_ID) on update cascade on delete CASCADE,
foreign key(RECIPIENT_ID) references MBR(MBR_ID) on update cascade on delete CASCADE
);

alter table prod add ROOM_COUNT int default 0;