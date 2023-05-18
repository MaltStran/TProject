--liquibase formatted sql

--changeset zhamwan:table-chat

create table chat(
    id bigserial primary key,
    chat_id bigserial,
    name text
)

--rollback DROP TABLE "link";