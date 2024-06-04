package es.uco.pw.business.inscripcion;


import es.uco.pw.business.usuario.*;
import es.uco.pw.business.campamento.*;
import java.time.LocalDate;

/**
 * La factoría completa que representa un Registro Tardío que hereda de la clase inscripción.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 * */

public class RegistroTemprano extends CreadorInscripcion{
	
	
	private boolean cancelado;
	/**
	 * Indica si esta cancelado o no
	 */
	
		@Override
	public InscripcionCompleta creadorInscripcionCompleta(DTOCampamento campamento, DTOUsuario usuario, float precio) {
			
		InscripcionCompleta inscripcionCompleta = new InscripcionCompleta();
		
		boolean esTemprano= comprobarFecha(campamento.getFechaInicio());
		
		if (esTemprano)
			inscripcionCompleta.setFecha(LocalDate.now());
		
		inscripcionCompleta.setPrecio(precio);
		inscripcionCompleta.setIdUsuario(usuario);
		inscripcionCompleta.setIdCampamento(campamento.getIdentificador());
		inscripcionCompleta.setTipo("completo");
		inscripcionCompleta.setEstado("temprano");
		
		return inscripcionCompleta;
	}
	

		@Override
	public InscripcionParcial creadorInscripcionParcial(DTOCampamento campamento, DTOUsuario usuario, float precio) {
			
		InscripcionParcial inscripcionParcial = new InscripcionParcial();
		
		boolean esTemprano= comprobarFecha(campamento.getFechaInicio());
		
		if (esTemprano)
			inscripcionParcial.setFecha(LocalDate.now());
		
		
		inscripcionParcial.setPrecio(precio);
		inscripcionParcial.setIdUsuario(usuario);
		inscripcionParcial.setIdCampamento(campamento.getIdentificador());
		inscripcionParcial.setTipo("parcial");
		inscripcionParcial.setEstado("temprano");

		return inscripcionParcial;
	}
		
		/**
		 * Comprueba si la fecha de inscripcion es temprana: antes de 15 dias de la fecha de inicio del campamento.
		 *
		 * @param fecha La fecha a comprobar.
		 * @return `true` si la fecha está dentro del rango, `false` en caso contrario.
		 */
	public boolean comprobarFecha(LocalDate fecha) {
		LocalDate fechaLimite = fecha.minusDays(15);
		if ( LocalDate.now().isBefore(fechaLimite) ) {
			return true;
		}
		return false;
	}
		
	
	/**
 * @return si esta o no cancelado
 */
	public boolean isCancelado() {	
		return cancelado;
	}

	/**
	 * @param cancelado es el atributo cancelado para settear
	 */
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
		
		
		
		
}
