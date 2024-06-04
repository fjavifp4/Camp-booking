package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.campamento.GestorCampamentos;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.display.javabean.ActListBean;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.MonitorListBean;


/**
 * Servlet implementation class MonitorActividadServlet
 */
@WebServlet(name="MonitorActividad", urlPatterns="/MonitorActividad")
public class MonitorActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorActividadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties prop = new Properties();
		Properties sqlprop = new Properties();

		String conf = getServletContext().getInitParameter("config");	
		String sqlconf = getServletContext().getInitParameter("sql");

		InputStream input = getServletContext().getResourceAsStream(conf);
		InputStream sqlinput = getServletContext().getResourceAsStream(sqlconf);

		prop.load(input);
		sqlprop.load(sqlinput);

		HttpSession session = request.getSession();
		CustomerBean Usuario =  (CustomerBean) session.getAttribute("Usuario");

		if(Usuario == null || !Usuario.getRol().equals("admin")) {
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		else	{
			GestorCampamentos gestor = new GestorCampamentos();
			MonitorListBean monitorListBean = new MonitorListBean();
			ActListBean actListBean = new ActListBean();

			monitorListBean.setMiLista(gestor.listarMonitores(prop, sqlprop));
			actListBean.setMiLista(gestor.listarActividades(prop, sqlprop));

			session.setAttribute("Monitores", monitorListBean);
			session.setAttribute("Actividades", actListBean);
			request.getRequestDispatcher("/mvc/view/admin/MonitorActividadView.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties prop = new Properties();
		Properties sqlprop = new Properties();

		String conf = getServletContext().getInitParameter("config");	
		String sqlconf = getServletContext().getInitParameter("sql");

		InputStream input = getServletContext().getResourceAsStream(conf);
		InputStream sqlinput = getServletContext().getResourceAsStream(sqlconf);

		prop.load(input);
		sqlprop.load(sqlinput);

		HttpSession session = request.getSession();
		CustomerBean Usuario =  (CustomerBean) session.getAttribute("Usuario");

		if( Usuario == null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		else	{
			GestorCampamentos gestor = new GestorCampamentos();



			DTOMonitor monitor = gestor.monitorPorId(Integer.parseInt(request.getParameter("monitor")), prop, sqlprop);

			DTOActividad actividad = gestor.actividadPorNombre(request.getParameter("actividad"), prop, sqlprop);

			if(gestor.monitorAActividad(monitor, actividad, prop, sqlprop)){
				session.removeAttribute("Monitores");
				session.removeAttribute("Actividades");
				session.setAttribute("mensajeExito", "Monitor asignado con éxito");
				response.sendRedirect(request.getContextPath());

			}
			else{
				request.setAttribute("ErrorActCamp", "false");
				request.getRequestDispatcher("/mvc/view/admin/MonitorActividadView.jsp").forward(request, response);
			}


		}
	}

}
