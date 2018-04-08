/*
Navicat MySQL Data Transfer

Source Server         : 阿里云数据库
Source Server Version : 50638
Source Host           : 47.94.99.53:3306
Source Database       : demo0307

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-04-08 22:34:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tab_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tab_menu`;
CREATE TABLE `tab_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_path` varchar(100) DEFAULT NULL COMMENT '访问路径',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` varchar(10) DEFAULT NULL COMMENT '菜单类型',
  `menu_parent` int(11) DEFAULT NULL COMMENT '父菜单',
  `menu_auth` tinyint(1) DEFAULT NULL COMMENT '是否需要权限',
  `menu_enabled` tinyint(1) DEFAULT NULL COMMENT '菜单是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_menu
-- ----------------------------
INSERT INTO `tab_menu` VALUES ('1', '/manager', '管理后台', '1', '0', '0', '0');
INSERT INTO `tab_menu` VALUES ('2', '/web', '手机端', '2', '0', '1', '0');
INSERT INTO `tab_menu` VALUES ('3', '/manager/user', '用户管理', '1', '1', '0', '0');
INSERT INTO `tab_menu` VALUES ('5', '/manager/user/addUser', '新增1', '2', '2', '0', '0');

-- ----------------------------
-- Table structure for `tab_role`
-- ----------------------------
DROP TABLE IF EXISTS `tab_role`;
CREATE TABLE `tab_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role
-- ----------------------------
INSERT INTO `tab_role` VALUES ('9', 'ewrwe', 'ewrewrew');
INSERT INTO `tab_role` VALUES ('10', 'rewr', 'ewrrewqrwe');

-- ----------------------------
-- Table structure for `tab_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tab_role_menu`;
CREATE TABLE `tab_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `m_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role_menu
-- ----------------------------
INSERT INTO `tab_role_menu` VALUES ('13', '7', '1');
INSERT INTO `tab_role_menu` VALUES ('14', '7', '3');
INSERT INTO `tab_role_menu` VALUES ('15', '7', '2');
INSERT INTO `tab_role_menu` VALUES ('16', '7', '5');

-- ----------------------------
-- Table structure for `tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(200) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES ('36', '姚雨霄', 'gMU/B0RsL4XpeaKd+lVA7Q==');
INSERT INTO `tab_user` VALUES ('37', '2213', 'qEPoI5yEa693YOShOQQ4gA==');

-- ----------------------------
-- Table structure for `tab_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user_role`;
CREATE TABLE `tab_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `r_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user_role
-- ----------------------------
INSERT INTO `tab_user_role` VALUES ('15', '36', '9');
INSERT INTO `tab_user_role` VALUES ('16', '37', '10');

-- ----------------------------
-- Table structure for `tab_wx_order`
-- ----------------------------
DROP TABLE IF EXISTS `tab_wx_order`;
CREATE TABLE `tab_wx_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) DEFAULT NULL,
  `order_id` varchar(50) DEFAULT NULL,
  `type` varchar(4) DEFAULT '' COMMENT '1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调',
  `title` varchar(10) DEFAULT NULL COMMENT '冰箱 洗衣机 换锁芯 电视 空调',
  `question` varchar(200) DEFAULT NULL COMMENT '问题描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `gps_address` varchar(100) DEFAULT NULL COMMENT 'gps获取的地理位置',
  `area` varchar(100) DEFAULT NULL COMMENT '省市县行政区域',
  `address` varchar(100) DEFAULT NULL COMMENT '具体门牌号地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_wx_order
-- ----------------------------
INSERT INTO `tab_wx_order` VALUES ('1', 'null', 'c0c1dae299a786641804081523197815603', '', '', '234234234', '2018-04-08 22:30:16', '153 4453 2323', '', '北京市 市辖区 东城区', '32432423');
INSERT INTO `tab_wx_order` VALUES ('2', 'null', 'c0c1dae299a786641804081523197910523', '', '', 'r4erewr', '2018-04-08 22:31:51', '175 4325 3425', '', '河北省 保定市 竞秀区', '345432543252');

-- ----------------------------
-- Table structure for `tab_wx_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_wx_user`;
CREATE TABLE `tab_wx_user` (
  `openid` varchar(50) DEFAULT NULL COMMENT '用户的唯一标识',
  `scope` varchar(30) DEFAULT NULL,
  `expires_in` varchar(10) DEFAULT NULL,
  `refresh_token` varchar(200) DEFAULT NULL,
  `access_token` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `sex` varchar(10) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(50) DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `city` varchar(50) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `country` varchar(10) DEFAULT NULL COMMENT '国家，如中国为CN',
  `headimgurl` varchar(200) DEFAULT NULL COMMENT '用户头像，用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `privilege` varchar(100) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom',
  `unionid` varchar(100) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  UNIQUE KEY `unique_index01` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_wx_user
-- ----------------------------
INSERT INTO `tab_wx_user` VALUES ('oI2OOwotXwQD-NACEebh7BwXeHzk', 'snsapi_userinfo', '7200', '8_a-xrdjMRXFxS_OigW51GW28hWdMgr_aqAboApT0-DDjBmUM7JBzUz8pglgEbYgY35R7XTRxR8g8hby9ySeVJkM0GV28q4Oh7q_s62OBohtc', '8_ZigULPSH8c5YkjPsDFD83mQ2kCF7IU2OmqpUhLwtuO-udLrICimE0UQJi7KtKG_Mhv6Ag8DJwfMmCrZ2nd9Dm0OA2-b4F8dya5E4IKNARNY', '2018-04-08 21:59:45', null, 'yaoyuxiao001', '1', '湖北', '襄阳', '中国', 'http://thirdwx.qlogo.cn/mmopen/vi_32/1qXJYsC7RhQpTf3EbGKC2WNP5p89Rc4pyxJXrH9QCvMliad0v8RK7wlOMhohdKdf81iajTcY7o6xpB8U2ibYEw6Ow/132', '[]', null);
