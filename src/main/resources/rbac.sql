/*
Navicat MySQL Data Transfer

Source Server         : song
Source Server Version : 80012
Source Host           : 127.0.0.1:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-03-17 21:42:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'el-icon-user-solid', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'el-icon-user', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'el-icon-document-copy', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'el-icon-s-order', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'el-icon-s-tools', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', 'el-icon-platform-eleme', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', 'el-icon-platform-eleme', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('9', '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', 'el-icon-platform-eleme', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('10', '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', 'el-icon-platform-eleme', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('11', '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', 'el-icon-platform-eleme', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('12', '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', 'el-icon-platform-eleme', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('13', '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', 'el-icon-platform-eleme', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('14', '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', 'el-icon-platform-eleme', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('15', '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', 'el-icon-platform-eleme', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('16', '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', 'el-icon-platform-eleme', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('17', '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', 'el-icon-platform-eleme', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('18', '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', 'el-icon-platform-eleme', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('19', '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', 'el-icon-platform-eleme', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('20', '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', 'el-icon-platform-eleme', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('21', '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', 'el-icon-platform-eleme', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('22', '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', 'el-icon-platform-eleme', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('23', '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', 'el-icon-platform-eleme', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('24', '/system/menu/**', '/sys/menu', 'SysMenu', '菜单管理', 'el-icon-platform-eleme', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('25', '/system/role/**', '/sys/role', 'SysRole', '角色管理', 'el-icon-platform-eleme', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('26', '/system/user/**', '/sys/user', 'SysUser', '操作员管理', 'el-icon-platform-eleme', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('27', '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', 'el-icon-platform-eleme', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('28', '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', 'el-icon-platform-eleme', null, '1', '6', '1');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('161', '7', '3');
INSERT INTO `menu_role` VALUES ('162', '7', '6');
INSERT INTO `menu_role` VALUES ('163', '9', '6');
INSERT INTO `menu_role` VALUES ('164', '10', '6');
INSERT INTO `menu_role` VALUES ('165', '11', '6');
INSERT INTO `menu_role` VALUES ('166', '12', '6');
INSERT INTO `menu_role` VALUES ('167', '13', '6');
INSERT INTO `menu_role` VALUES ('168', '14', '6');
INSERT INTO `menu_role` VALUES ('169', '15', '6');
INSERT INTO `menu_role` VALUES ('170', '16', '6');
INSERT INTO `menu_role` VALUES ('171', '17', '6');
INSERT INTO `menu_role` VALUES ('172', '18', '6');
INSERT INTO `menu_role` VALUES ('173', '19', '6');
INSERT INTO `menu_role` VALUES ('174', '20', '6');
INSERT INTO `menu_role` VALUES ('175', '21', '6');
INSERT INTO `menu_role` VALUES ('176', '22', '6');
INSERT INTO `menu_role` VALUES ('177', '23', '6');
INSERT INTO `menu_role` VALUES ('178', '25', '6');
INSERT INTO `menu_role` VALUES ('179', '26', '6');
INSERT INTO `menu_role` VALUES ('180', '27', '6');
INSERT INTO `menu_role` VALUES ('181', '28', '6');
INSERT INTO `menu_role` VALUES ('182', '24', '6');
INSERT INTO `menu_role` VALUES ('247', '7', '4');
INSERT INTO `menu_role` VALUES ('248', '8', '4');
INSERT INTO `menu_role` VALUES ('249', '11', '4');
INSERT INTO `menu_role` VALUES ('250', '7', '2');
INSERT INTO `menu_role` VALUES ('251', '8', '2');
INSERT INTO `menu_role` VALUES ('252', '9', '2');
INSERT INTO `menu_role` VALUES ('253', '10', '2');
INSERT INTO `menu_role` VALUES ('254', '12', '2');
INSERT INTO `menu_role` VALUES ('255', '13', '2');
INSERT INTO `menu_role` VALUES ('756', '7', '1');
INSERT INTO `menu_role` VALUES ('757', '8', '1');
INSERT INTO `menu_role` VALUES ('758', '9', '1');
INSERT INTO `menu_role` VALUES ('759', '10', '1');
INSERT INTO `menu_role` VALUES ('760', '11', '1');
INSERT INTO `menu_role` VALUES ('761', '12', '1');
INSERT INTO `menu_role` VALUES ('762', '13', '1');
INSERT INTO `menu_role` VALUES ('763', '14', '1');
INSERT INTO `menu_role` VALUES ('764', '15', '1');
INSERT INTO `menu_role` VALUES ('765', '16', '1');
INSERT INTO `menu_role` VALUES ('766', '17', '1');
INSERT INTO `menu_role` VALUES ('767', '18', '1');
INSERT INTO `menu_role` VALUES ('768', '19', '1');
INSERT INTO `menu_role` VALUES ('769', '20', '1');
INSERT INTO `menu_role` VALUES ('770', '21', '1');
INSERT INTO `menu_role` VALUES ('771', '22', '1');
INSERT INTO `menu_role` VALUES ('772', '23', '1');
INSERT INTO `menu_role` VALUES ('773', '24', '1');
INSERT INTO `menu_role` VALUES ('774', '25', '1');
INSERT INTO `menu_role` VALUES ('775', '26', '1');
INSERT INTO `menu_role` VALUES ('776', '27', '1');
INSERT INTO `menu_role` VALUES ('777', '28', '1');
INSERT INTO `menu_role` VALUES ('780', '7', '25');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_manager', '部门经理');
INSERT INTO `role` VALUES ('2', 'ROLE_personnel', '人事专员');
INSERT INTO `role` VALUES ('3', 'ROLE_recruiter', '招聘主管');
INSERT INTO `role` VALUES ('4', 'ROLE_train', '培训主管');
INSERT INTO `role` VALUES ('5', 'ROLE_performance', '薪酬绩效主管');
INSERT INTO `role` VALUES ('6', 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES ('25', 'ROLE_test1', '测试');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'hrID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '系统管理员', '18568887789', '029-82881234', '深圳南山', '1', 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '123');
INSERT INTO `user` VALUES ('5', '李白', '18568123489', '029-82123434', '海口美兰', '1', 'libai', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'http://localhost:8080/upload/2020/03/17/1eece5b3-49b0-45cc-b7de-f2eb7d229717.jpg', '123456');
INSERT INTO `user` VALUES ('10', '韩愈', '18568123666', '029-82111555', '广州番禺', '1', 'hanyu', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg', null);
INSERT INTO `user` VALUES ('11', '柳宗元', '18568123377', '029-82111333', '广州天河', '1', 'liuzongyuan', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515233756&di=0856d923a0a37a87fd26604a2c871370&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2014-09-27%2F041716704.jpg', null);
INSERT INTO `user` VALUES ('12', '曾巩', '18568128888', '029-82111222', '广州越秀', '1', 'zenggong', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `hr_role_ibfk_1` (`uid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('35', '12', '4');
INSERT INTO `user_role` VALUES ('36', '12', '3');
INSERT INTO `user_role` VALUES ('37', '12', '2');
INSERT INTO `user_role` VALUES ('43', '11', '3');
INSERT INTO `user_role` VALUES ('44', '11', '2');
INSERT INTO `user_role` VALUES ('45', '11', '4');
INSERT INTO `user_role` VALUES ('46', '11', '5');
INSERT INTO `user_role` VALUES ('146', '5', '1');
INSERT INTO `user_role` VALUES ('172', '3', '6');
INSERT INTO `user_role` VALUES ('176', '10', '1');
