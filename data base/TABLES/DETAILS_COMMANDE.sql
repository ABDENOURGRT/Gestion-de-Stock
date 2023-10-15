--------------------------------------------------------
--  DDL for Table DETAILS_COMMANDE
--------------------------------------------------------

  CREATE TABLE "TPPOO"."DETAILS_COMMANDE" 
   (	"ID_COM" NUMBER(*,0), 
	"REF_PRO" VARCHAR2(20 BYTE), 
	"QUANTITE" NUMBER(*,0), 
	"FIDEL" NUMBER(*,0), 
	"MONTANT" FLOAT(126)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
