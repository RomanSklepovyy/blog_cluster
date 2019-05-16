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
    user_name varchar(30) null,
    title varchar(25) null,
    text longtext null,
    date_created datetime null,
    date_last_edited datetime null

)CHARACTER SET utf8 COLLATE utf8_general_ci;

