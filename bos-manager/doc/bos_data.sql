-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bos
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_permissions`
--

DROP TABLE IF EXISTS `sys_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permissions` (
  `per_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `per_pid` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `code` varchar(64) NOT NULL,
  `page` varchar(128) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`per_id`),
  UNIQUE KEY `sys_permissions_code_uindex` (`code`),
  UNIQUE KEY `sys_permissions_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permissions`
--

LOCK TABLES `sys_permissions` WRITE;
/*!40000 ALTER TABLE `sys_permissions` DISABLE KEYS */;
INSERT INTO `sys_permissions` VALUES (1,NULL,'用户管理','用户管理权限','user:manager','user/managerUser.action',1,1),(2,1,'添加用户','添加用户的权限','user:add','user/addUser.action',1,1),(3,1,'删除用户','删除用户的权限','user:delete','user/deleteUser.action',1,1),(4,1,'修改用户','修改用户的权限','user:modify','user/modifyUser.action',1,1),(5,1,'查询用户','查询用户的权限','user:get','user/getUser.action',1,1),(6,NULL,'角色管理','角色管理权限','role:manager','role/roleManager.action',1,1),(7,NULL,'权限管理','权限管理权限','permission:manager','permission/permissionManager.action',1,1),(8,6,'添加角色','添加角色权限','role:add','role/addRole.action',1,1),(9,6,'编辑角色','编辑角色权限','role:modify','role/modifyRole.action',1,1),(10,6,'删除角色','删除角色权限','role:delete','role/deleteRole.action',1,1),(11,NULL,'管理菜单','管理菜单的权限','menu:manager','',0,1),(12,11,'用户、角色、权限管理','用户、角色、权限管理菜单','menu:user-role-per:manager',' ',1,0),(14,12,'管理用户','用户管理菜单','menu:user-role-manager:user:manager','forwardTo?pageUrl=/WEB-INF/view/user/userList.jsp',1,0),(15,12,'管理角色','角色管理菜单','menu:user-role-manager:role:manager	','forwardTo?pageUrl=/WEB-INF/view/user/roleList.jsp',1,0),(16,12,'管理权限','权限管理菜单','menu:user-role-manager:permission:manager	','forwardTo?pageUrl=/WEB-INF/view/user/permissionList.jsp',1,0),(17,NULL,'博客管理','板块分类的管理','menu:blog:manager','',NULL,0),(18,17,'板块管理','板块相关的管里菜单','menu:bolg:bord:manager','',NULL,0);
/*!40000 ALTER TABLE `sys_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `code` varchar(128) NOT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `sys_role_code_uindex` (`code`),
  UNIQUE KEY `sys_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (9,'普通用户','没有删除操作','role:normal',1),(11,'特殊用户2','瞎编','role:bullshit',NULL),(12,'admin','超级管理员','role:admin',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permissions`
--

DROP TABLE IF EXISTS `sys_role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permissions` (
  `role_id` bigint(20) NOT NULL,
  `per_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`per_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permissions`
--

LOCK TABLES `sys_role_permissions` WRITE;
/*!40000 ALTER TABLE `sys_role_permissions` DISABLE KEYS */;
INSERT INTO `sys_role_permissions` VALUES (9,1),(9,2),(9,4),(9,5),(9,6),(9,8),(9,9),(9,11),(9,12),(9,14),(9,15),(9,16),(11,1),(11,2),(11,3),(11,4),(11,5),(11,11),(11,12),(11,14),(11,15),(11,16),(12,1),(12,2),(12,3),(12,4),(12,5),(12,6),(12,7),(12,8),(12,9),(12,10),(12,11),(12,12),(12,14),(12,15),(12,16),(12,17),(12,18);
/*!40000 ALTER TABLE `sys_role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `salary` decimal(10,0) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender` char(5) DEFAULT NULL,
  `station` varchar(32) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (22,'admin','f669baaf026fdc16203be946c3cddfee',NULL,NULL,30000,NULL,NULL,NULL,'13111111111',NULL),(23,'yu','9070dd13984cae9abe055826d3c76ef4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'yyyu','c9c6c8d67abfcc8d201fff2924f623ed',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,11),(1,12),(5,9),(6,12),(7,12),(15,12),(16,9),(22,12);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_token`
--

DROP TABLE IF EXISTS `sys_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `user_token` varchar(64) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`user_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-token令牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_token`
--

LOCK TABLES `sys_user_token` WRITE;
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` VALUES (22,'09c0fce8-1def-43b8-a5cd-22cebcc65f47','2017-11-16 02:49:43'),(23,'238d2be2-ab9d-4c07-98d1-a56361f2c6b3','2017-11-15 08:43:32');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-23 10:11:35
