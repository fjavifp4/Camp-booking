package es.uco.pw.data.dao.monitor;

import es.uco.pw.data.common.DBconnection;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.business.monitor.DTOMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Clase DAO para gestionar las operaciones relacionadas con los monitores en la base de datos.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class DAOmonitor extends DBconnection{

	
	 /**
     * Almacena un monitor en la base de datos.
     *
     * @param monitor DTO del monitor a almacenar.
     * @return True si la operación fue exitosa, false en caso contrario.
     */
    public boolean Almacenar(DTOMonitor monitor, Properties prop, Properties sqlProperties){
    	

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("GuardarMonitor");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, monitor.getNombreApellidos());
            boolean educador_especial = false;
            if(monitor.getEducadorEspecial()){
                educador_especial = true;
            }
            else{
                educador_especial = false;
            }
            ps.setBoolean(2, educador_especial);

            return ps.executeUpdate()>0;
        }catch(Exception e) {
			System.out.println(e);

        }
        return false;
    }
    
    /**
     * Modifica un monitor en la base de datos.
     *
     * @param monitor DTO del monitor a modificar.
     * @return True si la operación fue exitosa, false en caso contrario.
     */
    public boolean Modificar(int identificador, DTOMonitor monitor, Properties prop, Properties sqlProperties){
    	

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ModificarMonitor");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, monitor.getNombreApellidos());
            boolean educador_especial = false;
            if(monitor.getEducadorEspecial()){
                educador_especial = true;
            }
            else{
                educador_especial = false;
            }
            ps.setBoolean(2, educador_especial);

            ps.setInt(3, identificador);

            return ps.executeUpdate()>0;

        }catch(Exception e) {
			System.out.println(e);

        }
        return false;
    }
    
    /**
     * Borra un monitor en la base de datos.
     *
     * @param monitor DTO del monitor a borrar.
     * @return True si la operación fue exitosa, false en caso contrario.
     */

    public boolean Borrar(DTOMonitor monitor, Properties prop, Properties sqlProperties){
    	

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BorrarMonitor");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, monitor.getIdentificador());

            return ps.executeUpdate()>0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }
        return false;
    }
    
    /**
     * Obtiene la lista de monitores almacenados en la base de datos.
     *
     * @return Lista de monitores.
     */
    public ArrayList<DTOMonitor> ListaMonitores(Properties prop, Properties sqlProperties){
        ArrayList<DTOMonitor> lista = new ArrayList<DTOMonitor>();
        

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("LeerMonitor");
			PreparedStatement ps = con.prepareStatement(statement);
		    ResultSet rs = ps.executeQuery(statement);

            while(rs.next()){
                int identificador = rs.getInt("identificador");
                String nombre = rs.getString("nombre_apellidos");
                boolean atencion = rs.getBoolean("educadorEspecial");

                DTOMonitor aux = new DTOMonitor();
                aux.setIdentificador(identificador);
                aux.setNombreApellidos(nombre);
                aux.setEducadorEspecial(atencion);

                lista.add(aux);
            }
        } catch(Exception e) {
			System.out.println(e);
		}
        return lista;
    }
}
