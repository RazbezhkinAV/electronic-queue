create table visitor
(
    id           serial       not null primary key,
    first_name   varchar(255),
    last_name    varchar(255),
    phone_number varchar(255),
    password     varchar(255),
    login        varchar(255) not null unique
);

create table room
(
    id         serial not null primary key,
    name       varchar (255)
);

create table ticket
(
    id         serial not null primary key,
    time       timestamp,
    created    timestamp,
    visitor_id integer,
    room_id    integer,
    foreign key (visitor_id) references visitor (id),
    foreign key (room_id) references room (id)
);