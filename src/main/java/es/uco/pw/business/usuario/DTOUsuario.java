package es.uco.pw.business.usuario;

import java.time.LocalDate;

/**
 * La clase Usuario representa a una persona que asiste al campamento de verano.
 * Esta clase almacena información sobre el usuario, como su identificador, nombre y apellidos,
 * fecha de nacimiento y si requiere atención especial.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
public class DTOUsuario
{
    private int identificador;
    /** Identificador del usuario */
    
    private String nombre_apellidos;
    /**Nombre y apellidos del usuario */
    
    private LocalDate fechaNacimiento; 
    /** Fecha de nacimiento del usuario */
    
    private boolean requiereAtencionEspecial=false;
    /** Indica si requiere o no atención especial */

    private String email;
    /** Email del usuario */

    private String password;
    /** Contraseña del usuario */

    private String rol;
    /** Rol del usuario */

   


	/**
     * Constructor vacio de la clase Usuario.
     * Inicializa todas las propiedades con valores predeterminados.
     */
    public DTOUsuario()
    {
        
    }

    /**
     * Constructor de la clase Usuario que acepta parámetros para inicializar todas las propiedades.
     *
     * @param identificador El identificador único del usuario.
     * @param nombre_apellidos El nombre y apellidos del usuario.
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     * @param requiereAtencionEspecial Indica si el usuario requiere atención especial.
     */
    public DTOUsuario(int identificador, String nombre_apellidos, LocalDate fechaNacimiento, boolean requiereAtencionEspecial, String email, String password, String rol)
    {
        this.identificador = identificador;
        this.nombre_apellidos = nombre_apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.requiereAtencionEspecial = requiereAtencionEspecial;
        this.email = email;
        this.password = password;
        this.rol = rol;

    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return El identificador del usuario.
     */
    public int getIdentificador()
    {
        return identificador;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param identificador El identificador único del usuario.
     */
    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    /**
     * Obtiene el nombre y apellidos del usuario.
     *
     * @return El nombre y apellidos del usuario.
     */
    public String getNombreApellidos()
    {
        return nombre_apellidos;
    }

    /**
     * Establece el nombre y apellidos del usuario.
     *
     * @param nombre_apellidos El nombre y apellidos del usuario.
     */
    public void setNombreApellidos(String nombre_apellidos)
    {
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return La fecha de nacimiento del usuario.
     */
    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Comprueba si el usuario requiere atención especial.
     *
     * @return true si el usuario requiere atención especial, false en caso contrario.
     */
    public boolean esrequiereAtencionEspecial()
    {
        return requiereAtencionEspecial;
    }

    /**
     * Establece si el usuario requiere atención especial.
     *
     * @param requiereAtencionEspecial true si el usuario requiere atención especial, false en caso contrario.
     */
    public void setRequiereAtencionEspecial(boolean requiereAtencionEspecial)
    {
        this.requiereAtencionEspecial = requiereAtencionEspecial;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return El email del usuario.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email El email del usuario.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */

    public String getPassword()
    {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol()
    {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String rol)
    {
        this.rol = rol;
    }


    /**
     * Devuelve una representación en forma de cadena de la clase Usuario.
     *
     * @return Una cadena que representa los datos del usuario.
     */
    public String toString() 
    {
        return "Usuario [identificador=" + identificador + ", nombre_apellidos=" + nombre_apellidos + ", fechaNacimiento=" + fechaNacimiento + ", requiereAtencionEspecial=" + requiereAtencionEspecial
                +", email"+email+", password"+password+", rol"+rol+ "]";
    } 
   
}

