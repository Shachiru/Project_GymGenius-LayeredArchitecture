DROP DATABASE if exists gym_geniusnew;
create database gym_geniusnew;

use gym_geniusnew;

create table user(
ID varchar(10) primary key,
Username varchar(30)not null,
Password varchar(20)not null
);


create table employee(
ID varchar(10)primary key,
EmpName varchar(30)not null,
Address varchar(30)not null,
Mobile varchar(15),
EmpRole varchar(50)not null,
UserID varchar(10),
FOREIGN KEY (UserID) REFERENCES user(ID)on update cascade on delete cascade
);

create table emp_attendance(
EmpID varchar(10),
Date date,
FOREIGN KEY (EmpID) REFERENCES employee(ID)on update cascade on delete cascade
);

create table salary(
SalaryId int primary key,
EmpID varchar(10),
Date date,
Amount Decimal(10,2),
FOREIGN KEY (EmpID) REFERENCES employee(ID)on update cascade on delete cascade
);

create table member(
ID varchar(10)primary key,
Name varchar(30),
Address varchar(30),
Mobile varchar(15),
DOB date,
Gender varchar(10)
);

create table schedule(
m_id varchar(10),
emp_id varchar(10),
Level varchar(30),
Time time,
FOREIGN KEY (emp_id) REFERENCES employee(ID)on update cascade on delete cascade,
FOREIGN KEY (m_id) REFERENCES member(ID)on update cascade on delete cascade
);

create table c_attendance(
m_id varchar(10),
Date date,
FOREIGN KEY (m_id) REFERENCES member(ID)on update cascade on delete cascade
);

create table payment(
Id int primary key,
m_id varchar(10),
Date date,
Amount decimal(10,2),
FOREIGN KEY (m_id) REFERENCES member(ID)on update cascade on delete cascade
);

create table supplements(
ID varchar(10)primary key,
Description varchar(255),
UnitPrice decimal(10,2),
Qty int
);

create table orders(
o_id varchar(10) primary key,
Date date,
m_id varchar(10),
FOREIGN KEY (m_id) REFERENCES member(ID)on update cascade on delete cascade
);

create table order_detail(
    order_id varchar(10) not null,
    sup_id varchar(10)not null ,
    qty int not null ,
    unitPrice decimal not null ,
    total double,
    foreign key (order_id) references orders(o_id) on update cascade on delete cascade ,
    foreign key (sup_id) references supplements(ID) on update cascade on delete cascade
);

insert into User values ('U001','admin','1234');