INSERT IGNORE INTO usuario VALUES (1, '89501021V', 'David', 'Salcedo', 2, '660196547', '1964-04-17', 'davidsa@gmail.com', 'davidsa', 'davidsa');
INSERT IGNORE INTO usuario VALUES (2, '29537424L', 'Maria', 'Sanchez', 3, '916353741', '2010-05-08', 'maria@gilmarcentro.com', 'gilmar', 'gilmar');
INSERT IGNORE INTO usuario VALUES (3, '57430722K', 'Celia', 'Herrero', 1, '658996632', '1993-12-12', 'herrero@gmail.com', 'celiaherrero', 'celiaherrero');
INSERT IGNORE INTO usuario VALUES (4, '12815770D', 'Bruno', 'Velasco Bravo', 4, '954899654', '1980-08-18', 'bravo@gmail.com', 'bravo9', 'bravo9');
INSERT IGNORE INTO usuario VALUES (5, '90422933C', 'Silvia', 'Hernandez Mora', 1, '689544723', '1988-10-10', 'silvia@inmocasa.com', 'inmocasa', 'inmocasa');
INSERT IGNORE INTO usuario VALUES (6, '22781434A', 'Alonso', 'Soler', 2, '925448965', '1976-01-04', 'alonso@gmail.com', 'alonso7', 'alonso7');
INSERT IGNORE INTO usuario VALUES (7, '20239533Q', 'Julia', 'Torres Ortiz', 1, '698854123', '1987-10-11', 'julia@gmail.com', 'juliatorres', 'juliatorres');
INSERT IGNORE INTO usuario VALUES (8, '43724217K', 'Vicente', 'Camacho', 2, '698854780', '1965-06-11', 'vicente@housin.com', 'housininmo', 'housininmo');
INSERT IGNORE INTO usuario VALUES (9, '25421224Z', 'Alejandra', null, 1, '965482301', '1999-07-25', 'ale@gmail.com', 'alejandra', 'alejandra');
INSERT IGNORE INTO usuario VALUES (10, '68618216Q', 'Andres', 'Gomez', 2, '987452362', '1956-09-10', 'gomez@gmail.com', 'gomez7', 'gomez7');
INSERT IGNORE INTO usuario VALUES (11, '58876764F', 'Rodrigo', 'Castillo Castro', 2, '632100259', '1987-12-08', 'rcc@gmail.com', 'rodrigo', 'rodrigo');
INSERT IGNORE INTO usuario VALUES (12, '71918848X', 'Teresa', 'Lopez Sanchez', 2, '658003214', '1991-09-18', 'teresa@inversionesreina.com', 'inversionesreina', 'inversionesreina');
INSERT IGNORE INTO usuario VALUES (13, '65683746R', 'Administrador', null, 3, '654789958', '1991-09-18', 'admin@admin.com', 'admin', 'admin');

INSERT IGNORE INTO authority VALUES ('admin', 'admin');
INSERT IGNORE INTO authority VALUES ('davidsa', 'propietario');
INSERT IGNORE INTO authority VALUES ('gilmar', 'propietario');
INSERT IGNORE INTO authority VALUES ('celiaherrero', 'propietario');
INSERT IGNORE INTO authority VALUES ('bravo9', 'cliente');
INSERT IGNORE INTO authority VALUES ('inmocasa', 'propietario');
INSERT IGNORE INTO authority VALUES ('alonso7', 'cliente');
INSERT IGNORE INTO authority VALUES ('juliatorres', 'propietario');
INSERT IGNORE INTO authority VALUES ('housininmo', 'propietario');
INSERT IGNORE INTO authority VALUES ('alejandra', 'cliente');
INSERT IGNORE INTO authority VALUES ('gomez7', 'cliente');
INSERT IGNORE INTO authority VALUES ('rodrigo', 'cliente');
INSERT IGNORE INTO authority VALUES ('inversionesreina', 'propietario');

INSERT IGNORE INTO propietario VALUES (1, 2, 1, 'Gilmar Centro', 'D98828643');
INSERT IGNORE INTO propietario VALUES (2, 5, 1, 'Inmocasa', 'H92374206');
INSERT IGNORE INTO propietario VALUES (3, 8, 1, 'Housin', 'Q5021330E');
INSERT IGNORE INTO propietario VALUES (4, 12, 1, 'Inversiones Reina', 'R2223473F');
INSERT IGNORE INTO propietario VALUES (5, 3, 0, null, null);
INSERT IGNORE INTO propietario VALUES (6, 7, 0, null, null);
INSERT IGNORE INTO propietario VALUES (7, 1, 0, null, null);

INSERT IGNORE INTO cliente VALUES (1, 11, null);
INSERT IGNORE INTO cliente VALUES (2, 4, null);
INSERT IGNORE INTO cliente VALUES (3, 6, '2, 6');
INSERT IGNORE INTO cliente VALUES (4, 9, '8');
INSERT IGNORE INTO cliente VALUES (5, 10, null);

