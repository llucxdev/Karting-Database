drop database if exists karting;
create database karting;
use karting;

create table driver (
driver_id int primary key auto_increment,
name varchar(45) not null unique,
dob date not null,
age int not null,
laps int default null,
races int default null,
podiums int default null,
wins int default null,
team int default null,
kart int default null,
img longblob default null
);

create table team (
team_id int primary key auto_increment,
name varchar(45) not null unique,
date date not null,
img longblob default null
);

create table kart (
kart_id int primary key auto_increment,
available boolean default true
);

create table lap (
lap_id int primary key auto_increment,
driver_id int not null,
kart_id int not null,
time timestamp(3) not null,
date date not null
);

create table race (
race_id int primary key auto_increment,
date date not null,
laps int not null
);

create table race_result (
race int not null,
driver int not null,
position int,
primary key (`race`, `driver`)
);