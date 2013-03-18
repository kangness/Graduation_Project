create table users(
name varchar(10) unique not null primary key,
password varchar(40) default null,
sex varchar(2) default 'm',
user_key varchar(40) default null
)
