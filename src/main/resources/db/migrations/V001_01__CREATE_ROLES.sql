CREATE TABLE `roles` (
    `id`   INT          NOT NULL AUTO_INCREMENT  COMMENT '識別番号' ,
    `code` VARCHAR(255) NOT NULL                 COMMENT 'ロール' ,
    `name` VARCHAR(255) NOT NULL                 COMMENT 'ロール' ,

    PRIMARY KEY (`id`),
    UNIQUE `roles_name` (`name`),
    UNIQUE `roles_name` (`code`)
);

INSERT INTO `roles` (`name`) VALUES ('SYSTEM');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');
INSERT INTO `roles` (`name`) VALUES ('USER');
