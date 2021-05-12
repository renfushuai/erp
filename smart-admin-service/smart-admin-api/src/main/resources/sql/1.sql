-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.32-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 导出  表 smart-admin-dev.erp_company 结构
DROP TABLE IF EXISTS `erp_company`;
CREATE TABLE IF NOT EXISTS `erp_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司表';

-- 正在导出表  smart-admin-dev.erp_company 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `erp_company` DISABLE KEYS */;
INSERT INTO `erp_company` (`id`, `name`, `status`, `update_time`, `create_time`) VALUES
	(2, '诚诺美迪', 1, '2021-05-12 05:53:16', '2021-05-12 05:53:16'),
	(3, '安亿坦', 1, '2021-05-12 05:58:48', '2021-05-12 05:58:48'),
	(4, '新恩仕', 1, '2021-05-12 05:58:58', '2021-05-12 05:58:58');
/*!40000 ALTER TABLE `erp_company` ENABLE KEYS */;

-- 导出  表 smart-admin-dev.erp_hospital 结构
DROP TABLE IF EXISTS `erp_hospital`;
CREATE TABLE IF NOT EXISTS `erp_hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医院名称',
  `area_code` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区编号',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医院';

-- 正在导出表  smart-admin-dev.erp_hospital 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `erp_hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `erp_hospital` ENABLE KEYS */;

-- 导出  表 smart-admin-dev.erp_product 结构
DROP TABLE IF EXISTS `erp_product`;
CREATE TABLE IF NOT EXISTS `erp_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_number` varchar(100) DEFAULT NULL COMMENT '货号',
  `product_name` varchar(200) DEFAULT NULL,
  `specifications` varchar(100) DEFAULT NULL COMMENT '规格',
  `stock` int(11) DEFAULT NULL,
  `standard_price` decimal(19,2) DEFAULT NULL COMMENT '单价',
  `product_type` int(11) DEFAULT NULL COMMENT '商品类型1=外科，2=内科 3=器械',
  `company_name` varchar(100) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `erp_product_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  smart-admin-dev.erp_product 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `erp_product` DISABLE KEYS */;
INSERT INTO `erp_product` (`id`, `product_number`, `product_name`, `specifications`, `stock`, `standard_price`, `product_type`, `company_name`, `company_id`) VALUES
	(1, NULL, '组织镊', 'FL6024.2', 100, 27600.00, 3, '新恩仕', 1),
	(3, NULL, '一次性使用无菌双极射频消融钳', 'MZ-RFK', 100, 18000.00, NULL, '诚诺美迪', NULL);
/*!40000 ALTER TABLE `erp_product` ENABLE KEYS */;

-- 导出  表 smart-admin-dev.erp_sales_order 结构
DROP TABLE IF EXISTS `erp_sales_order`;
CREATE TABLE IF NOT EXISTS `erp_sales_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '订单号',
  `actually_amount` decimal(19,2) DEFAULT NULL COMMENT '实付金额',
  `order_amount` decimal(19,2) DEFAULT NULL COMMENT '订单金额',
  `hospital_name` varchar(100) DEFAULT NULL COMMENT '医院',
  `hospital_id` int(11) DEFAULT NULL COMMENT '医院id',
  `order_type` int(11) DEFAULT NULL COMMENT '1=外科2=内科3=器械',
  `order_type_name` varchar(100) DEFAULT NULL COMMENT '销售单类型',
  `manager` varchar(100) DEFAULT NULL COMMENT '经办人',
  `channel` int(11) DEFAULT NULL COMMENT '渠道1=医院 2=代理商',
  `channel_name` varchar(100) DEFAULT NULL COMMENT '渠道名称',
  `area_code` varchar(50) DEFAULT NULL COMMENT '地区编号',
  `area_name` varchar(50) DEFAULT NULL COMMENT '地区名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `order_source_name` varchar(200) DEFAULT NULL COMMENT '订单来源',
  `order_source_id` int(11) DEFAULT NULL COMMENT '订单来源',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8;

-- 正在导出表  smart-admin-dev.erp_sales_order 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `erp_sales_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `erp_sales_order` ENABLE KEYS */;

-- 导出  表 smart-admin-dev.erp_sales_order_info 结构
DROP TABLE IF EXISTS `erp_sales_order_info`;
CREATE TABLE IF NOT EXISTS `erp_sales_order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(100) DEFAULT NULL COMMENT '销售单号',
  `product_id` int(11) DEFAULT NULL COMMENT '商品Id',
  `product_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `product_number` varchar(100) DEFAULT NULL COMMENT '货号',
  `specifications` varchar(100) DEFAULT NULL COMMENT '规格',
  `sales_quantity` int(11) DEFAULT NULL COMMENT '数量',
  `sales_price` decimal(19,2) DEFAULT NULL COMMENT '出货金额',
  `standard_price` int(11) DEFAULT NULL COMMENT '出货单价',
  PRIMARY KEY (`id`),
  KEY `order_code__index` (`order_code`)
) ENGINE=InnoDB AUTO_INCREMENT=942 DEFAULT CHARSET=utf8;

-- 正在导出表  smart-admin-dev.erp_sales_order_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `erp_sales_order_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `erp_sales_order_info` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
