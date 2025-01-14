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

 Date: 02/12/2024 19:51:34
*/


-- ----------------------------
-- Table structure for tio_boot_admin_sa_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."tio_boot_admin_sa_token";
CREATE TABLE "public"."tio_boot_admin_sa_token" (
  "id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "value" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "timeout" "pg_catalog"."int8",
  "ob" "pg_catalog"."bytea",
  "ob_timeout" "pg_catalog"."int8",
  "remark" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "creator" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "create_time" "pg_catalog"."timestamptz" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "update_time" "pg_catalog"."timestamptz" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" "pg_catalog"."int2" DEFAULT 0,
  "tenant_id" "pg_catalog"."int8" NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Primary Key structure for table tio_boot_admin_sa_token
-- ----------------------------
ALTER TABLE "public"."tio_boot_admin_sa_token" ADD CONSTRAINT "tio_boot_admin_sa_token_pkey" PRIMARY KEY ("id");