INSERT IGNORE INTO vivienda VALUES (1, 1, '2010-05-08', 'Calle Juzgado, Barrio San Julián, Sevilla', 'Centro', 215.000, 72, true, 'Primera planta', 'https://st3.idealista.com/news/archivos/styles/news_detail/public/2018-01/consejos_publicacion_foto_3.jpg', 'La entrada a la vivienda se encuentra en la planta primera del edificio (Sin ascensor). En la planta baja del piso se encuentra la cocina, el salón, una habitación con armario empotrado y un cuarto de baño completo con bañera. Subimos a la planta superior de la vivienda a través de una escalera de caracol situada en el salón y que da acceso a la estancia superior; desde la cual podemos salir a la terraza privada del piso.', 'Aire acondicionado', false);
INSERT IGNORE INTO vivienda VALUES (2, 1, '2015-09-18', 'Avenida Buhaira, 30, Sevilla', 'Nervion', 605.000, 151, true, 'Tercera planta', 'https://img3.idealista.com/blur/WEB_LISTING_TOP/90/id.pro.es.image.master/d5/7f/2a/733661786.jpg', 'Casa Forestier está situada al este del centro de Sevilla, en el moderno y lujoso distrito de Nervión. Céntrico pero tranquilo, y a un agradable paseo del centro histórico. Vivirás a pocos minutos del parque de María Luisa y del intercambiador de San Bernardo, que te permitirá moverte cómodamente por la ciudad en transporte público tanto con el tranvía, metro o tren. La oferta de ocio y servicios es inmejorable en el barrio, estarás al lado del Centro Comercial Nervión Plaza o de El Corte Inglés. Las casas tienen un diseño inteligente, podrás aprovechar cada rincón de tu casa para hacerla más acogedora.', 'Garaje, Trastero', false);
INSERT IGNORE INTO vivienda VALUES (3, 3, '2020-01-13', 'Barrio Arenal - Museo, Sevilla', 'Centro', 549.000, 165, false, 'Quinta planta', 'https://img3.idealista.com/blur/WEB_LISTING_TOP/0/id.pro.es.image.master/a6/3e/cc/750934600.jpg', 'Disfrute de este maravilloso piso, en pleno centro de Sevilla, en la zona del Mercado del Barranco, a un paso del Puente de Triana y al lado del barrio del Arenal, centro histórico de la ciudad. El inmueble consta de 4 habitaciones, de las que 3 de ellas son habitaciones dobles, todas con armarios empotrados y 3 cuartos de baños completos', 'Aire acondicionado', false);
INSERT IGNORE INTO vivienda VALUES (4, 4, '2019-05-02', 'Avenida Kansas City, 36, Barrio Luis Montoto - Santa Justa, Sevilla', 'Nervion', 475.000, 123, false, 'Octava planta', 'https://img3.idealista.com/blur/WEB_LISTING/0/id.pro.es.image.master/c7/4c/31/750112795.jpg', 'Vivienda de nueva construcción completamente a estrenar en 8ª planta con 123,20 m², 3 habitaciones, 2 baños, terraza y piscinas comunitaria. Garaje y trastero en opción. La vivienda tiene fachada a la avenida Kansas City con unas privilegiadas vistas y luz natural gracias a situarse en la penúltima planta del edificio', null, false);
INSERT IGNORE INTO vivienda VALUES (5, 3, '2018-10-12', 'Glorieta de las Cigarreras, 1, Sevilla', 'Los Remedios', 1150.000, 337, true, 'Tercera planta', 'https://img3.idealista.com/blur/WEB_LISTING_TOP/0/id.pro.es.image.master/21/78/eb/753584893.jpg', 'Fantástica vivienda de 377 m2 construidos, en Glorieta de las Cigarreras, con espectaculares vistas al Rio Guadalquivir. Garaje y trastero.', 'Aire acondicionado', false);
INSERT IGNORE INTO vivienda VALUES (6, 5, '2016-06-28', 'ROCHELAMBERT s/n, Barrio La Plata, Sevilla', 'Cerro Amate', 95.260, 64, false, 'Primera planta', 'https://img3.idealista.com/blur/WEB_LISTING_TOP/0/id.pro.es.image.master/e6/30/32/739796621.jpg', 'Estupendo piso RECIÉN REFORMADO, listo para entrar a vivir. Piso consta de 63 m2, distribuidos en dos dormitorios dobles muy amplios y luminosos, baño completo y cocina muy amplia con zona de comedor totalmente amueblada con mesa y sillas, todo muy luminoso, carpintería de aluminio, suelo de gres porcelanico split frió/calor. Linea de autobús cercana, colegios, centro de salud y zonas infantiles, rodeado de zona comercial ¡NO LO DUDES ESTE ES TU PISO!', 'Aire acondicionado', false);
INSERT IGNORE INTO vivienda VALUES (7, 6, '2019-11-30', 'AFAN DE RIBERA, 102, Sevilla', 'Cerro Amate', 195.960, 133, true, 'Primera planta', 'https://img3.idealista.com/blur/WEB_LISTING/0/id.pro.es.image.master/b7/de/06/756800072.jpg', '¿Buscas un piso coqueto? No esperes más lo tenemos para ti. Estupendo piso totalmente nuevo en la mejor zona del CERRO DEL ÁGUILA, suelos de mármol, con aluminio lacado en blanco en todas las ventanas, aire acondicionado y calefacción todo centralizado, tres dormitorios y uno de ellos con un baño completo, otro baño completo bastante amplio con placa de ducha. Tres armarios empotrados y una magnifica despensa en la cocina. Linea de autobús cercana, colegios, centro de salud y zonas infantiles, muchos negocios justo enfrente ¡Vamos llámame y empieza a disfrutar de todas sus ventajas!', 'Aire acondicionado', false);
INSERT IGNORE INTO vivienda VALUES (8, 7, '2020-02-10', 'Calle Parque Guell, 4, Sevilla', 'Bellavista', 260.800, 163, true, null, 'https://img3.idealista.com/blur/WEB_LISTING_TOP/0/id.pro.es.image.master/39/e3/99/750535452.jpg', 'SE VENDE ADOSADA DE NUEVA CONSTRUCCIÓN DE 163 M2 CONSTRUIDOS EN PARCELA DE 131 M2 A UN PASO DEL CENTRO DE SEVILLA. La casa dispone de porche delantero con acceso peatonal y acceso para vehículo (garaje) y también dispone de porche trasero de 21 m2 de superficie.', 'Jardín', false);
INSERT IGNORE INTO vivienda VALUES (9, 2, '2020-08-03', 'Calle Mar de Alboran, Sevilla', 'Pino Montano', 50.800, 52, true, 'Sexta planta', 'https://img3.idealista.com/blur/WEB_LISTING/0/id.pro.es.image.master/f9/07/6d/741356370.jpg', 'Ya tenemos disponible este estupendo piso justo frente al ambulatorio de calle Mar de Alboran, el edificio tiene ascensor, con todo tipo de negocios en la zona y linea de autobuses con muy buena comunicación, Colegios, Centros de Salud. etc todo muy cercano.', null, false);

