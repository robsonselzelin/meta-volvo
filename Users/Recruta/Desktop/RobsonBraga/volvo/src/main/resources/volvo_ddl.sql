create database volvo;

use volvo;

create table DEPARTMENTS (
	id int not null AUTO_INCREMENT
	, name varchar(50)
	, description varchar(50)
	, constraint departments_pk primary key (id)
) ENGINE=InnoDB;

create table PERMISSIONS (
	id int not null AUTO_INCREMENT
	, name varchar(50)
	, description varchar(50)
	, constraint permissions_pk primary key (id)
) ENGINE=InnoDB;

create table USERS (
	id int not null AUTO_INCREMENT
	, name varchar(50)
	, description varchar(50)
	, department_id int
	, constraint user_pk primary key (id)
	, constraint foreign key user_department_fk (department_id) references departments (id)
) ENGINE=InnoDB;

create table USER_PERMISSION (
	user_id int not null
	, permission_id int not null
	, constraint user_permission_pk primary key (user_id, permission_id)
	, constraint foreign key user_permission_permission_fk (permission_id) references permissions (id)
	, constraint foreign key user_permission_user_fk (user_id) references users (id) 
) ENGINE=InnoDB;
