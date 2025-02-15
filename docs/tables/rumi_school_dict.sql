CREATE TABLE "public"."rumi_school_dict" (
  "id" BIGINT NOT NULL PRIMARY KEY,
  "rmp_school_id" INT4,
  "full_name" VARCHAR,
  "abbr_name" VARCHAR,
  "bot_name" VARCHAR,
  "remark" VARCHAR,
  "creator" VARCHAR DEFAULT ''::VARCHAR,
  "create_time" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" VARCHAR DEFAULT ''::VARCHAR,
  "update_time" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" INT2 NOT NULL DEFAULT 0,
  "tenant_id" INT8 NOT NULL DEFAULT 0
);