INSERT IGNORE INTO habitacion VALUES (1, 2, 1, 2, 'Habitación doble con cama de matrimonio', 'https://d1vp8nomjxwyf1.cloudfront.net/wp-content/uploads/sites/406/2019/11/12151329/23.-COM-800x533.jpg');
INSERT IGNORE INTO habitacion VALUES (2, 2, 2, null, 'Cocina amueblada con horno, microondas, frigorifico', 'https://i.pinimg.com/originals/f8/af/17/f8af17df7fa83f09cc403065859c4414.jpg');
INSERT IGNORE INTO habitacion VALUES (3, 4, 1, 1, 'Habitación individual', 'https://d1bvpoagx8hqbg.cloudfront.net/originals/habitacion-estudiantes-casa-familia-aa8c8a0f835c7a0b15f3f68d849a1d88.jpg');
INSERT IGNORE INTO habitacion VALUES (4, 7, 3, null, 'Habitación doble con cama de matrimonio', 'https://d1vp8nomjxwyf1.cloudfront.net/wp-content/uploads/sites/406/2019/11/12151329/23.-COM-800x533.jpg');
INSERT IGNORE INTO habitacion VALUES (5, 5, 5, null, null, null);
INSERT IGNORE INTO habitacion VALUES (6, 5, 2, null, 'Cocina nueva', 'https://i.blogs.es/1c5aea/planificacion-diseno-y-supervision-del-proyecto-eitan-cohen-studio-etn-creditos-de-imagenes-de-shay-epstein/450_1000.jpeg');
INSERT IGNORE INTO habitacion VALUES (7, 9, 4, null, 'Comedor moderno', 'https://cdn.portobellostreet.es/imagenes_muebles/Muebles-Comedor-vintage-Maze.jpg');

INSERT IGNORE INTO compra VALUES (1, 2, 1, 215.000, 1, null);
INSERT IGNORE INTO compra VALUES (2, 4, 3, 549.000, 2, 'No estoy de acuerdo con la oferta que ofrece');
INSERT IGNORE INTO compra VALUES (3, 3, 6, 195.960, 1, "Muchas gracias");
INSERT IGNORE INTO compra VALUES (4, 2, 8, 260.800, 3, null);

INSERT IGNORE INTO visita VALUES (1, 2, '2019-10-23', 'Calle Juzgado, Sevilla');
INSERT IGNORE INTO visita VALUES (2, 2, '2020-01-15', 'Bellavista');

INSERT IGNORE INTO valoracion VALUES (1, 1, 4, 'Me ha gustado mucho');