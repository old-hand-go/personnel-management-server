-- MariaDB dump 10.17  Distrib 10.4.8-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: personnel_management_server
-- ------------------------------------------------------
-- Server version	10.4.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '修改时间',
  `department_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门名称',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '上级部门ID',
  `is_enabled` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '开关：1开启，0关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict`
--

DROP TABLE IF EXISTS `dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `dict_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典注释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict`
--

LOCK TABLES `dict` WRITE;
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_detail`
--

DROP TABLE IF EXISTS `dict_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `label` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典标签',
  `dict_value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `sort` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '排序',
  `dict_id` bigint(20) unsigned DEFAULT NULL COMMENT '字典ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典细节表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_detail`
--

LOCK TABLES `dict_detail` WRITE;
/*!40000 ALTER TABLE `dict_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '修改时间',
  `job_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职位名称',
  `dept_id` bigint(20) unsigned DEFAULT NULL COMMENT '部门ID',
  `is_enabled` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '开关：1开启，0关闭',
  `sort` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='职位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件',
  `component_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件名称',
  `menu_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `sort` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '上级菜单',
  `is_frame` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '是否外链：1是，0否',
  `is_hidden` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '是否隐藏：1隐藏，0显示',
  `is_cache` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '是否缓存：1是，0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `permission_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `alias` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '别名',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '上级权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `role_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_level` smallint(5) unsigned NOT NULL COMMENT '角色等级',
  `data_scope` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色范围',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_departments`
--

DROP TABLE IF EXISTS `roles_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `department_id` bigint(20) unsigned DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_departments`
--

LOCK TABLES `roles_departments` WRITE;
/*!40000 ALTER TABLE `roles_departments` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_menus`
--

DROP TABLE IF EXISTS `roles_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_menus` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_menus`
--

LOCK TABLES `roles_menus` WRITE;
/*!40000 ALTER TABLE `roles_menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_permissions`
--

DROP TABLE IF EXISTS `roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '修改时间',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_permissions`
--

LOCK TABLES `roles_permissions` WRITE;
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '修改时间',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户邮箱',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `pass_word` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户家庭地址',
  `dept_id` bigint(20) unsigned DEFAULT NULL COMMENT '部门ID',
  `job_id` bigint(20) unsigned DEFAULT NULL COMMENT '职位ID',
  `is_enabled` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '开关：1开，0关',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2019-10-14 14:18:44.000000','2019-10-14 14:18:46.000000','1018729292@qq.com','张天赐','123456',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_code`
--

DROP TABLE IF EXISTS `verification_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verification_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `code_value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '验证码',
  `is_type` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '验证码类型：1验证码 2邮件验证',
  `code_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接收邮箱',
  `scenes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '验证码业务类型',
  `is_status` tinyint(3) unsigned NOT NULL DEFAULT 1 COMMENT '状态：1有效，0过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='验证码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_code`
--

LOCK TABLES `verification_code` WRITE;
/*!40000 ALTER TABLE `verification_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-15 10:09:35
