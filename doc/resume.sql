/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : resume

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-09-21 18:44:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `demo_t`
-- ----------------------------
DROP TABLE IF EXISTS `demo_t`;
CREATE TABLE `demo_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_t
-- ----------------------------
INSERT INTO `demo_t` VALUES ('1', '测试', 'sfasgfaf', '24');

-- ----------------------------
-- Table structure for `u_permission`
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL COMMENT 'menu(菜单)、button(按钮)',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `perm_code` varchar(50) DEFAULT NULL COMMENT '权限标识，如：user:create',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_permission
-- ----------------------------
INSERT INTO `u_permission` VALUES ('4', 'button', '权限列表', '/permission/index.shtml', 'perm:query');
INSERT INTO `u_permission` VALUES ('6', 'button', '权限添加', '/permission/addPermission.shtml', 'perm:create');
INSERT INTO `u_permission` VALUES ('7', 'button', '权限删除', '/permission/deletePermissionById.shtml', 'perm:delete');
INSERT INTO `u_permission` VALUES ('8', 'menu', '用户列表', '/member/list.shtml', 'user:query');
INSERT INTO `u_permission` VALUES ('9', 'menu', '在线用户', '/member/online.shtml', 'user:query');
INSERT INTO `u_permission` VALUES ('10', 'button', '用户Session踢出', '/member/changeSessionStatus.shtml', 'user:kickout');
INSERT INTO `u_permission` VALUES ('11', 'button', '用户激活&禁止', '/member/forbidUserById.shtml', 'user:update');
INSERT INTO `u_permission` VALUES ('12', 'button', '用户删除', '/member/deleteUserById.shtml', 'user:delete');
INSERT INTO `u_permission` VALUES ('13', 'button', '权限修改', '/permission/addPermission2Role.shtml', 'perm:update');
INSERT INTO `u_permission` VALUES ('14', 'button', '用户角色分配清空', '/role/clearRoleByUserIds.shtml', 'role:update');
INSERT INTO `u_permission` VALUES ('15', 'button', '角色分配保存', '/role/addRole2User.shtml', 'role:update');
INSERT INTO `u_permission` VALUES ('16', 'button', '角色列表删除', '/role/deleteRoleById.shtml', 'role:delete');
INSERT INTO `u_permission` VALUES ('17', 'button', '角色列表添加', '/role/addRole.shtml', 'role:create');
INSERT INTO `u_permission` VALUES ('18', 'meun', '角色列表', '/role/index.shtml', 'role:query');
INSERT INTO `u_permission` VALUES ('19', 'button', '权限分配', '/permission/allocation.shtml', 'role:perm');
INSERT INTO `u_permission` VALUES ('20', 'button', '角色分配', '/role/allocation.shtml', 'user:role');
INSERT INTO `u_permission` VALUES ('21', 'menu', 'demo查询', '/demo/query', 'demo:query');

-- ----------------------------
-- Table structure for `u_role`
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', '系统管理员', '888888');
INSERT INTO `u_role` VALUES ('3', '权限角色', '100003');
INSERT INTO `u_role` VALUES ('4', '用户中心', '100002');

-- ----------------------------
-- Table structure for `u_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_permission
-- ----------------------------
INSERT INTO `u_role_permission` VALUES ('1', '1', '8');
INSERT INTO `u_role_permission` VALUES ('2', '4', '9');
INSERT INTO `u_role_permission` VALUES ('3', '4', '10');
INSERT INTO `u_role_permission` VALUES ('4', '4', '11');
INSERT INTO `u_role_permission` VALUES ('5', '4', '12');
INSERT INTO `u_role_permission` VALUES ('6', '3', '4');
INSERT INTO `u_role_permission` VALUES ('7', '3', '6');
INSERT INTO `u_role_permission` VALUES ('8', '3', '7');
INSERT INTO `u_role_permission` VALUES ('9', '3', '13');
INSERT INTO `u_role_permission` VALUES ('10', '3', '14');
INSERT INTO `u_role_permission` VALUES ('11', '3', '15');
INSERT INTO `u_role_permission` VALUES ('12', '3', '16');
INSERT INTO `u_role_permission` VALUES ('13', '3', '17');
INSERT INTO `u_role_permission` VALUES ('14', '3', '18');
INSERT INTO `u_role_permission` VALUES ('15', '3', '19');
INSERT INTO `u_role_permission` VALUES ('16', '3', '20');
INSERT INTO `u_role_permission` VALUES ('17', '1', '4');
INSERT INTO `u_role_permission` VALUES ('18', '1', '6');
INSERT INTO `u_role_permission` VALUES ('19', '1', '7');
INSERT INTO `u_role_permission` VALUES ('20', '1', '8');
INSERT INTO `u_role_permission` VALUES ('21', '1', '9');
INSERT INTO `u_role_permission` VALUES ('22', '1', '10');
INSERT INTO `u_role_permission` VALUES ('23', '1', '11');
INSERT INTO `u_role_permission` VALUES ('24', '1', '12');
INSERT INTO `u_role_permission` VALUES ('25', '1', '13');
INSERT INTO `u_role_permission` VALUES ('26', '1', '14');
INSERT INTO `u_role_permission` VALUES ('27', '1', '15');
INSERT INTO `u_role_permission` VALUES ('28', '1', '16');
INSERT INTO `u_role_permission` VALUES ('29', '1', '17');
INSERT INTO `u_role_permission` VALUES ('30', '1', '18');
INSERT INTO `u_role_permission` VALUES ('31', '1', '19');
INSERT INTO `u_role_permission` VALUES ('32', '1', '20');
INSERT INTO `u_role_permission` VALUES ('33', '1', '21');

-- ----------------------------
-- Table structure for `u_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `account` varchar(128) DEFAULT NULL COMMENT '登录帐号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2016-06-16 11:15:33', '2016-09-17 15:19:38', '1');
INSERT INTO `u_user` VALUES ('11', 'soso', '8446666@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '2016-05-26 20:50:54', '2016-06-16 11:24:35', '1');
INSERT INTO `u_user` VALUES ('12', '8446666', '8446666', 'e10adc3949ba59abbe56e057f20f883e', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '1');

-- ----------------------------
-- Table structure for `u_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO `u_user_role` VALUES ('1', '12', '4');
INSERT INTO `u_user_role` VALUES ('2', '11', '3');
INSERT INTO `u_user_role` VALUES ('3', '11', '4');
INSERT INTO `u_user_role` VALUES ('4', '1', '1');
