CREATE TABLE "llm_vector_embedding" (
  "id" BIGINT PRIMARY KEY,
  "t" VARCHAR,
  "v" VECTOR,
  "m" VARCHAR,
  "md5" VARCHAR
);

CREATE INDEX "idx_llm_vector_embedding_md5" ON "llm_vector_embedding"(
  "md5" ASC NULLS LAST
);