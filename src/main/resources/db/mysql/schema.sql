CREATE TABLE IF NOT EXISTS usuario
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   dni VARCHAR (30) NOT NULL,
   nombre VARCHAR (30) NOT NULL,
   apellidos VARCHAR (30),
   genero ENUM
   (
      'Femenino',
      'Masculino',
      'Otro',
      'Prefiero no decirlo'
   )
   NOT NULL,
   telefono VARCHAR (30),
   fecha_nacimiento DATE,
   email VARCHAR (30),
   username VARCHAR (30) NOT NULL,
   password VARCHAR (30) NOT NULL
);
CREATE TABLE IF NOT EXISTS authorities
(
   username VARCHAR (40) NOT NULL,
   authority VARCHAR (40) NOT NULL
);
CREATE TABLE IF NOT EXISTS cliente
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   usuario_id INT (4) UNSIGNED NOT NULL,
   favoritos VARCHAR (50),
   FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);
CREATE TABLE IF NOT EXISTS propietario
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   usuario_id INT (4) UNSIGNED NOT NULL,
   es_inmobiliaria BOOLEAN,
   inmobiliaria TEXT,
   CIF VARCHAR (20),
   FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);
CREATE TABLE IF NOT EXISTS vivienda
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   propietario_id INT (4) UNSIGNED NOT NULL,
   fecha_publicacion DATE NOT NULL,
   direccion VARCHAR (80) NOT NULL,
   zona VARCHAR (50) NOT NULL,
   precio INTEGER NOT NULL,
   dimensiones INTEGER,
   amueblado BOOLEAN,
   planta VARCHAR (20),
   foto TEXT,
   caracteristicas TEXT NOT NULL,
   equipamiento TEXT,
   denunciado BOOLEAN,
   FOREIGN KEY (propietario_id) REFERENCES propietario (id)
);
CREATE TABLE IF NOT EXISTS habitacion
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   vivienda_id INT (4) UNSIGNED NOT NULL,
   tipoHabitacion ENUM
   (
      'Dormitorio',
      'Cocina',
      'Baño',
      'Salon comedor',
      'Sala de estar'
   )
   NOT NULL,
   numCamas INT (10),
   caracteristicas TEXT,
   foto VARCHAR (50),
   FOREIGN KEY (vivienda_id) REFERENCES vivienda (id)
);
CREATE TABLE IF NOT EXISTS compra
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   cliente_id INT (4) UNSIGNED NOT NULL,
   vivienda_id INT (4) UNSIGNED NOT NULL,
   precio_final INTEGER NOT NULL,
   estado ENUM
   (
      'ACEPTADO',
      'RECHAZADO',
      'PENDIENTE'
   )
   NOT NULL,
   comentario VARCHAR (255),
   FOREIGN KEY (cliente_id) REFERENCES cliente (id),
   FOREIGN KEY (vivienda_id) REFERENCES vivienda (id)
);
CREATE TABLE IF NOT EXISTS visita
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   cliente_id INT (4) UNSIGNED NOT NULL,
   fecha DATE NOT NULL,
   lugar VARCHAR (40) NOT NULL,
   FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);
CREATE TABLE IF NOT EXISTS valoracion
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   visita_id INT (4) UNSIGNED NOT NULL,
   puntuacion INT (5) NOT NULL,
   comentario VARCHAR (50),
   FOREIGN KEY (visita_id) REFERENCES visita (id)
);
CREATE TABLE IF NOT EXISTS mensaje
(
   id INT (4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   sender_id INT (4) UNSIGNED NOT NULL,
   recipient_id INT (4) UNSIGNED NOT NULL,
   destinatario VARCHAR (20) NOT NULL,
   receptor VARCHAR (20) NOT NULL,
   asunto VARCHAR (20) NOT NULL,
   cuerpo VARCHAR (50),
   FOREIGN KEY (sender_id) REFERENCES usuario (id),
   FOREIGN KEY (recipient_id) REFERENCES usuario (id)
);