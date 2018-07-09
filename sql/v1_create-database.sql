use mysql;
-- db 생성
CREATE DATABASE test DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- 유저 생성
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';

-- DB 별 권한 부여
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv) VALUES('localhost','test','test','Y','Y','Y','Y','Y','Y','Y','Y');

FLUSH PRIVILEGES;
