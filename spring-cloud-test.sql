/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : spring-cloud-test

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 09/17/2017 11:09:10 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `OAUTH_ACCESS_TOKEN`
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_ACCESS_TOKEN`;
CREATE TABLE `OAUTH_ACCESS_TOKEN` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION_ID` varchar(256) DEFAULT NULL,
  `USER_NAME` varchar(256) DEFAULT NULL,
  `CLIENT_ID` varchar(256) DEFAULT NULL,
  `AUTHENTICATION` blob,
  `REFRESH_TOKEN` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `OAUTH_APPROVALS`
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_APPROVALS`;
CREATE TABLE `OAUTH_APPROVALS` (
  `USERID` varchar(256) DEFAULT NULL,
  `CLIENTID` varchar(256) DEFAULT NULL,
  `SCOPE` varchar(256) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXPIRESAT` timestamp NULL DEFAULT NULL,
  `LASTMODIFIEDAT` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `OAUTH_CLIENT_DETAILS`
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_CLIENT_DETAILS`;
CREATE TABLE `OAUTH_CLIENT_DETAILS` (
  `CLIENT_ID` varchar(255) NOT NULL,
  `RESOURCE_IDS` varchar(256) DEFAULT NULL,
  `CLIENT_SECRET` varchar(256) DEFAULT NULL,
  `SCOPE` varchar(256) DEFAULT NULL,
  `AUTHORIZED_GRANT_TYPES` varchar(256) DEFAULT NULL,
  `WEB_SERVER_REDIRECT_URI` varchar(256) DEFAULT NULL,
  `AUTHORITIES` varchar(256) DEFAULT NULL,
  `ACCESS_TOKEN_VALIDITY` int(11) DEFAULT NULL,
  `REFRESH_TOKEN_VALIDITY` int(11) DEFAULT NULL,
  `ADDITIONAL_INFORMATION` varchar(4096) DEFAULT NULL,
  `AUTOAPPROVE` varchar(45) DEFAULT 'true',
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `OAUTH_CLIENT_DETAILS`
-- ----------------------------
BEGIN;
INSERT INTO `OAUTH_CLIENT_DETAILS` VALUES ('ui', null, 'secret', 'ui-scope', 'authorization_code,password,refresh_token,client_credentials', '', 'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', '30000', '30000', null, 'false'), ('zuul', null, 'secret', 'server-scope', 'client_credentials', '', 'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', '30000', '30000', null, 'false');
COMMIT;

-- ----------------------------
--  Table structure for `OAUTH_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_CODE`;
CREATE TABLE `OAUTH_CODE` (
  `CODE` varchar(256) DEFAULT NULL,
  `AUTHENTICATION` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `OAUTH_REFRESH_TOKEN`
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_REFRESH_TOKEN`;
CREATE TABLE `OAUTH_REFRESH_TOKEN` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `USER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `ID` char(36) NOT NULL,
  `LOGIN_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `NICK_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `STATUS` varchar(20) NOT NULL,
  `GRADE` tinyint(2) DEFAULT '0',
  `VERSION` bigint(20) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID_UINDEX` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `USER`
-- ----------------------------
BEGIN;
INSERT INTO `USER` VALUES ('806f0ac6-a3e4-42a1-8dfc-1a3e56002881', 'gavin', '$2a$10$lZtZ84C7opaUODCAdYzhwuNOuGqpSVjZLiM/gcZiAqEDDI/Vfq/Vu', 'gavin-guo', 'gavin.guo@msn.com', '13621670031', 'ENABLED', '1', '1', '2016-11-03 07:25:03', '2016-11-03 07:25:03');
COMMIT;

-- ----------------------------
--  Table structure for `USER_AUTHORITY`
-- ----------------------------
DROP TABLE IF EXISTS `USER_AUTHORITY`;
CREATE TABLE `USER_AUTHORITY` (
  `ID` char(36) NOT NULL,
  `USER_ID` char(36) NOT NULL,
  `AUTHORITY` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_AUTHORITY_ID_UINDEX` (`ID`),
  KEY `USER_AUTHORITY_USER_ID_FK` (`USER_ID`),
  CONSTRAINT `USER_AUTHORITY_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `USER_AUTHORITY`
-- ----------------------------
BEGIN;
INSERT INTO `USER_AUTHORITY` VALUES ('bca21a1c-2afe-4305-875d-ee5ec5ac395f', '806f0ac6-a3e4-42a1-8dfc-1a3e56002881', 'AUTHORITY_SUPER');
COMMIT;

-- ----------------------------
--  Table structure for `lcn_tx_genesis_tx_user_money_ms`
-- ----------------------------
DROP TABLE IF EXISTS `lcn_tx_genesis_tx_user_money_ms`;
CREATE TABLE `lcn_tx_genesis_tx_user_money_ms` (
  `id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `retried_count` int(3) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `state` int(2) NOT NULL,
  `group_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `l_unique` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `task_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `invocation` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `lcn_tx_genesis_tx_user_ms`
-- ----------------------------
DROP TABLE IF EXISTS `lcn_tx_genesis_tx_user_ms`;
CREATE TABLE `lcn_tx_genesis_tx_user_ms` (
  `id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `retried_count` int(3) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `state` int(2) NOT NULL,
  `group_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `l_unique` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `task_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `invocation` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) DEFAULT NULL,
  `stock` int(18) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_goods`
-- ----------------------------
BEGIN;
INSERT INTO `t_goods` VALUES ('1', '耐克', '100', '2017-03-27 00:00:00', null), ('2', '阿迪', '10', '2017-03-27 00:00:00', '1010');
COMMIT;

-- ----------------------------
--  Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsId` bigint(20) NOT NULL COMMENT '商品ID',
  `goodsName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `price` double(255,2) DEFAULT NULL COMMENT '单价',
  `num` bigint(20) DEFAULT NULL COMMENT '个数',
  `createTime` datetime DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_order`
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES ('1', '1', '耐克', '100.00', '1', '2017-03-27 00:00:00', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('7', 'Test Tx', '1', '20');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_money`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_money`;
CREATE TABLE `t_user_money` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user_money`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_money` VALUES ('3', '1', '100');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
