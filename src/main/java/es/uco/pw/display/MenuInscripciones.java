package es.uco.pw.display;

/* 

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.uco.pw.business.asistente.DTOAsistente;
import es.uco.pw.business.campamento.*;
import es.uco.pw.business.inscripcion.*;
import es.uco.pw.business.asistente.*;

public class MenuInscripciones {

    Scanner leer = new Scanner(System.in);
    GestorInscripciones gestorInscripciones = new GestorInscripciones();
    GestorAsistentes gestorAsistentes = new GestorAsistentes();
    GestorCampamentos gestorCampamentos = new GestorCampamentos();



    @SuppressWarnings("static-access")
	public void menu() throws FileNotFoundException, IOException{
		int opcion;
		boolean salir = false;
		int idCampamento = 0,idAsistente=0;	
		//Fichero ficheroC=new Fichero();

			
		while (!salir) {
			System.out.println("------------------\nMENU DE INSCRIPCIONES\n------------------");
			System.out.println("1.Realizar inscripcion \n2.Cancelar inscripcion\n3.Consultar campamentos disponibles.\n4.SALIR.\n");
			try {
				System.out.println("Introduce una opcion:");
				opcion = leer.nextInt();
				switch (opcion) {
				case 1:				
					gestorInscripciones.listarCampamentos();
					System.out.println("Campamento a la que quieres ir (id del campamento): ");
					
					idCampamento = leer.nextInt();
					
					System.out.println("Introduzca los datos necesarios para hacer una inscripcion.");
					gestorAsistentes.listadoAsistente();
					System.out.println("Asistente que la realiza (id del asistente):");
					idAsistente = leer.nextInt();
							
					DTOAsistente asistente = new DTOAsistente();
					DTOCampamento campamento  = new DTOCampamento();
							
					asistente = gestorInscripciones.buscarAsistente(idAsistente);
					campamento = gestorInscripciones.buscarCampamento(campamento, idCampamento);

					if ( asistente == null )
					{
						System.out.println("El asistente debe estar regitrado.");
						break;
					}
					else
					{
						System.out.println("El asistente esta registrado.");
					}
					

					if ( campamento == null || gestorInscripciones.plazasDisponibles(campamento) == 0 )
					{
						System.out.println("El campamento no existe o no esta disponible.");
						break;
					}
					else
					{
						System.out.println("El campamento esta disponible.");
					}
					
					int numeroActividades = gestorInscripciones.numeroActividadesCampamento(campamento.getIdentificador());
					
				
					System.out.println("Quiere hacer una inscripcion Completa o Parcial?\n 1.Completa\n 2.Parcial\nOpcion(Introduzca 1 o 2): ");
					int opt = leer.nextInt();

					if(gestorInscripciones.comprobarAsistenteInscrito(asistente,campamento))
					{
						System.out.println("No se puede realizar la inscripcion a este campamento. (Ya fuiste inscrito en este campamento de manera completa o parcial)\n");
						break;
					}
					
						
					if(gestorInscripciones.funcionCalcularTemprano(idCampamento)){
						System.out.println(opt);

						boolean status = gestorInscripciones.realizarRegistroTemprano(numeroActividades, campamento, asistente, opt);
						if(status){
							System.out.println("Inscripcion realizada.");
						}
						else{
							System.out.println("No se ha podido realizar la inscripcion.");
						}
						
					}
					else if(gestorInscripciones.funcionCalcularTardio(idCampamento)){

						if(gestorInscripciones.realizarRegistroTardio(numeroActividades, campamento, asistente, opt)){
							System.out.println("Inscripcion realizada.");
						}else{
							System.out.println("No se ha podido hacer la isncripcion por poca antelación al campamento.\n");
						}	
					}else	{
						System.out.println("No se ha podido realizar la inscripcion.");
					}
						
				
					break;

				case 2:

					System.out.println("Introduzca los datos necesarios para cancelar una inscripcion.");

					gestorAsistentes.listadoAsistente();
					System.out.println("Asistente que la realiza(id del asistente):");
					idAsistente = leer.nextInt();

					if(!gestorInscripciones.comprobarExistenciaCampamento(idCampamento))
					{	
						System.out.println("El campamento no existe.");
						break; 

					}
					
					gestorCampamentos.listarCampamentos();

					System.out.println("Campamento al que quieres cancelar la inscripcion(id del campamento):");
					idCampamento = leer.nextInt();

					if(!gestorInscripciones.comprobarExistenciaAsistente(idAsistente))
					{	
						System.out.println("El asistente no existe.");
						break; 

					}
						
							
					DTOCampamento c = new DTOCampamento();
					c.setIdentificador(idCampamento);
					DTOAsistente a = new DTOAsistente();
					a.setIdentificador(idAsistente);
				
					
					if ( gestorInscripciones.funcionCalcularTemprano(idCampamento))
					{
						if(gestorInscripciones.cancelarInscripcion(a,c))
						{
							System.out.println("Cancelacion exitosa.");
						}
						else
						{
							System.out.println("Error al cancelar la inscripcion.");
						}
					}
					else
					{
						System.out.println("No se puede cancelar la inscripcion (la inscripcion se realizo en periodo tardio).");
					}
		
					
					break;
				
                case 3:
					System.out.println("Lista de campamentos disponibles: \n");
					gestorInscripciones.listarCampamentos();
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
        
		
		

    }
    
}

*/