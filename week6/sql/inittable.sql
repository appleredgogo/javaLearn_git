CREATE DATABASE if not exists `shop`;
USE `shop`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '姓名',
  `password` varchar(25) NOT NULL COMMENT '密码',
  `account` varchar(25) NOT NULL COMMENT '帐号',
  `id_card` varchar(25) NOT NULL COMMENT '身份证号',
  `amount` float(11,2) NOT NULL COMMENT '账户余额',
  `phone` varchar(25) NOT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(30) NOT NULL COMMENT '商店名称',
  `description` varchar(2000) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int NOT NULL,
  `product_name` varchar(30) NOT NULL COMMENT '商品名称',
  `description` varchar(2000) NOT NULL COMMENT '描述',
  `price` float(11,2) NOT NULL COMMENT '价格',
  `inventory_number` int(11) NOT NULL COMMENT '库存数量',
  `store_id` int(11) NOT NULL COMMENT '所属商店id',
  PRIMARY KEY (`id`),
  foreign key (store_id) references stores(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL,
  `user_id` int NOT NULL COMMENT '用户id',
  `order_list` varchar(2000) NOT NULL COMMENT '订单列表',
  `status` varchar(5) NOT NULL COMMENT '订单状态',
  `total_price` float(11,2) NOT NULL,
  `logistics_status` varchar(2000) NOT NULL COMMENT '物流情况',
  `create_time` int NOT NULL COMMENT '创建时间',
  `update_time` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  foreign key (user_id) references users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;