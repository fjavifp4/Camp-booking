package es.uco.pw.display;
/* 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.uco.pw.business.asistente.*;
import java.time.LocalDate;

public class MenuAsistentes{
    @SuppressWarnings("static-access")
	Scanner leer = new Scanner(System.in);
	public void menu() throws FileNotFoundException, IOException{
		int opcion;
		boolean salir = false;
		GestorAsistentes gestorAsistentes = new GestorAsistentes();
		//Fichero ficheroAsistentes = new Fichero();
		//listaAsistentes = ficheroAsistentes.cargarFicheroAsistente();

		while (!salir) {
			System.out.println("------------------\nMENU DE ASISTENTES\n------------------");
			System.out.println("1.Dar de alta a un asistente.\n2.Modificar un asistente.\n3.Mostrar el listado de asistentes.\n4.SALIR.\n");
			try {
				System.out.println("Introduce una opcion:");
				opcion = leer.nextInt();
				switch (opcion) {
				case 1:
					DTOAsistente a=new DTOAsistente();
					
					a=rellenarDatosAsistente();
					//System.out.println(a.toString());
					//System.out.println(a.esrequiereAtencionEspecial());

					if(gestorAsistentes.altaAsistente(a)) {
						System.out.println("asistente dado de alta.\n");
					}
					else
						System.out.println("El asistente no ha podido ser dado de alta.\n");
					
					break;
					
				case 2:
					System.out.println("Introduzca el identificador del asistente a modificar:");
			        int identificadorAntiguo = leer.nextInt();
			        
			        if(gestorAsistentes.comprobarExistenciaAsistente(identificadorAntiguo)==true) {
			        	DTOAsistente antiguo=new DTOAsistente();
			        	antiguo=gestorAsistentes.asistentePorID(identificadorAntiguo);
			        	
			        	
			        	DTOAsistente nuevo=new DTOAsistente();
			        	nuevo=rellenarDatosAsistenteMod(antiguo);
			        				        	
			        	//System.out.println(antiguo.esrequiereAtencionEspecial());
			        	//System.out.println(antiguo.toString());
			        	//System.out.println(nuevo.toString());
			        	
			        	if(gestorAsistentes.modificarAsistente(nuevo)) {
							System.out.println("asistente modificado.");
			        	}
			        	else {
							System.out.println("El asistente no ha podido ser modificado.");
			        	}
			        }
			        else {
			        	System.out.println("El asistente no ha podido ser modificado.");
			        }
			        
					break;
                case 3:
					gestorAsistentes.listadoAsistente();
					break;
					
				case 4:
					salir = true;
					break;
					
				default:
					System.out.println("La opcion elegida no es valida.");
					break;

				}
			}catch (InputMismatchException e) {
				System.out.println("Debes insertar un numero");
				leer.next();
			}
		}
        
		//ficheroAsistentes.escribirFicheroAsistente(listaAsistentes);
		//leer.close();
		
    }

	/**
	 * Permite ingresar los datos de un nuevo asistente.
	 * @return instancia de un asistente.
	 * @throws FileNotFoundException Si hay un error al acceder a un archivo.
	 * @throws IOException Si ocurre un error de lectura o escritura de datos.
	
	@SuppressWarnings("static-access")
	public DTOAsistente rellenarDatosAsistente() throws FileNotFoundException, IOException{
		DTOAsistente a = new DTOAsistente();
		
		//System.out.println("Introduzca el identificador del asistente:");
		//int identificador = leer.nextInt();
		
		System.out.println("\nIntroduzca el nombre y los apellidos del nuevo asistente:");
		leer.nextLine();
		String nombre_apellidos = leer.nextLine();
		
		boolean dia=false,mes=false,anio=false;
		int day = 1,month = 1,year = 1;

		while(!dia){
			System.out.println("Introduzca el dia de nacimiento del asistente:");
			//leer.nextInt();
			day = leer.nextInt();

			if(day<1 || day>31)
			{
				System.out.println("Dia introducido no valido");
				dia=false;
			}
			else
			{
				dia=true;
			}
		}

		while(!mes){
			System.out.println("Introduzca el mes de nacimiento del asistente:");
			//leer.nextInt();
			month = leer.nextInt();

			if(month<1 || month>12)
			{
				System.out.println("Mes introducido no valido");
				mes=false;
			}
			else
			{
				mes=true;
			}
		}

		while(!anio){
			System.out.println("Introduzca el año de nacimiento del asistente:");
			//leer.nextInt();
			year = leer.nextInt();

			if(year>2023)
			{
				System.out.println("Anio introducido no valido");
				anio=false;
			}
			else
			{
				anio=true;
			}
		}

		System.out.print("El asistente requiere atención especial? (S/N): ");
		leer.nextLine();
		String respuesta = leer.nextLine();
		boolean requiereAtencionEspecial = false;

		if (respuesta.equalsIgnoreCase("S")) 
		{
			requiereAtencionEspecial = true;
		}
		else
		{
			requiereAtencionEspecial = false;
		}
		
		//LocalDate fechaNacimientoFormatted = LocalDate.now();
		//fechaNacimientoFormatted.of(year, month, day);		
		LocalDate fechaNacimientoFormatted = LocalDate.of(year,month,day);
		
		//a.setIdentificador(identificador);
		a.setIdentificador(-1);
		a.setNombreApellidos(nombre_apellidos);
		a.setFechaNacimiento(fechaNacimientoFormatted);
		a.setRequiereAtencionEspecial(requiereAtencionEspecial);		
		
		return a;
	}

	public DTOAsistente rellenarDatosAsistenteMod(DTOAsistente antiguo) throws FileNotFoundException, IOException{
		DTOAsistente nuevo = new DTOAsistente(antiguo.getIdentificador(),antiguo.getNombreApellidos(),antiguo.getFechaNacimiento(),antiguo.esrequiereAtencionEspecial());
		//nuevo=antiguo;
		
		//leer.nextLine();
		//System.out.print("¿Quieres modificar el identificador del asistente? (S/N): ");
		//leer.nextLine();
		String respuesta = leer.nextLine();
		//leer.nextLine();

		//if (respuesta.equalsIgnoreCase("S")) {
		//    System.out.println("Introduzca el identificador nuevo del asistente:");
		//    int identificadorAsistente = leer.nextInt();  
		//    nuevo.setIdentificador(identificadorAsistente);
		//    
		//}

		System.out.print("¿Quieres modificar el nombre del asistente? (S/N): ");
		//leer.nextLine();
		respuesta = leer.nextLine();
		//leer.nextLine();
			
		if (respuesta.equalsIgnoreCase("S")) {
			System.out.print("Ingresa el nuevo nombre del asistente: ");
			//leer.nextLine();
			String nombreAsistente = leer.nextLine();
			//leer.nextLine();
			nuevo.setNombreApellidos(nombreAsistente);
		}

		System.out.print("¿Quieres modificar la fecha de nacimiento del asistente? (S/N): ");
		//leer.nextLine();
		respuesta = leer.nextLine();
		//leer.nextLine();

		boolean dia=false,mes=false,anio=false;
		int day=1,month=1,year=1;

		if (respuesta.equalsIgnoreCase("S")) {
			
			while(!dia){
				System.out.println("Introduzca el nuevo dia de nacimiento del asistente:");
				day = leer.nextInt();

				if(day<1 || day>31)
				{
					System.out.println("Dia introducido no valido");
					dia=false;
				}
				else
				{
					dia=true;
				}
			}
		

			while(!mes){
				System.out.println("Introduzca el nuevo mes de nacimiento del asistente:");
				month = leer.nextInt();

				if(month<1 || month>12)
				{
					System.out.println("Mes introducido no valido");
					mes=false;
				}
				else
				{
					mes=true;
				}
			}

			while(!anio){
				System.out.println("Introduzca el nuevo año de nacimiento del asistente:");
				year = leer.nextInt();

				if(year>2024)
				{
					System.out.println("Anio introducido no valido");
					anio=false;
				}
				else
				{
					anio=true;
				}
			}
			
			//LocalDate fechaNacimientoFormatted = LocalDate.now();
			//fechaNacimientoFormatted.of(year,month,day);
			LocalDate fechaNacimientoFormatted = LocalDate.of(year,month,day);
			nuevo.setFechaNacimiento(fechaNacimientoFormatted);
		}

		System.out.print("El asistente requiere atención especial? (S/N): ");
		leer.nextLine();
		respuesta = leer.nextLine();
		//leer.nextLine();
		boolean antencionAsistente = false;

		if (respuesta.equalsIgnoreCase("S")) 
		{
			antencionAsistente = true; 
			nuevo.setRequiereAtencionEspecial(antencionAsistente);
		}
		else
		{
			antencionAsistente = false; 
			nuevo.setRequiereAtencionEspecial(antencionAsistente);
		}
		//System.out.println(nuevo.toString());
		return nuevo;
	}
}
*/