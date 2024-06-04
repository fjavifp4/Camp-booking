
package es.uco.pw.ficheros;
/** 
import es.uco.pw.business.asistente.Asistente;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.campamento.Campamento;
import es.uco.pw.business.inscripcion.*;
import es.uco.pw.business.monitor.*;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;

	

public class Fichero{

    public Fichero(){
    }

    
    @SuppressWarnings({ "static-access"})
	public ArrayList<Asistente> cargarFicheroAsistente() throws FileNotFoundException, IOException{
    	
        //Propiedades del fichero 
        Properties p = new Properties();
        //cargamos propiedades
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        //Creamos una nueva instacia de entrada salida
        File f = new File(p.getProperty("asistentes"));
        //Leemos el fichero con fr
    	FileReader fr = null;
        //lector de buffer
    	BufferedReader buffer = null;
        //el escaneador de linea
     	String lineaFichero = null;
        //coge los distintos daatos del objeto
     	String[] lineaCampos;
        //array de asistentes volcados
     	ArrayList<Asistente> arrayAsistentes = new ArrayList<Asistente>();

        //creamos la lectura del fichero
      	fr = new FileReader(f);
        //creamos el buffer
      	buffer = new BufferedReader(fr);
      	
       	Asistente Asistente;
       	int identificador=0;
      	String nombre_apellidos="";
      	LocalDate fechaNacimiento;
        //boolean requiereAtencionEspecial = true;
      	
        /**Tipo de estructura:
        * ID ; Requiere/No Requiere ; nombre_apellido ; fecha nacimiento
        
      	while((lineaFichero = buffer.readLine()) != null) {
          	Asistente = new Asistente();
          	lineaCampos = lineaFichero.split(";");

            identificador = Integer.parseInt(lineaCampos[0]);

         	if(lineaCampos[1].equals("Requiere")){
				Asistente.setRequiereAtencionEspecial(true);
           	}
         	
          	nombre_apellidos = lineaCampos[2];
            fechaNacimiento = LocalDate.parse(lineaCampos[3]);

          	Asistente.setIdentificador(identificador);
            //Asistente.setRequiereAtencionEspecial(requiereAtencionEspecial);
          	Asistente.setNombreApellidos(nombre_apellidos);
        	Asistente.setFechaNacimiento(fechaNacimiento);
          	arrayAsistentes.add(Asistente);
			//requiereAtencionEspecial = false;
      	}
      	buffer.close();
      	return arrayAsistentes;

    }

    @SuppressWarnings("static-access")
	public ArrayList<Actividad> cargarFicheroActividades()throws FileNotFoundException, IOException{
     	Properties p = new Properties();
     	p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
      	File f = new File(p.getProperty("actividades"));
       	FileReader fr = null;
       	BufferedReader buffer = null;
      	String lineaFichero = null;
      	String[] lineaCampos;
      	ArrayList<Actividad> arrayActividades = new ArrayList<Actividad>();
      	fr = new FileReader(f);
       	buffer = new BufferedReader(fr);

      	Actividad actividad;
		Monitor moni;
       	String nombre = "";
        //NivelEducativo nivel = null;
        //Horario horario = null;
        int max;
        int monitoresNecesarios;
		String[] asigandosString;
        ArrayList<Monitor> asignados = new ArrayList<Monitor>();
		//Boolean educador = false;

		/**Tipo de estructura:
        * nombre ; nivel ; horario ; max ; nMonitores ; asignado1             - asignado2- ....-asignado n
		* 												id < nombre < Educador/No como arreglamos el problema de cuando ponemos muchos monitores ssalta el error ese aver que error salta ejecutalo
        

      	while((lineaFichero = buffer.readLine()) != null) {
          	actividad = new Actividad();
          	lineaCampos = lineaFichero.split(";");
			nombre = lineaCampos[0];
			if(lineaCampos[1].equals("adolescente")){
				actividad.setNivelEducativo(NivelEducativo.adolescente);
			}
			else if(lineaCampos[1].equals("juvenil")){
				actividad.setNivelEducativo(NivelEducativo.juvenil);
			}
			else if(lineaCampos[1].equals("infantil")){
				actividad.setNivelEducativo(NivelEducativo.infantil);
			}
			if(lineaCampos[2].equals("manana")){
				actividad.setHorario(Horario.manana);
			}
			else if(lineaCampos[2].equals("tarde")){
				actividad.setHorario(Horario.tarde);
			}
			max = Integer.parseInt(lineaCampos[3]);
			monitoresNecesarios = Integer.parseInt(lineaCampos[4]);
			asigandosString = lineaCampos[4].split("-");

			//una cosa manu
			//porque en la linea de monitores necesarios/asignados esta leyendo el elemento 4-> esto que es lo que no entiendes, no es el 4to campo?
			//ademas en el bucle for, lo hace para menor o igual a 4 por lo cual por eso esta dando error cuando ponemos mjuchos monitoeres no?o no?
			for(int i =4 ; i < asigandosString.length; i++){
				String []datosMonitor;
				datosMonitor = lineaCampos[i].split("<");
				moni = new Monitor();
				moni.setIdentificador(Integer.parseInt(datosMonitor[0]));
				moni.setNombreApellidos(datosMonitor[1]);
				if(datosMonitor[2].equals("Educador"))
					moni.setEducadorEspecial(true);	
				
				//educador = false;
				asignados.add(moni);
			}

          	actividad.setNombreActividad(nombre);
			//actividad.setHorario(horario);
			actividad.setMaxParticipantes(max);
			actividad.setMonitoresNecesarios(monitoresNecesarios);
			actividad.setMonitoresAsignados(asignados);
	
	       	arrayActividades.add(actividad);
      	}
      	buffer.close();
      	return arrayActividades;   
    }

	@SuppressWarnings("static-access")
	public ArrayList<Campamento> cargarFicheroCampamento() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
		File f = new File(p.getProperty("campamentos"));
		FileReader fr = null;
		BufferedReader buffer = null;
		String lineaFichero = null;
		String[] lineaCampos;
		ArrayList<Campamento> arrayCampamento = new ArrayList<Campamento>();

		fr = new FileReader(f);
		buffer = new BufferedReader(fr);

		Campamento campamento;
		int identificador;
		LocalDate fechaInicio;
		LocalDate fechaFin;
		NivelEducativo nivelEducativo = null;
		int maxAsistentes;
		Monitor monitorResponsable = new Monitor();
		Monitor monitorEspecial= new Monitor();
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		//Boolean educador = false;

		/**Tipo de estructura:
        * ID / inicio / fin / nivel / maxAsis / Respon/                  Especi /   Actividad1             / Actividad2/ ..../ Actividad n
		* 										id < nombre < Educador/No		       datos de actividad
        
		

		while((lineaFichero = buffer.readLine()) != null) {
			campamento = new Campamento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			lineaCampos = lineaFichero.split("/");
			//Tomamos los datos
			identificador = Integer.parseInt(lineaCampos[0]);
			
			fechaInicio = LocalDate.parse(lineaCampos[1],formatter);
			System.out.println(fechaInicio);
			fechaFin = LocalDate.parse(lineaCampos[2],formatter);
			nivelEducativo = NivelEducativo.valueOf(lineaCampos[3]);
			maxAsistentes = Integer.parseInt(lineaCampos[4]);
			//Monitores 
				//Responsable
			String []datosMonitor;
			

			if (!(lineaCampos[5].equals(" ")) )
			{
				datosMonitor = lineaCampos[5].split("<");
				monitorResponsable.setIdentificador(Integer.parseInt(datosMonitor[0]));
				monitorResponsable.setNombreApellidos(datosMonitor[1]);
				if(datosMonitor[2].equals("Educador"))
				monitorResponsable.setEducadorEspecial(true);
				
			}
			
			//educador = false;
				//Especial
			if (!lineaCampos[6].equals(" "))
			{
				datosMonitor = lineaCampos[6].split("<");
				monitorEspecial.setIdentificador(Integer.parseInt(datosMonitor[0]));
				monitorEspecial.setNombreApellidos(datosMonitor[1]);
				if(datosMonitor[2].equals("Educador"))
					monitorResponsable.setEducadorEspecial(true);
				//educador = true;	
				//monitorEspecial.setEducadorEspecial(educador);
				//educador = false;
			}
			
			
			if (! (lineaCampos[7].equals(" ")) )
			{
				for(int i = 7; i < lineaCampos.length; i++){
					Actividad actividad;
					Monitor moni;
					String nombre = "";
					//NivelEducativo nivel = null;
					//Horario horario = null;
					int max;
					int monitoresNecesarios;
					String[] asigandosString;
					ArrayList<Monitor> asignados = new ArrayList<Monitor>();
					//educador = false;

					/**Tipo de estructura:
					* nombre ; nivel ; horario ; max ; nMonitores ; asignado1             - asignado2- ....-asignado n
					* 												id < nombre < Educador/No
					

				
					actividad = new Actividad();
					lineaCampos = lineaFichero.split(";");
					nombre = lineaCampos[0];
					if(lineaCampos[1].equals("adolescente")){
						actividad.setNivelEducativo(NivelEducativo.adolescente);
					}
					if(lineaCampos[1].equals("juvenil")){
						actividad.setNivelEducativo(NivelEducativo.juvenil);
					}
					if(lineaCampos[1].equals("infantil")){
						actividad.setNivelEducativo(NivelEducativo.infantil);
					}
					if(lineaCampos[2].equals("manana")){
						actividad.setHorario(Horario.manana);
					}
					if(lineaCampos[2].equals("tarde")){
						actividad.setHorario(Horario.tarde);
					}
					max = Integer.parseInt(lineaCampos[3]);
					monitoresNecesarios = Integer.parseInt(lineaCampos[4]);
					asigandosString = lineaCampos[5].split("-");
					for(int j = 5; j < asigandosString.length; j++){
						datosMonitor = lineaCampos[i].split("<");
						moni = new Monitor();
						moni.setIdentificador(Integer.parseInt(datosMonitor[0]));
						moni.setNombreApellidos(datosMonitor[1]);
						if(datosMonitor[2].equals("Educador"))
							moni.setEducadorEspecial(true);
							//educador = true;	
						//moni.setEducadorEspecial(educador);
						//educador = false;
						asignados.add(moni);
					}

					actividad.setNombreActividad(nombre);
					//actividad.setNivelEducativo(nivel);
					//actividad.setHorario(horario);
					actividad.setMaxParticipantes(max);
					actividad.setMonitoresNecesarios(monitoresNecesarios);
					actividad.setMonitoresAsignados(asignados);
			
					actividades.add(actividad);
					
				}
			}

			

			campamento.setIdentificador(identificador);
			campamento.setFechaInicio(fechaInicio);
			campamento.setFechaFin(fechaFin);
			campamento.setNivelEducativo(nivelEducativo);
			campamento.setMaxAsistentes(maxAsistentes);
			campamento.setMonitorResponsable(monitorResponsable);
			campamento.setMonitorEspecial(monitorEspecial);
			campamento.setActividades(actividades);

			arrayCampamento.add(campamento);
		}
		buffer.close();
		return arrayCampamento;
	}

	@SuppressWarnings("static-access")
	public ArrayList<InscripcionCompleta> cargarFicheroInscripcionCompleta() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
		File f = new File(p.getProperty("inscripcionCompleta"));
		FileReader fr = null;
		BufferedReader buffer = null;
		String lineaFichero = null;
		String[] lineaCampos;
		ArrayList<InscripcionCompleta> arrayInscripcionCompleta = new ArrayList<InscripcionCompleta>();

		fr = new FileReader(f);
		buffer = new BufferedReader(fr);

		int idAsistente;
		int idCampamento;
		LocalDate fecha;
		float precio;
		Asistente asistente;
		Campamento campamento;


		/**Estructura de Inscripción Completa
		 * ID asistente ; ID Campamento ; fecha ; precio
		 * 
		 
		while((lineaFichero = buffer.readLine()) != null) {
			InscripcionCompleta inscripcion = new InscripcionCompleta();
			asistente = new Asistente();
			campamento = new Campamento();
			lineaCampos = lineaFichero.split(";");
			idAsistente = Integer.parseInt(lineaCampos[0]);
			idCampamento = Integer.parseInt(lineaCampos[1]);
			fecha = LocalDate.parse(lineaCampos[2]);
			precio = Float.parseFloat(lineaCampos[3]);

			asistente.setIdentificador(idAsistente);
			campamento.setIdentificador(idCampamento);
			inscripcion.setIdAsistente(asistente);
			inscripcion.setIdCampamento(campamento);
			inscripcion.setFecha(fecha);
			inscripcion.setPrecio(precio);

			arrayInscripcionCompleta.add(inscripcion);
		}
		buffer.close();
		return arrayInscripcionCompleta;
	}
	
	@SuppressWarnings("static-access")
	public ArrayList<InscripcionParcial> cargarFicheroInscripcionParcial() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
		File f = new File(p.getProperty("inscripcionParcial"));
		FileReader fr = null;
		BufferedReader buffer = null;
		String lineaFichero = null;
		String[] lineaCampos;
		ArrayList<InscripcionParcial> arrayInscripcionParcial = new ArrayList<InscripcionParcial>();

		fr = new FileReader(f);
		buffer = new BufferedReader(fr);

		int idAsistente;
		int idCampamento;
		LocalDate fecha;
		float precio;
		Asistente asistente;
		Campamento campamento;

		/**Estructura de Inscripción Parcial
		 * ID asistente ; ID Campamento ; fecha ; precio
		 * 
		 
		while((lineaFichero = buffer.readLine()) != null) {
			InscripcionParcial inscripcion = new InscripcionParcial();
			asistente = new Asistente();
			campamento = new Campamento();
			lineaCampos = lineaFichero.split(";");
			idAsistente = Integer.parseInt(lineaCampos[0]);
			idCampamento = Integer.parseInt(lineaCampos[1]);
			fecha = LocalDate.parse(lineaCampos[2]);
			precio = Float.parseFloat(lineaCampos[3]);

			asistente.setIdentificador(idAsistente);
			campamento.setIdentificador(idCampamento);
			inscripcion.setIdAsistente(asistente);
			inscripcion.setIdCampamento(campamento);
			inscripcion.setFecha(fecha);
			inscripcion.setPrecio(precio);

			arrayInscripcionParcial.add(inscripcion);
		}
		buffer.close();
		return arrayInscripcionParcial;
	}
	
	@SuppressWarnings("static-access")
	public ArrayList<Monitor> cargarFicheroMonitor()throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
		File f = new File(p.getProperty("monitor"));
		FileReader fr = null;
		BufferedReader buffer = null;
		String lineaFichero = null;
		String[] lineaCampos;
		ArrayList<Monitor> arrayMonitor = new ArrayList<Monitor>();

		fr = new FileReader(f);
		buffer = new BufferedReader(fr);

		Monitor monitor;
		int identificador;
		String nombre_apellidos = "";
		Boolean educadorEspecial = false;
		/**Estructura de monitor:
		 * ID < nombre < Educador/No
		 

		while((lineaFichero = buffer.readLine()) != null) {
			monitor = new Monitor();
			lineaCampos = lineaFichero.split("<");
			
			identificador = Integer.parseInt(lineaCampos[0]);
			nombre_apellidos = lineaCampos[1];
			if(lineaCampos[2].equals("Educador"))
				educadorEspecial = true;
			monitor.setIdentificador(identificador);
			monitor.setNombreApellidos(nombre_apellidos);
			monitor.setEducadorEspecial(educadorEspecial);
			arrayMonitor.add(monitor);
			educadorEspecial = false;
		}
		buffer.close();
		return arrayMonitor;
	}
	
	
	@SuppressWarnings("null")
	public void escribirFicheroAsistente(ArrayList<Asistente> listaAsistentes) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
		File f = new File(p.getProperty("asistentes"));
		//ArrayList<Asistente> arrayAsistentes = null;
		//arrayAsistentes = new Fichero().cargarFicheroAsistente();

		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("");
		bw.close();

		FileWriter fw = null;
		fw = new FileWriter(f);

		ArrayList<String> lineaUsuario = new ArrayList<String>();
		Asistente asistente;

		/**Tipo de estructura:
        * ID ; Requiere/No Requiere ; nombre_apellido ; fecha nacimiento
        
		
		for(int i = 0; i < listaAsistentes.size(); i++) {
			asistente = listaAsistentes.get(i);
			lineaUsuario.add(String.valueOf(asistente.getIdentificador()));
			if(asistente.esrequiereAtencionEspecial() == true) {
				lineaUsuario.add("Requiere");
			}else {
				lineaUsuario.add("No Requiere");;
			}
			lineaUsuario.add(asistente.getNombreApellidos());
			lineaUsuario.add(asistente.getFechaNacimiento().toString());;


			fw.write(lineaUsuario.get(0) + ";" + lineaUsuario.get(1) + ";" + lineaUsuario.get(2) + ";" + lineaUsuario.get(3) +"\n");
			// fw.write(useraux.toString(););
			lineaUsuario = new ArrayList<String>();
		}
		fw.close();
	}

    @SuppressWarnings("null")
	public void escribirFicheroActividades(ArrayList<Actividad> listaActividades)throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        File f = new File( p.getProperty("actividades"));
        // ArrayList<Pista> arrayPistaDisponibles = null;
        // arrayPistaDisponibles = new Ficheros().cargarFicheroPistaDisponibles();

        BufferedWriter bw =new BufferedWriter(new FileWriter(f));
        bw.write("");
        bw.close();

        FileWriter fw = null;
        fw = new FileWriter(f);

        ArrayList<String> lineaUsuario = new ArrayList<String>();
        Actividad actividad;

        for(int i=0; i<listaActividades.size();i++) {
            actividad = listaActividades.get(i);
			lineaUsuario.add(actividad.getNombreActividad());
            if(actividad.getNivelEducativo()==NivelEducativo.adolescente) {
                lineaUsuario.add("adolescente");
            }
			else if(actividad.getNivelEducativo()==NivelEducativo.juvenil){
                lineaUsuario.add("juvenil");
            }
			else if(actividad.getNivelEducativo()==NivelEducativo.infantil){
                lineaUsuario.add("infantil");
            }
			if(actividad.getHorario()==Horario.manana) {
                lineaUsuario.add("manana");
            }
			else if(actividad.getHorario()==Horario.tarde){
                lineaUsuario.add("tarde");
            }

			lineaUsuario.add(String.valueOf(actividad.getMaxParticipantes()));
			lineaUsuario.add(String.valueOf(actividad.getMonitoresNecesarios()));
			
			ArrayList<Monitor> monitores = actividad.getMonitoresAsignados();
			String auxi = "";
			for(int j = 0; j < monitores.size(); j++){
				Monitor aux =  monitores.get(j);
				
				auxi =  String.valueOf(aux.getIdentificador()) + "<"+aux.getNombreApellidos()+"<";
				if(aux.getEducadorEspecial() == true){
					auxi = auxi + "Educador";
				}
				else{
					auxi = auxi + "No";
				}
				if(j != monitores.size() - 1)
					auxi = auxi +  "-";
			}
			lineaUsuario.add(auxi);
			System.out.println(lineaUsuario.get(4));
            fw.write(lineaUsuario.get(0) + ";" + lineaUsuario.get(1) + ";" + lineaUsuario.get(2) + ";" + lineaUsuario.get(3) + ";" + lineaUsuario.get(4) + ";" + lineaUsuario.get(5) + "\n");
			lineaUsuario = new ArrayList<String>();
        }
        fw.close();
    }

    @SuppressWarnings("null")
	public void escribirFicheroCampamento(ArrayList<Campamento> listaCampamentos)throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        File f = new File(p.getProperty("campamentos"));
		

        BufferedWriter bw =new BufferedWriter(new FileWriter(f));
        bw.write("");
        bw.close();

        FileWriter fw = null;
        fw = new FileWriter(f);

        ArrayList<String> lineaUsuario = new ArrayList<String>();
		Campamento campamento = new Campamento();

		/**Tipo de estructura:
        * ID / inicio / fin / nivel / maxAsis / Respon/                  Especi /   Actividad1             / Actividad2/ ..../ Actividad n
		* 										id < nombre < Educador/No		       datos de actividad
        

        for(int i=0; i<listaCampamentos.size();i++ ){
            campamento=listaCampamentos.get(i);

			lineaUsuario.add(String.valueOf(campamento.getIdentificador()));
			lineaUsuario.add(campamento.getFechaInicio().toString());
			lineaUsuario.add(campamento.getFechaFin().toString());
			//System.out.println(campamento.getFechaInicio());
			
			//Nivel

			 if(campamento.getNivelEducativo()==NivelEducativo.adolescente) {
                lineaUsuario.add("adolescente");
            }
			else if(campamento.getNivelEducativo()==NivelEducativo.juvenil){
                lineaUsuario.add("juvenil");
            } 
			else if(campamento.getNivelEducativo()==NivelEducativo.infantil){
                lineaUsuario.add("infantil");
            }

			lineaUsuario.add(String.valueOf(campamento.getMaxAsistentes()));

			//Monitor responsable
			String auxi = "";

			if (campamento.getMonitorResponsable()  != null)
			{
				Monitor monitor = new Monitor(campamento.getMonitorResponsable().getIdentificador(), campamento.getMonitorResponsable().getNombreApellidos(), campamento.getMonitorResponsable().getEducadorEspecial());
			
			
				auxi = String.valueOf(monitor.getIdentificador()) + "<" + monitor.getNombreApellidos() + "<" ;
				if(monitor.getEducadorEspecial() == true)
					auxi = auxi + "Educador";
				else	
					auxi = auxi + "No";
				
			
			}
			lineaUsuario.add(" ");
			
			//Monitor Especial

			if ( campamento.getMonitorEspecial() != null)
			{
				Monitor monitor2 = campamento.getMonitorEspecial();
				auxi = String.valueOf(monitor2.getIdentificador()) + "<" + monitor2.getNombreApellidos() + "<" ;
				if(monitor2.getEducadorEspecial() == true)
					auxi = auxi + "Educador";
				else
					auxi = auxi + "No";
				
				
			}
			
			lineaUsuario.add(" ");
			//Actividades
			Actividad actividad;

			ArrayList<Actividad> actividades = new ArrayList<Actividad>();
			
			if ( campamento.getActividades() != null)
			{
				actividades = campamento.getActividades();
				for(int j = 0; j < actividades.size(); j++){
				actividad = actividades.get(j);

				auxi="";
				auxi=auxi+actividad.getNombreActividad()+";";
				
				if(actividad.getNivelEducativo()==NivelEducativo.adolescente) {
					auxi+="adolescente;";
				}
				else if(actividad.getNivelEducativo()==NivelEducativo.juvenil){
					auxi+="juvenil;";
				}
				else if(actividad.getNivelEducativo()==NivelEducativo.infantil){
					auxi+="infantil;";
				}
				if(actividad.getHorario()==Horario.manana) {
					auxi=auxi + "manana;";
				}
				else if(actividad.getHorario()==Horario.tarde){
					auxi=auxi + "tarde;";
				}
				auxi=auxi+String.valueOf(actividad.getMaxParticipantes())+";";
				auxi=auxi+String.valueOf(actividad.getMonitoresNecesarios())+";";

				ArrayList<Monitor> monitores = actividad.getMonitoresAsignados();
				for(int z = 0; z < monitores.size(); z++){
					Monitor aux = monitores.get(z);
					auxi = auxi + String.valueOf(aux.getIdentificador())+"<"+ aux.getNombreApellidos() + "<";
					if(aux.getEducadorEspecial() == true){
						auxi = auxi + "Educador";
					}
					else{
						auxi = auxi + "No";
					}
					if(z != monitores.size()-1){
						auxi = auxi + "-";
					}
				}
				
				lineaUsuario.add(auxi);
				
			}

			}
			
			lineaUsuario.add(" ");
			

			String superstring = "";
			for(int r = 0; r < lineaUsuario.size(); r++){
				superstring = superstring + lineaUsuario.get(r);
				//System.out.println("lineausuariojajaxd:"+lineaUsuario.get(i));
				//System.out.println("supersringjajant:"+superstring);

				
				if(r != lineaUsuario.size()-1){
					superstring = superstring + "/";
				}
			}
			//System.out.println(lineaUsuario.get(2));
            fw.write(superstring + "\n");
			lineaUsuario = new ArrayList<String>();
            //fw.write(useraux.toString(););
        }
        fw.close();
    }

    @SuppressWarnings("null")
    public void escribirFicheroInscripcionCompleta(ArrayList<InscripcionCompleta> listaInscripciones)throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        File f = new File(p.getProperty("inscripcionCompleta"));
        // ArrayList<Usuario> arrayUsuario = null;
        // arrayUsuario = new Ficheros().cargarFicheroUsuarios();

        BufferedWriter bw =new BufferedWriter(new FileWriter(f));
        bw.write("");
        bw.close();

        FileWriter fw = null;
        fw = new FileWriter(f);

        ArrayList<String> lineaUsuario = new ArrayList<String>();
        InscripcionCompleta inscripcion;
      

        for(int i=0; i<listaInscripciones.size();i++ ){
        	inscripcion = listaInscripciones.get(i);
        	System.out.println(inscripcion.getFecha());
			lineaUsuario.add(String.valueOf(inscripcion.getIdAsistente()));
			lineaUsuario.add(String.valueOf(inscripcion.getIdCampamento()));
			lineaUsuario.add(inscripcion.getFecha().toString());
			lineaUsuario.add(String.valueOf(inscripcion.getPrecio()));

            fw.write(lineaUsuario.get(0) + ";" + lineaUsuario.get(1) + ";" + lineaUsuario.get(2) + ";" + lineaUsuario.get(3) + "\n");
			lineaUsuario = new ArrayList<String>();
        }
        fw.close();
    }
	@SuppressWarnings("null")
    public void escribirFicheroInscripcionParcial(ArrayList<InscripcionParcial> listaInscripciones)throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        File f = new File(p.getProperty("inscripcionParcial"));
        // ArrayList<Usuario> arrayUsuario = null;
        // arrayUsuario = new Ficheros().cargarFicheroUsuarios();

        BufferedWriter bw =new BufferedWriter(new FileWriter(f));
        bw.write("");
        bw.close();

        FileWriter fw = null;
        fw = new FileWriter(f);

        ArrayList<String> lineaUsuario = new ArrayList<String>();
        InscripcionParcial inscripcion;

        for(int i=0; i<listaInscripciones.size();i++ ){
			inscripcion = listaInscripciones.get(i);
        	lineaUsuario.add(String.valueOf(inscripcion.getIdAsistente()));
			lineaUsuario.add(String.valueOf(inscripcion.getIdCampamento()));
			lineaUsuario.add(inscripcion.getFecha().toString());
			lineaUsuario.add(String.valueOf(inscripcion.getPrecio()));

            fw.write(lineaUsuario.get(0) + ";" + lineaUsuario.get(1) + ";" + lineaUsuario.get(2) + ";" + lineaUsuario.get(3) + "\n");
			lineaUsuario = new ArrayList<String>();
        }
        fw.close();
    }
	@SuppressWarnings("null")
    public void escribirFicheroMonitor(ArrayList<Monitor> listaMonitores)throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileReader("src/es/uco/pw/config.properties.txt"));
        File f = new File(p.getProperty("monitor"));
        // ArrayList<Usuario> arrayUsuario = null;
        // arrayUsuario = new Ficheros().cargarFicheroUsuarios();

        BufferedWriter bw =new BufferedWriter(new FileWriter(f));
        bw.write("");
        bw.close();

        FileWriter fw = null;
        fw = new FileWriter(f);

        ArrayList<String> lineaUsuario = new ArrayList<String>();
        Monitor monitor;

        for(int i=0; i<listaMonitores.size();i++ ){
        	monitor = listaMonitores.get(i);
			lineaUsuario.add(String.valueOf(monitor.getIdentificador()));
			lineaUsuario.add(String.valueOf(monitor.getNombreApellidos()));
			if(monitor.getEducadorEspecial() == true){
				lineaUsuario.add("Educador");
			}				
			else{
				lineaUsuario.add("No");
			}

            fw.write(lineaUsuario.get(0) + "<" + lineaUsuario.get(1) + "<" + lineaUsuario.get(2) +"\n");
			lineaUsuario = new ArrayList<String>();
        }
        fw.close();
    }
}
*/