package es.uco.pw.data.dao.usuario;

import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.data.common.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Un DAO para los usuarios que hace uso de una conexión a una base de datos MySQL a través de JDBC. 
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class DAOusuario extends DBconnection
{
	/**
	 * Almacena un usuario en la base de datos.
	 *
	 * @param usuario El DTO del usuario que se va a almacenar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */

    public boolean Almacenar(DTOUsuario usuario, Properties prop, Properties sqlProperties)
    {
        

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("GuardarUsuario");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1,usuario.getNombreApellidos());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(usuario.getFechaNacimiento());
            ps.setDate(2, sqlDate1);

            if(usuario.esrequiereAtencionEspecial())
            {
                ps.setBoolean(3, true);
            }
            else
            {
                ps.setBoolean(3, false);
            }

            ps.setString(4,usuario.getEmail());
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getRol());

            return ps.executeUpdate()>0;

            

        }
        catch(Exception e)
        {
			System.out.println(e);

        }
        return false;

    }
    
    /**
     * Modifica un usuario en la base de datos.
     *
     * @param usuario El DTO del usuario que se va a modificar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean Modificar(DTOUsuario usuario, Properties prop, Properties sqlProperties)
    {
    	
              
        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ModificarUsuario");
            PreparedStatement ps = con.prepareStatement(statement);
            
            ps.setString(1,usuario.getNombreApellidos());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(usuario.getFechaNacimiento());
            ps.setDate(2, sqlDate1);

            if(usuario.esrequiereAtencionEspecial())
            {
                ps.setBoolean(3, true);
            }
            else
            {
                ps.setBoolean(3, false);
            }

            ps.setString(4, usuario.getPassword());
        
            ps.setInt(5, usuario.getIdentificador());


           return ps.executeUpdate() > 0;                

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Borra un usuario en la base de datos.
     *
     * @param usuario El DTO del usuario que se va a almacenar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean Borrar(DTOUsuario usuario, Properties prop, Properties sqlProperties)
    {
    	

        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BorrarUsuario");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, usuario.getIdentificador());

            return ps.executeUpdate()>0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return false;

    }
    
    /**
     * Saca la informacion de la tabla Usuario y lo guarda en una lista.
     *
     * @return la lista de actividades.
     */

    public ArrayList<DTOUsuario> ListaUsuarios(Properties prop, Properties sqlProperties){
        ArrayList<DTOUsuario> lista = new ArrayList<DTOUsuario>();
        

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("LeerUsuario");
			PreparedStatement ps = con.prepareStatement(statement);
		    ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int identificador = rs.getInt("identificador");
                String nombre = rs.getString("nombre_apellidos");
                LocalDate date = java.time.LocalDate.parse(rs.getDate("fechaNacimiento").toString());
                boolean atencion = rs.getBoolean("requiereAtencionEspecial");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String rol = rs.getString("rol");

                DTOUsuario aux = new DTOUsuario();
                aux.setIdentificador(identificador);
                aux.setNombreApellidos(nombre);
                aux.setFechaNacimiento(date);
                aux.setRequiereAtencionEspecial(atencion);
                aux.setEmail(email);
                aux.setPassword(password);
                aux.setRol(rol);

                lista.add(aux);
            }
        } catch(Exception e) {
			System.out.println(e);
		}
        return lista;
    }

    /**
     * Saca la informacion de la tabla Usuario y lo guarda en una lista.
     * @param identificador El identificador del usuario que se va a obtener.
     * @return El DTO del usuario que se va a obtener.
     */

     public DTOUsuario getUsuario(int identificador, Properties prop, Properties sqlProperties)
    {
    	       
        DTOUsuario Usuario = new DTOUsuario();
        try
        {
        	
        	DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement= sqlProperties.getProperty("LeerUsuarioPorId");         
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1,identificador);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Usuario.setIdentificador(identificador);
                Usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                Usuario.setNombreApellidos(rs.getString("nombre_apellidos"));
                Usuario.setRequiereAtencionEspecial(rs.getBoolean("requiereAtencionEspecial"));
                Usuario.setEmail(rs.getString("email"));
                Usuario.setPassword(rs.getString("password"));
                Usuario.setRol(rs.getString("rol"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return Usuario;
    }

/**
     * Obtiene un usuario de la base de datos a partir de su email.
     *
     * @param email El email del usuario que se va a obtener.
     * @return El DTO del usuario que se va a obtener.
     */
    
    public DTOUsuario UsuarioPorEmail(String email, Properties prop, Properties sqlProperties)
    {

        DTOUsuario usuario = new DTOUsuario();
        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("LeerUsuarioPorEmail");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                usuario.setIdentificador(rs.getInt("identificador"));
                usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                usuario.setNombreApellidos(rs.getString("nombre_apellidos"));
                usuario.setRequiereAtencionEspecial(rs.getBoolean("requiereAtencionEspecial"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
            }

            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return usuario;
    }
    /**
     * Comprueba las credenciales del usuario
     * @param email
     * @param password
     * @return boolean
     * 
     */
    public boolean ComprobarCredenciales(String email, String password, Properties prop, Properties sqlProperties)
    {
        
        try
        {
        	DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ComprobarCredenciales");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
}