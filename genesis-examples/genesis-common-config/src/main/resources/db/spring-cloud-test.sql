/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : spring-cloud-test

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2016-10-24 16:58:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) DEFAULT NULL,
  `stock` int(18) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
