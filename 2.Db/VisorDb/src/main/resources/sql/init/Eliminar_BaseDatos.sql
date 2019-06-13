# Script que se ejecuta para eliminar la base de datos
# Autor	: Henry Wong
# Correo: hwongu@gmail.com

DROP DATABASE IF EXISTS visorbd;
DROP USER 'visoruser'@'localhost';
DROP USER 'visoruser'@'%';