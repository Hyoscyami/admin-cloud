-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: developer-platform
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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `client_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端id',
  `client_secret` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端秘钥',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端名称',
  `redirect_uri` varchar(80) COLLATE utf8mb4_general_ci NOT NULL COMMENT '重定向地址',
  `is_admin` bit(1) NOT NULL COMMENT '是否为超管权限',
  `authentication_method` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端认证方式：client_secret_basic(http basic认证),client_secret_post(post提交认证),client_secret_jwt(jwt认证),private_key_jwt(私钥jwt认证),none(不认证),多个的情况以","分割',
  `authorization_grant_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权方式：authorization_code(授权码),refresh_token,client_credentials(客户端授权),password(密码授权),jwt_bearer',
  `is_require_proof_key` bit(1) DEFAULT b'0' COMMENT '是否校验key，0：否，1：是，默认否',
  `is_authorization_consent` bit(1) DEFAULT b'0' COMMENT '是否自定义授权页面，0：否，1：是，默认否',
  `access_token_time_to_live` int DEFAULT '5' COMMENT 'access_token存活时间，单位为分钟，默认5分钟',
  `is_reuse_refresh_tokens` bit(1) DEFAULT b'1' COMMENT '返回access_token时是否重用refresh_token，0：否，1：是，默认1',
  `refresh_token_time_to_live` int DEFAULT '60' COMMENT 'refresh_token存活时间，单位为分钟，默认60分钟',
  `signature_algorithm` char(5) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '签名算法，RS256，RS384,RS512,ES256,ES384,ES512,PS256,PS384,PS512',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) NOT NULL COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户端基本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_group_relation`
--

DROP TABLE IF EXISTS `client_group_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_group_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `client_id` bigint unsigned NOT NULL COMMENT '客户端id',
  `group_id` bigint unsigned NOT NULL COMMENT '分组id',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) NOT NULL COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户端分组关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_group_relation`
--

LOCK TABLES `client_group_relation` WRITE;
/*!40000 ALTER TABLE `client_group_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_group_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scope`
--

DROP TABLE IF EXISTS `scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scope` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `parent_id` bigint unsigned NOT NULL COMMENT '父id',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型，1：oidc类型，2：自定义类型',
  `value` varchar(80) COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `code` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) NOT NULL COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
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
-- Dumping data for table `scope`
--

LOCK TABLES `scope` WRITE;
/*!40000 ALTER TABLE `scope` DISABLE KEYS */;
/*!40000 ALTER TABLE `scope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scope_group`
--

DROP TABLE IF EXISTS `scope_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scope_group` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) NOT NULL COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='权限分组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scope_group`
--

LOCK TABLES `scope_group` WRITE;
/*!40000 ALTER TABLE `scope_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `scope_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scope_group_relation`
--

DROP TABLE IF EXISTS `scope_group_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scope_group_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `scope_id` bigint unsigned NOT NULL COMMENT '权限id',
  `group_id` bigint unsigned NOT NULL COMMENT '分组id',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` tinyint unsigned DEFAULT NULL COMMENT '类型',
  `code` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `deleted` bit(1) NOT NULL COMMENT '是否被删除，1：被删除，0：未删除',
  `sort` int DEFAULT '1' COMMENT '排序，默认为1',
  `status` tinyint unsigned NOT NULL COMMENT '是否有效，1：有效，0：无效',
  `note` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint unsigned NOT NULL COMMENT '创建人id',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `modifier_id` bigint unsigned DEFAULT NULL COMMENT '更新人id',
  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='权限分组关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scope_group_relation`
--

LOCK TABLES `scope_group_relation` WRITE;
/*!40000 ALTER TABLE `scope_group_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `scope_group_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-02 21:37:49
