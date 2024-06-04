package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.inscripcion.DTOInscripcion;
import es.uco.pw.business.inscripcion.GestorInscripciones;
import es.uco.pw.display.javabean.CampListBean;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.MonitorListBean;
import es.uco.pw.business.campamento.*;
import es.uco.pw.business.monitor.*;
import java.io.PrintWriter;


/**
 * Servlet implementation class MonitorCampamentoServlet
 */
@WebServlet(name="MonitorCampamento", urlPatterns="/MonitorCampamento")
public class MonitorCampamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorCampamentoServlet() {
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

		HttpSession session= request.getSession();
		CustomerBean Usuario = (CustomerBean)session.getAttribute("Usuario");

		if ( Usuario == null || !Usuario.getRol().equals("admin") )
		{
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
		{

			GestorCampamentos gestor = new GestorCampamentos();
			CampListBean Campamentos = new CampListBean();


			Campamentos.setMiLista(gestor.listarCampamentos(prop, sqlprop));
			request.setAttribute("Campamentos", Campamentos);
			request.getRequestDispatcher("/mvc/view/admin/MonitorCampamentoView.jsp").forward(request, response);
			
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
		
		GestorCampamentos gestor = new GestorCampamentos();
		CampListBean Campamentos = new CampListBean();
		MonitorListBean Monitores = new MonitorListBean();

		if(Usuario == null || !Usuario.getRol().equals("admin")) {
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else	{
			
			if((request.getParameter("campamento")!=null && !request.getParameter("campamento").isEmpty()) && (request.getParameter("monitor")==null) && (request.getParameter("monitorespecial")==null)) {
				
				
				session.setAttribute("campamento", request.getParameter("campamento"));
				
				
				Campamentos.setMiLista(gestor.listarCampamentos(prop, sqlprop));
				
				DTOCampamento campamento = gestor.campamentoPorId(Integer.parseInt(request.getParameter("campamento")), prop, sqlprop);

				if(campamento.getMonitorResponsable() == 0)
				{
					Monitores.setMiLista(gestor.monitoresPosibles(campamento, prop, sqlprop));
					request.setAttribute("Monitores", Monitores);
				}
				if(campamento.getMonitorEspecial() == 0)
				{
					MonitorListBean MonitoresEspeciales= new MonitorListBean();
					MonitoresEspeciales.setMiLista(gestor.monitoresEspecialesPosibles(campamento, prop, sqlprop));
					request.setAttribute("MonitoresEspeciales", MonitoresEspeciales);
				}
				request.setAttribute("Campamentos",Campamentos);
				request.getRequestDispatcher("/mvc/view/admin/MonitorCampamentoView.jsp").forward(request, response);
			}
			
			if(request.getParameter("monitor")!=null)
			{
				DTOCampamento campamento = gestor.campamentoPorId(Integer.parseInt((String)session.getAttribute("campamento")), prop, sqlprop);
				DTOMonitor monitor = gestor.monitorPorId(Integer.parseInt(request.getParameter("monitor")), prop, sqlprop);
				if(gestor.monitorResponsableACampamento(monitor, campamento, prop, sqlprop)){
					session.setAttribute("mensajeExito", "Monitor responsable asociado a campamento con exito");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/mvc/view/admin/MonitorCampamentoView.jsp").forward(request, response);
				}
			}
			if(request.getParameter("monitorespecial")!=null)
			{
				DTOCampamento campamento = gestor.campamentoPorId(Integer.parseInt((String)session.getAttribute("campamento")), prop, sqlprop);
				DTOMonitor monitor = gestor.monitorEspecialPorId(Integer.parseInt(request.getParameter("monitorespecial")), prop, sqlprop);
				GestorInscripciones gestorInscripciones = new GestorInscripciones();
				if (gestorInscripciones.comprobarAsistenteEspecial(campamento, prop, sqlprop))
				{
					if(gestor.monitorEspecialACampamento(monitor, campamento, prop, sqlprop)){
						session.removeAttribute("campamento");
						session.setAttribute("mensajeExito", "Monitor responsable de necesidades especiales asociado a campamento con exito");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("/mvc/view/admin/MonitorCampamentoView.jsp").forward(request, response);
					}
					
				}
				else
				{
					request.setAttribute("mensajeError", "true");
					request.getRequestDispatcher("/mvc/view/admin/MonitorCampamentoView.jsp").forward(request, response);
					

				}
			}
		}	
	}
}
