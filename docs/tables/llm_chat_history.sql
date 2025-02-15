DROP TABLE IF EXISTS llm_chat_history;
CREATE TABLE "llm_chat_history" (
  "id" BIGINT PRIMARY KEY,
  "session_id" BIGINT,
  "role" VARCHAR NOT NULL,
  "content" VARCHAR NOT NULL,
  "citions" varchar,
  "type" varchar default 'text',
  "metadata" JSONB,
  "hidden" BOOLEAN DEFAULT FALSE,
  "liked" BOOLEAN,
  "remark" VARCHAR(256),
  "creator" VARCHAR(64) DEFAULT '',
  "create_time" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" VARCHAR(64) DEFAULT '',
  "update_time" TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" SMALLINT DEFAULT 0,
  "tenant_id" BIGINT NOT NULL DEFAULT 0
);