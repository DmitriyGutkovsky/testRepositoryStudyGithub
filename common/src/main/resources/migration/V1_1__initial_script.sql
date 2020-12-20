create table m_users
(
    id         bigserial                                                  not null
        constraint m_users_pkey
            primary key,
    name       varchar(100)                                               not null,
    surname    varchar(100)                                               not null,
    birth_date date,
    gender     varchar(20)      default 'NOT_SELECTED'::character varying not null,
    created    timestamp(6)     default CURRENT_TIMESTAMP                 not null,
    changed    timestamp(6)     default CURRENT_TIMESTAMP                 not null,
    weight     double precision default 65                                not null,
    login      varchar(100)     default 'login'::character varying        not null,
    password   varchar(100)     default 'password'::character varying     not null
);

alter table m_users
    owner to test;

create index m_users_name_index
    on m_users (name);

create index m_users_birth_date_index
    on m_users (birth_date);

create index m_users_created_index
    on m_users (created desc);

create index m_users_gender_index
    on m_users (gender);

create index m_users_weight_index
    on m_users (weight);

create unique index m_users_login_uindex
    on m_users (login);

create table m_cars
(
    id            bigserial                                           not null
        constraint m_cars_pk
            primary key,
    model         varchar(100)                                        not null,
    creation_year integer                                             not null,
    user_id       bigint                                              not null
        constraint m_cars_m_users_id_fk
            references m_users,
    price         double precision default 11000                      not null,
    color         varchar(100)     default 'BLACK'::character varying not null
);

alter table m_cars
    owner to postgres;

create unique index m_cars_id_uindex
    on m_cars (id);

create table m_roles
(
    id        bigserial                                           not null
        constraint m_roles_pk
            primary key,
    role_name varchar(100) default 'ROLE_USER'::character varying not null,
    user_id   bigint                                              not null
        constraint m_roles_m_users_id_fk
            references m_users
            on delete cascade
);

alter table m_roles
    owner to postgres;

create index m_roles_role_name_index
    on m_roles (role_name);

create table m_goods
(
    id    bigserial        not null
        constraint m_goods_pk
            primary key,
    name  varchar(100)     not null,
    price double precision not null
);

alter table m_goods
    owner to postgres;

create table l_user_goods
(
    id      bigserial not null
        constraint l_user_goods_pk
            primary key,
    user_id bigint    not null
        constraint l_user_goods_m_users_id_fk
            references m_users,
    good_id bigint    not null
        constraint l_user_goods_m_goods_id_fk
            references m_goods
);

alter table l_user_goods
    owner to postgres;

