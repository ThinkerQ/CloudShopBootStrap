/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : numdemo

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-07-18 22:46:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_ID` bigint(40) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL COMMENT '分类',
  `firstName` varchar(255) DEFAULT NULL COMMENT '主名称',
  `secondName` varchar(255) DEFAULT NULL COMMENT '副名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态:0.使用;1.停用',
  `littleImgUrl` varchar(255) DEFAULT NULL COMMENT '小图URL',
  `bigImgUrl` varchar(255) DEFAULT NULL COMMENT '大图URL',
  PRIMARY KEY (`product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '5', '苹果手机', 'apple', '2017-07-16 00:00:00', '0', '20170718/c1335b17d67d4fcb9d3f495daa9d555b.jpg', '20170718/c1335b17d67d4fcb9d3f495daa9d555b.jpg');
INSERT INTO `product` VALUES ('2', '11', '奥迪', '奥迪A8', '2017-07-18 14:25:23', '0', '20170718/c1335b17d67d4fcb9d3f495daa9d555b.jpg', '20170718/d924ea35bab44f5099b5bd7d662de25b.jpg');
