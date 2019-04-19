--------------------------------------------------------
--  建立表格
--------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP TABLE LATEST_NEWS;
DROP TABLE RENTING_HOUSE_INFORMATION;


DROP SEQUENCE ln_no_seq;
DROP SEQUENCE rhi_no_seq;


--------------------------------------------------------
--  for Table LATEST_NEWS 建立最新消息表格
--------------------------------------------------------
CREATE TABLE LATEST_NEWS (
  LN_NO VARCHAR2(10) NOT NULL, 
  LN_CONTENT CLOB, 
  LN_DATE DATE, 
  CONSTRAINT  LATEST_NEWS_PK PRIMARY KEY (LN_NO));
	  
CREATE SEQUENCE ln_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),null, sysdate);
Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),null, sysdate);
Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),null, sysdate);
Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),null, sysdate);
Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),null, sysdate);

--------------------------------------------------------
--  for Table RENTING_HOUSE_INFORMATION 建立租屋資訊表格
--------------------------------------------------------
CREATE TABLE RENTING_HOUSE_INFORMATION (
  RHI_NO VARCHAR2(10) NOT NULL, 
  RHI_CONTENT VARCHAR2(2000), 
  RHI_STATUS NUMBER(10),   
  RHI_DATE DATE,
  RHI_P1 BLOB, 
  RHI_P2 BLOB, 
  RHI_P3 BLOB, 
  RHI_P4 BLOB, 
  RHI_P5 BLOB, 

  CONSTRAINT RENTING_HOUSE_INFORMATION_PK PRIMARY KEY (RHI_NO));
  
CREATE SEQUENCE rhi_no_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into RENTING_HOUSE_INFORMATION (RHI_NO,RHI_CONTENT,RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),null, 0,  sysdate ,null , null, null, null, null);
Insert into RENTING_HOUSE_INFORMATION (RHI_NO,RHI_CONTENT,RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),null, 0,  sysdate ,null , null, null, null, null);
Insert into RENTING_HOUSE_INFORMATION (RHI_NO,RHI_CONTENT,RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),null, 0,  sysdate ,null , null, null, null, null);
Insert into RENTING_HOUSE_INFORMATION (RHI_NO,RHI_CONTENT,RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),null, 0,  sysdate ,null , null, null, null, null);
Insert into RENTING_HOUSE_INFORMATION (RHI_NO,RHI_CONTENT,RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),null, 0,  sysdate ,null , null, null, null, null);


commit;