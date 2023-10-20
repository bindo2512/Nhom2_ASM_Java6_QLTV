use qlns
go
drop database qltv
go
create database qltv
go
use qltv
go
create table bname(
	booknameid int primary key identity (1,1),
	bookname nvarchar(255)
)

create table categories(
	categoryid int primary key identity(1,1),
	categoryname nvarchar(255),
)

create table authors (
	authorid int primary key identity(1,1),
	authorname nvarchar(255),
)

create table publishers(
	publisherid int primary key identity(1,1),
	publishername nvarchar(255),
	publisheraddress nvarchar(255),
)

create table orderstate(
	orderstateid int primary key identity(1,1),
	orderstatename nvarchar(255),
)

create table books(
	bookid int primary key identity(1,1),
	booknameid int foreign key references bname(booknameid),
	authorid int foreign key references authors(authorid),
	publisherid int foreign key references publishers(publisherid),
	categoryid int foreign key references categories(categoryid),
	description nvarchar(max),
	yearpub int,
	image varchar(255),
	pdf varchar(255),
	available bit,
)

create table accounts (
	username varchar(255) primary key,
	password varchar(255),
	isadmin varchar(5),
	isactive bit,
)

create table accountdetail(
	accountdetailid int primary key identity(1,1),
	username varchar(255) foreign key references accounts(username),
	fullname nvarchar(255),
	phonenum varchar(50),
	address nvarchar(255),
	email varchar(255),
	gender bit,
	image varchar(255),
)

create table retails (
	retailid int primary key identity(1,1),
	retaildate date,
	returndate date,
	username varchar(255) foreign key references accounts(username)
)

create table details (
	detailid int primary key identity(1,1),
	retailid int foreign key references retails(retailid),
	bookid int foreign key references books(bookid),
	orderstateid int foreign key references orderstate(orderstateid)
)

create table comments(
	commentid int primary key identity(1,1),
	bookid int foreign key references books(bookid),
	username varchar(255) foreign key references accounts(username),
	commentdes nvarchar(max),
)
