--liquibase formatted sql

--changeset MaltStran:table-link

create table link(
    id bigserial primary key,
    url varchar(255) unique not null,
    update timestamp not null
)

--rollback DROP TABLE "link";
