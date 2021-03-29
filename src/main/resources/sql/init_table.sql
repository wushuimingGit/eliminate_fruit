
DROP TABLE IF EXISTS `level`;
CREATE TABLE IF NOT EXISTS `level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主建',
  `one` varchar(255) DEFAULT NULL COMMENT '第一行的八个水果',
  `two` varchar(255) DEFAULT NULL COMMENT '第二行的八个水果',
  `three` varchar(255) DEFAULT NULL COMMENT '...',
  `four` varchar(255) DEFAULT NULL,
  `five` varchar(255) DEFAULT NULL,
  `six` varchar(255) DEFAULT NULL,
  `seven` varchar(255) DEFAULT NULL,
  `eight` varchar(255) DEFAULT NULL COMMENT '...',
  `init` int(10) DEFAULT NULL COMMENT '是否为初始级别 数据 1 表示初始级别(uid 为空)  0 表示否 也就是存储用户的更新数据(uid 不能为空)',
  `uid` varchar(255) DEFAULT '' COMMENT '用户标识',
  `lv` int(10) DEFAULT NULL COMMENT '表示关卡 只有 init 为 1 时 该值有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS  `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `level` int(10) DEFAULT '1' COMMENT '关卡表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;