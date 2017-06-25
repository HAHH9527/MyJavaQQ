/*
Navicat MySQL Data Transfer

Source Server         : linode
Source Server Version : 50636
Source Host           : 172.104.74.239:3306
Source Database       : MyJavaQQ_DB

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-06-24 13:57:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group_List
-- ----------------------------
DROP TABLE IF EXISTS `group_List`;
CREATE TABLE `group_List` (
  `group_Number` int(11) NOT NULL AUTO_INCREMENT COMMENT '群号',
  `group_Master` int(11) NOT NULL COMMENT '群主',
  PRIMARY KEY (`group_Number`),
  KEY `group_Master` (`group_Master`),
  KEY `group_Master_2` (`group_Master`),
  KEY `group_Master_3` (`group_Master`),
  KEY `group_Master_4` (`group_Master`),
  KEY `group_Master_5` (`group_Master`),
  KEY `group_Master_6` (`group_Master`),
  KEY `group_Master_7` (`group_Master`),
  CONSTRAINT `akg` FOREIGN KEY (`group_Master`) REFERENCES `user_Info` (`user_Number`) ON DELETE CASCADE,
  CONSTRAINT `akj` FOREIGN KEY (`group_Number`) REFERENCES `join_Group` (`group_Number`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_List
-- ----------------------------

-- ----------------------------
-- Table structure for join_Group
-- ----------------------------
DROP TABLE IF EXISTS `join_Group`;
CREATE TABLE `join_Group` (
  `user_Number` int(11) NOT NULL,
  `group_Number` int(11) NOT NULL,
  PRIMARY KEY (`user_Number`,`group_Number`),
  KEY `User_Number` (`user_Number`),
  KEY `Group_Number` (`group_Number`),
  CONSTRAINT `fk5` FOREIGN KEY (`user_Number`) REFERENCES `user_Info` (`user_Number`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_Group
-- ----------------------------

-- ----------------------------
-- Table structure for user_Friends
-- ----------------------------
DROP TABLE IF EXISTS `user_Friends`;
CREATE TABLE `user_Friends` (
  `myself_Number` int(11) NOT NULL COMMENT '主体账号',
  `friend_Number` int(11) NOT NULL COMMENT '好友账号',
  PRIMARY KEY (`myself_Number`,`friend_Number`),
  KEY `friend_Number` (`friend_Number`),
  KEY `friend_Number_2` (`friend_Number`),
  KEY `friend_Number_3` (`friend_Number`),
  CONSTRAINT `fkfk2` FOREIGN KEY (`friend_Number`) REFERENCES `user_Info` (`user_Number`) ON UPDATE CASCADE,
  CONSTRAINT `fkfkfk` FOREIGN KEY (`myself_Number`) REFERENCES `user_Info` (`user_Number`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_Friends
-- ----------------------------
INSERT INTO `user_Friends` VALUES ('100001', '100000');
INSERT INTO `user_Friends` VALUES ('100000', '100001');
INSERT INTO `user_Friends` VALUES ('100002', '100001');
INSERT INTO `user_Friends` VALUES ('100003', '100001');
INSERT INTO `user_Friends` VALUES ('100004', '100001');
INSERT INTO `user_Friends` VALUES ('100001', '100002');
INSERT INTO `user_Friends` VALUES ('100003', '100002');
INSERT INTO `user_Friends` VALUES ('100001', '100003');
INSERT INTO `user_Friends` VALUES ('100002', '100003');
INSERT INTO `user_Friends` VALUES ('100001', '100004');

-- ----------------------------
-- Table structure for user_Info
-- ----------------------------
DROP TABLE IF EXISTS `user_Info`;
CREATE TABLE `user_Info` (
  `user_Number` int(11) NOT NULL AUTO_INCREMENT COMMENT '账号',
  `user_Password` varchar(20) NOT NULL COMMENT '密码',
  `user_Regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  `user_Nickname` varchar(20) NOT NULL COMMENT '昵称',
  `user_Online` enum('在线','离线') NOT NULL DEFAULT '离线' COMMENT '是否在线',
  `user_Sex` enum('男','女','') DEFAULT NULL COMMENT '性别',
  `user_Birthday` date DEFAULT NULL COMMENT '生日',
  `user_Phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `user_Address` varchar(50) DEFAULT '' COMMENT '地址',
  `user_Hometown` varchar(50) DEFAULT '' COMMENT '家乡',
  `user_Other` varchar(50) DEFAULT '' COMMENT '其他说明',
  PRIMARY KEY (`user_Number`)
) ENGINE=InnoDB AUTO_INCREMENT=100009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_Info
-- ----------------------------
INSERT INTO `user_Info` VALUES ('100000', 'password', '2017-06-14 16:34:06', '管理员账号', '离线', '', '2017-06-09', '18974576261', '', '', '');
INSERT INTO `user_Info` VALUES ('100001', '123456', '2017-06-15 09:59:25', '测试账号1', '离线', '', '2017-06-15', '', '', '', '');
INSERT INTO `user_Info` VALUES ('100002', '123456', '2017-06-15 12:17:22', '测试账号2', '离线', '', '2017-06-15', '', '', '', '');
INSERT INTO `user_Info` VALUES ('100003', '123456', '2017-06-15 12:20:43', '测试账号3', '离线', '', '2017-06-15', '', '', '', '');
INSERT INTO `user_Info` VALUES ('100004', '123456', '2017-06-17 08:08:31', '测试账号4', '离线', '', '2017-06-15', '', '', '', '');
INSERT INTO `user_Info` VALUES ('100005', '123456', '2017-06-17 08:08:42', '测试账号5', '离线', '', '2017-06-15', '', '', '', '');
INSERT INTO `user_Info` VALUES ('100006', '123456', '2017-06-19 16:08:36', '注册测试1', '离线', '男', '2017-06-19', '', '湖南', '湖南', '这是注册测试账号');
INSERT INTO `user_Info` VALUES ('100007', '123456', '2017-06-19 16:10:28', '注册测试2', '离线', '男', '1999-03-09', '', '湘潭', '湘潭', '其他');
INSERT INTO `user_Info` VALUES ('100008', '123456', '2017-06-22 07:06:06', '注册测试3', '离线', '', '2017-06-09', '18974576261', '', '', '');

-- ----------------------------
-- Table structure for user_Safe
-- ----------------------------
DROP TABLE IF EXISTS `user_Safe`;
CREATE TABLE `user_Safe` (
  `user_Number` int(11) NOT NULL COMMENT '账号',
  `safe_ Problem` varchar(20) NOT NULL COMMENT '问题',
  `safe_Answer` varchar(20) NOT NULL COMMENT '答案',
  PRIMARY KEY (`safe_ Problem`,`user_Number`),
  KEY `user_Number` (`user_Number`),
  KEY `user_Number_2` (`user_Number`),
  KEY `user_Number_3` (`user_Number`),
  CONSTRAINT `fkfk` FOREIGN KEY (`user_Number`) REFERENCES `user_Info` (`user_Number`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_Safe
-- ----------------------------

-- ----------------------------
-- View structure for View_AllgroupView_Allgroup
-- ----------------------------
DROP VIEW IF EXISTS `View_AllgroupView_Allgroup`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `View_AllgroupView_Allgroup` AS select `join_Group`.`group_Number` AS `group_Number` from `join_Group` ;

-- ----------------------------
-- View structure for View_AllQQView_AllQQView_AllQQView_AllQQView_AllQQ
-- ----------------------------
DROP VIEW IF EXISTS `View_AllQQView_AllQQView_AllQQView_AllQQView_AllQQ`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `View_AllQQView_AllQQView_AllQQView_AllQQView_AllQQ` AS select `user_Info`.`user_Number` AS `user_Number` from `user_Info` ;
SET FOREIGN_KEY_CHECKS=1;
