/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : gxshop

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-07-06 22:57:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `period`
-- ----------------------------
DROP TABLE IF EXISTS `period`;

CREATE TABLE `t_period` (
  `period_ID` bigint(40) NOT NULL AUTO_INCREMENT,
  `periodNumber` varchar(100) NOT NULL COMMENT '期数',
  `prizeUserId` bigint(11) DEFAULT NULL COMMENT '获奖用户',
  `needCount` int(11) DEFAULT NULL COMMENT '需要人次',
  `productId` bigint(11) DEFAULT NULL COMMENT '商品',
  `alreadyCount` int(11) DEFAULT NULL COMMENT '已投人次',
  `prizeNumber` varchar(100) DEFAULT NULL COMMENT '幸运号码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `prizeTime` datetime DEFAULT NULL COMMENT '揭晓时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`period_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of period
-- ----------------------------
