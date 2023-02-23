insert into t_order (order_id, clothing_type, item_number)
values (1, 't-shirt', 1),
       (2, 'shoe', 2),
       (3, 'shoe', 3),
       (4, 'shoe', 21),
       (5, 'pant', 100);

insert into t_anomaly (anomaly_type, order_order_id, status)
values ('Incorrect number of items', 3, 'CLOSED'),
       ('Incorrect number of items', 4, 'OPEN'),
       ('Max limit of items exceeded', 4, 'OPEN'),
       ('Max limit of items exceeded', 5, 'OPEN');
