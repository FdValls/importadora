insert into engine(Marca, NroSerie, Potencia)
values('Ducati','1234','1000cc');

insert into engine(Marca, NroSerie, Potencia)
values('BMW','5478','1200cc');

select * from engine;

/***************************************************/

insert into redes(Name, Description)
values('Instagram','FdValls-BestDealer');

insert into redes(Name, Description)
values('Facebook','FdValls');

select * from redes;

/***************************************************/

insert into wheel(Marca, Rodado)
values('Michelin Pilot Street',17);

insert into wheel(Marca, Rodado)
values('Pirelli Dark City',18);

select * from wheel;

/***************************************************/

insert into customer(Name, LastName, Old, Identification)
values('Fernando','Valls',31,'35323873');

insert into customer(Name, LastName, Old, Identification)
values('Daniela','Cohen-Duek',34,'33181205');

select * from customer;

/***************************************************/
    
insert into motorcycle (Chasis,EngineId, WheelID, Patente, Marca, Year, Kms, Estado, Peso)
values('1111',1,1,'A097EOW','Yamaha','2019',14480,'u',150);

insert into motorcycle (Chasis,EngineId, WheelID, Patente, Marca, Year, Kms, Estado, Peso)
values('1111',2,1,'A097EOW','Yamaha','2019',14480,'u',150);

select * from motorcycle;

select e.Marca, m.Kms, m.Marca from engine as e
inner join motorcycle as m on m.EngineId = e.EngineId
where e.EngineId = 1;