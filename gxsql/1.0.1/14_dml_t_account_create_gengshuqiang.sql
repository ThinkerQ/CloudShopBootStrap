CREATE TABLE `t_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `usable_amount` decimal(18,4) DEFAULT '0.0000' COMMENT '可用余额',
  `version` int(255) DEFAULT '0' COMMENT '版本号',
  `insert_date` datetime DEFAULT NULL COMMENT '插入时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

