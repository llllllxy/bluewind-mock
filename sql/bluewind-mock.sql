/*
 Navicat MySQL Data Transfer

 Source Server         : 本地MySQL-127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : bluewind-mock

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 01/12/2022 10:06:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_invitees_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_invitees_info`;
CREATE TABLE `sys_invitees_info`  (
    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
    `invitation_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邀请码',
    `invite_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邀请人',
    `invitees_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被邀请人',
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统注册邀请记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_invitees_info
-- ----------------------------
INSERT INTO `sys_invitees_info` VALUES ('1509179036764385280', 'DQWBP8', '183892222922', '1509179036651139072', '2022-03-30 22:41:36', '2022-03-30 22:41:36');

-- ----------------------------
-- Table structure for sys_mock_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_mock_info`;
CREATE TABLE `sys_mock_info`  (
              `mock_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
              `mock_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'mock名称',
              `project_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对应项目id',
              `method` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GET,HEAD,POST,PUT, PATCH, DELETE,OPTIONS,TRACE;',
              `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
              `json_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返回的数据',
              `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL',
              `mockjs_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否开启mockjs模拟随机数据，0不开启，1开启',
              `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
              `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
              `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
              `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
              PRIMARY KEY (`mock_id`) USING BTREE,
              UNIQUE INDEX `sys_mock_info_url_project_uk`(`url`, `project_id`) USING BTREE COMMENT 'url和project_id唯一索引，不可重复'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统Mock信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mock_info
-- ----------------------------
INSERT INTO `sys_mock_info` VALUES ('1510623945662291968', 'dadadasd', '123176700783', 'GET', NULL, '{\"sites\":{\"site\":[{\"id\":\"1\",\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"},{\"id\":\"2\",\"name\":\"菜鸟工具\",\"url\":\"c.runoob.com\"},{\"id\":\"3\",\"name\":\"Google\",\"url\":\"www.google.com\"}]}}', '/index/recommendArticle', '0', '183892222922', '183892222922', '2022-04-03 22:23:09', '2022-04-03 22:28:44');
INSERT INTO `sys_mock_info` VALUES ('222312312208', '测试接口2', '939393939393', 'POST', '测试接口2', '{}', '/cainiao8', '1', NULL, NULL, '2022-03-27 14:03:29', '2022-03-31 20:28:50');
INSERT INTO `sys_mock_info` VALUES ('222312312234', '测试接口2', '939393939393', 'POST', '测试接口2', '{}', '/cainiao3', '1', NULL, NULL, '2022-03-27 14:03:29', '2022-03-31 20:28:50');
INSERT INTO `sys_mock_info` VALUES ('222312312235', '测试接口2', '939393939393', 'POST', '测试接口2', '{}', '/cainiao4', '1', NULL, NULL, '2022-03-27 14:03:29', '2022-03-31 20:28:52');
INSERT INTO `sys_mock_info` VALUES ('22231231233', '菜鸟测试接口2', '939393939393', 'POST', '测试接口2', '{\"code\":\"0000\",\"data\":{\"list|20\":[{\"name\":\"@cname\",\"age\":\"@integer(1,10)\",\"float\":\"@float(60, 100, 2, 2)\",\"boolean\":\"@boolean\",\"string|1-2\":\"@string\",\"date\":\"@date(yyyy-MM-dd)\",\"datetime\":\"@datetime\",\"now\":\"@now\",\"id\":\"@id\",\"guid\":\"@guid\",\"url\":\"@url\",\"email\":\"@email\",\"image\":\"@image(200x200)\",\"title\":\"@title\",\"upper\":\"@upper(@title)\",\"cparagraph\":\"@cparagraph\",\"csentence\":\"@csentence\",\"range\":\"@range(2, 10)\",\"region\":\"@region\",\"province\":\"@province\",\"city\":\"@city\",\"county\":\"@county\"}],\"url\":\"@email\"},\"desc\":\"成功\"}', '/cainiao2/hh', '1', NULL, NULL, '2022-03-27 14:03:29', '2022-03-31 20:28:46');
INSERT INTO `sys_mock_info` VALUES ('32216789701', '菜鸟测试接口', '939393939393', 'GET', '菜鸟测试接口', '{\"number1|1-100.1-10\":1,\"number2|123.1-10\":1,\"number3|123.3\":1,\"number4|123.10\":1.123}', '/cainiao', '0', NULL, NULL, '2022-03-26 23:17:02', '2022-03-31 20:28:41');

-- ----------------------------
-- Table structure for sys_project_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_project_info`;
CREATE TABLE `sys_project_info`  (
             `project_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目ID',
             `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
             `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目简介',
             `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目路径，路径只允许有一个 /',
             `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属用户',
             `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
             `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
             PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统项目信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_project_info
-- ----------------------------
INSERT INTO `sys_project_info` VALUES ('123176700783', '第二个项目', '第二个项目的说明', '/second', '183892222922', NULL, NULL, '2022-03-26 21:46:31', '2022-03-26 21:46:31');
INSERT INTO `sys_project_info` VALUES ('939393939393', '第一个项目', '第一个项目的说明', '/first', '183892222922', NULL, NULL, '2022-03-26 21:45:52', '2022-03-26 21:45:52');

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
              `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
              `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
              `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
              `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
              `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
              `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
              `invitation_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请码',
              `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0--正常 1--冻结）',
              `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0--未删除1--已删除）',
              `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
              `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
              `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
              `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
              PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('1509179036651139072', 'admin2', '75d0c890ebf066fd7b737db3210b6e3bc1694b4e226f7e4195eae402e84dd784', NULL, NULL, NULL, NULL, '0', '0', NULL, '2022-03-30 22:41:36', NULL, '2022-03-30 22:41:36');
INSERT INTO `sys_user_info` VALUES ('183892222922', 'admin', '75d0c890ebf066fd7b737db3210b6e3bc1694b4e226f7e4195eae402e84dd784', '喵喵', NULL, 'http://halo.lxyccc.top/头像.jpg', 'DQWBP8', '0', '0', '0', '2020-09-06 19:40:49', '1', '2022-03-30 17:23:51');

SET FOREIGN_KEY_CHECKS = 1;
