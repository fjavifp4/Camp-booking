package es.uco.pw.data.dao.actividad;

import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.business.actividad.Horario;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.data.common.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Un DAO para las actividades que hace uso de una conexión a una base de datos MySQL a través de JDBC. 
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */


public class DAOactividad extends DBconnection
{
	/**
	 * Almacena una actividad en la base de datos.
	 *
	 * @param actividad La actividad a almacenar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */

    public boolean Almacenar(DTOActividad actividad, Properties prop, Properties sqlProperties)
    {
    	

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("GuardarActividad");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1,actividad.getNombreActividad());

            String nivel_educativo = null;
            if(actividad.getNivelEducativo() == NivelEducativo.juvenil){
                nivel_educativo = "juvenil";
            }
            else if(actividad.getNivelEducativo() == NivelEducativo.adolescente){
                nivel_educativo = "adolescente";
            }
            else{
                nivel_educativo = "infantil";
            }
            ps.setString(2,nivel_educativo);

            String horario = null;
            if(actividad.getHorario() == Horario.manana){
                horario = "manana";
            }
            else{
                horario = "tarde";
            }
            ps.setString(3,horario);

            ps.setInt(4, actividad.getMaxParticipantes());

            ps.setInt(5, actividad.getMonitoresNecesarios());

            return ps.executeUpdate() > 0;
            
        }
        catch(Exception e) {
			System.out.println(e);
        }
        return false;
    }

    /**
 * Modifica una actividad en la base de datos.
 *
 * @param nombre El nombre de la actividad a modificar.
 * @param actividad La actividad con los nuevos datos.
 * @return true si la operación fue exitosa, false de lo contrario.
 */

    public boolean Modificar(String nombre, DTOActividad actividad, Properties prop, Properties sqlProperties)
    {
    	      

        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ModificarActividad");
            PreparedStatement ps = con.prepareStatement(statement);
            

            String nivel_educativo = null;

            if(actividad.getNivelEducativo() == NivelEducativo.juvenil)
            {
                nivel_educativo = "juvenil"; 
            }
            else if(actividad.getNivelEducativo() == NivelEducativo.adolescente)
            {
                nivel_educativo = "adolescente";
            }
            else
            {
                nivel_educativo = "infantil";
            }
            ps.setString(1, nivel_educativo);

            String horario = null;
            if(actividad.getHorario() == Horario.manana){
                horario = "manana";
            }
            else{
                horario = "tarde";
            }
            ps.setString(2,horario);

            ps.setInt(3, actividad.getMaxParticipantes());
            ps.setInt(4, actividad.getMonitoresNecesarios()); 

            ps.setString(5, nombre);
            return ps.executeUpdate() > 0;  
            

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;

    }

    /**
     * Borra una actividad de la base de datos.
     *
     * @param actividad La actividad a borrar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */

    public boolean Borrar(DTOActividad actividad, Properties prop, Properties sqlProperties)
    {
    	

        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BorrarActividad");
            PreparedStatement ps = con.prepareStatement(statement);
    
            ps.setString(1, actividad.getNombreActividad());

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return false;

    }
    /**
     * Almacena un registro de asociacion entre un monitor y una actividad.
     *
     * @param nombreActividad El nombre de la actividad.
     * @param idMonitor El identificador del monitor.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    
    public boolean AlmacenarMonitor(String nombreActividad, int idMonitor, Properties prop, Properties sqlProperties)
    {
    	

        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("GuardarMonitorEnActividad");
            PreparedStatement ps = con.prepareStatement(statement);
    
            ps.setString(1,nombreActividad);
            ps.setInt(2, idMonitor);

            return ps.executeUpdate() > 0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;

    }
    
    /**
     * Borra una relación entre una actividad y un monitor en la base de datos.
     *
     * @param nombreActividad El nombre de la actividad.
     * @param idMonitor El identificador del monitor.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean BorrarMonitor(String nombreActividad, int idMonitor, Properties prop, Properties sqlProperties)
    {
    	

        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BorrarActividad");
            PreparedStatement ps = con.prepareStatement(statement);
    
            ps.setString(1, nombreActividad);
            ps.setInt(2, idMonitor);

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return false;

    }
    
    /**
     * Saca la informacion de la tabla Actividad y lo guarda en una lista.
     *
     * @return la lista de actividades.
     */
	public ArrayList<DTOActividad> ListaActividades(Properties prop, Properties sqlProperties){
		
        ArrayList<DTOActividad> lista = new ArrayList<DTOActividad>();

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("SeleccionarActividades");
			PreparedStatement ps = con.prepareStatement(statement);
		    ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String nombre = rs.getString("nombreActividad");
                String nivel = rs.getString("nivelEducativo");
                String h = rs.getString("horario");
                int max = rs.getInt("maxParticipantes");
                int monitores = rs.getInt("monitoresNecesarios");
                
                
                DTOActividad aux = new DTOActividad();

                aux.setNombreActividad(nombre);
                NivelEducativo nivel_educativo = null;
                if(nivel.equals("juvenil")){
                    nivel_educativo = NivelEducativo.juvenil;
                }
                else if(nivel.equals("infantil")){
                    nivel_educativo = NivelEducativo.infantil;
                }
                else{
                    nivel_educativo = NivelEducativo.adolescente;
                }
                aux.setNivelEducativo(nivel_educativo);

                Horario horario = null;
                if(h.equals("manana")){
                    horario = Horario.manana;
                }
                else{
                    horario = Horario.tarde;
                }
                aux.setHorario(horario);
                aux.setMaxParticipantes(max);
                aux.setMonitoresNecesarios(monitores);

                lista.add(aux);
            }


        } catch(Exception e) {
			System.out.println(e);
		}
		return lista;
    }

     /**
     * Comprueba si un monitor ya esta asociado a una actividad
     *
     * @param actividad DTO de actividad
     * @param campamento DTO de monitor
     * @return true si ya esta asociada, false si no.
     */

     public boolean monitorEnActividad(DTOMonitor monitor, DTOActividad actividad, Properties prop, Properties sqlProperties)
     {
        
 
         try
         {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
             String statement = sqlProperties.getProperty("MonitorEnActividad");
             PreparedStatement ps = con.prepareStatement(statement);
             ps.setInt(1, monitor.getIdentificador());
             ps.setString(2, actividad.getNombreActividad());
             
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