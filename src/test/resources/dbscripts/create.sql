create schema importadora;
use importadora;

create table engine (
	EngineId int primary key auto_increment,
    Marca varchar(30),
    NroSerie varchar(30),
    Potencia varchar(10)
);

create table redes(
	RedesId int primary key auto_increment,
    Name varchar(30),
    Description varchar(100)
);

create table wheel(
	WheelId int primary key auto_increment,
    Marca varchar(30),
    Rodado int
);

create table customer(
	CustomerId int primary key auto_increment,
    Name varchar(30),
    LastName varchar(30),
    Old int,
    Identification varchar (40)
);

create table motorcycle(
	MotorcycleId int primary key auto_increment,
    Chasis varchar (60),
    EngineId int unique,
    WheelId int unique,
    Patente varchar(30),
    Marca varchar(30),
    Year varchar (10),
    Kms int,
    Estado char,
    Peso int,
    FOREIGN KEY (EngineId) REFERENCES engine(EngineId),
    FOREIGN KEY (WheelId) REFERENCES wheel(WheelId)
);

create table dealer(
	DealerId int primary key auto_increment,
    RazonSocial varchar(30),
    Cuil varchar(30),
    Direccion varchar(30),
    Telefono varchar(30),
    RedesId int,
    MotorcycleId int,
    CustomerId int,
    FOREIGN KEY (RedesId) REFERENCES redes(RedesId),
    FOREIGN KEY (MotorcycleId) REFERENCES motorcycle(MotorcycleId),
    FOREIGN KEY (CustomerId) REFERENCES customer(CustomerId)
);

create table importer(
	ImporterId int primary key auto_increment,
    Cuil varchar(30),
    Direccion varchar(30),
    Telefono varchar(30),
	RedesId int,
    MotorcycleId int,
    DealerId int,
    FOREIGN KEY (RedesId) REFERENCES redes(RedesId),
	FOREIGN KEY (MotorcycleId) REFERENCES motorcycle(MotorcycleId),
    FOREIGN KEY (DealerId) REFERENCES dealer(DealerId)
);
