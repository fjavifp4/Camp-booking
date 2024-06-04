package es.uco.pw.business.actividad;

import java.util.ArrayList;
import es.uco.pw.business.monitor.*;

/**
 * La clase Actividad representa una actividad que forma parte de la organización de un campamento.
 * Contiene información sobre el nombre de la actividad, nivel educativo, horario, número máximo de participantes,
 * número de monitores necesarios y la lista de monitores asignados a la actividad.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 * */
 


public class DTOActividad 
{
    private String nombreActividad;
    /** Nombre de la actividad */
    
    private NivelEducativo nivelEducativo;
    /** Nivel educativo*/
    
    private Horario horario;
    /** Horario de la actividad */
    
    private Integer maxParticipantes;
    /** Numero maximo de participantes */
    
    private Integer monitoresNecesarios;
    /** Numero de monitores necesarios */
    

    /**
     * Constructor vacío de la clase Actividad. Inicializa la lista de monitores asignados como una lista vacía.
     */
    public DTOActividad()
    {
        this.nombreActividad = "";
        this.maxParticipantes = 0;
        this.monitoresNecesarios = 0;
    }

    /**
     * Constructor parametrizado de la clase Actividad.
     *
     * @param nombreActividad El nombre de la actividad.
     * @param nivelEducativo El nivel educativo de la actividad.
     * @param horario El horario de la actividad.
     * @param maxParticipantes El número máximo de participantes en la actividad.
     * @param monitoresNecesarios El número de monitores necesarios.
     */
    public DTOActividad(String nombreActividad, NivelEducativo nivelEducativo, Horario horario, Integer maxParticipantes, Integer monitoresNecesarios)
    {
        this.nombreActividad = nombreActividad;
        this.nivelEducativo = nivelEducativo;
        this.horario = horario;
        this.maxParticipantes = maxParticipantes;
        this.monitoresNecesarios = monitoresNecesarios;
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
    public String getNombreActividad()
    {
        return nombreActividad;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombreActividad El nombre de la actividad.
     */
    public void setNombreActividad(String nombreActividad)
    {
        this.nombreActividad = nombreActividad;
    }

    /**
     * Devuelve el Nivel Educativo de una actividad.
     *
     * @return Una variable de tipo NivelEducativo que representa el nivel educativo de una actividad.
    */
    
    
    public NivelEducativo getNivelEducativo()
    {
        return nivelEducativo;
    
    }
    /**
	 * @param nivelEducativo es el nivelEducativo para settear
	 */
	public void setNivelEducativo(NivelEducativo nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}
    
    /**
     * Obtiene la lista de monitores asignados a la actividad.
     *
     * @return La lista de monitores asignados.
     */
   

    /**
	 * @return el horario
	 */
	public Horario getHorario() {
		return horario;
	}

	/**
	 * @param horario es el horario para settear
	 */
	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	/**
	 * @return el maxParticipantes
	 */
	public Integer getMaxParticipantes() {
		return maxParticipantes;
	}

	/**
	 * @param maxParticipantes es el maxParticipantes para settear
	 */
	public void setMaxParticipantes(Integer maxParticipantes) {
		this.maxParticipantes = maxParticipantes;
	}

	/**
	 * @return los monitoresNecesarios
	 */
	public Integer getMonitoresNecesarios() {
		return monitoresNecesarios;
	}

	/**
	 * @param monitoresNecesarios son los monitoresNecesarios para settear
	 */
	public void setMonitoresNecesarios(Integer monitoresNecesarios) {
		this.monitoresNecesarios = monitoresNecesarios;
	}



	/**
     * Agrega un monitor a la lista de monitores asignados si no se ha alcanzado el máximo de monitores necesarios.
     *
     * @param monitor El monitor a ser asignado.
     */
  
    
  
    
    /**
     * Devuelve una representación en forma de cadena de la clase Actividad.
     *
     * @return Una cadena que representa la información de la actividad.
    */
    public String toString() 
    {
        return "Actividad [nombreActividad=" + nombreActividad + ", nivelEducativo=" + nivelEducativo +
                ", horario=" + horario + ", maxParticipantes=" + maxParticipantes +
                ", monitoresNecesarios=" + monitoresNecesarios+ "]";
    }
}



