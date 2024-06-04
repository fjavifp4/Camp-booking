package es.uco.pw.business.campamento;
import java.time.LocalDate;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

//import es.uco.pw.ficheros.Fichero;
import es.uco.pw.business.usuario.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.monitor.*;
import es.uco.pw.data.dao.actividad.*;
import es.uco.pw.data.dao.monitor.*;
import es.uco.pw.data.dao.campamento.*;
import es.uco.pw.data.dao.inscripcion.DAOinscripcion;
import es.uco.pw.data.dao.usuario.*;


/**
 * Clase que gestiona los campamentos, actividades y monitores disponibles.
 * Permite la creación y asociación de actividades, monitores y campamentos,
 * así como la gestión de inscripciones. Los datos se guardan y recuperan de ficheros.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class GestorCampamentos{
	private ArrayList<DTOActividad> listaActividades = new ArrayList<DTOActividad>();	//Actividades que estan disponibles
	private ArrayList<DTOMonitor> listaMonitores = new ArrayList<DTOMonitor>();	//Monitores que estan disponibles
	private ArrayList<DTOCampamento> listaCampamentos = new ArrayList<DTOCampamento>();//Campamentos que estan disponibles

	Scanner leer = new Scanner(System.in);//Scanner para leer datos por teclado
	
	
	/**
	 * Comprueba la existencia de una actividad por su nombre.
	 *
	 * @param nombre El nombre de la actividad a buscar.
	 * @return true si la actividad existe, false en caso contrario.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public boolean comprobarExistenciaActividad(String nombre, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{

		DAOactividad actividadDAO = new DAOactividad();
		//Fichero fichero = new Fichero();
		listaActividades=actividadDAO.ListaActividades(prop, sqlProperties);
		
	    for(DTOActividad a : listaActividades) {
			if(a.getNombreActividad().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Obtiene la actividad por su nombre.
	 *
	 * @param nombre El nombre de la actividad a buscar.
	 * @return La actividad correspondiente al nombre proporcionado.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public DTOActividad actividadPorNombre(String nombre, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		DAOactividad actividadDAO = new DAOactividad();
		listaActividades=actividadDAO.ListaActividades(prop, sqlProperties);

		DTOActividad actividad=new DTOActividad();
				
	    for(DTOActividad a : listaActividades) {
			if(a.getNombreActividad().equals(nombre)) {
				actividad=a;
			}
	    }
	    return actividad;
	}
	
	/**
	 * Imprime la lista de actividades.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public ArrayList<DTOActividad> listarActividades(Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		DAOactividad actividadDAO = new DAOactividad();
		return actividadDAO.ListaActividades(prop, sqlProperties);

	}

	/**for(DTOActividad a : listaActividades) {
			System.out.println(a.toString());
		}
     * Crea una nueva actividad y la añade a la lista de actividades disponibles.
     * @return true si la actividad se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearActividad(DTOActividad actividad, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOactividad actividadDAO = new DAOactividad();
		
		//Fichero fichero = new Fichero();
		//listaActividades=actividadDAO.ListaActividades();
		
		if(comprobarExistenciaActividad(actividad.getNombreActividad(), prop, sqlProperties)==false){
			return actividadDAO.Almacenar(actividad, prop, sqlProperties);
		}
		return false;
	}
	

	/**
	 * Verifica la existencia de un monitor mediante su identificador.
	 *
	 * @param id El identificador del monitor a verificar.
	 * @return true si el monitor existe, false si no.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public boolean comprobarExistenciaMonitor(String nombre_apellidos, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores(prop, sqlProperties);

		for(DTOMonitor m : listaMonitores){
			if(m.getNombreApellidos().equals(nombre_apellidos)){
				return true;
			}
		}	
		return false;
	}
	/**
	 * Imprime todos los monitores.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public ArrayList<DTOMonitor> listarMonitores(Properties prop, Properties sqlProperties) throws	FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		
		DAOmonitor monitorDAO = new DAOmonitor();
		return monitorDAO.ListaMonitores(prop, sqlProperties);
		
		
	}
	
	/**
	 * Muestra los monitores especiales.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public ArrayList<DTOMonitor> listarMonitoresEspeciales(Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		
		DAOmonitor monitorDAO = new DAOmonitor();
		return monitorDAO.ListaMonitores(prop, sqlProperties);

			
	}
	
	
	/**
	 * Obtiene un monitor mediante su identificador.
	 *
	 * @param id El identificador del monitor a obtener.
	 * @return El DTO del monitor encontrado, o un objeto monitor vacío si no se encuentra.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public DTOMonitor monitorPorId(int id, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DTOMonitor monitor=new DTOMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores(prop, sqlProperties);
		
		for(DTOMonitor m : listaMonitores){
			if(m.getIdentificador()==id)
			{
				monitor=m;
			}
		}	
		return monitor;
	}
	
	/**
	 * Obtiene un monitor especial mediante su identificador.
	 *
	 * @param id El identificador del monitor a obtener.
	 * @return El DTO del monitor encontrado, o un objeto monitor vacío si no se encuentra.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public DTOMonitor monitorEspecialPorId(int id, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DTOMonitor monitor=new DTOMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores(prop, sqlProperties);

		for(DTOMonitor m : listaMonitores){
			if(m.getIdentificador()==id && m.getEducadorEspecial()==true)
			{
				monitor=m;
			}
		}	
		return monitor;
	}

	/**
     * Crea un nuevo monitor y lo añade a la lista de monitores disponibles.
     * @return true si el monitor se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearMonitor(DTOMonitor monitor, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		//listaMonitores=monitorDAO.ListaMonitores();
		
		if(comprobarExistenciaMonitor(monitor.getNombreApellidos(), prop, sqlProperties)==false){
			return monitorDAO.Almacenar(monitor, prop, sqlProperties);
		}
		
		return false;
	}
	
	/**
	 * Comprueba la existencia de un campamento mediante su identificador.
	 *
	 * @param id El identificador del campamento a comprobar.
	 * @return `true` si el campamento existe, `false` en caso contrario.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public boolean comprobarExistenciaCampamento(DTOCampamento campamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();

		DAOcampamento campamentoDAO = new DAOcampamento();
		
		listaCampamentos=campamentoDAO.ListaCampamento(prop, sqlProperties);
		
		for(DTOCampamento c : listaCampamentos) {
			if(c.getIdentificador()==campamento.getIdentificador() || c.getNombre().equals(campamento.getNombre()) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca un campamento por su ID.
	 *
	 * @param id El identificador del campamento a comprobar.
	 * @return el campamento a buscar
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public DTOCampamento campamentoPorId(int id, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DTOCampamento campamento=new DTOCampamento();
		
		DAOcampamento campamentoDAO = new DAOcampamento();
		listaCampamentos=campamentoDAO.ListaCampamento(prop, sqlProperties);
		
		for(DTOCampamento c : listaCampamentos) {
			if(c.getIdentificador()==id) {
				campamento=c;
			}
		}
		return campamento;
	}

	
	/**
	 * Lista todos los campamentos.
	 *

	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public ArrayList<DTOCampamento> listarCampamentos(Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();	
		return campamentoDAO.ListaCampamento(prop, sqlProperties);
		
		
	}

	/**
     * Crea un nuevo campamento y lo añade a la lista de campamentos disponibles.
     * @return true si el campamento se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearCampamento(DTOCampamento campamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();
		listaCampamentos=campamentoDAO.ListaCampamento(prop, sqlProperties);
		
		if(comprobarExistenciaCampamento(campamento, prop, sqlProperties)==false && campamento.getFechaFin().isAfter(campamento.getFechaInicio())){
			
			return campamentoDAO.Almacenar(campamento, prop, sqlProperties);
			
		}
		return false;
	}
	
	/**
     * Asocia un monitor a una actividad específica.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorAActividad(DTOMonitor monitor,DTOActividad actividad, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOactividad actividadDAO = new DAOactividad();

		if ( actividadDAO.monitorEnActividad(monitor, actividad, prop, sqlProperties))
		{
			return false;
		}
		return actividadDAO.AlmacenarMonitor(actividad.getNombreActividad(), monitor.getIdentificador(), prop, sqlProperties);

		
	}

	/**
     * Asocia una actividad a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean actividadACampamento(DTOCampamento campamento,DTOActividad actividad, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if(campamentoDAO.actividadEnCampamento(actividad, campamento, prop, sqlProperties) || campamento.getNivelEducativo() != actividad.getNivelEducativo())
		{
			return false;
		}

		return campamentoDAO.AlmacenarActividadEnCampamento(actividad.getNombreActividad(), campamento.getIdentificador(), prop, sqlProperties);
		
		
	}

	/**
     * Asocia un monitor a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorResponsableACampamento(DTOMonitor monitor,DTOCampamento campamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if (campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(),campamento.getIdentificador(), prop, sqlProperties))
		{
			if (campamentoDAO.monitorResponsableEnCampamento(monitor, campamento, prop, sqlProperties))
			{
				return false;
			}
			return campamentoDAO.AsignarMonitorResponsable(monitor.getIdentificador(), campamento.getIdentificador(), prop, sqlProperties);

		}

		return false;

		
	}	

	/**
     * Asocia un monitor de atención especial a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorEspecialACampamento(DTOMonitor monitor,DTOCampamento campamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if ((!campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(),campamento.getIdentificador(), prop, sqlProperties)))
		{
			if (campamentoDAO.monitorEspecialEnCampamento(monitor, campamento, prop, sqlProperties))
			{
				return false;
			}
			return campamentoDAO.AsignarMonitorEspecial(monitor.getIdentificador(), campamento.getIdentificador(),prop, sqlProperties);

		}

		return false;
		
	}	

	public ArrayList<DTOMonitor> monitoresPosibles(DTOCampamento campamento, Properties prop, Properties sqlProp) throws FileNotFoundException, IOException
	{
		DAOcampamento campamentoDAO = new DAOcampamento();
		DAOmonitor	monitorDAO = new DAOmonitor();	
		ArrayList<DTOMonitor> monitores = new ArrayList<DTOMonitor>();

		for (DTOMonitor monitor: monitorDAO.ListaMonitores(prop, sqlProp))
		{
			if (campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(), campamento.getIdentificador(), prop, sqlProp))
			{
				monitores.add(monitor);
			}
		}

		return monitores;

	}

	public ArrayList<DTOMonitor> monitoresEspecialesPosibles(DTOCampamento campamento, Properties prop, Properties sqlProp) throws FileNotFoundException, IOException
	{
		DAOcampamento campamentoDAO = new DAOcampamento();
		DAOmonitor	monitorDAO = new DAOmonitor();	
		ArrayList<DTOMonitor> monitores = new ArrayList<DTOMonitor>();

		for (DTOMonitor monitor: monitorDAO.ListaMonitores(prop, sqlProp))
		{
			if (!(campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(), campamento.getIdentificador(), prop, sqlProp)) && monitor.getEducadorEspecial() == true)
			{
				monitores.add(monitor);
			}
		}

		return monitores;

	}

	public ArrayList<DTOCampamento> filtrarFechasCampamentos(LocalDate fecha1, LocalDate fecha2, Properties prop, Properties sqlProp) throws FileNotFoundException, IOException
	{
		DAOcampamento campamentoDAO = new DAOcampamento();
		return campamentoDAO.filtrarFechasCampamento(fecha1, fecha2, prop, sqlProp);

	}
	public ArrayList<DTOCampamento> filtrarNivelCampamentos(NivelEducativo nivelEducativo, Properties prop, Properties sqlProp) throws FileNotFoundException, IOException{
		DAOcampamento campamentoDAO = new DAOcampamento();
		return campamentoDAO.filtrarNivelCampamento(nivelEducativo, prop, sqlProp);	
	}


	/**
     * Obtiene la lista de campamentos disponibles.
     * @return ArrayList de campamentos.
     */
	public ArrayList<DTOCampamento>  getListaCampamentos(){
		return listaCampamentos;
	}
	/**
     * Obtiene la lista de monitores disponibles.
     * @return ArrayList de monitores.
     */
	public ArrayList<DTOMonitor> getListaMonitores(){
		return listaMonitores;
	}
	/**
     * Obtiene la lista de actividades disponibles.
     * @return ArrayList de actividades.
     */
	public ArrayList<DTOActividad>  getListaActividades(){
		return listaActividades;
	}
}

