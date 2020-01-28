create database projetojava;
use projetojava;

create table tblusuario
(
	usrId int(5) primary key not null auto_increment,
    usrNome varchar(20) not null,
    usrSobrenome varchar(30) not null,
    usrEmail varchar(100) not null,
    usrCpf varchar(11) not null,
    usrSexo varchar(1) not null
)