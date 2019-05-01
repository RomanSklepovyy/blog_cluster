create table note
(
  id int auto_increment
    primary key,
  user_id int not null,
  title varchar(25) null,
  text longtext null,
  date_created datetime null,
  date_last_edited datetime null
);

INSERT INTO data.note (id, user_id, title, text, date_created, date_last_edited)
VALUES (1, 1, 'Hello', 'Hello world!', '2019-04-19 16:04:26', '2019-04-19 16:04:31');



