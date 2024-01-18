create database spring_asm3;
use spring_asm3;

-- phòng khám, cơ sở y tế 
create table clinics (
	id int not null auto_increment,
    name varchar(255) not null,
    address varchar(255),
    phone varchar(255),
    introduceHTML text,
    introduce_markdown text,
    description text,
    image varchar(255),
    create_at datetime not null default current_timestamp,
    primary key (id)
);

-- chuyên khoa 
create table specializations (
	id int not null auto_increment,
    name varchar(255) not null,
    description text,
    image varchar(255),
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id)
);

-- loại tài khoản của người sử dụng 
create table roles (
	id int not null auto_increment,
    name varchar(255) not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id)
);
insert into roles(name) values ('User'), ('Doctor'), ('Admin');

-- tài khoản người dùng 
create table users (
	id int not null auto_increment,
    name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	address varchar(255),
    phone varchar(255),
    avatar varchar(255),
    gender varchar(255),
    description text,
    role_id int not null,
    is_active tinyint not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id),
    foreign key (role_id) references roles(id)
);

-- thông tin về tài khoản của bác sĩ
create table doctor_users (
	id int not null auto_increment,
    doctor_id int not null,
    clinic_id int,
    specialization_id int not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    intro text,
    train_process text,
    achievement text,
    primary key (id),
    foreign key (doctor_id) references users(id),
    foreign key (clinic_id) references clinics(id),
    foreign key (specialization_id) references specializations(id)
);

-- thông tin đăng ký khám bệnh của bệnh nhân
create table comments (
	id int not null auto_increment,
    doctor_id int not null,
    price int,
    time_booking time,
    date_booking date,
    name varchar(255),
    gender varchar(255),
    birthday date,
    phone varchar(255),
    address varchar(255),
    content text,
    status tinyint(1) not null,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id),
    foreign key (doctor_id) references doctor_users(id)
);

-- thông tin bệnh nhân
create table patients (
	id int not null auto_increment,
    doctor_id int not null,
    status_id int,
    email varchar(255),
    name varchar(255),
    gender varchar(255),
    birthday date,
    phone varchar(255),
    address varchar(255),
    content text,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id),
    foreign key (doctor_id) references doctor_users(id)
);

-- bệnh lý chi tiết của bệnh nhân
create table extrainfos (
	id int not null auto_increment,
    patient_id int not null,
    history_breath text,
    old_forms text,
    send_forms text,
    more_info text,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id),
    foreign key (patient_id) references patients(id)
);

-- lịch khám bệnh của bác sĩ
create table schedules (
	id int not null auto_increment,
    doctor_id int not null,
    date date not null,
    time time not null,
    max_booking int,
    sum_booking int,
    created_at datetime not null default current_timestamp,
    updated_at datetime,
    deleted_at datetime,
    primary key (id),
    foreign key (doctor_id) references doctor_users(id)
);