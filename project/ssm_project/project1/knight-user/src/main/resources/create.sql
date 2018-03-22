DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '城市编号',
  `province_id` INT(10) UNSIGNED NOT NULL
  COMMENT '城市省号',
  `city_name`   VARCHAR(25)               DEFAULT NULL
  COMMENT '城市名称',
  `description` VARCHAR(25)               DEFAULT NULL
  COMMENT '描述',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`       INT(12) PRIMARY KEY
  COMMENT '用户Id',
  `userName` VARCHAR(50)               NOT NULL
  COMMENT '用户登录账号',
  `nikeName` VARCHAR(50) DEFAULT '未命名' NOT NULL
  COMMENT '用户昵称',
  `password` VARCHAR(100)              NOT NULL
  COMMENT '用户密码',
  PRIMARY KEY (`id`)

)