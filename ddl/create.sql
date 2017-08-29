CREATE DATABASE entrec CHARACTER SET = UTF8;

CREATE USER entrec_user IDENTIFIED BY 'entrec_user!';

GRANT ALL ON entrec.* TO entrec_user;



DROP TABLE entrec.user;

CREATE TABLE entrec.user (
  user_id			varchar(12)		NOT NULL								COMMENT '���[�UID'
  , family_name		varchar(5)		NULL DEFAULT NULL						COMMENT '��'
  , name			varchar(5)		NULL DEFAULT NULL						COMMENT '��'
  , mail			varchar(200)	NULL DEFAULT NULL						COMMENT '���[���A�h���X'
  , password		varchar(100)	NOT NULL								COMMENT '�p�X���[�h'
  , regist_date		timestamp		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�o�^�N����'
  , regist_user_id	varchar(12)		NOT NULL								COMMENT '�o�^���[�UID'
  , update_date		timestamp		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�X�V�N����'
  , update_user_id	varchar(12)		NOT NULL								COMMENT '�X�V���[�UID'
  , version			int(11)			NOT NULL DEFAULT '0'					COMMENT '�o�[�W�����i�y�σ��b�N�p�j'
  , PRIMARY KEY (user_id)
);



DROP TABLE entrec.user_cookie;

CREATE TABLE entrec.user_cookie (
  user_id 				varchar(12) 	NOT NULL								COMMENT '���[�UID'
  , cookie_name 		varchar(12) 	NOT NULL								COMMENT '�N�b�L�[���O'
  , cookie_value 		varchar(100) 	NOT NULL								COMMENT '�N�b�L�[�l'
  , regist_date 		timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�o�^�N����'
  , regist_user_id 		varchar(12) 	NOT NULL 								COMMENT '�o�^���[�UID'
  , update_date 		timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�X�V�N����'
  , update_user_id 		varchar(12) 	NOT NULL 								COMMENT '�X�V���[�UID'
  , version 			int(11) 		NOT NULL DEFAULT '0' 					COMMENT '�o�[�W�����i�y�σ��b�N�p�j'
  , PRIMARY KEY (user_id,cookie_name,cookie_value)
);



DROP TABLE entrec.check_history;

CREATE TABLE entrec.check_history (
  check_year 				smallint(6) 	NOT NULL 								COMMENT '�`�F�b�N�Ώ۔N'
  , check_month				tinyint(4) 		NOT NULL 								COMMENT '�`�F�b�N�Ώی�'
  , check_day				tinyint(4) 		NOT NULL 								COMMENT '�`�F�b�N�Ώۓ�'
  , chack_date_seq			varchar(12) 	NOT NULL 								COMMENT '�`�F�b�N�Ώۓ��A��'
  , entry_user_id			varchar(12) 	NULL DEFAULT NULL 						COMMENT '���َ�ID'
  , entry_time 				time 			NULL DEFAULT NULL 						COMMENT '���َ���'
  , exit_user_id 			varchar(12) 	NULL DEFAULT NULL 						COMMENT '�ފَҖ�'
  , exit_time 				time 			NULL DEFAULT NULL 						COMMENT '�ފَ���'
  , pc_power 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT 'PC�d��'
  , clear_desk 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '�N���A�f�X�N'
  , storage_lock 			bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '�ۊǌɎ{���i�L���r�l�b�g�j'
  , light_off 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '����'
  , door_window_lock 		bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '�ˁE���̎{��'
  , regist_date 			timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�o�^�N����'
  , regist_user_id 			varchar(12) 	NOT NULL 								COMMENT '�o�^���[�UID'
  , update_date				timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '�X�V�N����'
  , update_user_id 			varchar(12) 	NOT NULL 								COMMENT '�X�V���[�UID'
  , version 				int(11) 		NOT NULL DEFAULT '0' 					COMMENT '�o�[�W�����i�y�σ��b�N�p�j'
  , PRIMARY KEY (check_year,check_month,check_day,chack_date_seq)
);


