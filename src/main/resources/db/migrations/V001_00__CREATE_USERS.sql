-- テーブル作成
CREATE TABLE `users` (
    `id`       
    	INT NOT NULL AUTO_INCREMENT
    	COMMENT '識別番号' ,
    	
    `username` 
    	VARCHAR(255) CHARACTER SET ascii NOT NULL 
    	COMMENT 'アカウント' ,
    	
    `email`    
    	VARCHAR(255) CHARACTER SET ascii NOT NULL 
    	COMMENT 'メールアドレス' ,
    	
    `password` 
    	VARCHAR(255) CHARACTER SET ascii NOT NULL
    	COMMENT 'パスワード' ,
    	
    `enabled`  
    	BIT(1) NOT NULL
    	DEFAULT 0
    	COMMENT '有効フラグ' ,
    	
    `created`  
    	DATETIME NOT NULL 
    	DEFAULT CURRENT_TIMESTAMP 
    	COMMENT '作成日時' ,
    	
    `updated`
    	DATETIME NOT NULL
		DEFAULT CURRENT_TIMESTAMP 
		ON UPDATE CURRENT_TIMESTAMP
		COMMENT '更新日時' ,
    
    PRIMARY KEY (`id`)
);

-- キー
ALTER TABLE `users` ADD UNIQUE KEY `users_username` USING HASH (`username`);
ALTER TABLE `users` ADD UNIQUE KEY `users_email`    USING HASH (`email`);

-- 初期データ投入
INSERT INTO `users` (`username`,`email`,`password`,`enabled`) VALUES ('system','system@example.co.jp','system', 1);
