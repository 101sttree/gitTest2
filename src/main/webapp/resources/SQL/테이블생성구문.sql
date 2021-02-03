create table usertb
(
	uno int auto_increment not null,
	udate date not null,
	id varchar(50) not null,
	pw varchar(50) not null,
	name varchar(20) not null,
	primary key(uno)
);
create databases springBoard;
create table boardtb
(
	bno int auto_increment not null, 
	uno int not null,
	hit int not null default 0,
	writer varchar(50) not null,
	title varchar(100) not null,
	bdate date not null,
	btext text not null,
	origino int default 0,
	groupord int default 0,
	grouplayer int default 0,
	primary key(bno),
	foreign key (uno) references usertb (uno)
);

create table commenttb
(
	cno int auto_increment not null,
	bno int not null,
	uno int not null,
	cwriter varchar(50) not null,
	cdate date not null,
	ctext text not null,
	origino int default 0,
	groupord int default 0,
	grouplayer int default 0,
	primary key(cno),
	foreign key (bno) references boardtb (bno),
	foreign key (uno) references usertb (uno)
);

create table filetb
(
	fno int auto_increment not null,
	bno int not null,
	uno int not null, 
	fname varchar(500) not null,
	ex varchar(10) not null,
	path varchar(500) not null,
	fsize int not null,
	primary key(fno),
	foreign key (bno) references boardtb (bno),
	foreign key (uno) references usertb (uno)
);

create table banswer
(
	
)

create table canswer
(
	
)

삭제용 구문
drop table commenttb;
drop table filetb;
drop table boardtb;