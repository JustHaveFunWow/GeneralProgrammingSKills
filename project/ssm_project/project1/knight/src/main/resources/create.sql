DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  `province_id` int(10) unsigned NOT NULL COMMENT '城市省号',
  `city_name` VARCHAR (25) DEFAULT NULL COMMENT '城市名称',
  `description` VARCHAR (25) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;