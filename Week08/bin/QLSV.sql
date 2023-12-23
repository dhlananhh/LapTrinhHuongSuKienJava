create database QLSV;
go

use QLSV
go

create table LopHoc (
	maLop varchar(30) primary key,
	tenLop nvarchar(100) unique not null,
	gvcn nvarchar(100)
)
go

create table SinhVien (
	maSV varchar(20) primary key,
	hoTen nvarchar(100) not null,
	diaChi nvarchar(100),
	email varchar(100),
	maLop varchar(30) foreign key references LopHoc(maLop)
)
go