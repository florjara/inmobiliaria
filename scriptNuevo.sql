use inmobiliaria;
INSERT INTO rol (rol_id, eliminado, rol_nombre) VALUES (NULL, 0, "ADMIN"),
(NULL, 0, "USUARIO");

INSERT INTO images(propiedad_id,imagen)VALUES(1,'casa1a.jpg'),(1,'casa1b.jpg'),(1,'casa1c.jpg'),(2,'casa2a.jpg'),(2,'casa2b.jpg'),(2,'casa2c.jpg'),
(3,'casa3a.jpg'),(3,'casa3b.jpg'),(3,'casa3b.jpg'),(4,'casa4a.jpg'),(4,'casa4b.jpg'),(4,'casa4c.jpg'),(5,'casa5a.jpg'),(5,'casa5b.jpg'),
(5,'casa5c.jpg'),(6,'casa6a.jpg'),(6,'casa6b.jpg'),(6,'casa6c.jpg'),(7,'casa7a.jpg'),(7,'casa7b.jpg'),(7,'casa7c.jpg'),(8,'casa8a.jpg'),(8,'casa8b.jpg'),
(8,'casa8c.jpg');


INSERT INTO propiedad(id,ambiente,banos,ciudad,descripcion,direccion,eliminado,estacionamiento,patio,precio,tipo,tipo_transaccion,titulo,usuario) VALUES 

(1,2,1,"POSADAS", "flexible, que se adecua a tus necesidades de hoy y mañana.","Avenida Quaranta 235",0,0,0,4500000,"DEPARTAMENTO","VENTA","Departamento dos ambientes",1),

(2,3,1,"POSADAS", "Alquiler de Casa 3 DORMITORIOS en Las Orquideas","Av Urquiza 4600. Entre Las araucarias y Av las heras
",0,1,1,100000,"CASA","ALQUILER","Casa en Barrio Las Orquideas",2),

(3,6,3,"OBERA", "Preciosa casa desarrollada en dos plantas, con piscina en un entorno inmejorable.
Terreno de 505m2 !!","La Paz 500",0,1,1,9797775,"CASA","VENTA","Venta Casa con Pileta Oberá Misiones",2),

(4,3,1,"POSADAS", "Consta de dos dormitorios (uno con aire acondicionado), baño, Cocina-comedor, galerias al frente y al costado, y cochera","Luchessi 2748. Entre Gomez portinho y Sebastopol
",0,1,1,70000,"CASA","ALQUILER","Casa de 2 Dorm. Luchessi E/ Lavalle y Fco. de Haro",2),

(5,7,1,"OBERA", "Casa amplia en barrio muy tranquilo, 3 dormitorios + 1 local, cocina, comedor, living, cochera techada para 4 autos, quincho, pileta, muros perimetrales, sobre calle asfaltada con buena iluminación",
"Olavarria 1254",0,1,1,9225089,"CASA","VENTA","Amplia Casa",2),

(6,3,1,"POSADAS", "Departamento sobre calle Mbororé Nº2635 casi calle Iguazú. a pasos del centro de Posadas.
Cuenta con dos habitaciones, cocina con alacena y bajo mesada, sector de lavadero, amplio baño, aire acondicionado, placard.","Avenida Quaranta 235",0,1,0,70000,"DEPARTAMENTO","ALQUILER","Departamento de Dos Dorm. en Zona El Brete",2),

(7,4,2,"POSADAS", "HERMOSA PROPIEDAD EN VENTA EN BARRIO SAN MARTIN.","Padre Serrano 2909. Entre Antonio de llamas y Av blas parera",0,1,1,85000,"CASA","ALQUILER","Casa de 3 Dorm. en Venta",2),

(8,7,2,"OBERA", "","Chubut 100",0,1,1,1000000,"CASA","VENTA","En Venta, Casa Céntrica En Oberá, Diseño Y Calidad",1),

(9,2,1,"POSADAS", "flexible, que se adecua a tus necesidades de hoy y mañana.","Avenida Quaranta 235",0,0,0,7500000,"DEPARTAMENTO","VENTA","Departamento dos ambientes",1);