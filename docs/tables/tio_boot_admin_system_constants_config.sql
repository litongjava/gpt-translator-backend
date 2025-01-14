/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.9-postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 160003
 Source Host           : 192.168.3.9:5432
 Source Catalog        : gpt_translator
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003
 File Encoding         : 65001

 Date: 02/12/2024 19:52:10
*/


-- ----------------------------
-- Table structure for tio_boot_admin_system_constants_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."tio_boot_admin_system_constants_config";
CREATE TABLE "public"."tio_boot_admin_system_constants_config" (
  "id" "pg_catalog"."int8" NOT NULL,
  "key_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "key_value" "pg_catalog"."text" COLLATE "pg_catalog"."default" NOT NULL,
  "remark" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "login_date" "pg_catalog"."timestamp",
  "creator" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "create_time" "pg_catalog"."timestamp" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "update_time" "pg_catalog"."timestamp" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" "pg_catalog"."int2" NOT NULL DEFAULT 0,
  "tenant_id" "pg_catalog"."int8" NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Records of tio_boot_admin_system_constants_config
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table tio_boot_admin_system_constants_config
-- ----------------------------
ALTER TABLE "public"."tio_boot_admin_system_constants_config" ADD CONSTRAINT "tio_boot_admin_system_constants_config_pkey" PRIMARY KEY ("id");
