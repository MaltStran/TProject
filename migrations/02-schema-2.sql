--liquibase formatted sql

--changeset MaltStran:table-chat

create table chat(
    id bigserial primary key,
    chat_id bigserial,
    name text
)

--rollback DROP TABLE "link";
