create table product
(
    id           serial primary key,
    product_code varchar(7) unique,
    product_name varchar(256) not null
);

create table model
(
    id            serial primary key,
    product_model varchar(128) unique,
    unit_price    int     not null,
    product_id    integer not null
--     constraint product_model_fk foreign key (product_id) references product (id)
);

create table location
(
    id      serial primary key,
    country varchar(30) not null,
    city    varchar(30),
    constraint location_uk unique (country, city)
);

create table supply_center
(
    id                 serial primary key,
    supply_center_name varchar(80) not null,
    director_name      varchar(80) not null,
    constraint supply_center_uk unique (supply_center_name)
);

create table salesman
(
    id               serial primary key,
    name             varchar(80) not null,
    salesman_number  varchar(8)  not null,
    gender           varchar(6)  not null,
    age              int         not null,
    mobile_phone     varchar(20) not null,
    supply_center_id integer     not null,
    constraint salesman_uk unique (name, salesman_number, gender, age, mobile_phone)
--     constraint salesman_supply_center_fk foreign key (supply_center_id) references supply_center(id)
);

create table enterprise
(
    id               serial primary key,
    enterprise_name  varchar(80) not null,
    industry         varchar(80) not null,
    location_id      integer     not null,
    supply_center_id integer     not null,
    constraint enterprise_uk unique (enterprise_name, industry, location_id, supply_center_id)
--     constraint location_enterprise foreign key (location_id) references location (id),
--     constraint enterprise_supply_center_fk foreign key (supply_center_id) references supply_center (id)

);

create table contract
(
    id              serial primary key,
    contract_number varchar(10) not null unique,
    contract_date   date        not null,
    enterprise_id   integer     not null
--     constraint enterprise_contract foreign key (enterprise_id) references enterprise (id)
);

create table orders
(
    id                      serial primary key,
    quantity                integer not null,
    estimated_delivery_date date    not null,
    lodgement_date          date,
    model_id                integer not null,
    contract_id             integer not null,
    constraint orders_uk unique (quantity, estimated_delivery_date, lodgement_date, model_id, contract_id)
--     constraint contract_order foreign key (contract_id) references contract (id),
--     constraint model_order foreign key (model_id) references model (id)
);

-- truncate table contract, enterprise, location, model, orders, product, salesman, supply_center;