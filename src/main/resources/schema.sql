create table currency_name (
    id integer auto_increment not null,
    ename varchar(10) not null,
    cname varchar(10),
    primary key (id),
    constraint uq1 unique (ename)
);

create table currency (
    id bigint auto_increment not null,
    code varchar(10) not null,
    symbol varchar(20),
    rate varchar(50),
    description varchar(200),
    rate_float numeric,
    primary key (id),
    foreign key (code) references currency_name(ename)
);

