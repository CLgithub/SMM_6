/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : smm6

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 09/24/2016 22:45:42 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `depcode` int(11) NOT NULL,
  `depname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentCode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`depcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_department`
-- ----------------------------
BEGIN;
INSERT INTO `sys_department` VALUES ('100000000', '总部', '0', '1'), ('102000000', 'yy', '100000000', '1'), ('102010000', 'yy1', '102000000', '1'), ('102020000', 'yy2', '102000000', '1'), ('103000000', 'zz', '100000000', '1'), ('103020000', 'zz1', '103000000', '1'), ('104000000', 'xx', '100000000', '1'), ('104010000', 'xx1', '104000000', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(255) NOT NULL,
  `operTime` datetime DEFAULT NULL,
  `operName` varchar(255) DEFAULT NULL,
  `operParams` varchar(255) DEFAULT NULL,
  `operResult` varchar(255) DEFAULT NULL,
  `resultMsg` varchar(10240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `menuUrl` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parentID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '菜单', '', '0', '1'), ('2', '菜单管理', '/view/page/system/menu.jsp', '1', '1'), ('3', '用户管理', '/view/page/system/user.jsp', '1', '1'), ('4', '权限管理', '/view/page/system/rights.jsp', '1', '1'), ('5', '日志管理', '/view/page/system/logs.jsp', '1', '1'), ('6', '部门管理', '/view/page/system/departMent.jsp', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu_right`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_right`;
CREATE TABLE `sys_menu_right` (
  `menuID` int(11) NOT NULL,
  `rightID` int(11) NOT NULL,
  PRIMARY KEY (`menuID`,`rightID`),
  KEY `rightID` (`rightID`),
  CONSTRAINT `sys_menu_right_ibfk_1` FOREIGN KEY (`menuID`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `sys_menu_right_ibfk_2` FOREIGN KEY (`rightID`) REFERENCES `sys_rights` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_menu_right`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu_right` VALUES ('1', '1'), ('2', '2'), ('3', '3'), ('4', '4'), ('5', '5'), ('1', '6'), ('2', '8'), ('2', '9'), ('3', '10'), ('3', '12'), ('4', '13'), ('4', '15'), ('2', '16'), ('3', '21'), ('1', '22'), ('1', '24'), ('6', '25'), ('6', '27'), ('6', '29'), ('1', '30'), ('1', '31');
COMMIT;

-- ----------------------------
--  Table structure for `sys_rights`
-- ----------------------------
DROP TABLE IF EXISTS `sys_rights`;
CREATE TABLE `sys_rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `righturl` varchar(50) DEFAULT NULL,
  `rightname` varchar(200) DEFAULT NULL,
  `rightdesc` varchar(200) DEFAULT NULL,
  `rightpos` int(11) DEFAULT NULL,
  `rightcode` bigint(20) DEFAULT NULL,
  `common` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_rights`
-- ----------------------------
BEGIN;
INSERT INTO `sys_rights` VALUES ('1', '/loginController/doLogin', '登录', '登录', '0', '1', b'1'), ('2', '/sysMenuController/getPBBySearch', '查看菜单列表', '查看菜单列表', '0', '2', b'0'), ('3', '/sysUserController/getPBBySearch', '查看用户列表', '查看用户列表', '0', '4', b'0'), ('4', '/sysRightsController/getPBBySearch', '查看权限列表', '查看权限列表', '0', '8', b'0'), ('5', '/sysLogsController/getPBBySearch', '查看日志列表', '查看日志列表', '0', '16', b'0'), ('6', '/loginController/doLogout', '退出', '退出', '0', '32', b'1'), ('8', '/sysMenuController/deleteByID', '删除菜单', '删除菜单', '0', '128', b'0'), ('9', '/sysMenuController/saveOrUpdate', '新增或修改目录', '新增或修改目录', '0', '256', b'0'), ('10', '/sysUserController/saveOrUpdate', '新增或修改用户', '新增或修改用户', '0', '512', b'0'), ('12', '/sysUserController/deleteByIDs', '删除用户', '删除用户', '0', '2048', b'0'), ('13', '/sysRightsController/saveOrUpdate', '新增或修改权限', '新增或修改权限', '0', '4096', b'0'), ('15', '/sysRightsController/deleteByIDs', '删除权限', '删除权限', '0', '16384', b'0'), ('16', '/sysMenuController/getAllList', '得到菜单列表', '得到所有菜单列表', '0', '32768', b'0'), ('21', '/sysRightsController/setRights', '设置人员权限', '设置人员权限', '0', '65536', b'0'), ('22', '/commonController/getRightByUser', '得到用户权限', '根据用户id得到该用户的权限id', '0', '131072', b'1'), ('24', '/commonController/loadUserMenu', '加载用户菜单', '加载用户菜单', '0', '262144', b'1'), ('25', '/sysDepartmentController/getPBBySearch', '查看部门列表', '查看部门列表', '0', '524288', b'0'), ('27', '/sysDepartmentController/saveOrUpdate', '新增或修改部门', '新增或修改部门', '0', '2097152', b'0'), ('29', '/sysDepartmentController/deleteByCode', '删除部门', '删除部门', '0', '4194304', b'0'), ('30', '/commonController/getUserDepList', '得到部门及其以下部门列表', '得到部门及其以下部门列表', '0', '8388608', b'1'), ('31', '/commonController/getUserMenu', '得到用户能见菜单', '得到用户能见菜单', '0', '16777216', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `loginName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sysDepCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sysDepCode` (`sysDepCode`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', '德赫亚', 'dhy', null, '1', '1', '103000000'), ('2', '斯莫林', 'sml', '21232f297a57a5a743894a0e4a801fc3', '2', '1', '102010000'), ('3', '菲尔琼斯', 'feqs', '21232f297a57a5a743894a0e4a801fc3', '4', '1', '102000000'), ('4', '卢克肖', 'lkx', '21232f297a57a5a743894a0e4a801fc3', '3', '1', '102020000'), ('23', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '1', '100000000');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_right`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_right`;
CREATE TABLE `sys_user_right` (
  `userID` int(11) NOT NULL DEFAULT '0',
  `rightID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`,`rightID`),
  KEY `rightID` (`rightID`),
  CONSTRAINT `sys_user_right_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_right_ibfk_2` FOREIGN KEY (`rightID`) REFERENCES `sys_rights` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_user_right`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_right` VALUES ('23', '1'), ('23', '2'), ('3', '3'), ('23', '3'), ('23', '4'), ('23', '5'), ('23', '6'), ('23', '8'), ('23', '9'), ('23', '10'), ('23', '12'), ('23', '13'), ('23', '15'), ('23', '16'), ('23', '21'), ('23', '22'), ('23', '24'), ('23', '25'), ('23', '27'), ('23', '29'), ('23', '30'), ('23', '31');
COMMIT;

-- ----------------------------
--  View structure for `user_right_menu`
-- ----------------------------
DROP VIEW IF EXISTS `user_right_menu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_right_menu` AS select `su`.`name` AS `name`,`su`.`loginName` AS `loginName`,`su`.`password` AS `password`,`su`.`number` AS `number`,`su`.`status` AS `status`,`su`.`sysDepCode` AS `sysDepCode`,`sr`.`righturl` AS `righturl`,`sr`.`rightname` AS `rightname`,`sr`.`rightdesc` AS `rightdesc`,`sm`.`menuName` AS `menuName`,`sm`.`menuUrl` AS `menuUrl`,`sm`.`parentID` AS `parentID`,`su`.`id` AS `userID`,`sr`.`id` AS `rightID`,`sm`.`id` AS `menuID` from ((((`sys_user` `su` join `sys_user_right` `sur`) join `sys_rights` `sr`) join `sys_menu_right` `smr`) join `sys_menu` `sm` on(((`su`.`id` = `sur`.`userID`) and (`sur`.`rightID` = `sr`.`id`) and (`sr`.`id` = `smr`.`rightID`) and (`smr`.`menuID` = `sm`.`id`))));

SET FOREIGN_KEY_CHECKS = 1;
