package es.uco.pw.business.inscripcion;



import es.uco.pw.business.usuario.*;
import es.uco.pw.business.campamento.*;
import java.time.LocalDate;

/**
 * La clase abstracta que representa el producto abstracto inscripción.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 * */

public abstract class DTOInscripcion {
	private int identificador;
	/** Identificador de la inscripcion */
	private int idUsuario;
	/** Identificador del asistente */

	private int idCampamento;
	/** Identificador del campamento */

	private LocalDate fecha;
	/** Fecha de inscripción. Usaremos esta clase porque nos hara falta mas adelante para el tema de la hora.*/

	private float precio;
	/** Precio de la inscripción*/

	private String tipo;
	/**Tipo de inscripcion (completa o parcial) */
	
	private String estado;
	/**Estado (temprano o tardio) */

	
	/**
     * Constructor vacio de la clase Inscripcion.
     * Inicializa todas las propiedades con valores predeterminados.
     */
	
	public DTOInscripcion() {
		
	}

	/**
	 * @return el identificador
	 */
	public int getIdentificador() {
		return identificador;
	}
	/**
	 * @return el idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}


	/**
	 * @param idUsuario rd el idUsuario para settear
	 */
	public void setIdUsuario(DTOUsuario usuario) {
		this.idUsuario = usuario.getIdentificador();
	}


	/**
	 * @return el idCampamento
	 */
	public int getIdCampamento() {
		return idCampamento;
	}


	/**
	 * @param idCampamento es el idCampamento para settear
	 */
	public void setIdCampamento(int identificador) {
		this.idCampamento = identificador;
	}
	/**
	 * @param identificador es el identificador de la inscripcion para settear
	 */
	public void setIdentificador(int identificador_){
		this.identificador = identificador_;
	}


	/**
	 * @return la fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}


	/**
	 * @param fecha es la fecha para settear
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return el precio
	 */
	public float getPrecio() {
		return precio;
	}


	/**
	 * @param precio es el precio para settear
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * @return el tipo de inscripcion
	 */

	public String getTipo() {
		return tipo;
	}

	/**
	 * @param es el tipo a settear
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	/**
	 * @return el estado de inscripcion
	 */

	public String getEstado() {
		return estado;
	}

	/**
	 * @param es el estado a settear
	 */

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene informacion de la inscripcion.
	 * 
	 * @return infoReserva Cadena de texto con la informacion de la inscripcion.
	 */
	public String toString() {
		return "Identificador del asistente: " + idUsuario + "\nIdentificador del campamento: " + idCampamento + "\nFecha en la que se realizó la inscripcion: " + fecha
				+ "\nPrecio de la inscripción:" + precio;
	}
	
	
	
	
	

}
