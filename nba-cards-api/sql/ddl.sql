drop database if exists nba_cards;
create database nba_cards;
use nba_cards;

create table team (
    team_id int primary key auto_increment,
    team_name varchar(150) not null,
    city varchar(250) not null
);

create table nba_card (
	card_id int primary key auto_increment,
    `name` varchar(150) not null,
    image_url varchar(350) not null,
    team_id int not null,
    ppg decimal(5,3) not null,
    apg decimal(5,3) not null,
    rpg decimal(5,3) not null,
	`position` varchar(250) not null,
    constraint fk_nba_card_team_id
        foreign key (team_id)
        references team(team_id)
);

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled boolean not null default(0)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);