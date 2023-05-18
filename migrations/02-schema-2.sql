--liquibase formatted sql

--changeset MaltStrann:table-chat

create table chat(
    id bigserial primary key,
    chat_id bigserial,
    name text
)

--rollback DROP TABLE "link";
