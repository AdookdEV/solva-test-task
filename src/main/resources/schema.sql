-- Active: 1713626085459@@127.0.0.1@5432@testdb
-- drop table if exists t_transactions;
-- drop table if exists t_expense_limit;

-- drop table if exists t_transactions;

-- drop table if exists t_expense_limits;


create sequence if not exists t_expense_limits_id_seq start with 1 increment by 50;
create table if not exists t_expense_limits (
    id bigint primary key,
    account varchar(10) not null,
    sum numeric not null,
    remaining_sum numeric not null,
    date timestamp with time zone not null,
    currency varchar(10) not null default 'USD',
    expense_category varchar(50) not null

    
);

create sequence if not exists t_transactions_id_seq start with 1 increment by 50;
create table if not exists t_transactions (
    id bigint primary key,
    account_from varchar(10) not null,
    account_to varchar(10) not null,
    sum numeric not null,
    currency_shortname varchar(10) not null,
    expense_category varchar(50) not null,
    datetime timestamp with time zone not null,
    limit_id bigint not null,
    limit_exceeded boolean not null,

    foreign key (limit_id) references t_expense_limits(id)
);

create sequence if not exists t_exchange_rates_id_seq start with 1 increment by 50;
create table if not exists t_exchange_rates (
    id int primary key,
    symbol varchar(50) not null,
    on_close_value numeric not null,
    date timestamp with time zone not null
);


