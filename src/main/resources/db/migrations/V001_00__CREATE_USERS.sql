-- テーブル作成
CREATE TABLE `users` (
    `id`       INT          NOT NULL AUTO_INCREMENT      COMMENT '識別番号' ,
    `username` VARCHAR(255) NOT NULL CHARACTER SET ascii COMMENT 'アカウント' ,
    `email`    VARCHAR(255) NOT NULL CHARACTER SET ascii COMMENT 'メールアドレス' ,
    `password` VARCHAR(255) NOT NULL CHARACTER SET ascii COMMENT 'パスワード' ,
    `enabled`  BIT(1)       NOT NULL                     COMMENT '有効フラグ' ,
    `created`  DATETIME     NOT NULL                     COMMENT '作成日時' ,
    `updated`  DATETIME     NOT NULL                     COMMENT '更新日時' 
);

-- キー
ALTER TABLE `users` ADD PRIMARY KEY (`id`);
ALTER TABLE `users` ADD UNIQUE KEY `users_username` USING HASH (`username`)
ALTER TABLE `users` ADD UNIQUE KEY `users_email`    USING HASH (`email`)

-- 初期値
ALTER TABLE `users` ALTER `enabled` SET DEFAULT 0; 
ALTER TABLE `users` ALTER `created` SET DEFAULT CURRENT_TIMESTAMP; 
ALTER TABLE `users` ALTER `updated` SET DEFAULT CURRENT_TIMESTAMP; 
 
-- 初期データ投入
INSERT INTO `users` (`username`,`email`               ,`password`,`enabled`) 
VALUES              ('system'  ,'system@example.co.jp','system'  , 1       );
