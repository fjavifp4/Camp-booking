package es.uco.pw.business.inscripcion;

import java.time.LocalDate;

import es.uco.pw.business.usuario.*;
import es.uco.pw.business.campamento.*;
import es.uco.pw.business.usuario.DTOUsuario;
/**
 * La factoría completa que representa un Registro Tardío que hereda de la clase inscripción.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 * */

public class RegistroTardio extends CreadorInscripcion {
	
	@Override
	public InscripcionCompleta creadorInscripcionCompleta(DTOCampamento campamento, DTOUsuario usuario, float precio) {
		
		InscripcionCompleta inscripcionCompleta = new InscripcionCompleta();
		
		boolean esTardio = comprobarFecha(campamento.getFechaInicio());
		
		if ( esTardio ) {
			inscripcionCompleta.setFecha(LocalDate.now());
		}
		
		inscripcionCompleta.setPrecio(precio);
		inscripcionCompleta.setIdUsuario(usuario);
		inscripcionCompleta.setIdCampamento(campamento.getIdentificador());
		inscripcionCompleta.setTipo("completo");
		inscripcionCompleta.setEstado("tardio");

		return inscripcionCompleta;
	}
		
	@Override
	public InscripcionParcial creadorInscripcionParcial(DTOCampamento campamento, DTOUsuario usuario, float precio) {
			
		InscripcionParcial inscripcionParcial = new InscripcionParcial();
		
		boolean esTardio = comprobarFecha(campamento.getFechaInicio());
		
		
		if ( esTardio ) {
			inscripcionParcial.setFecha(LocalDate.now());
		}
		
		
		inscripcionParcial.setPrecio(precio);
		inscripcionParcial.setIdUsuario(usuario);
		inscripcionParcial.setIdCampamento(campamento.getIdentificador());
		inscripcionParcial.setTipo("parcial");
		inscripcionParcial.setEstado("tardio");

		return inscripcionParcial;
	}
	/**
	 * Comprueba si la fecha proporcionada está dentro del rango de 2 a 15 días antes de la fecha actual.
	 *
	 * @param fecha La fecha a comprobar.
	 * @return `true` si la fecha está dentro del rango, `false` en caso contrario.
	 */
		
	public boolean comprobarFecha(LocalDate fecha) {
		LocalDate fechaLimite1 = fecha.minusDays(15);
		LocalDate fechaLimite2 = fecha.minusDays(2);
		//System.out.println(fechaLimite1+"\n"+fechaLimite2);
		if ( LocalDate.now().isAfter(fechaLimite1) && LocalDate.now().isBefore(fechaLimite2) ) {
			return true;
		} 
		return false;
	}
			
}
