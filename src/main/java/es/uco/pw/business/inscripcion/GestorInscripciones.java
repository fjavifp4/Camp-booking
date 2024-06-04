package es.uco.pw.business.inscripcion;

//import es.uco.pw.ficheros.Fichero;
import es.uco.pw.business.usuario.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.campamento.*;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.data.dao.actividad.DAOactividad;
import es.uco.pw.data.dao.usuario.DAOusuario;
import es.uco.pw.data.dao.campamento.DAOcampamento;
import es.uco.pw.data.dao.inscripcion.DAOinscripcion;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;




public class GestorInscripciones {
	
    private ArrayList<DTOCampamento> listaCampamentos = new ArrayList<DTOCampamento>();
	/**Lista de campamentos en la que se volcara la informacion de las bases de datos  */

	private ArrayList<InscripcionCompleta> listaInscripcionesCompletas = new ArrayList<InscripcionCompleta>();
	/**Lista de inscripciones completas en la que se volcara la informacion de las bases de datos  */

    private ArrayList<InscripcionParcial> listaInscripcionesParciales = new ArrayList<InscripcionParcial>();
	/**Lista de inscripciones completas en la que se volcara la informacion de las bases de datos  */


    private ArrayList<DTOUsuario> listaUsuarios = new ArrayList<DTOUsuario>();
	/**Lista de asistentes en la que se volcara la informacion de las bases de datos  */



    Scanner leer = new Scanner(System.in);
	
	


	public boolean comprobarExistenciaUsuario(int idUsuario, Properties prop, Properties sqlProperties)
	{
		//Fichero ficheroA=new Fichero();
		//listaAsistentes=ficheroA.cargarFicheroAsistente();

		DAOusuario usuarioDAO = new DAOusuario();
		
		listaUsuarios = usuarioDAO.ListaUsuarios(prop, sqlProperties);

		for (DTOUsuario a: listaUsuarios) {
			if (a.getIdentificador()==(idUsuario)) {
				return true;
			}
		}
		return false;

	}

	public boolean comprobarExistenciaCampamento(int idCampamento, Properties prop, Properties sqlProperties)
	{
		//Fichero ficheroC=new Fichero();
		//listaCampamentos=ficheroC.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();
		
		listaCampamentos = campamentoDAO.ListaCampamento(prop, sqlProperties);

		for (DTOCampamento c: listaCampamentos) {
			if (c.getIdentificador()==(idCampamento)) {
				return true;
			}
		}
		return false;
	}

	public DTOUsuario buscarUsuario(int idUsuario, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DTOUsuario usuario = new DTOUsuario();
		DAOusuario daoUsuario = new DAOusuario();
		
		listaUsuarios = daoUsuario.ListaUsuarios(prop, sqlProperties);

	

		for (DTOUsuario a: listaUsuarios) {
			if (a.getIdentificador()==(idUsuario)) {
				usuario = a;

			}
		}

		return usuario;

	}


	public DTOCampamento buscarCampamento(DTOCampamento campamento, int idCampamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos = daoCampamento.ListaCampamento(prop,  sqlProperties);

		for (DTOCampamento c: listaCampamentos) {
			if (c.getIdentificador()==(idCampamento) &&  plazasDisponibles(c, prop, sqlProperties) > 0) {
				campamento = c;
			}
		}

		return campamento;

	}
	/**
	 * Funcion para calcular si el registro es temprano
	 * @param idCampamento
	 * @return true si esta dentro del plazo (antes de los 15 dias de la fecha de inicio) y false si no lo cumple
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean funcionCalcularTemprano(int idCampamento,Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{


		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos = daoCampamento.ListaCampamento(prop, sqlProperties);

		for(DTOCampamento c: listaCampamentos){
			if(c.getIdentificador() == idCampamento){
	            
				if(LocalDate.now().isBefore(c.getFechaInicio().minusDays(15))){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Funcion para calcular si el registro es tardio
	 * @param idCampamento
	 * @return true si esta dentro del plazo (despues de 15 dias y 2 dias antes de la fecha de inicio y ) y false si no lo cumple
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean funcionCalcularTardio(int idCampamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
	

		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos= daoCampamento.ListaCampamento(prop, sqlProperties);
		
		
		
		for(DTOCampamento c: listaCampamentos){
			if(c.getIdentificador() == idCampamento){
				if(LocalDate.now().isAfter(c.getFechaInicio().minusDays(15)) && LocalDate.now().isBefore(c.getFechaInicio().minusDays(2))){
					return true;
				}
			}
		}
	
		return false;
	}
	/**
	 * Metodo para realizar un registro temprano
	 * Permite crear una inscripcion completa y parcial
	 * @param idCampamento
	 * @return precio de la inscripcion si se hizo bien, si no devuelve 0
	 * @throws FileNotFoundException
	 * @throws IOException
	 */



	

