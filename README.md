由于第一次使用GitHub，上传项目还在摸索中。
该项目的目录结构为：
src-->
     com.d302-->
	client-->
		Client.java
	controller-->
		Menu.java
		Operator.java
	dao-->
		BookDao.java
		BookDaoImpl.java
	entity-->
		Book.java
	service-->
		BookService.java
		BookServiceImpl.java
	util-->
		DButil.java
		Demo.java
		Input.java
    
另，数据库建表语句为：
create database if not exists d302;
use d302;
create table Book(
    id int identity(1,1) primary key not null,
    title varchar(30),
    author varchar(30),
    publisher varchar(30),
    pub_date date,
    price float
)
