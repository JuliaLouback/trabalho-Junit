CREATE DATABASE bd_funcionario
USE bd_funcionario

drop table Funcionario
CREATE TABLE  Funcionario (
  Id_funcionario INT IDENTITY(1,1),
  Cpf VARCHAR(14) NOT NULL UNIQUE,
  Nome VARCHAR(80) NOT NULL,
  Email VARCHAR(60) NOT NULL,
  Cargo VARCHAR(45) NOT NULL,
  Data_nascimento DATE NOT NULL,
  Salario FLOAT NOT NULL,
   PRIMARY KEY (Id_funcionario))


   select * from Funcionario