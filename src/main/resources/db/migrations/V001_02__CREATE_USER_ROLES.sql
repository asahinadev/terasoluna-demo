-- テーブル作成
CREATE TABLE `user_roles` (
    `user_id` INT NOT NULL COMMENT '識別番号(users)' ,
    `role_id` INT NOT NULL COMMENT '識別番号(roles)' 
);

-- キー
ALTER TABLE `users` ADD PRIMARY KEY (`id`);
ALTER TABLE `users` ADD FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;
ALTER TABLE `users` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

/* マイグレーション実行時の初期ユーザーは１メイのみ */
INSERT INTO `user_roles`  (`user_id`, `role_id`)
SELECT	`users`.`id`, `roles`.`id` FROM	`users`, `roles`;

