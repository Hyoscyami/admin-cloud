-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: uims
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(18) COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `is_admin` bit(1) DEFAULT b'0' COMMENT '是否为超管，1：超管，0：不是超管',
  `avatar` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='账号基本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role_relation`
--

DROP TABLE IF EXISTS `account_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `account_id` bigint unsigned NOT NULL COMMENT '账号id，即account.id',
  `role_id` bigint unsigned NOT NULL COMMENT '角色id，即role.id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='账号权限关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role_relation`
--

LOCK TABLES `account_role_relation` WRITE;
/*!40000 ALTER TABLE `account_role_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org`
--

DROP TABLE IF EXISTS `org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `org` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `parent_id` bigint unsigned NOT NULL COMMENT '父id',
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型，1:实体组织，2：部门',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `source` tinyint unsigned NOT NULL COMMENT '数据来源，1：系统新增',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org`
--

LOCK TABLES `org` WRITE;
/*!40000 ALTER TABLE `org` DISABLE KEYS */;
/*!40000 ALTER TABLE `org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_node_relation`
--

DROP TABLE IF EXISTS `org_node_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `org_node_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `parent_id` bigint unsigned NOT NULL COMMENT '父id',
  `child_id` bigint unsigned NOT NULL COMMENT '子Id',
  `distance` int NOT NULL COMMENT '子组织距离父组织中间隔了几级，当parent_id等于child_id时为0',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织节点关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_node_relation`
--

LOCK TABLES `org_node_relation` WRITE;
/*!40000 ALTER TABLE `org_node_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_node_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_role_relation`
--

DROP TABLE IF EXISTS `org_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `org_role_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `org_id` bigint unsigned NOT NULL COMMENT '组织id，即org.id',
  `role_id` bigint unsigned NOT NULL COMMENT '角色id，即role.id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织角色关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_role_relation`
--

LOCK TABLES `org_role_relation` WRITE;
/*!40000 ALTER TABLE `org_role_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `org_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `parent_id` bigint unsigned NOT NULL COMMENT '父id，根节点为0',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `router_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '前端页面路由名称',
  `path` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标路径',
  `hidden` bit(1) DEFAULT NULL COMMENT '是否隐藏，1：是，0：否',
  `is_leaf` bit(1) DEFAULT b'1' COMMENT '是否为叶子节点，1：是，0：否',
  `type` tinyint unsigned DEFAULT NULL COMMENT '权限类型，1:页面，2:按钮',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='权限';
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型，1：普通角色，2：预设角色',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission_relation`
--

DROP TABLE IF EXISTS `role_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `role_id` bigint unsigned NOT NULL COMMENT '角色id，即role.id',
  `permission_id` bigint unsigned NOT NULL COMMENT '权限id，即permission.id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission_relation`
--

LOCK TABLES `role_permission_relation` WRITE;
/*!40000 ALTER TABLE `role_permission_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-27 16:58:49
