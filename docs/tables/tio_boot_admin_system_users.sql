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

 Date: 02/12/2024 19:52:25
*/


-- ----------------------------
-- Table structure for tio_boot_admin_system_users
-- ----------------------------
DROP TABLE IF EXISTS "public"."tio_boot_admin_system_users";
CREATE TABLE "public"."tio_boot_admin_system_users" (
  "id" "pg_catalog"."int8" NOT NULL,
  "username" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "password" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "nickname" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "signature" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "title" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "group_name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "tags" "pg_catalog"."json",
  "notify_count" "pg_catalog"."int4" DEFAULT 0,
  "unread_count" "pg_catalog"."int4" DEFAULT 0,
  "country" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "access" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "geographic" "pg_catalog"."json",
  "address" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "remark" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "dept_id" "pg_catalog"."int8",
  "post_ids" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "email" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "phone" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "sex" "pg_catalog"."int2" DEFAULT 0,
  "avatar" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "status" "pg_catalog"."int2" NOT NULL DEFAULT 0,
  "login_ip" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
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
-- Records of tio_boot_admin_system_users
-- ----------------------------
INSERT INTO "public"."tio_boot_admin_system_users" VALUES (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', 'This is a signature', 'Admin', 'Administrators', '{"tags": [{"key": "tag1", "label": "Tag 1"}, {"key": "tag2", "label": "Tag 2"}]}', 10, 5, 'United States', 'admin', '{"province": {"label": "California", "key": "CA"}, "city": {"label": "San Francisco", "key": "SF"}}', '123 Main St, San Francisco, CA 94122', '管理员', 103, '[1]', 'aoteman@126.com', '15612345678', 1, 'http://127.0.0.1:48080/admin-api/infra/file/4/get/your_avatar.png', 0, '127.0.0.1', '2023-11-30 09:16:00', 'admin', '2021-01-05 17:03:47', NULL, '2024-03-23 08:49:55', 0, 1);

-- ----------------------------
-- Uniques structure for table tio_boot_admin_system_users
-- ----------------------------
ALTER TABLE "public"."tio_boot_admin_system_users" ADD CONSTRAINT "tio_boot_admin_system_users_username_key" UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table tio_boot_admin_system_users
-- ----------------------------
ALTER TABLE "public"."tio_boot_admin_system_users" ADD CONSTRAINT "tio_boot_admin_system_users_pkey" PRIMARY KEY ("id");
