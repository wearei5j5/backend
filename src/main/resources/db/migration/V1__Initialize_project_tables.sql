create table clova_studio_engine_setting
(
    include_ai_filters boolean,
    include_tokens     boolean,
    max_tokens         integer,
    repeat_penalty     float(53),
    temperature        float(53),
    topk               integer,
    topp               float(53),
    id                 bigint not null auto_increment,
    text               varchar(2000),
    restart            varchar(255),
    start              varchar(255),
    stop_before        varchar(255),
    primary key (id)
);

create table movie
(
    created_at       timestamp(6),
    id               bigint not null auto_increment,
    updated_at       timestamp(6),
    user_id          bigint,
    keywords         varchar(255),
    movie_name       varchar(255),
    poster_image_url varchar(255),
    release_date     varchar(255),
    primary key (id)
);

create table recommended_movie_log
(
    created_at       timestamp(6),
    id               bigint not null auto_increment,
    updated_at       timestamp(6),
    keywords         varchar(255),
    movie_name       varchar(255),
    poster_image_url varchar(255),
    release_date     varchar(255),
    primary key (id)
);

create table refresh_token
(
    created_at timestamp(6),
    id         bigint not null auto_increment,
    updated_at timestamp(6),
    user_id    bigint,
    jwt        varchar(255),
    primary key (id)
);

create table review
(
    satisfaction varchar(255),
    created_at   timestamp(6),
    id           bigint not null auto_increment,
    updated_at   timestamp(6),
    primary key (id)
);
create table static_config
(
    created_at      timestamp(6),
    id              bigint not null auto_increment,
    updated_at      timestamp(6),
    client_base_url varchar(255),
    primary key (id)
);
create table user
(
    age               integer,
    created_at        timestamp(6),
    id                bigint not null auto_increment,
    updated_at        timestamp(6),
    name              varchar(255),
    ott_list          varchar(255),
    role              varchar(255),
    social_provider   varchar(255),
    social_uid        varchar(255),
    state             varchar(255),
    profile_image_url varchar(255),
    primary key (id)
);