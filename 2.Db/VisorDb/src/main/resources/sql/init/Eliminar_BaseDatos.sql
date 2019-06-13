# Script que se ejecuta para eliminar la base de datos


DROP DATABASE IF EXISTS visorbd;
DROP USER 'visoruser'@'localhost';
DROP USER 'visoruser'@'%';