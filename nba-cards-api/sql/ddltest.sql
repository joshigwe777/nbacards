drop database if exists nba_cards_test;
create database nba_cards_test;
use nba_cards_test;

create table team (
    team_id int primary key auto_increment,
    team_name varchar(150) not null,
    city varchar(250) not null
);

create table nba_card (
	card_id int primary key auto_increment,
    `name` varchar(150) not null,
    image_url varchar(250) not null,
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
delimiter //
create procedure set_known_good_state()
begin
	delete from app_user_role;
	delete from app_role;
    alter table app_role auto_increment = 1;
	delete from app_user;
    alter table app_user auto_increment = 1;
	delete from nba_card;
    alter table nba_card auto_increment = 1;
	delete from team;
    alter table team auto_increment = 1;

    insert into app_role (`name`) values
        ('USER'),
        ('ADMIN');

    -- passwords are set to "P@ssw0rd!"
    insert into app_user (username, password_hash, disabled)
        values
        ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
        ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

    insert into app_user_role
        values
        (1, 2),
        (2, 1);

	insert into team (team_name, city)
    values
    	('Lakers', 'Los Angeles'),
    	(' Celtics', 'Boston'),
    	('Mavericks', 'Dallas');

    insert into nba_card (`name`, image_url, team_id, ppg, apg, rpg, `position`)
    values 
		("Lebron James", "https://media.gettyimages.com/photos/lebron-james-of-the-los-angeles-lakers-dunks-against-the-atlanta-in-picture-id1194180180?s=612x612",1, 27.1, 7.4, 7.5,"sf"),
		("Jayson Tatum", "https://upload.wikimedia.org/wikipedia/commons/9/9f/Jayson_Tatum_%2851687926198%29_%28cropped%29.jpg", 2 , 20.9, 3.0, 6.6,"sf"),
		("Luka Doncic", "https://www.gannett-cdn.com/presto/2020/10/23/USAT/6fa5033f-9c44-4058-9d08-b80d61265fe6-Luka_Doncic.jpg?crop=3377,2529,x369,y147",3, 26.4, 8.0, 8.5, "pg");
end //
delimiter ;