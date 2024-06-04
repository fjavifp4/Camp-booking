package es.uco.pw.business.monitor;



/**
 * La clase Monitor representa a un monitor del campamento de verano, que puede ser un educador especial o no.
 * Esta clase almacena información sobre el monitor, como su identificador, nombre y apellidos,
 * y si es un educador especial.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */


public class DTOMonitor 
{
    private int identificador;
    /** Identificador del monitor*/

    private String nombre_apellidos;
    /**Nombre y apellidos del monitor*/

    private boolean educadorEspecial;
    /**Indica si es educador especial o no  */


    /**
     * Constructor vacio de la clase Monitor.
     * Inicializa todas las propiedades con valores predeterminados.
     */
    public DTOMonitor()
    {
        
    }

    /**
     * Constructor de la clase Monitor que acepta parámetros para inicializar todas las propiedades.
     *
     * @param identificador El identificador único del monitor.
     * @param nombre_apellidos El nombre y apellidos del monitor.
     * @param educadorEspecial Indica si el monitor es un educador especial.
     */
    public DTOMonitor(int identificador, String nombre_apellidos, boolean educadorEspecial)
    {
        this.identificador = identificador;
        this.nombre_apellidos = nombre_apellidos;
        this.educadorEspecial = educadorEspecial;
    }

    /**
     * Obtiene el identificador del monitor.
     *
     * @return El identificador del monitor.
     */
    public int getIdentificador()
    {
        return identificador;
    }

    /**
     * Establece el identificador del monitor.
     *
     * @param identificador El identificador único del monitor.
     */
    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    /**
     * Obtiene el nombre y apellidos del monitor.
     *
     * @return El nombre y apellidos del monitor.
     */
    public String getNombreApellidos()
    {
        return nombre_apellidos;
    }

    /**
     * Establece el nombre y apellidos del monitor.
     *
     * @param nombre_apellidos El nombre y apellidos del monitor.
     */
    public void setNombreApellidos(String nombre_apellidos)
    {
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Comprueba si el monitor es un educador especial.
     *
     * @return true si el monitor es un educador especial, false en caso contrario.
     */
    public boolean getEducadorEspecial()
    {
        return educadorEspecial;
    }

    /**
     * Establece si el monitor es un educador especial.
     *
     * @param educadorEspecial true si el monitor es un educador especial, false en caso contrario.
     */
    public void setEducadorEspecial(boolean educadorEspecial)
    {
        this.educadorEspecial = educadorEspecial;
    }

    /**
     * Devuelve una representación en forma de cadena de la clase Monitor.
     *
     * @return Una cadena que representa los datos del monitor.
     */
    public String toString() 
    {
        return "Monitor [identificador=" + identificador + ", nombre_apellidos=" + nombre_apellidos +
                ", educadorEspecial=" + educadorEspecial + "]";
    }
}


