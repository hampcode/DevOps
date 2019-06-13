# Script que se ejecuta inicialmente para crear la base de datos
# Autor	: Henry Wong
# Correo: hwongu@gmail.com

CREATE DATABASE visorbd CHARACTER SET utf8 COLLATE utf8_general_ci;
use visorbd;
CREATE USER 'visoruser' IDENTIFIED BY 'visorpass';
GRANT ALL ON visorbd.* TO 'visoruser'@'%' IDENTIFIED BY 'visorpass';
GRANT ALL ON visorbd.* TO 'visoruser'@'localhost' IDENTIFIED BY 'visorpass';
FLUSH PRIVILEGES;
