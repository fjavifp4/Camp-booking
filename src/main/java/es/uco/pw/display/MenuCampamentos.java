package es.uco.pw.display;
/*
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.uco.pw.business.actividad.*;
import es.uco.pw.business.monitor.*;
import es.uco.pw.business.campamento.*;
import java.time.LocalDate;

public class MenuCampamentos{
    @SuppressWarnings("static-access")
    Scanner leer = new Scanner(System.in);
	GestorCampamentos gestorCampamentos = new GestorCampamentos();

    public void menu() throws FileNotFoundException, IOException{
		int opcion;//Opcion elegida por el usuario
		boolean salir = false;
		
		while (!salir) {
			System.out.println("------------------\nMENU DE CAMPAMENTOS\n------------------");
			System.out.println("1.Crear actividad.\n2.Crear monitor.\n3.Crear campamento.\n4.Asociar monitor a actividad.\n5.Asociar actividad a campamento.\n6.Asociar monitor a campamento.\n7.Asociar monitor de atención especial.\n8.Listar actividades.\n9.Listar monitores.\n10.Listar Campamentos.\n11.Salir.\n");
			try {
				System.out.println("Introduce una opcion:");
				opcion = leer.nextInt();
				switch (opcion) {
				case 1:
					DTOActividad nuevaActividad=new DTOActividad();
					nuevaActividad=rellenarDatosActividad();

					if(gestorCampamentos.crearActividad(nuevaActividad)) {
						System.out.println("Se ha creado la actividad.\n");
					}
					else
						System.out.println("No se ha podido crear la actividad.\n");
					
					break;
					
				case 2:
					DTOMonitor nuevMonitor=new DTOMonitor();
					nuevMonitor=rellenarDatosMonitor();

					if(gestorCampamentos.crearMonitor(nuevMonitor)) {
						System.out.println("Se ha creado el monitor.\n");
					}
					else
						System.out.println("No se ha podido crear el monitor.\n");
					
					break;
					
				case 3:
					DTOCampamento nuevCampamento=new DTOCampamento();
					nuevCampamento=rellenarDatosCampamento();

					if(gestorCampamentos.crearCampamento(nuevCampamento)) {
						System.out.println("Se ha creado el campamento.\n");
					}
					else
						System.out.println("No se ha podido crear el campamento.\n");
					
					break;
				case 4:
					/*
					 	listar actividades
					 	pedir nombre nommbre de actividad
					 	comprobar si existe
					 	encontrar actividad a parti del nombre
					 	
					 	preguntar si quiere listar los monitores disponibles
					 	pedir id del monitor
					 	comprobar si existe
					 	encontrar monitor a partir de id
					 	
					 	mandar el monitor y la actividad a la funcion de monitor a actividad
					
					DTOActividad actividad=new DTOActividad();
					DTOMonitor monitor=new DTOMonitor();
					
					System.out.println("Actividades disponibles:");
					gestorCampamentos.listarActividades();
					
					System.out.println("Introduce el nombre de la actividad:");
					leer.nextLine();
					String nombreActividad=leer.nextLine();
					
					if(gestorCampamentos.comprobarExistenciaActividad(nombreActividad)==true) {
						actividad=gestorCampamentos.actividadPorNombre(nombreActividad);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar a la actividad, id no existe.");
						break;
					}
					
					System.out.println("Monitores disponibles:");
					gestorCampamentos.listarMonitores();
					
					System.out.println("Introduce el id del monitor:");
					int idMonitor=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaMonitor(idMonitor)==true) {
						monitor=gestorCampamentos.monitorPorId(idMonitor);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar a la actividad, nombre no existe.");
						break;
					}
					
					if(gestorCampamentos.monitorAActividad(monitor,actividad)==true) {
						System.out.println("Monitor asociado a actividad.");
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar a la actividad (tambien recuerda que solo se pueden asociar una vez)");
					}
					
					break;
					
				case 5:
					DTOActividad actividad1=new DTOActividad();
					DTOCampamento campamento=new DTOCampamento();
					
					System.out.println("Actividades disponibles:");
					gestorCampamentos.listarActividades();
					
					System.out.println("Introduce el nombre de la actividad:");
					leer.nextLine();
					String nombreActividad1=leer.nextLine();
					
					if(gestorCampamentos.comprobarExistenciaActividad(nombreActividad1)==true) {
						actividad1=gestorCampamentos.actividadPorNombre(nombreActividad1);
					}
					else
					{
						System.out.println("No se ha podido asociar la actividad al campamento, nombre no existe.");
						break;
					}
					
					System.out.println("Campamentos disponibles:");
					gestorCampamentos.listarCampamentos();
					
					System.out.println("Introduce el id del campamento:");
					int idCampamento=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaCampamento(idCampamento)==true) {
						campamento=gestorCampamentos.campamentoPorId(idCampamento);
					}
					else
					{
						System.out.println("No se ha podido asociar la actividad al campamento1, id no existe.");
						break;
					}
					
					if(actividad1.getNivelEducativo()!=campamento.getNivelEducativo()) {
						System.out.println("No se ha podido asociar, niveles educativos no coinciden.");
					}
					
					if(gestorCampamentos.actividadACampamento(campamento,actividad1)==true) {
						System.out.println("Actividad asociada al campamento.");
					}
					else
					{
						System.out.println("No se ha podido asociar la actividad al campamento (tambien recuerda que solo se pueden asociar una vez).");
					}
					
					break;
				case 6: 
					DTOCampamento campamento1=new DTOCampamento();
					DTOMonitor monitor1=new DTOMonitor();
					
					System.out.println("Monitores disponibles:");
					gestorCampamentos.listarMonitores();
					
					System.out.println("Introduce el id del monitor:");
					int idMon=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaMonitor(idMon)==true) {
						monitor1=gestorCampamentos.monitorPorId(idMon);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campameno, id no existe.");
						break;
					}
					
					System.out.println("Campamentos disponibles:");
					gestorCampamentos.listarCampamentos();
					
					System.out.println("Introduce el id del campamento:");
					int idCamp=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaCampamento(idCamp)==true) {
						campamento1=gestorCampamentos.campamentoPorId(idCamp);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campamento, id no existe.");
						break;
					}
					if(gestorCampamentos.monitorResponsableACampamento(monitor1,campamento1)==true) {
						System.out.println("Monitor asociado al campamento.");
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campamento, compruebe que el monitor este asociado a una actividad de ese campamento (tambien recuerda que solo se pueden asociar una vez)");
					}

					break;
                case 7: 
                	DTOCampamento campamento2=new DTOCampamento();
					DTOMonitor monitor2=new DTOMonitor();
					
					System.out.println("Monitores disponibles:");
					gestorCampamentos.listarMonitoresEspeciales();
					
					System.out.println("Introduce el id del monitor:");
					int idMonEs=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaMonitor(idMonEs)==true) {
						monitor2=gestorCampamentos.monitorEspecialPorId(idMonEs);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campameno, id no existe o no presta atencion especial.");
						break;
					}
					
					System.out.println("Campamentos disponibles:");
					gestorCampamentos.listarCampamentos();
					
					System.out.println("Introduce el id del campamento:");
					int idCampEs=leer.nextInt();
					
					if(gestorCampamentos.comprobarExistenciaCampamento(idCampEs)==true) {
						campamento2=gestorCampamentos.campamentoPorId(idCampEs);
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campamento, id no existe.");
						break;
					}
					if(gestorCampamentos.monitorEspecialACampamento(monitor2,campamento2)==true) {
						System.out.println("Monitor asociado al campamento.");
					}
					else
					{
						System.out.println("El monitor no se ha podido asociar al campamento, compruebe que el monitor no este asociado a una actividad, sea de atencion especial y este asociado al campamento.");
					}
                    
					break;
				
				case 8:
					gestorCampamentos.listarActividades();

					break;

				case 9:
					gestorCampamentos.listarMonitores();

					break;
				
				case 10:
					gestorCampamentos.listarCampamentos();

					break;

                case 11: 
					salir = true;
					break;
				default:
					 System.out.println("La opcion elegida no es valida.\n");
					break;
				}
			}catch (InputMismatchException e) {
					System.out.println("Debes insertar un numero");
					leer.next();
			}
		}
		
	}



    public DTOActividad rellenarDatosActividad(){
		DTOActividad actividad=new DTOActividad();

		System.out.println("Introduzca los datos necesarios para crear una actividad.");
		System.out.println("Nombre:");
		leer.nextLine();
		String nombreActividad = leer.nextLine();
		NivelEducativo nivelActividad = NivelEducativo.infantil;
		Horario horario = Horario.manana;
		int maxPart=0,monNec=0;

		int i=0;
		while(i==0){//Comprobamos que el nivel introducido es valido
			System.out.println("Nivel educativo de la actividad: \n\t1.Infantil. \n\t2.Juvenil. \n\t3.Adolescente.");
			int nivel=leer.nextInt();
			nivelActividad = NivelEducativo.infantil;
			if(nivel==1) {
				nivelActividad=NivelEducativo.infantil;
				i=1;
			}
			else if(nivel==2) {
				nivelActividad=NivelEducativo.juvenil;
				i=1;
			}
			else if(nivel==3)
			{
				nivelActividad=NivelEducativo.adolescente;
				i=1;
			}
			else{
				System.out.println("El nivel introducido no es valido.");
			}
		}

		i=0;
		while(i==0)//Comprobamos que el horario introducido es valido
		{
			System.out.println("Horario: \n\t1.Mañana. \n\t2.Tarde.");
			int hor=leer.nextInt();
			horario = Horario.manana;
			if(hor==1) {
				horario=Horario.manana;
				i=1;
			}
			else if(hor==2){
				horario=Horario.tarde;
				i=1;
			}
			else{
				System.out.println("El horario introducido no es valido.");
			}
		}
		
		i=0;
		while(i==0)//Comprobamos que el numero maximo de participantes es valido
		{
			System.out.println("Numero maximo de participantes permitidos:");
			maxPart=leer.nextInt();
			if(maxPart>0) {
				i=1;
			}
			else{
				System.out.println("El numero introducido no es valido.");
			}
		}

		i=0;
		while(i==0){//Comprobamos que el numero de monitores necesarios es valido		
			System.out.println("Numero necesario de monitores:");
			monNec=leer.nextInt();
			if(monNec>0) {
				i=1;
			}
			else{
				System.out.println("El numero introducido no es valido.");
			}
		}

		actividad.setNombreActividad(nombreActividad);
		actividad.setNivelEducativo(nivelActividad);
		actividad.setHorario(horario);
		actividad.setMaxParticipantes(maxPart);
		actividad.setMonitoresNecesarios(monNec);

		return actividad;
	}

    public DTOMonitor rellenarDatosMonitor(){
		DTOMonitor monitor=new DTOMonitor();

		System.out.println("Introduzca los datos necesarios para crear un monitor.");
		//System.out.println("Identificador:");
		//int idMonitor = leer.nextInt();
		
		System.out.println("Nombre del monitor:");
		leer.nextLine();
		String nombreMonitor=leer.nextLine();		

		int i=0;
		boolean esEducadorEspecial=false;
		while(i==0){//Comprobamos que el estado introducido es valido
			System.out.println("Esducador especial: \n\t1.Si. \n\t2.No.");
			int estadoM=leer.nextInt();
			esEducadorEspecial = false;
			if(estadoM==1) {
				esEducadorEspecial=true;
				i=1;
			}
			else if(estadoM==2) {
				esEducadorEspecial=false;
				i=1;
			}
			else{
				System.out.println("El estado introducido no es valido.");
			}
		}
		
		//monitor.setIdentificador(idMonitor);
		monitor.setIdentificador(-1);
		monitor.setNombreApellidos(nombreMonitor);
		monitor.setEducadorEspecial(esEducadorEspecial);

		return monitor;
	}

    public DTOCampamento rellenarDatosCampamento(){
		DTOCampamento campamento=new DTOCampamento();

		System.out.println("Introduzca los datos necesarios para crear un campamento.");
		
		//System.out.println("Identificador:");
		//int idCamp = leer.nextInt();

		boolean dia=false,mes=false,anio=false;//Variables para comprobar que la fecha introducida es valida
		int dayI=0,monthI=0,yearI=0;//Variables para la fecha de inicio del campamento
		int dayF=0,monthF=0,yearF=0;//Variables para la fecha de finalizacion del campamento

		while(!dia){//Comprobamos que el dia introducido es valido
            System.out.println("Introduzca el dia de inicio del campamento:");
			dayI = leer.nextInt();

			if(dayI<1 || dayI>31)
			{
				System.out.println("Dia introducido no valido");
				dia=false;
			}
			else
			{
				dia=true;
			}
        }

		while(!mes){//Comprobamos que el mes introducido es valido
            System.out.println("Introduzca el mes de inicio del campamento:");
			monthI = leer.nextInt();

			if(monthI<1 || monthI>12)
			{
				System.out.println("Mes introducido no valido");
				mes=false;
			}
			else
			{
				mes=true;
			}
        }

		while(!anio){//Comprobamos que el año introducido es valido
            System.out.println("Introduzca el año de inicio del campamento:");
			yearI = leer.nextInt();

			if(yearI<2023)
			{
				System.out.println("Año introducido no valido");
				anio=false;
			}
			else
			{
				anio=true;
			}
        }

		dia=mes=anio=false;

		while(!dia){
            System.out.println("Introduzca el dia de finalizacion del campamento:");
			dayF = leer.nextInt();

			if(dayF<1 || dayF>31)
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
            System.out.println("Introduzca el mes de finalizacion del campamento:");
			monthF = leer.nextInt();

			if(monthF<1 || monthF>12)
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
            System.out.println("Introduzca el año de finalizacion del campamento:");
			yearF = leer.nextInt();

			if(yearF<2023)
			{
				System.out.println("Año introducido no valido");
				anio=false;
			}
			else
			{
				anio=true;
			}
        }

		int i=0;
		NivelEducativo nivelCampamento=NivelEducativo.infantil;
		while(i==0){//Comprobamos que el nivel introducido es valido
			System.out.println("Nivel educativo de la actividad: \n\t1.Infantil. \n\t2.Juvenil. \n\t3.Adolescente.");
			int nivel=leer.nextInt();
			nivelCampamento = NivelEducativo.infantil;
			if(nivel==1) {
				nivelCampamento=NivelEducativo.infantil;
				i=1;
			}
			else if(nivel==2) {
				nivelCampamento=NivelEducativo.juvenil;
				i=1;
			}
			else if(nivel==3)
			{
				nivelCampamento=NivelEducativo.adolescente;
				i=1;
			}
			else{
				System.out.println("El nivel introducido no es valido [1,2,3].");
			}
		}
			
		i=0;
		int maxPart=0;
		while(i==0)//Comprobamos que el numero maximo de participantes es valido
		{
			System.out.println("Numero maximo de participantes permitidos:");
			maxPart=leer.nextInt();
			if(maxPart>0) {
				i=1;
			}
			else{
				System.out.println("El numero introducido no es valido.");
			}
		}

		//campamento.setIdentificador(idCamp);
		campamento.setIdentificador(-1);
		campamento.setFechaInicio(LocalDate.of(yearI, monthI, dayI));
		campamento.setFechaFin(LocalDate.of(yearF, monthF, dayF));
		campamento.setNivelEducativo(nivelCampamento);
		campamento.setMaxAsistentes(maxPart);

		return campamento;
	}
    
}
*/