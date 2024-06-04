package es.uco.pw.business.usuario;

import java.util.*;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import es.uco.pw.ficheros.*;
//import es.uco.pw.data.dao.*;
import es.uco.pw.data.dao.usuario.DAOusuario;

/**
 * Clase que gestiona los asistentes y proporciona un menú interactivo para dar de alta,
 * modificar y listar asistentes.
 * Los datos se guardan y recuperan de ficheros.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
public class GestorUsuarios{
    private ArrayList<DTOUsuario> listaUsuarios = new ArrayList<DTOUsuario>();
    

    Scanner leer = new Scanner(System.in);

	

    /**
     * Comprueba la existencia de un asistente en la lista de asistentes.
     *
     * @param id El identificador del asistente a comprobar.
     * @return {@code true} si el asistente existe, {@code false} si no.
     * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */  
    public boolean comprobarExistenciaUsuario(int id, String email, Properties prop, Properties sqProperties) throws FileNotFoundException, IOException{
		DAOusuario usuarioDAO = new DAOusuario();
    	//Fichero fichero =new Fichero();
    	listaUsuarios=usuarioDAO.ListaUsuarios(prop, sqProperties);
    	
    	for(DTOUsuario a : listaUsuarios) {
    		if(a.getIdentificador()==id || a.getEmail().equals(email)) {
    			return true;
    		}
    	}
    	return false;
    }
    

	/**
     * Permite dar de alta a un nuevo asistente.
     * @return true si el asistente se da de alta exitosamente, false si no se pudo dar de alta.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
    @SuppressWarnings("static-access")
	public boolean altaUsuario(DTOUsuario usuario, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOusuario usuarioDAO = new DAOusuario();
		
			
		if(comprobarExistenciaUsuario(usuario.getIdentificador(), usuario.getEmail(), prop, sqlProperties)==false) {
			return usuarioDAO.Almacenar(usuario, prop ,sqlProperties);
		}
		return false;
	}

	/**
     * Permite modificar los datos de un asistente existente.
     * @return true si el asistente se modifica exitosamente, false si no se pudo modificar.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
    @SuppressWarnings("static-access")
	public boolean modificarUsuario(DTOUsuario nuevo, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
	
		DAOusuario usuarioDAO = new DAOusuario();
		
		return usuarioDAO.Modificar(nuevo, prop, sqlProperties);

      		
	}
    
    /**
     * Devuelve el asistente asignado a un ID.
     *
     * @param id El identificador del asistente a buscar.
     * @return El objeto {@code DTOUsuario} correspondiente al ID proporcionado, o un objeto vacío si no se encuentra.
     * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    
    public DTOUsuario usuarioPorID(int id, Properties prop, Properties sqlProperties)throws FileNotFoundException, IOException{ //devuelve el asistente asignado a un id
		DAOusuario usuarioDAO = new DAOusuario();
    	//Fichero fichero=new Fichero();
    	//listaAsistentes=fichero.cargarFicheroAsistente();
		return usuarioDAO.getUsuario(id, prop, sqlProperties);
    }
   

	/**
     * Lista todos los asistentes registrados.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public void listadoUsuarios(Properties prop, Properties sqlProperties)throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaAsistentes = fichero.cargarFicheroAsistente();
		DAOusuario usuarioDAO = new DAOusuario();
		listaUsuarios = usuarioDAO.ListaUsuarios(prop, sqlProperties);

		for(DTOUsuario a : listaUsuarios) {
			System.out.println(a.toString());
		}
	}

	public boolean comprobarCredenciales(String email, String password, Properties conf, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOusuario UsuarioDAO = new DAOusuario();
		return UsuarioDAO.ComprobarCredenciales(email, password, conf, sqlProperties);
	}

    /**
	 * Comprueba la existencia de un Usuario en la lista de Usuarios.
	 * @param email El email del Usuario a comprobar.
	 * @return el DTO del usuario en cuestion.
	 */
    
	public DTOUsuario getUsuarioPorEmail(String email, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOusuario UsuarioDAO = new DAOusuario();
		return UsuarioDAO.UsuarioPorEmail(email, prop, sqlProperties);
		
	}
}