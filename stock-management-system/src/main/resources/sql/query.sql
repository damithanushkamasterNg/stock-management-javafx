drop database if exists restaurant;

create database if not exists restaurant;

use restaurant;

create table customer(
                         id varchar(15) primary key,
                         name varchar(50) not null,
                         address text not null,
                         tel varchar(15) not null
);

create table item(
                     code varchar(15) primary key,
                     description text not null,
                     unit_price double not null,
                     qty_on_hand int not null
);