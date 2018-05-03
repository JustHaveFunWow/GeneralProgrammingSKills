DROP TABLE IF EXISTS `knitht-id`;

CREATE TABLE `knight-ids` (
  `ids_system_id` int(11) PRIMARY KEY NOT NULL COMMENT '编号',
  `ids_business_name` varchar(100) NOT NULL UNIQUE COMMENT '业务名称',
  `ids_business_id` int(11) NOT NULL AUTO_INCREMENT UNIQUE COMMENT '业务id'
) ENGINE=InnoDB AUTO_INCREMENT= 1 DEFAULT CHARSET = utf8mb4 COMMENT ='id递增自动生成';