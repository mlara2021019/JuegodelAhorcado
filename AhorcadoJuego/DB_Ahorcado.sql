-- drop database if exists db_ahorcado;
create database if not exists db_ahorcado;
use db_ahorcado;

create table if not exists palabras (
    codigoPalabra int auto_increment,
    palabra varchar(100) not null,
    pista1 varchar(255),
    pista2 varchar(255),
    pista3 varchar(255),
    primary key (codigoPalabra)
);

insert into palabras (palabra, pista1, pista2, pista3) 
values('RELAMPAGO', 'Luz brillante en el cielo.', 'Ocurre durante tormentas.', 'Aparece antes del trueno.'),
	  ('ACUARELA', 'Técnica de pintura.', 'Utiliza agua para diluir colores.', 'Se aplica sobre papel.'),
	  ('TELEFONO', 'Medio de comunicación.', 'Permite hablar a distancia.', 'Tiene botones o pantalla táctil.'),
	  ('VOLCANICO', 'Relacionado con un volcán.', 'Puede ser lava, roca o ceniza.', 'Proviene de erupciones.'),
	  ('MANUSCRITO', 'Texto escrito a mano.', 'Anterior a la imprenta.', 'Puede estar en pergamino.');

delimiter //
create procedure sp_obtener_palabras_aleatorias()
begin
    select codigoPalabra, palabra, pista1, pista2, pista3
    from palabras
    order by rand()
    limit 1;
end //
delimiter ;

call sp_obtener_palabras_aleatorias();