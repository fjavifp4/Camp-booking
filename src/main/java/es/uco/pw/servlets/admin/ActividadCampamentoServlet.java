package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.campamento.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.display.javabean.*;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

/**
 * Servlet implementation class ActividadCampamentoServlet
 */
@WebServlet(name="ActividadCampamento", urlPatterns="/ActividadCampamento")
public class ActividadCampamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActividadCampamentoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		CustomerBean Usuario = (CustomerBean)sesion.getAttribute("Usuario");
		ActListBean actListBean = new ActListBean();
		CampListBean campListBean = new CampListBean();



		Properties prop = new Properties();
		Properties sqlprop = new Properties();

		String conf = getServletContext().getInitParameter("config");
		String sqlconf = getServletContext().getInitParameter("sql");

		InputStream input = getServletContext().getResourceAsStream(conf);
		InputStream sqlinput = getServletContext().getResourceAsStream(sqlconf);

		prop.load(input);
		sqlprop.load(sqlinput);

		
		if(Usuario == null || !Usuario.getRol().equals("admin")){
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else	{
			GestorCampamentos gestor = new GestorCampamentos();
			actListBean.setMiLista(gestor.listarActividades(prop, sqlprop));
			campListBean.setMiLista(gestor.listarCampamentos(prop, sqlprop));
			
			sesion.setAttribute("Actividades", actListBean);
			sesion.setAttribute("Campamentos", campListBean);
			request.getRequestDispatcher("/mvc/view/admin/ActividadCampamentoView.jsp").forward(request, response);
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

		
		if(Usuario == null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else	{
			
			GestorCampamentos gestor = new GestorCampamentos();
			DTOActividad actividad = gestor.actividadPorNombre(request.getParameter("actividad"), prop, sqlprop);
			DTOCampamento campamento = gestor.campamentoPorId(Integer.parseInt(request.getParameter("campamento")), prop, sqlprop);
			
			if(gestor.actividadACampamento(campamento, actividad, prop, sqlprop)){
				session.removeAttribute("Actividades");
				session.removeAttribute("Campamentos");
				session.setAttribute("mensajeExito", "Actividad asociada a campamento con exito");
				response.sendRedirect(request.getContextPath());
				
			}else{
				request.setAttribute("ErrorActCamp", "false");
				request.getRequestDispatcher("/mvc/view/admin/ActividadCampamentoView.jsp").forward(request, response);
			}
		}
	}

}
