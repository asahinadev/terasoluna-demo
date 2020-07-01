CREATE TABLE `user_roles` (
    `user_id` INT NOT NULL COMMENT '識別番号(users)' ,
    `role_id` INT NOT NULL COMMENT '識別番号(roles)' ,

     PRIMARY KEY (`user_id`, `role_id`),
     CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
     CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

/* マイグレーション実行時の初期ユーザーは１メイのみ */
INSERT INTO `user_roles`  (`user_id`, `role_id`)
SELECT	`users`.`id`, `roles`.`id` FROM	`users`, `roles`;