    public boolean realizarRegistroTemprano(float precio, DTOCampamento campamento, DTOUsuario usuario, int opcion, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		
		RegistroTemprano registro = new RegistroTemprano();
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		switch(opcion){
			case 1:
				InscripcionCompleta ic = registro.creadorInscripcionCompleta(campamento, usuario, precio);
				if(daoInscripcion.Almacenar(ic, prop, sqlProperties)){
					return true;
				}else{
					return false;
				}
			case 2:
				InscripcionParcial ip = registro.creadorInscripcionParcial(campamento, usuario, precio);
				if(daoInscripcion.Almacenar(ip, prop, sqlProperties)){
					return true;
				}
				else{
					return false;
				}
			default:
				return false;				
		}
	}
/**	 
 * Metodo para realizar un registro tardio
 * Permite crear una inscripcion completa o parcial
 * @param idCampamento
 * @return precio de la inscripcion si se hizo bien, si no devuelve 0
 * @throws FileNotFoundException
 * @throws IOException
 */

	public boolean realizarRegistroTardio(float precio, DTOCampamento campamento, DTOUsuario usuario, int opcion, Properties prop, Properties sqlProperties ) throws FileNotFoundException, IOException{	
		
		RegistroTardio registro = new RegistroTardio();
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		switch(opcion){
			case 1:
				InscripcionCompleta ic = registro.creadorInscripcionCompleta(campamento, usuario, precio);
				if(daoInscripcion.Almacenar(ic, prop, sqlProperties)){
					return true;
				}else{
					return false;
				}
			case 2:
				InscripcionParcial ip = registro.creadorInscripcionParcial(campamento, usuario, precio);
				if(daoInscripcion.Almacenar(ip, prop, sqlProperties)){
					return true;
				}else{
					return false;
				}
			default:
				return false;
		}
	}


	public float calcularPrecio(int numeroActividades, int opcion, Properties prop, Properties sqlProperties ) throws FileNotFoundException, IOException{	
		
		
		switch(opcion){
			case 1:
				return 300 + numeroActividades*20;
				
			case 2:
				return 100 + numeroActividades*20;
				
			default:
				return 0;
		}
		
	}

