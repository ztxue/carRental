/*
 Navicat Premium Data Transfer

 Source Server         : huahua
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/09/2021 11:12:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_class
-- ----------------------------
DROP TABLE IF EXISTS `car_class`;
CREATE TABLE `car_class`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `class_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_class
-- ----------------------------
INSERT INTO `car_class` VALUES (1, '奔驰E级', '2021-09-08 16:03:32', '2021-09-10 16:03:38');
INSERT INTO `car_class` VALUES (2, '宝马1', '2021-09-08 16:03:32', '2021-09-10 16:03:43');
INSERT INTO `car_class` VALUES (3, '宝马2', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (4, '大众', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (5, '比亚迪', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (6, '路虎', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (7, '老爷车', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (8, '东风标致', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (9, '丰田', '2021-09-08 16:03:32', '2021-09-08 16:03:32');
INSERT INTO `car_class` VALUES (10, '特斯拉', '2021-09-08 16:03:32', '2021-09-08 16:03:32');

-- ----------------------------
-- Table structure for car_customer
-- ----------------------------
DROP TABLE IF EXISTS `car_customer`;
CREATE TABLE `car_customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lease_Num` int(0) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_customer
-- ----------------------------
INSERT INTO `car_customer` VALUES (1, '李四', '3703020220201010191', '13247979797', 1, '女', 45, '山东省日照市东港区烟台路获奖社区88号', '2021-09-12 10:39:34', '2021-09-12 10:39:34');
INSERT INTO `car_customer` VALUES (2, '张童学', '3459061999066442201', '12131467485', 1, '男', 22, '浙江省杭州市西湖区西湖街道拿奖社区88号', '2021-09-12 10:39:34', '2021-09-12 10:39:34');
INSERT INTO `car_customer` VALUES (3, '小张', '1334155633749067860', '12369403002', 1, '男', 12, '江苏省苏州市虎丘区虎啸街道得奖社区66号', '2021-09-12 10:39:34', '2021-09-12 10:39:34');
INSERT INTO `car_customer` VALUES (4, '小丽', '5232291858104812959', '15263235201', 2, '女', 32, '广东省广州市花都区新华街道中奖社区88号', '2021-09-12 10:47:47', '2021-09-12 10:59:55');

-- ----------------------------
-- Table structure for car_data
-- ----------------------------
DROP TABLE IF EXISTS `car_data`;
CREATE TABLE `car_data`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  `status_Dsecrbe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seating` int(0) NULL DEFAULT NULL,
  `buy_Date` datetime(0) NULL DEFAULT NULL,
  `day_Price` float NULL DEFAULT NULL,
  `month_Price` float NULL DEFAULT NULL,
  `run_Km` int(0) NULL DEFAULT NULL,
  `class_Id` int(0) UNSIGNED NULL DEFAULT NULL,
  `user_Id` int(0) UNSIGNED NULL DEFAULT NULL,
  `create_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_data
-- ----------------------------
INSERT INTO `car_data` VALUES (1, '鲁L888888', b'1', '已启用', '大红', '宝马1', 4, '2021-08-30 08:00:00', 1000, 10000, 1000000, 2, 1, '2021-09-11 14:49:15', '2021-09-12 10:56:45');
INSERT INTO `car_data` VALUES (2, '鲁L888888', b'1', '已启用', '白色', '大众', 4, '2021-09-26 08:00:00', 10, 100, 1, 4, 1, '2021-09-11 15:03:54', '2021-09-11 15:03:54');
INSERT INTO `car_data` VALUES (3, '鲁L888888', b'1', '已启用', '酷黑', '路虎', 8, '2021-09-21 08:00:00', 100, 1000, 1000, 6, 1, '2021-09-11 20:12:26', '2021-09-11 20:12:26');
INSERT INTO `car_data` VALUES (4, '鲁L888888', b'1', '已启用', '飒黑', '丰田', 8, '2021-09-29 08:00:00', 90, 2700, 10, 9, 3, '2021-09-11 20:21:17', '2021-09-11 20:21:17');
INSERT INTO `car_data` VALUES (5, '鲁L66666', b'1', '已启用', '白色', '大奔', 4, '2021-09-08 08:00:00', 1000, 30000, 10, 1, 2, '2021-09-11 20:12:06', '2021-09-11 20:12:06');
INSERT INTO `car_data` VALUES (6, '鲁A99999', b'1', '已启用', '漆黑', '特斯拉aa', 6, '2021-09-06 08:00:00', 100, 1000, 1, 10, 3, '2021-09-11 20:12:46', '2021-09-11 20:12:46');
INSERT INTO `car_data` VALUES (7, '鲁D111111', b'1', '已启用', '宝石蓝', '宝马2', 5, '2021-09-11 08:00:00', 100, 1000, 2, 3, 3, '2021-09-11 20:17:28', '2021-09-11 20:17:28');
INSERT INTO `car_data` VALUES (8, '鲁B99999', b'1', '已启用', '典雅紫', '比亚迪大牛', 5, '2021-09-01 08:00:00', 999, 99999, 9, 5, 3, '2021-09-11 20:17:29', '2021-09-11 20:17:29');
INSERT INTO `car_data` VALUES (9, '鲁C99999', b'1', '已启用', '磨砂黑', '老爷车', 5, '2021-09-01 08:00:00', 999, 99999, 9, 7, 3, '2021-09-11 20:17:32', '2021-09-11 20:17:32');
INSERT INTO `car_data` VALUES (10, '鲁D888888', b'1', '已启用', '白色', '东风标致', 4, '2021-09-10 08:00:00', 12, 122, 12, 8, 2, '2021-09-11 20:20:45', '2021-09-12 10:59:55');

-- ----------------------------
-- Table structure for car_record
-- ----------------------------
DROP TABLE IF EXISTS `car_record`;
CREATE TABLE `car_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customer_Id` int(0) UNSIGNED NULL DEFAULT NULL,
  `car_Id` int(0) UNSIGNED NULL DEFAULT NULL,
  `time_Long` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  `deposit` float NULL DEFAULT NULL,
  `user_Id` int(0) UNSIGNED NULL DEFAULT NULL,
  `create_Time` datetime(0) NULL DEFAULT NULL,
  `update_Time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_record
-- ----------------------------
INSERT INTO `car_record` VALUES (26, 1, 1, '100天', b'1', 10000, 1, '2021-09-11 14:49:01', '2021-09-11 14:49:01');
INSERT INTO `car_record` VALUES (27, 3, 3, '5月', b'1', 50, 1, '2021-09-11 14:52:57', '2021-09-11 14:52:57');
INSERT INTO `car_record` VALUES (28, 2, 2, '90天', b'1', 9000, 1, '2021-09-11 15:02:36', '2021-09-11 15:02:36');
INSERT INTO `car_record` VALUES (29, 3, 5, '11天', b'1', 111, 1, '2021-09-11 20:04:12', '2021-09-11 20:04:12');
INSERT INTO `car_record` VALUES (30, 4, 10, '100天', b'1', 900, 1, '2021-09-12 10:59:36', '2021-09-12 10:59:55');

-- ----------------------------
-- Table structure for car_user
-- ----------------------------
DROP TABLE IF EXISTS `car_user`;
CREATE TABLE `car_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_Num` int(0) UNSIGNED NULL DEFAULT NULL,
  `create_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_user
-- ----------------------------
INSERT INTO `car_user` VALUES (1, 'admin', '96dfdaf9f6b508bfda73cfa1bf39d17a', 'admin@163.com', '13242637484', 'f', 1, '2021-09-01 16:05:55', '2021-09-10 16:05:58');
INSERT INTO `car_user` VALUES (2, '小李', '96dfdaf9f6b508bfda73cfa1bf39d17a', 'vip@163.com', '13242637484', 'm', 2, '2021-09-11 16:01:26', '2021-09-11 16:01:26');
INSERT INTO `car_user` VALUES (3, '阿三', '979b005f6837e3f3369f79f7402d589c', 'svip@163.com', '13242637484', 'm', 3, '2021-09-11 16:01:34', '2021-09-11 16:01:34');

SET FOREIGN_KEY_CHECKS = 1;
