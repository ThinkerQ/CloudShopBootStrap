/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : numdemo

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-07-26 21:09:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `advertisement_ID` bigint(40) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) DEFAULT NULL COMMENT '广告条编号',
  `location` varchar(255) DEFAULT NULL COMMENT '所在位置',
  `resolution` varchar(255) DEFAULT NULL COMMENT '分辨率',
  `fileSize` varchar(255) DEFAULT NULL COMMENT '建议文件大小',
  `fileFormat` varchar(255) DEFAULT NULL COMMENT '文件格式',
  `adsurl` varchar(1000) DEFAULT NULL COMMENT '广告链接',
  `description` varchar(1000) DEFAULT NULL COMMENT '广告描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`advertisement_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------
INSERT INTO `t_advertisement` VALUES ('1', '1', 'appbanner', '1920*1080', '2M', 'JGP', '', 'banner', '2017-07-26 15:55:50', '1', 'title');
