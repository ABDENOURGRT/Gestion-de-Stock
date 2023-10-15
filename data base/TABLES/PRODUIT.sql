--------------------------------------------------------
--  DDL for Table PRODUIT
--------------------------------------------------------

  CREATE TABLE "TPPOO"."PRODUIT" 
   (	"REF_PRO" VARCHAR2(20 BYTE), 
	"NOM_PRO" VARCHAR2(35 BYTE), 
	"QUANT_PRO" NUMBER(*,0), 
	"PRIX_PRO" NUMBER(*,0), 
	"ID_CAT" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;