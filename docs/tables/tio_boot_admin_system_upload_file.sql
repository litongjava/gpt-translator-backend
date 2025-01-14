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

 Date: 02/12/2024 19:52:17
*/


-- ----------------------------
-- Table structure for tio_boot_admin_system_upload_file
-- ----------------------------
DROP TABLE IF EXISTS "public"."tio_boot_admin_system_upload_file";
CREATE TABLE "public"."tio_boot_admin_system_upload_file" (
  "id" "pg_catalog"."int8" NOT NULL,
  "md5" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "size" "pg_catalog"."int8" NOT NULL,
  "user_id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "platform" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "region_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "bucket_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "file_id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "target_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "tags" "pg_catalog"."json",
  "creator" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "create_time" "pg_catalog"."timestamp" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "update_time" "pg_catalog"."timestamp" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" "pg_catalog"."int2" NOT NULL DEFAULT 0,
  "tenant_id" "pg_catalog"."int8" NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Records of tio_boot_admin_system_upload_file
-- ----------------------------
INSERT INTO "public"."tio_boot_admin_system_upload_file" VALUES (453512791798644736, '75051d7182a1a938768fdfb767b62569', 'ICS141-Session1--Intro.pdf', 924466, NULL, 'aws s3', 'us-west-1', 'imagi', '"75051d7182a1a938768fdfb767b62569"', 'translate/453512777775022080.pdf', NULL, '', '2024-12-02 18:58:14.815633', '', '2024-12-02 18:58:14.815633', 0, 0);

-- ----------------------------
-- Primary Key structure for table tio_boot_admin_system_upload_file
-- ----------------------------
ALTER TABLE "public"."tio_boot_admin_system_upload_file" ADD CONSTRAINT "tio_boot_admin_system_upload_file_pkey" PRIMARY KEY ("id");
