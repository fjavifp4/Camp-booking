package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


import es.uco.pw.business.campamento.GestorCampamentos;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.display.javabean.CustomerBean;


@WebServlet(name="CrearMonitor", urlPatterns="/CrearMonitor")
public class CrearMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CrearMonitorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		// TODO Auto-generated method stub
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
			boolean educador = request.getParameter("educadorEspecial") != null;
			DTOMonitor monitor = new DTOMonitor(-1, request.getParameter("nombre_apellidos"), educador);
			
			GestorCampamentos gestorCampamentos = new GestorCampamentos();

			if(gestorCampamentos.crearMonitor(monitor, prop, sqlprop)){
				session.setAttribute("mensajeExito", "Monitor creado con éxito");
				//request.getRequestDispatcher("/index.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath());

			}else{
				request.setAttribute("ErrorMonitor", "false");
				request.getRequestDispatcher("/mvc/view/admin/CrearMonitorView.jsp").forward(request, response);
			}	
		}
	}
}
