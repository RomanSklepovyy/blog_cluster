create table user
(
	id int auto_increment
		primary key,
	email varchar(50) not null,
	password varchar(20) null,
	name varchar(30) null,
	date_created datetime not null,
	date_last_entered datetime not null
)CHARACTER SET utf8 COLLATE utf8_general_ci;

create table note
(
    id int auto_increment
        primary key,
    user_id int not null,
    title varchar(25) null,
    text longtext null,
    date_created datetime null,
    date_last_edited datetime null

)CHARACTER SET utf8 COLLATE utf8_general_ci;


INSERT INTO blog_database.user (id, email, password, name, date_created, date_last_entered)
VALUES (1, 'test@gmail.com', '1111', 'Test User', '2019-04-19 16:04:26', '2019-04-19 16:04:31');

INSERT INTO blog_database.note (id, user_id, title, text, date_created, date_last_edited)
VALUES (1, 1, 'Hello', 'Hello world!', '2019-04-19 16:04:26', '2019-04-19 16:04:31');