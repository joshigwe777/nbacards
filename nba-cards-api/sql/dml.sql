use nba_cards;


insert into team (team_name, city)
values 
		('Lakers', 'Los Angeles'),
    	(' Celtics', 'Boston'),
    	('Mavericks', 'Dallas'),
        ('Pelicans', 'New Orleans'),
        ('Nets', 'Brooklyn'),
        ('Suns', 'Pheonix'),
        ('Heat', 'Miami');

 insert into app_user (username, password_hash, disabled)
        values
        ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
        ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

	insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');
    
    insert into app_user_role
        values
        (1, 2),
        (2, 1);
        

        
 insert into nba_card (`name`, image_url, team_id, ppg, apg, rpg, `position`)
    values 
		("Lebron James", 'https://media.gettyimages.com/photos/lebron-james-of-the-los-angeles-lakers-dunks-against-the-atlanta-in-picture-id1194180180?s=612x612',1, 27.1, 7.4, 7.5,"sf"),
		("Jayson Tatum", 'https://upload.wikimedia.org/wikipedia/commons/9/9f/Jayson_Tatum_%2851687926198%29_%28cropped%29.jpg', 2 , 20.9, 3.0, 6.6,"sf"),
		("Luka Doncic", 'https://www.gannett-cdn.com/presto/2020/10/23/USAT/6fa5033f-9c44-4058-9d08-b80d61265fe6-Luka_Doncic.jpg?crop=3377,2529,x369,y147',3, 26.4, 8.0, 8.5, "pg");