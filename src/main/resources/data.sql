-- Active: 1713626085459@@127.0.0.1@5432@testdb
insert into t_exchange_rates (id, symbol, on_close_value, date)
    values 
        (nextval('per_seq'), 'USD/KZT', 446.41501, '2024-04-19 10:23:54+05'),
        (nextval('per_seq'), 'USD/KZT', 446.15500, '2024-04-18 10:23:54+05'),
        (nextval('per_seq'), 'USD/KZT', 448.55499, '2024-04-17 10:23:54+05');