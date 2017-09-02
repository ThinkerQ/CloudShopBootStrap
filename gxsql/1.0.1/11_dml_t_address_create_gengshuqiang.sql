CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号自增',
  `province_id` int(11) DEFAULT NULL COMMENT '省份编号',
  `city_id` int(11) DEFAULT NULL COMMENT '城市编号',
  `area_id` int(11) DEFAULT NULL COMMENT '区域编号',
  `biz_code` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `address_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `phone_number` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `context_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

