-- Tạo database
DROP DATABASE IF EXISTS smart_motion_db;
CREATE DATABASE smart_motion_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE smart_motion_db;

-- Tạo bảng role (cần thiết để khóa ngoại hoạt động)
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `code` varchar(255) NOT NULL,
                        `createddate` datetime DEFAULT NULL,
                        `modifieddate` datetime DEFAULT NULL,
                        `createdby` varchar(255) DEFAULT NULL,
                        `modifiedby` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dữ liệu mẫu cho bảng role
INSERT INTO `role` VALUES (1,'Quản lý','MANAGER',NULL,NULL,NULL,NULL),(2,'Nhân viên','STAFF',NULL,NULL,NULL,NULL),(3,'Người dùng','USER',NULL,NULL,NULL,NULL);

-- Tạo bảng user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                        `username` VARCHAR(255) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `fullname` VARCHAR(255) DEFAULT NULL,
                        `createddate` DATETIME DEFAULT NULL,
                        `modifieddate` DATETIME DEFAULT NULL,
                        `status` INT DEFAULT NULL,
                        `createdby` VARCHAR(255) DEFAULT NULL,
                        `modifiedby` VARCHAR(255) DEFAULT NULL,
                        `email` VARCHAR(255) DEFAULT NULL,
                        `phone` VARCHAR(20) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dữ liệu mẫu cho bảng user
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES
                       (1, 'nguyenvana', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van a', NULL, NULL, 1, NULL, NULL, NULL, NULL),
                       (2, 'nguyenvanb', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van b', NULL, NULL, 1, NULL, NULL, NULL, NULL),
                       (3, 'nguyenvanc', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van c', NULL, NULL, 1, NULL, NULL, NULL, NULL),
                       (4, 'nguyenvand', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van d', NULL, NULL, 1, NULL, NULL, NULL, NULL);
UNLOCK TABLES;

-- Tạo bảng user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                             `role_id` BIGINT(20) NOT NULL,
                             `user_id` BIGINT(20) NOT NULL,
                             `createddate` DATETIME DEFAULT NULL,
                             `modifieddate` DATETIME DEFAULT NULL,
                             `createdby` VARCHAR(255) DEFAULT NULL,
                             `modifiedby` VARCHAR(255) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `fk_user_role` (`user_id`),
                             KEY `fk_role_user` (`role_id`),
                             CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                             CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dữ liệu mẫu cho bảng user_role
LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES
                            (1, 1, 1, NULL, NULL, NULL, NULL),
                            (2, 2, 2, NULL, NULL, NULL, NULL),
                            (3, 2, 3, NULL, NULL, NULL, NULL),
                            (4, 2, 4, NULL, NULL, NULL, NULL);
UNLOCK TABLES;

CREATE TABLE motion (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        time DATETIME,
                        motiontype VARCHAR(255) ,
                       ưqwqqwewqadas image VARCHAR(255),
                        created_date DATETIME DEFAULT CURRENT_TIMESaâTAMP,
                        modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);saáassassa

INSERT INTO motion (time, motiontype, image) VALUES
                                                 ('2025-07-30 08:15:00', 'person', 'motion_0815.jpg'),
                                                 ('2025-07-30 09:42:13', 'person', 'motion_0942.jpg'),
                                                 aá('2025-07-30 10:20:47', 'unknown', 'motion_1020.jpg'),
                                                 ('2025-07-30 10:59:02', 'person', 'motion_1059.jpg'),
                                                 ('2025-07-30 11:45:30', 'person', 'motion_1145.jpg');
INSERT INTO motion (time, motiontype, image) VALUES
                                                 ('2025-07-30 08:15:00', 'person', 'motion_0815.jpg'),
                                                 ('2025-07-30 09:42:13', 'person', 'motion_0942.jpg'),
                                                 ('2025-07-30 10:20:47', 'unknown', 'motion_1020.jpg'),
                                                 ('2025-07-30 10:59:02', 'person', 'motion_1059.jpg'),
                                                 ('2025-07-30 11:45:30', 'person', 'motion_1145.jpg');

