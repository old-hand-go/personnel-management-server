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
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255) NOT NULL COMMENT '名称',
    `pid`         bigint(20)   NOT NULL COMMENT '上级部门',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `enabled`     bit(1)       NOT NULL COMMENT '1开0关',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept`
    DISABLE KEYS */;
INSERT INTO `dept`
VALUES (1, 'eladmin', 0, '2019-03-25 09:14:05', ''),
       (2, '研发部', 7, '2019-03-25 09:15:32', ''),
       (5, '运维部', 7, '2019-03-25 09:20:44', ''),
       (6, '测试部', 8, '2019-03-25 09:52:18', ''),
       (7, '华南分部', 1, '2019-03-25 11:04:50', ''),
       (8, '华北分部', 1, '2019-03-25 11:04:53', ''),
       (9, '财务部', 7, '2019-03-25 11:05:34', ''),
       (10, '行政部', 8, '2019-03-25 11:05:58', ''),
       (11, '人事部', 8, '2019-03-25 11:07:58', ''),
       (12, '市场部', 7, '2019-03-25 11:10:24', '\0');
/*!40000 ALTER TABLE `dept`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict`
--

DROP TABLE IF EXISTS `dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict` (
                        `id`     bigint(11)   NOT NULL AUTO_INCREMENT,
                        `name`   varchar(255) NOT NULL COMMENT '字典名称',
                        `remark` varchar(255) DEFAULT NULL COMMENT '描述',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict`
--

LOCK TABLES `dict` WRITE;
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
INSERT INTO `dict`
VALUES (1, 'user_status', '用户状态'),
       (4, 'dept_status', '部门状态'),
       (5, 'job_status', '岗位状态');
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_detail`
--

DROP TABLE IF EXISTS `dict_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_detail` (
                               `id`      bigint(11)   NOT NULL AUTO_INCREMENT,
                               `label`   varchar(255) NOT NULL COMMENT '字典标签',
                               `value`   varchar(255) NOT NULL COMMENT '字典值',
                               `sort`    varchar(255) DEFAULT NULL COMMENT '排序',
                               `dict_id` bigint(11)   DEFAULT NULL COMMENT '字典id',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE,
                               CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `dict` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='字典详细描述表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_detail`
--

LOCK TABLES `dict_detail` WRITE;
/*!40000 ALTER TABLE `dict_detail` DISABLE KEYS */;
INSERT INTO `dict_detail`
VALUES (1, '正常', 'true', '1', 1),
       (2, '停用', 'false', '2', 1),
       (11, '正常', 'true', '1', 4),
       (12, '停用', 'false', '2', 4),
       (13, '正常', 'true', '1', 5),
       (14, '停用', 'false', '2', 5);
/*!40000 ALTER TABLE `dict_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_config`
--

DROP TABLE IF EXISTS `email_config`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_config`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `from_user` varchar(255) DEFAULT NULL COMMENT '收件人',
    `host`      varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
    `pass`      varchar(255) DEFAULT NULL COMMENT '密码',
    `port`      varchar(255) DEFAULT NULL COMMENT '端口',
    `user`      varchar(255) DEFAULT NULL COMMENT '发件者用户名',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='邮箱配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_config`
--

LOCK TABLES `email_config` WRITE;
/*!40000 ALTER TABLE `email_config`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `email_config`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
                       `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
                       `name`        varchar(255) NOT NULL COMMENT '职位名称',
                       `enabled`     bit(1)       NOT NULL COMMENT '1开0关',
                       `create_time` datetime   DEFAULT NULL COMMENT '创建时间',
                       `sort`        bigint(20)   NOT NULL COMMENT '排序',
                       `dept_id`     bigint(20) DEFAULT NULL COMMENT '对应部门ID',
                       PRIMARY KEY (`id`) USING BTREE,
                       KEY `FKmvhj0rogastlctflsxf1d6k3i` (`dept_id`) USING BTREE,
                       CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job`
VALUES (2, '董事长秘书', '', '2019-03-29 14:01:30', 2, 1),
       (8, '人事专员', '', '2019-03-29 14:52:28', 3, 11),
       (10, '产品经理', '\0', '2019-03-29 14:55:51', 4, 2),
       (11, '全栈开发', '', '2019-03-31 13:39:30', 6, 2),
       (12, '软件测试', '', '2019-03-31 13:39:43', 5, 2),
       (19, '董事长', '', '2019-03-31 14:58:15', 1, 1);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local_storage`
--

DROP TABLE IF EXISTS `local_storage`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `local_storage`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `real_name`   varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL COMMENT '文件名',
    `suffix`      varchar(255) DEFAULT NULL COMMENT '后缀',
    `path`        varchar(255) DEFAULT NULL COMMENT '路径',
    `type`        varchar(255) DEFAULT NULL COMMENT '类型',
    `size`        varchar(100) DEFAULT NULL COMMENT '大小',
    `operate`     varchar(255) DEFAULT NULL COMMENT '操作人',
    `create_time` datetime     DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime     DEFAULT NULL COMMENT '修改日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='本地文件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_storage`
--

LOCK TABLES `local_storage` WRITE;
/*!40000 ALTER TABLE `local_storage`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `local_storage`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `create_time`      datetime     DEFAULT NULL,
    `description`      varchar(255) DEFAULT NULL,
    `exception_detail` text         DEFAULT NULL,
    `log_type`         varchar(255) DEFAULT NULL,
    `method`           varchar(255) DEFAULT NULL,
    `params`           text         DEFAULT NULL,
    `request_ip`       varchar(255) DEFAULT NULL,
    `time`             bigint(20)   DEFAULT NULL,
    `username`         varchar(255) DEFAULT NULL,
    `address`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `log`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
                        `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `create_time`    datetime     DEFAULT NULL COMMENT '创建日期',
                        `i_frame`        bit(1)       DEFAULT NULL COMMENT '是否外链',
                        `name`           varchar(255) DEFAULT NULL COMMENT '菜单名称',
                        `component`      varchar(255) DEFAULT NULL COMMENT '组件',
                        `pid`            bigint(20) NOT NULL COMMENT '上级菜单ID',
                        `sort`           bigint(20) NOT NULL COMMENT '排序',
                        `icon`           varchar(255) DEFAULT NULL COMMENT '图标',
                        `path`           varchar(255) DEFAULT NULL COMMENT '链接地址',
                        `cache`          bit(1)       DEFAULT b'0',
                        `hidden`         bit(1)       DEFAULT b'0',
                        `component_name` varchar(20)  DEFAULT '-',
                        PRIMARY KEY (`id`) USING BTREE,
                        KEY `FKqcf9gem97gqa5qjm4d3elcqt5` (`pid`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu`
VALUES (1, '2018-12-18 15:11:29', '\0', '系统管理', NULL, 0, 1, 'system', 'system', '\0', '\0', NULL),
       (2, '2018-12-18 15:14:44', '\0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user', '\0', '\0', 'User'),
       (3, '2018-12-18 15:16:07', '\0', '角色管理', 'system/role/index', 1, 3, 'role', 'role', '\0', '\0', 'Role'),
       (4, '2018-12-18 15:16:45', '\0', '权限管理', 'system/permission/index', 1, 4, 'permission', 'permission', '\0', '\0',
        'Permission'),
       (5, '2018-12-18 15:17:28', '\0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu', '\0', '\0', 'Menu'),
       (6, '2018-12-18 15:17:48', '\0', '系统监控', NULL, 0, 10, 'monitor', 'monitor', '\0', '\0', NULL),
       (7, '2018-12-18 15:18:26', '\0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs', '', '\0', 'Log'),
       (8, '2018-12-18 15:19:01', '\0', '系统缓存', 'monitor/redis/index', 6, 13, 'redis', 'redis', '\0', '\0', 'Redis'),
       (9, '2018-12-18 15:19:34', '\0', 'SQL监控', 'monitor/sql/index', 6, 14, 'sqlMonitor', 'druid', '\0', '\0', 'Sql'),
       (10, '2018-12-19 13:38:16', '\0', '组件管理', NULL, 0, 50, 'zujian', 'components', '\0', '\0', NULL),
       (11, '2018-12-19 13:38:49', '\0', '图标库', 'components/IconSelect', 10, 51, 'icon', 'icon', '\0', '\0', 'Icons'),
       (14, '2018-12-27 10:13:09', '\0', '邮件工具', 'tools/email/index', 36, 24, 'email', 'email', '\0', '\0', 'Email'),
       (15, '2018-12-27 11:58:25', '\0', '富文本', 'components/Editor', 10, 52, 'fwb', 'tinymce', '\0', '\0', 'Editor'),
       (16, '2018-12-28 09:36:53', '\0', '图床管理', 'tools/picture/index', 36, 25, 'image', 'pictures', '\0', '\0',
        'Pictures'),
       (17, '2018-12-28 15:09:49', '', '项目地址', '', 0, 0, 'github', 'https://github.com/elunez/eladmin', '\0', '\0',
        NULL),
       (18, '2018-12-31 11:12:15', '\0', '存储管理', 'tools/storage/index', 36, 23, 'qiniu', 'storage', '\0', '\0',
        'Storage'),
       (19, '2018-12-31 14:52:38', '\0', '支付宝工具', 'tools/aliPay/index', 36, 27, 'alipay', 'aliPay', '\0', '\0',
        'AliPay'),
       (21, '2019-01-04 16:22:03', '\0', '多级菜单', '', 0, 900, 'menu', 'nested', '\0', '\0', NULL),
       (22, '2019-01-04 16:23:29', '\0', '二级菜单1', 'nested/menu1/index', 21, 999, 'menu', 'menu1', '\0', '\0', NULL),
       (23, '2019-01-04 16:23:57', '\0', '二级菜单2', 'nested/menu2/index', 21, 999, 'menu', 'menu2', '\0', '\0', NULL),
       (24, '2019-01-04 16:24:48', '\0', '三级菜单1', 'nested/menu1/menu1-1', 22, 999, 'menu', 'menu1-1', '\0', '\0', NULL),
       (28, '2019-01-07 20:34:40', '\0', '定时任务', 'system/timing/index', 36, 21, 'timing', 'timing', '\0', '\0',
        'Timing'),
       (30, '2019-01-11 15:45:55', '\0', '代码生成', 'generator/index', 36, 22, 'dev', 'generator', '\0', '\0',
        'GeneratorIndex'),
       (32, '2019-01-13 13:49:03', '\0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog', '\0', '\0',
        'ErrorLog'),
       (33, '2019-03-08 13:46:44', '\0', 'Markdown', 'components/MarkDown', 10, 53, 'markdown', 'markdown', '\0', '\0',
        'Markdown'),
       (34, '2019-03-08 15:49:40', '\0', 'Yaml编辑器', 'components/YamlEdit', 10, 54, 'dev', 'yaml', '\0', '\0',
        'YamlEdit'),
       (35, '2019-03-25 09:46:00', '\0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept', '\0', '\0', 'Dept'),
       (36, '2019-03-29 10:57:35', '\0', '系统工具', '', 0, 20, 'sys-tools', 'sys-tools', '\0', '\0', NULL),
       (37, '2019-03-29 13:51:18', '\0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job', '\0', '\0', 'Job'),
       (38, '2019-03-29 19:57:53', '\0', '接口文档', 'tools/swagger/index', 36, 26, 'swagger', 'swagger2', '\0', '\0',
        'Swagger'),
       (39, '2019-04-10 11:49:04', '\0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict', '\0', '\0', 'Dict');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
                              `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `alias`       varchar(255) DEFAULT NULL COMMENT '别名',
                              `create_time` datetime     DEFAULT NULL COMMENT '创建日期',
                              `name`        varchar(255) DEFAULT NULL COMMENT '名称',
                              `pid`         int(11)    NOT NULL COMMENT '上级权限',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 60
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission`
VALUES (1, '超级管理员', '2018-12-03 12:27:48', 'ADMIN', 0),
       (2, '用户管理', '2018-12-03 12:28:19', 'USER_ALL', 0),
       (3, '用户查询', '2018-12-03 12:31:35', 'USER_SELECT', 2),
       (4, '用户创建', '2018-12-03 12:31:35', 'USER_CREATE', 2),
       (5, '用户编辑', '2018-12-03 12:31:35', 'USER_EDIT', 2),
       (6, '用户删除', '2018-12-03 12:31:35', 'USER_DELETE', 2),
       (7, '角色管理', '2018-12-03 12:28:19', 'ROLES_ALL', 0),
       (8, '角色查询', '2018-12-03 12:31:35', 'ROLES_SELECT', 7),
       (10, '角色创建', '2018-12-09 20:10:16', 'ROLES_CREATE', 7),
       (11, '角色编辑', '2018-12-09 20:10:42', 'ROLES_EDIT', 7),
       (12, '角色删除', '2018-12-09 20:11:07', 'ROLES_DELETE', 7),
       (13, '权限管理', '2018-12-09 20:11:37', 'PERMISSION_ALL', 0),
       (14, '权限查询', '2018-12-09 20:11:55', 'PERMISSION_SELECT', 13),
       (15, '权限创建', '2018-12-09 20:14:10', 'PERMISSION_CREATE', 13),
       (16, '权限编辑', '2018-12-09 20:15:44', 'PERMISSION_EDIT', 13),
       (17, '权限删除', '2018-12-09 20:15:59', 'PERMISSION_DELETE', 13),
       (18, '缓存管理', '2018-12-17 13:53:25', 'REDIS_ALL', 0),
       (20, '缓存查询', '2018-12-17 13:54:07', 'REDIS_SELECT', 18),
       (22, '缓存删除', '2018-12-17 13:55:04', 'REDIS_DELETE', 18),
       (23, '图床管理', '2018-12-27 20:31:49', 'PICTURE_ALL', 0),
       (24, '查询图片', '2018-12-27 20:32:04', 'PICTURE_SELECT', 23),
       (25, '上传图片', '2018-12-27 20:32:24', 'PICTURE_UPLOAD', 23),
       (26, '删除图片', '2018-12-27 20:32:45', 'PICTURE_DELETE', 23),
       (29, '菜单管理', '2018-12-28 17:34:31', 'MENU_ALL', 0),
       (30, '菜单查询', '2018-12-28 17:34:41', 'MENU_SELECT', 29),
       (31, '菜单创建', '2018-12-28 17:34:52', 'MENU_CREATE', 29),
       (32, '菜单编辑', '2018-12-28 17:35:20', 'MENU_EDIT', 29),
       (33, '菜单删除', '2018-12-28 17:35:29', 'MENU_DELETE', 29),
       (35, '定时任务管理', '2019-01-08 14:59:57', 'JOB_ALL', 0),
       (36, '任务查询', '2019-01-08 15:00:09', 'JOB_SELECT', 35),
       (37, '任务创建', '2019-01-08 15:00:20', 'JOB_CREATE', 35),
       (38, '任务编辑', '2019-01-08 15:00:33', 'JOB_EDIT', 35),
       (39, '任务删除', '2019-01-08 15:01:13', 'JOB_DELETE', 35),
       (40, '部门管理', '2019-03-29 17:06:55', 'DEPT_ALL', 0),
       (41, '部门查询', '2019-03-29 17:07:09', 'DEPT_SELECT', 40),
       (42, '部门创建', '2019-03-29 17:07:29', 'DEPT_CREATE', 40),
       (43, '部门编辑', '2019-03-29 17:07:52', 'DEPT_EDIT', 40),
       (44, '部门删除', '2019-03-29 17:08:14', 'DEPT_DELETE', 40),
       (45, '岗位管理', '2019-03-29 17:08:52', 'USERJOB_ALL', 0),
       (46, '岗位查询', '2019-03-29 17:10:27', 'USERJOB_SELECT', 45),
       (47, '岗位创建', '2019-03-29 17:10:55', 'USERJOB_CREATE', 45),
       (48, '岗位编辑', '2019-03-29 17:11:08', 'USERJOB_EDIT', 45),
       (49, '岗位删除', '2019-03-29 17:11:19', 'USERJOB_DELETE', 45),
       (50, '字典管理', '2019-04-10 16:24:51', 'DICT_ALL', 0),
       (51, '字典查询', '2019-04-10 16:25:16', 'DICT_SELECT', 50),
       (52, '字典创建', '2019-04-10 16:25:29', 'DICT_CREATE', 50),
       (53, '字典编辑', '2019-04-10 16:27:19', 'DICT_EDIT', 50),
       (54, '字典删除', '2019-04-10 16:27:30', 'DICT_DELETE', 50),
       (55, '文件管理', '2019-09-08 12:31:54', 'LOCALSTORAGE_ALL', 0),
       (56, '文件搜索', '2019-09-08 12:40:53', 'LOCALSTORAGE_SELECT', 55),
       (57, '文件上传', '2019-09-08 12:41:05', 'LOCALSTORAGE_CREATE', 55),
       (58, '文件编辑', '2019-09-08 12:41:19', 'LOCALSTORAGE_EDIT', 55),
       (59, '文件删除', '2019-09-08 12:41:29', 'LOCALSTORAGE_DELETE', 55);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_time` datetime     DEFAULT NULL COMMENT '上传日期',
    `delete_url`  varchar(255) DEFAULT NULL COMMENT '删除的URL',
    `filename`    varchar(255) DEFAULT NULL COMMENT '图片名称',
    `height`      varchar(255) DEFAULT NULL COMMENT '图片高度',
    `size`        varchar(255) DEFAULT NULL COMMENT '图片大小',
    `url`         varchar(255) DEFAULT NULL COMMENT '图片地址',
    `username`    varchar(255) DEFAULT NULL COMMENT '用户名称',
    `width`       varchar(255) DEFAULT NULL COMMENT '图片宽度',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `picture`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_job`
--

DROP TABLE IF EXISTS `quartz_job`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_job`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `bean_name`       varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
    `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
    `is_pause`        bit(1)       DEFAULT NULL COMMENT '状态：1暂停、0启用',
    `job_name`        varchar(255) DEFAULT NULL COMMENT '任务名称',
    `method_name`     varchar(255) DEFAULT NULL COMMENT '方法名称',
    `params`          varchar(255) DEFAULT NULL COMMENT '参数',
    `remark`          varchar(255) DEFAULT NULL COMMENT '备注',
    `update_time`     datetime     DEFAULT NULL COMMENT '创建或更新日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='自动任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_job`
--

LOCK TABLES `quartz_job` WRITE;
/*!40000 ALTER TABLE `quartz_job`
    DISABLE KEYS */;
INSERT INTO `quartz_job`
VALUES (1, 'visitsTask', '0 0 0 * * ?', '\0', '更新访客记录', 'run', NULL, '每日0点创建新的访客记录', '2019-01-08 14:53:31'),
       (2, 'testTask', '0/5 * * * * ?', '', '测试1', 'run1', 'test', '带参测试，多参使用json', '2019-08-22 14:08:29'),
       (3, 'testTask', '0/5 * * * * ?', '', '测试', 'run', '', '不带参测试', '2019-09-26 16:44:39');
/*!40000 ALTER TABLE `quartz_job`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_log`
--

DROP TABLE IF EXISTS `quartz_log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_log`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `baen_name`        varchar(255) DEFAULT NULL,
    `create_time`      datetime     DEFAULT NULL,
    `cron_expression`  varchar(255) DEFAULT NULL,
    `exception_detail` text         DEFAULT NULL,
    `is_success`       bit(1)       DEFAULT NULL,
    `job_name`         varchar(255) DEFAULT NULL,
    `method_name`      varchar(255) DEFAULT NULL,
    `params`           varchar(255) DEFAULT NULL,
    `time`             bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 52
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='自动任务日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_log`
--

LOCK TABLES `quartz_log` WRITE;
/*!40000 ALTER TABLE `quartz_log`
    DISABLE KEYS */;
INSERT INTO `quartz_log`
VALUES (51, 'testTask', '2019-10-19 17:20:54', '0/5 * * * * ?', NULL, '', '测试1', 'run1', 'test', 3);
/*!40000 ALTER TABLE `quartz_log`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
                        `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `create_time` datetime     DEFAULT NULL COMMENT '创建日期',
                        `name`        varchar(255) NOT NULL COMMENT '名称',
                        `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
                        `data_scope`  varchar(255) DEFAULT NULL,
                        `level`       int(255)     DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role`
VALUES (1, '2018-11-23 11:04:37', '超级管理员', '·', '全部', 1),
       (2, '2018-11-23 13:09:06', '普通用户', '用于测试菜单与权限', '自定义', 3),
       (4, '2019-05-13 14:16:15', '普通管理员', '普通管理员级别为2，使用该角色新增用户时只能赋予比普通管理员级别低的角色', '自定义', 2);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_depts`
--

DROP TABLE IF EXISTS `roles_depts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_depts`
(
    `role_id` bigint(20) NOT NULL,
    `dept_id` bigint(20) NOT NULL,
    PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
    KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE,
    CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
    CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='角色部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_depts`
--

LOCK TABLES `roles_depts` WRITE;
/*!40000 ALTER TABLE `roles_depts`
    DISABLE KEYS */;
INSERT INTO `roles_depts`
VALUES (2, 5),
       (2, 8),
       (2, 9),
       (4, 6),
       (4, 7);
/*!40000 ALTER TABLE `roles_depts`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_menus`
--

DROP TABLE IF EXISTS `roles_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_menus` (
                               `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
                               KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE,
                               CONSTRAINT `FKo7wsmlrrxb2osfaoavp46rv2r` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
                               CONSTRAINT `FKtag324maketmxffly3pdyh193` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_menus`
--

LOCK TABLES `roles_menus` WRITE;
/*!40000 ALTER TABLE `roles_menus` DISABLE KEYS */;
INSERT INTO `roles_menus`
VALUES (1, 1),
       (1, 2),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 1),
       (5, 2),
       (6, 1),
       (6, 2),
       (7, 1),
       (8, 1),
       (8, 2),
       (9, 1),
       (9, 2),
       (10, 1),
       (10, 2),
       (11, 1),
       (11, 2),
       (14, 1),
       (14, 2),
       (15, 1),
       (15, 2),
       (16, 1),
       (16, 2),
       (17, 1),
       (17, 2),
       (18, 1),
       (18, 2),
       (19, 1),
       (19, 2),
       (21, 1),
       (21, 2),
       (22, 1),
       (22, 2),
       (23, 1),
       (23, 2),
       (24, 1),
       (24, 2),
       (28, 1),
       (28, 2),
       (30, 1),
       (30, 2),
       (32, 1),
       (33, 1),
       (33, 2),
       (34, 1),
       (34, 2),
       (35, 1),
       (35, 2),
       (36, 1),
       (36, 2),
       (37, 1),
       (37, 2),
       (38, 1),
       (38, 2),
       (39, 1),
       (39, 2);
/*!40000 ALTER TABLE `roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_permissions`
--

DROP TABLE IF EXISTS `roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_permissions` (
                                     `role_id`       bigint(20) NOT NULL COMMENT '角色ID',
                                     `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                     PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
                                     KEY `FKboeuhl31go7wer3bpy6so7exi` (`permission_id`) USING BTREE,
                                     CONSTRAINT `FK4hrolwj4ned5i7qe8kyiaak6m` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                                     CONSTRAINT `FKboeuhl31go7wer3bpy6so7exi` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_permissions`
--

LOCK TABLES `roles_permissions` WRITE;
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
INSERT INTO `roles_permissions`
VALUES (1, 1),
       (2, 3),
       (2, 8),
       (2, 14),
       (2, 20),
       (2, 23),
       (2, 24),
       (2, 25),
       (2, 26),
       (2, 30),
       (2, 36),
       (2, 41),
       (2, 46),
       (2, 51),
       (4, 3),
       (4, 4),
       (4, 5);
/*!40000 ALTER TABLE `roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                        `id`                       bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `avatar_id`                bigint(20)   DEFAULT NULL COMMENT '头像',
                        `create_time`              datetime     DEFAULT NULL COMMENT '创建日期',
                        `email`                    varchar(255) DEFAULT NULL COMMENT '邮箱',
                        `enabled`                  bigint(20)   DEFAULT NULL COMMENT '状态：1启用、0禁用',
                        `password`                 varchar(255) DEFAULT NULL COMMENT '密码',
                        `username`                 varchar(255) DEFAULT NULL COMMENT '用户名',
                        `last_password_reset_time` datetime     DEFAULT NULL COMMENT '最后修改密码的日期',
                        `dept_id`                  bigint(20)   DEFAULT NULL,
                        `phone`                    varchar(255) DEFAULT NULL,
                        `job_id`                   bigint(20)   DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE,
                        UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
                        UNIQUE KEY `username` (`username`) USING BTREE,
                        KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`) USING BTREE,
                        KEY `FKfftoc2abhot8f2wu6cl9a5iky` (`job_id`) USING BTREE,
                        KEY `FKpq2dhypk2qgt68nauh2by22jb` (`avatar_id`) USING BTREE,
                        CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
                        CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
                        CONSTRAINT `FKpq2dhypk2qgt68nauh2by22jb` FOREIGN KEY (`avatar_id`) REFERENCES `user_avatar` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 2, '2018-08-23 09:11:56', 'admin@eladmin.net', 1, 'e10adc3949ba59abbe56e057f20f883e', 'admin',
        '2019-05-18 17:34:21', 2, '18888888888', 11),
       (3, NULL, '2018-12-27 20:05:26', 'test@eladmin.net', 1, 'e10adc3949ba59abbe56e057f20f883e', 'test',
        '2019-04-01 09:15:24', 2, '17777777777', 12),
       (5, NULL, '2019-04-02 10:07:12', 'hr@eladmin.net', 1, 'e10adc3949ba59abbe56e057f20f883e', 'hr', NULL, 11,
        '15555555555', 8),
       (6, NULL, '2019-10-21 21:27:38', '123@qq.com', 0, 'e10adc3949ba59abbe56e057f20f883e', '鸡歌', NULL, 2,
        '17614884176', 11);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_avatar`
--

DROP TABLE IF EXISTS `user_avatar`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_avatar`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `real_name` varchar(255) DEFAULT NULL,
    `path`      varchar(255) DEFAULT NULL,
    `size`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='用户头像表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_avatar`
--

LOCK TABLES `user_avatar` WRITE;
/*!40000 ALTER TABLE `user_avatar`
    DISABLE KEYS */;
INSERT INTO `user_avatar`
VALUES (2, 'avatar灯泡-20191021093418389.png',
        '/home/dormirr/IdeaProjects/personnel-management-server/avatar灯泡-20191021093418389.png', '24.38KB   ');
/*!40000 ALTER TABLE `user_avatar`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
    KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE,
    CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles`
    DISABLE KEYS */;
INSERT INTO `users_roles`
VALUES (1, 1),
       (3, 2),
       (5, 4),
       (6, 2);
/*!40000 ALTER TABLE `users_roles`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_code`
--

DROP TABLE IF EXISTS `verification_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verification_code` (
                                     `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `code`        varchar(255) DEFAULT NULL COMMENT '验证码',
                                     `create_time` datetime     DEFAULT NULL COMMENT '创建日期',
                                     `status`      bit(1)       DEFAULT NULL COMMENT '状态：1有效、0过期',
                                     `type`        varchar(255) DEFAULT NULL COMMENT '验证码类型：email或者短信',
                                     `value`       varchar(255) DEFAULT NULL COMMENT '接收邮箱或者手机号码',
                                     `scenes`      varchar(255) DEFAULT NULL COMMENT '业务名称：如重置邮箱、重置密码等',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='验证码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_code`
--

LOCK TABLES `verification_code` WRITE;
/*!40000 ALTER TABLE `verification_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visits`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_time` datetime     DEFAULT NULL,
    `date`        varchar(255) DEFAULT NULL,
    `ip_counts`   bigint(20)   DEFAULT NULL,
    `pv_counts`   bigint(20)   DEFAULT NULL,
    `week_day`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UK_11aksgq87euk9bcyeesfs4vtp` (`date`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 98
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='访客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits`
    DISABLE KEYS */;
INSERT INTO `visits`
VALUES (96, '2019-10-19 17:17:27', '2019-10-19', 1, 2, 'Sat'),
       (97, '2019-10-21 14:55:43', '2019-10-21', 1, 9, 'Mon');
/*!40000 ALTER TABLE `visits`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-22 14:31:35
