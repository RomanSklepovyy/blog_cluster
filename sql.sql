create table user
(
	id int not null,
	name varchar(30) not null,
	password varchar(20) not null,
	email varchar(30) not null,
	date_creation datetime not null,
	date_lastentered datetime not null,
	constraint user_email_uindex
		unique (email),
	constraint user_id_uindex
		unique (id),
	constraint user_password_uindex
		unique (password)
);

alter table user
	add primary key (id);

create table note
(
	id int not null,
	user_id int not null,
	text longtext null,
	date_created datetime not null,
	date_last_edited datetime not null,
	constraint note_id_uindex
		unique (id),
	constraint note_user_id_uindex
		unique (user_id)
);

alter table note
	add primary key (id);

