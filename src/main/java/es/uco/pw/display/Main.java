package es.uco.pw.display;

/* 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;




public class Main{
   public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Scanner leer = new Scanner(System.in);
		boolean salir = false;
		MenuAsistentes menuAsistentes = new MenuAsistentes();
		MenuCampamentos menuCampamentos = new MenuCampamentos();
		MenuInscripciones menuInscripciones = new MenuInscripciones();
		
		
		
		while(!salir) {
			System.out.println("------------------\nPAGINA DE CAMPAMENTOS DE VERANO\n------------------");
			System.out.println("1. Menu de asistentes");
			System.out.println("2. Menu de campamentos");
			System.out.println("3. Menu de inscripciones");
			System.out.println("0. Salir");
			int opcion= leer.nextInt();
			switch(opcion) {
			case 1:
				menuAsistentes.menu();
				break;
				
			case 2:
				menuCampamentos.menu();
				break;
				
			case 3:
				menuInscripciones.menu();
				break;
				
			case 0:
				salir = true;
				break;
			default:
				System.out.println("Opcion no valida.");
				break;
			}
		}
		leer.close();
	}
}

*/