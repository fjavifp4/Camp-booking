package es.uco.pw.servlets.asistente;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.campamento.*;
import es.uco.pw.display.javabean.*;
import es.uco.pw.business.actividad.NivelEducativo;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

/**
 * Servlet implementation class ConsultarCampamentosServlet
 */
@WebServlet(name="ConsultarCampamentos", urlPatterns="/ConsultarCampamentos")
public class ConsultarCampamentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarCampamentosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerBean Usuario = (CustomerBean)request.getSession().getAttribute("Usuario");
		if(Usuario == null || !Usuario.getRol().equals("asistente")){
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		CustomerBean Usuario = (CustomerBean)sesion.getAttribute("Usuario");
		CampListBean campListBean = new CampListBean();

		Properties prop = new Properties();
		Properties sqlprop = new Properties();

		String conf = getServletContext().getInitParameter("config");
		String sqlconf = getServletContext().getInitParameter("sql");

		InputStream input = getServletContext().getResourceAsStream(conf);
		InputStream sqlinput = getServletContext().getResourceAsStream(sqlconf);

		prop.load(input);
		sqlprop.load(sqlinput);

		
		if(Usuario == null || !Usuario.getRol().equals("asistente")){
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else	{
			GestorCampamentos gestor = new GestorCampamentos();
			ArrayList<DTOCampamento> campamentos = gestor.listarCampamentos(prop, sqlprop);
			// LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
            // LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
			campamentos = gestor.filtrarFechasCampamentos(LocalDate.parse(request.getParameter("fechaInicio")), LocalDate.parse(request.getParameter("fechaFin")), prop, sqlprop);
			campListBean.setMiLista(campamentos);
            if (campamentos.isEmpty()){
				request.setAttribute("ErrEmpty", "No hay campamentos disponibles para esa fecha");
				request.getRequestDispatcher("/mvc/view/asistente/ConsultarCampamentosView.jsp").forward(request, response);  
				
			}
			else
			{
				request.setAttribute("Campamentos", campListBean);
            	request.getRequestDispatcher("/mvc/view/asistente/ConsultarCampamentosView.jsp").forward(request, response);  
				
			}
          
		}
	}
}