package es.uco.pw.data.dao.inscripcion;

import es.uco.pw.data.common.DBconnection;
import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.actividad.Horario;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.inscripcion.DTOInscripcion;
import es.uco.pw.business.inscripcion.InscripcionCompleta;
import es.uco.pw.business.inscripcion.InscripcionParcial;
import es.uco.pw.business.inscripcion.RegistroTardio;
import es.uco.pw.business.inscripcion.RegistroTemprano;
import es.uco.pw.business.monitor.DTOMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * Clase DAO para gestionar las operaciones relacionadas con las inscripciones en la base de datos.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
 
public class DAOinscripcion extends DBconnection{
	 
	 /**
	     * Almacena una inscripción en la base de datos.
	     *
	     * @param inscripcion Objeto DTOInscripcion que se va a almacenar.
	     * @return true si la operación fue exitosa, false en caso contrario.
	     */

    public boolean Almacenar(DTOInscripcion inscripcion, Properties prop, Properties sqlProperties){
    	
        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("GuardarInscripcion");
			PreparedStatement ps = con.prepareStatement(statement);
			//System.out.println(inscripcion.getFecha());
            ps.setInt(1, inscripcion.getIdUsuario());
            ps.setInt(2, inscripcion.getIdCampamento());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(inscripcion.getFecha());
            ps.setDate(3, sqlDate1);
            ps.setFloat(4, inscripcion.getPrecio());
            //System.out.println(inscripcion.getTipo());
            ps.setString(5, inscripcion.getTipo());
            ps.setString(6, inscripcion.getEstado());

            return ps.executeUpdate()>0;
        }catch(Exception e) {
			System.out.println(e);
        }
        return false;
    }

    /**
     * Borra una inscripción en la base de datos.
     *
     * @param inscripcion Objeto DTOInscripcion que se va a borrar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */

    public boolean Borrar(DTOUsuario usuario, DTOCampamento campamento, Properties prop, Properties sqlProperties){
    	

        try{
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BorrarInscripcion");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, campamento.getIdentificador());
            ps.setInt(2, usuario.getIdentificador());

            return ps.executeUpdate()>0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    
  
    /**
     * Busca un usuario inscrito en un campamento.
     *
     * @param usuario Usuario a buscar.
     * @param campamento Campamento en el que se busca.
     * @return Verdadero si el usuario está inscrito en el campamento, falso de lo contrario.
     */


    public boolean BuscarUsuarioInscrito(DTOUsuario usuario, DTOCampamento campamento, Properties prop, Properties sqlProperties)
    {
    
        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("BuscarUsuarioInscrito");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, usuario.getIdentificador());
            ps.setInt(2, campamento.getIdentificador());
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

    
    /**
     * Cuenta las plazas ocupadas en un campamento.
     *
     * @param campamento Campamento del que se cuentan las plazas ocupadas.
     * @return Número de plazas ocupadas en el campamento.
     */

    public int ContarPlazasOcupadas(DTOCampamento campamento, Properties prop, Properties sqlProperties)
    {
    	
        int count = 0;

        try {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ContarPlazasOcupadas");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, campamento.getIdentificador());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                count = rs.getInt("numUsuarios");
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return count;
    }
    /**
     * Guarda un una lista las inscripciones de un usuario ordenadas por fecha de inicio
     * 
     * @param id
     * @return ArrayList<DTOCampamento>
     * 
     */

    public ArrayList<DTOCampamento> FiltrarInscripciones(int id, Properties prop, Properties sqlProperties)
    {
        ArrayList<DTOCampamento> lista = new ArrayList<DTOCampamento>();

        try{

            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("FiltrarInscripcionesPorUsuario");
    		PreparedStatement ps = con.prepareStatement(statement);
    		ps.setInt(1, id);
 
    	    ResultSet rs = ps.executeQuery();
    	    
            
    	    while (rs.next())	
    	    {
                DTOCampamento camp = new DTOCampamento();

            	int identificador = rs.getInt("identificador");
                LocalDate fecha = java.time.LocalDate.parse(rs.getDate("fechaInicio").toString());
                
                camp.setIdentificador(identificador);
                camp.setFechaInicio(fecha); 
                lista.add(camp);
                
    	    }
            
        }
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
        return lista;
    	
    }
    /**
     * Cuenta las plazas ocupadas en un campamento para cada tipo
     * 
     * @param string
     * @return int
     * 
     */
    public int ContarPlazasOcupadasTipo(String tipo, DTOCampamento campamento, Properties properties, Properties sqlProperties)
    {
        int count = 0;

        try {
            DBconnection dbConnection = new DBconnection(properties);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ContarPlazasOcupadasTipo");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, campamento.getIdentificador());
            ps.setString(2, tipo);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                count = rs.getInt("numUsuarios");
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return count;
    }

    /**
     * Guarda en una lista los campamentos cancelables de un usuario.
     * 
     * @param DTOUsuario
     * @return ArrayList<DTOCampamento>
     */
    public ArrayList<DTOCampamento> ListarCampamentosCancelables(DTOUsuario usuario, Properties prop, Properties sqlProperties)
    {
        ArrayList<DTOCampamento> lista = new ArrayList<DTOCampamento>();

        try{

            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ListarCampamentosCancelables");
    		PreparedStatement ps = con.prepareStatement(statement);
    		ps.setInt(1, usuario.getIdentificador());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
 
    	    ResultSet rs = ps.executeQuery();
    	    
            
    	    while (rs.next())	
    	    {
                DTOCampamento camp = new DTOCampamento();

            	int identificador = rs.getInt("identificador");
                String nombre = rs.getString("nombre");
                
                camp.setNombre(nombre);
                camp.setIdentificador(identificador);
                lista.add(camp);
                
    	    }
            
        }
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
        return lista;
        
    }

    public boolean ComprobarAsistenteEspecial(DTOCampamento campamento, Properties prop, Properties sqlProperties)
    {
        try
        {
            DBconnection dbConnection = new DBconnection(prop);
            Connection con = dbConnection.getConnection();
            String statement = sqlProperties.getProperty("ComprobarAsistenteEspecial");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, campamento.getIdentificador());
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








