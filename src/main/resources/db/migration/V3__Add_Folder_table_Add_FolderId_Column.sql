create table folder
(
    id         bigint not null auto_increment,
    user_id    bigint,
    name       varchar(255),
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);

alter table movie
    add folder_id bigint null;