 Practica 2 PW
 

 Autores:
    - Manuel Cabrera Crespo
    - Javier García Fernández
    - Álvaro Eusebio Pérez
    - Francisco Javier Fernández Pastor

Hemos usado un archivo config.poperties para la conexion a la base de datos.
Esta es su estructura:

    -usuario=i12cacrm
	-password=pwgm4
	-servidor=jdbc:mysql://oraclepr.uco.es:3306/i12cacrm
	-nombre=i12cacrm

	Usuario: es el usuario para entrar en PhpMyAdmin, propietario de la base de datos.
	-Password: contraseña en PhpMyAdmin.
	-Servidor: url del servidor de la UCO.
	-Nombre: usuario propietario de la base de datos.
	    
	    
Por otro lado, hemos usado un sql.properties donde se encuentran las consultas SQL utilizadas para acceder a la BD.
Esta es su estructura:
	Nombre_consulta=consulta_sql 


Este es el comando para ejecutar el programa por linea de comandos:
    java -jar pw.jar