DROP TABLE user;

CREATE TABLE user (
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


