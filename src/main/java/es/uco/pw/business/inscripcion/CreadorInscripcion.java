package es.uco.pw.business.inscripcion;


import es.uco.pw.business.usuario.*;
import es.uco.pw.business.campamento.*;

/**
 * La definición de la factoría abstracta para crear inscripciones
 * @author Manuel Cabrera Crespo 
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 * */

public abstract class CreadorInscripcion {
	/** 
	 * Crea una inscripción completa.
	 * @return la inscripción completa de un asistente a un campamento.
	 *  */
	public abstract InscripcionCompleta creadorInscripcionCompleta(DTOCampamento campamento, DTOUsuario usuario, float precio);
	
	/** 
	 * Crea una inscripción parcial.
	 * @return la inscripción parcial de un asistente a un campamento.
	 *  */
	public abstract InscripcionParcial creadorInscripcionParcial(DTOCampamento campamento, DTOUsuario usuario, float precio);
	

}