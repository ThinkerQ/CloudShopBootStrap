CREATE TABLE `t_resetpwd_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `send_time` datetime DEFAULT NULL,
  `uuid` varchar(200) DEFAULT NULL COMMENT '唯一标识符',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

