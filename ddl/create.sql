CREATE DATABASE entrec CHARACTER SET = UTF8;

CREATE USER entrec_user IDENTIFIED BY 'entrec_user!';

GRANT ALL ON entrec.* TO entrec_user;



DROP TABLE entrec.user;

CREATE TABLE entrec.user (
  user_id			varchar(12)		NOT NULL								COMMENT 'ユーザID'
  , family_name		varchar(5)		NULL DEFAULT NULL						COMMENT '姓'
  , name			varchar(5)		NULL DEFAULT NULL						COMMENT '名'
  , mail			varchar(200)	NULL DEFAULT NULL						COMMENT 'メールアドレス'
  , password		varchar(100)	NOT NULL								COMMENT 'パスワード'
  , regist_date		timestamp		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '登録年月日'
  , regist_user_id	varchar(12)		NOT NULL								COMMENT '登録ユーザID'
  , update_date		timestamp		NOT NULL DEFAULT '0000-00-00 00:00:00' 	COMMENT '更新年月日'
  , update_user_id	varchar(12)		NOT NULL								COMMENT '更新ユーザID'
  , version			int(11)			NOT NULL DEFAULT '0'					COMMENT 'バージョン（楽観ロック用）'
  , PRIMARY KEY (user_id)
);



DROP TABLE entrec.user_cookie;

CREATE TABLE entrec.user_cookie (
  user_id 				varchar(12) 	NOT NULL								COMMENT 'ユーザID'
  , cookie_name 		varchar(12) 	NOT NULL								COMMENT 'クッキー名前'
  , cookie_value 		varchar(100) 	NOT NULL								COMMENT 'クッキー値'
  , regist_date 		timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '登録年月日'
  , regist_user_id 		varchar(12) 	NOT NULL 								COMMENT '登録ユーザID'
  , update_date 		timestamp 		NOT NULL DEFAULT '0000-00-00 00:00:00' 	COMMENT '更新年月日'
  , update_user_id 		varchar(12) 	NOT NULL 								COMMENT '更新ユーザID'
  , version 			int(11) 		NOT NULL DEFAULT '0' 					COMMENT 'バージョン（楽観ロック用）'
  , PRIMARY KEY (user_id,cookie_name,cookie_value)
);



DROP TABLE entrec.check_history;

CREATE TABLE entrec.check_history (
  check_year 				smallint(6) 	NOT NULL 								COMMENT 'チェック対象年'
  , check_month				tinyint(4) 		NOT NULL 								COMMENT 'チェック対象月'
  , check_day				tinyint(4) 		NOT NULL 								COMMENT 'チェック対象日'
  , chack_date_seq			varchar(12) 	NOT NULL 								COMMENT 'チェック対象日連番'
  , entry_user_id			varchar(12) 	NULL DEFAULT NULL 						COMMENT '入館者ID'
  , entry_time 				time 			NULL DEFAULT NULL 						COMMENT '入館時刻'
  , exit_user_id 			varchar(12) 	NULL DEFAULT NULL 						COMMENT '退館者名'
  , exit_time 				time 			NULL DEFAULT NULL 						COMMENT '退館時刻'
  , pc_power 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT 'PC電源'
  , clear_desk 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT 'クリアデスク'
  , storage_lock 			bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '保管庫施錠（キャビネット）'
  , light_off 				bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '消灯'
  , door_window_lock 		bit(1) 			NOT NULL DEFAULT b'0'					COMMENT '戸・窓の施錠'
  , regist_date 			timestamp 		NOT NULL DEFAULT CURRENT_TIMESTAMP		COMMENT '登録年月日'
  , regist_user_id 			varchar(12) 	NOT NULL 								COMMENT '登録ユーザID'
  , update_date				timestamp 		NOT NULL DEFAULT '0000-00-00 00:00:00'	COMMENT '更新年月日'
  , update_user_id 			varchar(12) 	NOT NULL 								COMMENT '更新ユーザID'
  , version 				int(11) 		NOT NULL DEFAULT '0' 					COMMENT 'バージョン（楽観ロック用）'
  , PRIMARY KEY (check_year,check_month,check_day,chack_date_seq)
);


