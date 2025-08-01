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
    image VARCHAR(255),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO motion (time, motiontype, image) VALUES
('2025-07-30 08:00:00', 'person', 'motion_0800.jpg'),
('2025-07-30 08:10:15', 'unknown', 'motion_0810.jpg'),
('2025-07-30 08:20:30', 'unknown', 'motion_0820.jpg'),
('2025-07-30 08:30:45', 'person', 'motion_0830.jpg'),
('2025-07-30 08:40:00', 'person', 'motion_0840.jpg'),
('2025-07-30 08:50:15', 'unknown', 'motion_0850.jpg'),
('2025-07-30 09:00:30', 'person', 'motion_0900.jpg'),
('2025-07-30 09:10:45', 'unknown', 'motion_0910.jpg'),
('2025-07-30 09:21:00', 'person', 'motion_0921.jpg'),
('2025-07-30 09:31:15', 'unknown', 'motion_0931.jpg'),
('2025-07-30 09:41:30', 'person', 'motion_0941.jpg'),
('2025-07-30 09:51:45', 'unknown', 'motion_0951.jpg'),
('2025-07-30 10:02:00', 'person', 'motion_1002.jpg'),
('2025-07-30 10:12:15', 'unknown', 'motion_1012.jpg'),
('2025-07-30 10:22:30', 'unknown', 'motion_1022.jpg'),
('2025-07-30 10:32:45', 'person', 'motion_1032.jpg'),
('2025-07-30 10:43:00', 'unknown', 'motion_1043.jpg'),
('2025-07-30 10:53:15', 'person', 'motion_1053.jpg'),
('2025-07-30 11:03:30', 'unknown', 'motion_1103.jpg'),
('2025-07-30 11:13:45', 'person', 'motion_1113.jpg'),
('2025-07-30 11:24:00', 'unknown', 'motion_1124.jpg'),
('2025-07-30 11:34:15', 'person', 'motion_1134.jpg'),
('2025-07-30 11:44:30', 'unknown', 'motion_1144.jpg'),
('2025-07-30 11:54:45', 'person', 'motion_1154.jpg'),
('2025-07-30 12:05:00', 'unknown', 'motion_1205.jpg'),
('2025-07-30 12:15:15', 'person', 'motion_1215.jpg'),
('2025-07-30 12:25:30', 'unknown', 'motion_1225.jpg'),
('2025-07-30 12:35:45', 'person', 'motion_1235.jpg'),
('2025-07-30 12:46:00', 'unknown', 'motion_1246.jpg'),
('2025-07-30 12:56:15', 'unknown', 'motion_1256.jpg'),
('2025-07-30 13:06:30', 'person', 'motion_1306.jpg'),
('2025-07-30 13:16:45', 'person', 'motion_1316.jpg'),
('2025-07-30 13:27:00', 'unknown', 'motion_1327.jpg'),
('2025-07-30 13:37:15', 'unknown', 'motion_1337.jpg'),
('2025-07-30 13:47:30', 'person', 'motion_1347.jpg'),
('2025-07-30 13:57:45', 'unknown', 'motion_1357.jpg'),
('2025-07-30 14:08:00', 'person', 'motion_1408.jpg'),
('2025-07-30 14:18:15', 'unknown', 'motion_1418.jpg'),
('2025-07-30 14:28:30', 'person', 'motion_1428.jpg'),
('2025-07-30 14:38:45', 'unknown', 'motion_1438.jpg'),
('2025-07-30 14:49:00', 'person', 'motion_1449.jpg'),
('2025-07-30 14:59:15', 'unknown', 'motion_1459.jpg'),
('2025-07-30 15:09:30', 'person', 'motion_1509.jpg'),
('2025-07-30 15:19:45', 'unknown', 'motion_1519.jpg'),
('2025-07-30 15:30:00', 'person', 'motion_1530.jpg'),
('2025-07-30 15:40:15', 'unknown', 'motion_1540.jpg'),
('2025-07-30 15:50:30', 'person', 'motion_1550.jpg');


