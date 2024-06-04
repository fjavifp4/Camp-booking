package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.campamento.GestorCampamentos;

/**
 * Servlet implementation class CrearActividadServlet
 */
@WebServlet(name="CrearActividad", urlPatterns="/CrearActividad")
public class CrearActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearActividadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerBean Usuario = (CustomerBean)session.getAttribute("Usuario");
		
		
		if(Usuario == null || !Usuario.getRol().equals("admin")) {
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerBean Usuario = (CustomerBean)session.getAttribute("Usuario");
		
		Properties prop = new Properties();
		Properties sqlprop = new Properties();

		String conf = getServletContext().getInitParameter("config");
		String sqlconf = getServletContext().getInitParameter("sql");

		InputStream input = getServletContext().getResourceAsStream(conf);
		InputStream sqlinput = getServletContext().getResourceAsStream(sqlconf);

		prop.load(input);
		sqlprop.load(sqlinput);

		
		if(Usuario == null || !Usuario.getRol().equals("admin")) {
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			NivelEducativo nivelEducativo = null;
			if(request.getParameter("nivelEducativo").equals("infantil")){
				nivelEducativo = NivelEducativo.infantil;
			}else if(request.getParameter("nivelEducativo").equals("juvenil")){
				nivelEducativo = NivelEducativo.juvenil;
			}else if (request.getParameter("nivelEducativo").equals("adolescente")){
				nivelEducativo = NivelEducativo.adolescente;
			}
			Horario horario = null;
			if(request.getParameter("horario").equals("manana")){
				horario = Horario.manana;
			}else{
				horario = Horario.tarde;
			}
			GestorCampamentos gestorCampamentos = new GestorCampamentos();
			
			
			
			DTOActividad actividad = new DTOActividad(request.getParameter("nombreActividad"), nivelEducativo,horario, Integer.parseInt(request.getParameter("maxParticipantes")), Integer.parseInt(request.getParameter("monitoresNecesarios")));
			if(gestorCampamentos.crearActividad(actividad, prop, sqlprop)){
				session.setAttribute("mensajeExito", "Actividad creada con exito");
				response.sendRedirect(request.getContextPath());
			}else{
				request.setAttribute("ErrorAct", "false");
				request.getRequestDispatcher("/mvc/view/admin/CrearActividadView.jsp").forward(request, response);
			}
		}
	}
}
