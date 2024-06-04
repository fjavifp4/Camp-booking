package es.uco.pw.data.common;

import java.io.InputStream;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.io.IOException;

/**
 * Enumeración que representa el tipo de horario.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
*/
/**
 * Clase base para la conexión a la base de datos.
 */

public class DBconnection{

	 /**
     * El servidor de la base de datos.
     */
    protected  String server;

    /**
     * El usuario de la base de datos.
     */
    protected String user;

    /**
     * La contraseña del usuario de la base de datos.
     */
    protected  String password;

    /**
     * El nombre de la base de datos.
     */
    protected String name;

    /**
     * La conexión a la base de datos.
     */
    protected Connection connection = null;

	public DBconnection(){
	}


	
	public DBconnection(Properties prop){
		this.server = prop.getProperty("servidor");
    	this.user = prop.getProperty("usuario");
    	this.password = prop.getProperty("password");
	}

	

    /**
     * Método para obtener las propiedades SQL desde el archivo "sql.properties".
     *
     * @return Las propiedades SQL.
     */
    //public static Properties getSQLProperties(){
	//	Properties sql_properties = new Properties();
	//	try{
//
	//		InputStream sql_properties_file = getClass().getClassLoader().getResourceAsStream("/sql.properties");
//
	//		sql_properties.load(sql_properties_file);
	//	}
	//	catch (IOException e) {
	//		System.out.println("La conexion ha fallado.");
	//		e.printStackTrace();
	//	}
	//	return sql_properties;
	//}
    
    /**
     * Método para cargar las propiedades SQL desde el archivo "sql.properties".
     *
     * @return Las propiedades SQL.
     */
	
	//public Properties cargarProperties() {
	//	Properties p = new Properties();
	//	try{	
	//	    p.load(new FileReader("config.properties"));
	//	    server = new String(p.getProperty("servidor"));
	//	    user = new String(p.getProperty("usuario"));
	//	    password = new String(p.getProperty("password"));
	//	    name = new String(p.getProperty("nombre"));
	//	}catch(IOException e) {
	//		System.err.println("La conexión ha fallado!");
	//		e.printStackTrace();
	//	}
	//	return p;
	//}
	
	/**
     * Método para conectarse a la base de datos.
     *
     * @return La conexion a la BD.
     */

	 

    public Connection getConnection(){
        //cargarProperties();

        try {
        	
            Class.forName("com.mysql.jdbc.Driver");
            this.connection= (Connection) DriverManager.getConnection(this.server,this.user,this.password);
			System.out.println("Conexión establecida con la base de datos.");
		}
        catch (SQLException e) {
			System.err.println("La conexión a la base de datos ha fallado!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("No se ha encontrado el JDBC Driver.");
			e.printStackTrace();
		}
        return this.connection;
	}
    



    /**
     * Método para cerrar la conexion.
     *
     */
	public void closeConnection() {
	try {
		if(this.connection != null && !this.connection.isClosed()) {
			this.connection.close();
			System.out.println("Se ha cerrado la conexión a la base de datos!");
		}
	} catch (SQLException e) {
		System.err.println("Error mientras se cerraba la base de datos.");
		e.printStackTrace();
	}
}

}