CREATE TABLE `t_verify_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(255) DEFAULT NULL COMMENT '接收手机号',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `code` varchar(200) DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