	public boolean comprobarUsuarioInscrito(DTOUsuario usuario, DTOCampamento campamento, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{

		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.BuscarUsuarioInscrito(usuario,campamento, prop, sqlProperties);
		
	}

/**
 * Metodo que permite cancelar una inscripcion completa o parcial
 * Solo se puede en caso de que el registro haya sido temprano
 * @return true si se ha podido completar la cancelacion y false en caso contrario
 * @throws FileNotFoundException
 * @throws IOException
 */

	public boolean cancelarInscripcion(DTOUsuario u, DTOCampamento c, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{


		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.Borrar(u,c, prop, sqlProperties);
		
		
	}

	/**
	 * Metodo para listar los campamentos disponibles
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public ArrayList<DTOCampamento> listarCampamentosDisponibles(Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException{
		DAOcampamento daoCampamento = new DAOcampamento();
		ArrayList<DTOCampamento> listaCampamentos = daoCampamento.ListaCampamento(prop, sqlProperties);
		ArrayList<DTOCampamento> lista = new ArrayList<DTOCampamento>();
			
		for(DTOCampamento c : listaCampamentos)
		{
			if (LocalDate.now().isBefore(c.getFechaInicio().minusDays(2)) && plazasDisponibles(c, prop, sqlProperties) > 0) {
				lista.add(c);
			}
		}
		return lista;
	}
/**
 * Metodo para contemplar las plazas disponibles de un campamento
 * @param c siendo el campamento a recorrer
 * @return el numero de plazas disponibles en el campamento c
 * @throws IOException
 * @throws FileNotFoundException
 */
	public int plazasDisponibles(DTOCampamento c, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException {

		
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		int plazasMaximas = c.getMaxUsuarios();
		int plazasOcupadas = daoInscripcion.ContarPlazasOcupadas(c, prop, sqlProperties);
		return plazasMaximas - plazasOcupadas;
	}

	public int numeroActividadesCampamento(int identificador, Properties prop, Properties sqlProperties)
	{
		DAOcampamento daoCampamento = new DAOcampamento();
		if( daoCampamento.ContarActividadesCampamento(identificador, prop, sqlProperties) > 0 )
		{
			return daoCampamento.ContarActividadesCampamento(identificador, prop, sqlProperties);
		}
		return 0;

	}

	public ArrayList<DTOCampamento> filtrarInscripciones(int id, Properties prop, Properties sqlProperties)
	{
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.FiltrarInscripciones(id, prop, sqlProperties);
	}
	
	public int contarPlazasOcupadasTipo(String tipo, DTOCampamento c, Properties prop, Properties sqlProperties) {
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.ContarPlazasOcupadasTipo(tipo, c, prop, sqlProperties);

	}
	public ArrayList<DTOCampamento> filtrarPlazasCampamentos(int minParticipantes, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException
	{
		DAOcampamento daoCampamento = new DAOcampamento();
		ArrayList<DTOCampamento> listaCampamentos = new ArrayList<DTOCampamento>();

		for ( DTOCampamento c : daoCampamento.ListaCampamento(prop, sqlProperties)) {

			if (minParticipantes <= plazasDisponibles(c, prop, sqlProperties))
			{
				listaCampamentos.add(c);
			}
			
			
		}

		return listaCampamentos;
	}

	public ArrayList<DTOCampamento> listarCampamentosCancelables(DTOUsuario usuario, Properties prop, Properties sqlProperties) throws FileNotFoundException, IOException
	{
		DAOinscripcion daoInscripcion = new DAOinscripcion();

		
		return daoInscripcion.ListarCampamentosCancelables(usuario, prop, sqlProperties);
			
		
	}

	public boolean comprobarAsistenteEspecial(DTOCampamento campamento, Properties prop, Properties sqlProperties)
	{
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.ComprobarAsistenteEspecial(campamento, prop, sqlProperties);
	}


	

	/**
	 * Get de la lista de campamentos
	 * @return la lista de campamentos
	 */
	public ArrayList<DTOCampamento>  getListaCampamentos(){
		return listaCampamentos;
	}
	/**
	 * Get de la lista de asistentes 
	 * @return la lista de asistentes
	 */
	public ArrayList<DTOUsuario> getListaUsuarios(){
		return listaUsuarios;
	}
	/**
	 * Get de la lista de inscripciones completas
	 * @return la lista de inscripciones completas
	 */
	public ArrayList<InscripcionCompleta>  getListaInscripcionCompletas(){
		return listaInscripcionesCompletas;
	}
	/**
	 * Get de la lista de inscripciones parciales
	 * @return la lista de inscripciones parciales
	 */
	public ArrayList<InscripcionParcial>  getListaInscripcionParciales(){
		return listaInscripcionesParciales;
	}	

}
	/**public  */
	
