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

 Date: 02/12/2024 19:51:20
*/


-- ----------------------------
-- Table structure for max_kb_document_translate_cache
-- ----------------------------
DROP TABLE IF EXISTS "public"."max_kb_document_translate_cache";
CREATE TABLE "public"."max_kb_document_translate_cache" (
  "id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "target" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "content" "pg_catalog"."text" COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of max_kb_document_translate_cache
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table max_kb_document_translate_cache
-- ----------------------------
ALTER TABLE "public"."max_kb_document_translate_cache" ADD CONSTRAINT "max_kb_document_translate_cache_pkey" PRIMARY KEY ("id");
