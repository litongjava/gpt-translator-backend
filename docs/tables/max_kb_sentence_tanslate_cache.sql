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

 Date: 02/12/2024 19:51:27
*/


-- ----------------------------
-- Table structure for max_kb_sentence_tanslate_cache
-- ----------------------------
DROP TABLE IF EXISTS "public"."max_kb_sentence_tanslate_cache";
CREATE TABLE "public"."max_kb_sentence_tanslate_cache" (
  "id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "from" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "user_id" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "md5" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "src_lang" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "dst_lang" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "src_text" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "dst_text" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "model" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "system_fingerprint" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "completion_tokens" "pg_catalog"."int4",
  "prompt_tokens" "pg_catalog"."int4",
  "total_tokens" "pg_catalog"."int4",
  "elapsed" "pg_catalog"."int8"
)
;

-- ----------------------------
-- Records of max_kb_sentence_tanslate_cache
-- ----------------------------
INSERT INTO "public"."max_kb_sentence_tanslate_cache" VALUES ('453508125885038592', 'telegram', 'UserId{6276672963}', '49f68a5c8493ec2c0bf489821c21fc3b', 'English', 'Chinese', 'hi', '嗨', 'gpt-4o-2024-08-06', 'fp_7f6be3efb0', 2, 50, 52, 1550);
INSERT INTO "public"."max_kb_sentence_tanslate_cache" VALUES ('453508237982007296', 'telegram', 'UserId{6276672963}', '35090d476b7b5cf0da288b8e8e3f1acb', 'English', 'Chinese', 'do we have other question?', '我们还有其他问题吗？', 'gpt-4o-2024-08-06', 'fp_7f6be3efb0', 6, 54, 60, 590);
INSERT INTO "public"."max_kb_sentence_tanslate_cache" VALUES ('453508261142953984', 'telegram', 'UserId{6276672963}', '755f85c2723bb39381c7379a604160d8', 'English', 'Chinese', 'good', '好', 'gpt-4o-2024-08-06', 'fp_a7d06e42a7', 1, 50, 51, 540);

-- ----------------------------
-- Indexes structure for table max_kb_sentence_tanslate_cache
-- ----------------------------
CREATE INDEX "max_kb_sentence_tanslate_cache_md5" ON "public"."max_kb_sentence_tanslate_cache" USING btree (
  "md5" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "max_kb_sentence_tanslate_cache_md5_model" ON "public"."max_kb_sentence_tanslate_cache" USING btree (
  "md5" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "model" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table max_kb_sentence_tanslate_cache
-- ----------------------------
ALTER TABLE "public"."max_kb_sentence_tanslate_cache" ADD CONSTRAINT "max_kb_sentence_tanslate_cache_pkey" PRIMARY KEY ("id");
