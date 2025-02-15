CREATE TABLE "llm_chat_session" (
  "id" BIGINT PRIMARY KEY,
  "user_id" VARCHAR,
  "name" VARCHAR,
  "school_id" INT8,
  "app_id" INT8,
  "type" VARCHAR,
  "chat_type" INT4,
  "remark" VARCHAR(256),
  "creator" VARCHAR(64) DEFAULT '',
  "create_time" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" VARCHAR(64) DEFAULT '',
  "update_time" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" SMALLINT DEFAULT 0,
  "tenant_id" BIGINT NOT NULL DEFAULT 0
);