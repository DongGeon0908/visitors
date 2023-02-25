CREATE DATABASE visitors CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `visitors_snapshot`
(
    `id`          bigint                          NOT NULL AUTO_INCREMENT,
    `target`      text COLLATE utf8mb4_general_ci NOT NULL,
    `count`       bigint                          NOT NULL,
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
