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

 Date: 09/19/2017 11:56:30 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `auth`
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(32) NOT NULL COMMENT '授权应用key',
  `secrity_key` varchar(32) NOT NULL COMMENT '安全密钥',
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_index` (`app_key`,`secrity_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Api 接口认证授权信息';

-- ----------------------------
--  Records of `auth`
-- ----------------------------
BEGIN;
INSERT INTO `auth` VALUES ('2', 'd3479612a5d54cef84bde277465f03e3', 'g6zpZ5RhnSLdR127', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkMzQ3OTYxMmE1ZDU0Y2VmODRiZGUyNzc0NjVmMDNlMyIsImF1ZCI6IiIsImV4cCI6MTUwNjM0Njg5NCwiaWF0IjoxNTA1NzQyMDk0fQ.F4Ij3QAhk9fAUi4dlJ04KGzMZT_tS9EJwovempGp4lc8lthMfGi73LrH0f9NpUbFGZ1_2EyW0OieO2eO4dD2kw');
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
--  Records of `lcn_tx_genesis_tx_user_money_ms`
-- ----------------------------
BEGIN;
INSERT INTO `lcn_tx_genesis_tx_user_money_ms` VALUES ('BznHVjwg', '0', '2017-09-19 11:40:48', '2017-09-19 11:40:48', '0', '298M62nN', '2145f891bd276daa411b5218a512ebb4', 'tNRR0BKm', 0x0b920130636f6d2e666c616d652e74782e757365722e6d6f6e65792e736572766963652e557365724d6f6e6579536572766963650c1204736176651b7a106a6176612e6c616e672e4f626a656374180110010bfa0719636f6d2e666c616d652e6d6f64656c2e557365724d6f6e657910161900000000000059400c1c237a0f6a6176612e6c616e672e436c617373180110010b920119636f6d2e666c616d652e6d6f64656c2e557365724d6f6e65790c24);
COMMIT;

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
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` tinyint(11) DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, '', '1', '2017-08-21 16:44:12'), ('8', 'user', '$2a$10$HjoWoNdG23N1XjHh720idePurSbrUA.pAcDfGGyDbI5KgsGgvuvPi', null, '', '1', '2017-08-21 17:26:34'), ('9', 'disabled', '$2a$10$ZgTe2Owsh9NPWJOnADJkeOPWT7dyX/7M64QhW6zqXdSNEGtv3W/Rm', null, '', '0', '2017-08-21 17:29:31');
COMMIT;

-- ----------------------------
--  Table structure for `user_authority`
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `authority_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_authority`
-- ----------------------------
BEGIN;
INSERT INTO `user_authority` VALUES ('1', '1', '1'), ('2', '1', '2'), ('13', '8', '1'), ('14', '9', '1');
COMMIT;

-- ----------------------------
--  Table structure for `user_money`
-- ----------------------------
DROP TABLE IF EXISTS `user_money`;
CREATE TABLE `user_money` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
