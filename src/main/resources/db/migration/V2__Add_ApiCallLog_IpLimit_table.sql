create table api_call_log
(
    id         bigint not null auto_increment,
    ip         varchar(255),
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);

create table ip_limit
(
    id               bigint not null auto_increment,
    ip               varchar(255),
    remain           int,
    total_call_count int,
    created_at       timestamp(6),
    updated_at       timestamp(6),
    primary key (id)
);