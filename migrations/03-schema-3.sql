--liquibase formatted sql

--changeset MaltStran:table-chat_link

create table chat_link(
    chat_id bigint not null references "chat" (id),
    link_id bigint not null REFERENCES "link" (id),
    PRIMARY KEY (chat_id,link_id)
)

--rollback DROP TABLE "chat_link";
