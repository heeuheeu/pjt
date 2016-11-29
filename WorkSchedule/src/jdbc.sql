select * from incmember

SELECT * FROM DIVISION

DROP DEPARTMENT CASCADE CONSTRAINT

--사업부_DIVISION--

CREATE TABLE DIVISION( 
DIVID 		VARCHAR2(10),
DIVNAME		VARCHAR2(20),
CONSTRAINT 	DIVISION_PK PRIMARY KEY(DIVID)
);


--부서_DEPARTMENT--

CREATE TABLE DEPARTMENT(
DEPTID 		VARCHAR2(10),
DEPTNAME	VARCHAR2(30),
DIVID 		VARCHAR2(10),
CONSTRAINT 	DEPARTMENT_PK PRIMARY KEY(DEPTID),
CONSTRAINT	DEPARTMENT_FK FOREIGN KEY(DIVID) REFERENCES DIVISION(DIVID)
);


--직원_EMPLOYEE--

CREATE TABLE EMPLOYEE(
EMPID		VARCHAR2(10),
EMPPWD		VARCHAR2(30),
EMPNAME		VARCHAR2(30),
EMPGRADE	VARCHAR2(30),
EMPPHONE	VARCHAR2(30),
EMPMAIL		VARCHAR2(30),
EMPLOC		VARCHAR2(30) 	DEFAULT '본사 10F',
DEPTID		VARCHAR2(10),
CONSTRAINT	EMPLOYEE_PK 	PRIMARY KEY(EMPID),
CONSTRAINT	EMPLOYEE_FK		FOREIGN KEY(DEPTID) REFERENCES DEPARTMENT(DEPTID)
);

--즐겨찾기_FAVORITE--

CREATE TABLE FAVORITE(
EMPID		VARCHAR2(10) NOT NULL,
EMPIDFAV	VARCHAR2(10) NOT NULL,
CONSTRAINT	FAVORITE_FK FOREIGN KEY(EMPIDFAV) REFERENCES EMPLOYEE(EMPID)
);

ALTER TABLE FAVORITE 
ADD CONSTRAINT FAVORITE_PK PRIMARY KEY (EMPID, EMPIDFAV)

--근무_WORK--

CREATE TABLE WORK(
WORKDATE	DATE NOT NULL,
EMPID		VARCHAR2(10) NOT NULL,
AMLOC		VARCHAR2(30),
AMLOCDETAIL VARCHAR2(30),
PMLOC		VARCHAR2(30),
PMLOCDETAIL	VARCHAR2(30),
CONSTRAINT	WORK_FK FOREIGN KEY(EMPID) REFERENCES EMPLOYEE(EMPID)
);

ALTER TABLE WORK 
ADD CONSTRAINT WORK_PK PRIMARY KEY (WORKDATE, EMPID)

--근무지_LOCATIONLIST--

CREATE TABLE LOCATIONLIST(
LOCNUM		NUMBER(20),
LOCNAME		VARCHAR2(30),
CONSTRAINT	LOCATIONLIST_PK PRIMARY KEY(LOCNUM)
);
