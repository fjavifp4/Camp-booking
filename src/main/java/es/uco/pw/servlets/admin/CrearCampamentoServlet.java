package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



import es.uco.pw.business.campamento.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.display.javabean.CustomerBean;
import java.time.LocalDate;

/**
 * Servlet implementation class CrearCampamentoServlet
 */
@WebServlet(name="CrearCampamento", urlPatterns="/CrearCampamento")
public class CrearCampamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearCampamentoServlet() {
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
			DTOCampamento campamento = new DTOCampamento ();
			campamento.setNombre(request.getParameter("nombre"));
			campamento.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicio")));
			campamento.setFechaFin(LocalDate.parse(request.getParameter("fechaFin")));
			campamento.setMaxUsuarios(Integer.parseInt(request.getParameter("maxUsuarios")));
			if ( request.getParameter("nivelEducativo").equals("infantil"))
			{
				campamento.setNivelEducativo(NivelEducativo.infantil);
			
			} else if (request.getParameter("nivelEducativo").equals("juvenil")){
				campamento.setNivelEducativo(NivelEducativo.juvenil);
			} else if (request.getParameter("nivelEducativo").equals("adolescente")){
				campamento.setNivelEducativo(NivelEducativo.adolescente);
			}

			GestorCampamentos gestor = new GestorCampamentos();
			if(gestor.crearCampamento(campamento, prop, sqlprop)){
				session.setAttribute("mensajeExito", "Campamento creado con éxito");
				response.sendRedirect(request.getContextPath());
			}else{
				request.setAttribute("ErrorCamp", "false");
				request.getRequestDispatcher("/mvc/view/admin/CrearCampamentoView.jsp").forward(request, response);
			}
		}
	}
}
