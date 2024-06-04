package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerBean implements Serializable {
    private static final long serialVersionUID = 1L;
	private int id;
    private String nombre_apellidos;
    private LocalDate fecha_nacimiento;
    private boolean requiereAtencionEspecial;

    private String email;
    private String password;
    private String rol;

    public CustomerBean(){
    	
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNombre_apellidos(){
        return nombre_apellidos;
    }
    public void setNombre_apellidos(String nombre_apellidos){
        this.nombre_apellidos = nombre_apellidos;
    }

    public LocalDate getFecha_nacimiento(){
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public boolean getRequiereAtencionEspecial(){
        return requiereAtencionEspecial;
    }
    public void setRequiereAtencionEspecial(boolean requiereAtencionEspecial){
        this.requiereAtencionEspecial = requiereAtencionEspecial;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRol(){
        return rol;
    }
    public void setRol(String rol){
        this.rol = rol;
    }

    @Override
    public String toString() 
    {
        return "CustormerBean [id=" + id + ", nombre_apellidos=" + nombre_apellidos + ", fecha_nacimiento=" + fecha_nacimiento + ", requiereAtencionEspecial=" + requiereAtencionEspecial + ", email=" + email + ", password=" + password + ", rol=" + rol +"]";
    } 

}