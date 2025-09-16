-- drop database if exists db_juego_ahorcado;
create database if not exists db_juego_ahorcado;
use db_juego_ahorcado;

create table if not exists palabras (
    codigo_Palabra int auto_increment,
    palabra varchar(100) not null,
    pista_1 varchar(255),
    pista_2 varchar(255),
    pista_3 varchar(255),
    primary key (codigo_Palabra)
);

create table if not exists Usuario (
	codigo_Usuario int auto_increment,
    usuario varchar(250) not null,
	contrasena varchar(250) not null,
    primary key (codigo_Usuario)
);


insert into palabras (palabra, pista_1, pista_2, pista_3) 
values('RELAMPAGO', 'Luz brillante en el cielo.', 'Ocurre durante tormentas.', 'Aparece antes del trueno.'),
	  ('ACUARELA', 'Técnica de pintura.', 'Utiliza agua para diluir colores.', 'Se aplica sobre papel.'),
	  ('TELEFONO', 'Medio de comunicación.', 'Permite hablar a distancia.', 'Tiene botones o pantalla táctil.'),
	  ('VOLCANICO', 'Relacionado con un volcán.', 'Puede ser lava, roca o ceniza.', 'Proviene de erupciones.'),
	  ('MANUSCRITO', 'Texto escrito a mano.', 'Anterior a la imprenta.', 'Puede estar en pergamino.');
      
      
insert into Usuario (usuario, contrasena)
values("mlara", "mlara1"),
	  ("xportillo", "xportillo1"),
      ("soxcal", "soxcal1"),
      ("jajcabul", "jajcabul1"),
      ("ehor", "ehor1");

select*from Usuario;

delimiter //
create procedure sp_obtener_palabras_aleatorias()
begin
    select codigo_Palabra, palabra, pista_1, pista_2, pista_3
    from palabras
    order by rand()
    limit 1;
end //
delimiter ;

call sp_obtener_palabras_aleatorias();