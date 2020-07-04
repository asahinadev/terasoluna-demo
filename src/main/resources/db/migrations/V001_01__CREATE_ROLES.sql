-- テーブル作成
CREATE TABLE `roles` (
	`id`       
		INT NOT NULL AUTO_INCREMENT
		COMMENT '識別番号' ,
    	
	`code` 
		VARCHAR(50) CHARACTER SET ascii NOT NULL 
		COMMENT '権限コード' ,
    	
	`name` 
		VARCHAR(50) NOT NULL
		COMMENT '権限名',
		
	PRIMARY KEY (`id`)
);

-- キー
ALTER TABLE `roles` ADD UNIQUE KEY `roles_code` USING HASH (`code`);

-- 初期データ投入
INSERT INTO `roles` (`code`,`name`) VALUES ('SYSTEM', 'システム管理者');
INSERT INTO `roles` (`code`,`name`) VALUES ('ADMIN',  '管理者');
INSERT INTO `roles` (`code`,`name`) VALUES ('USER',   '利用者');
