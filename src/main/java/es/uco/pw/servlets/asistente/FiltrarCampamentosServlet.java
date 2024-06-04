package es.uco.pw.servlets.asistente;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.campamento.GestorCampamentos;
import es.uco.pw.business.inscripcion.GestorInscripciones;
import es.uco.pw.display.javabean.*;
import es.uco.pw.business.actividad.*;


/**
 * Servlet implementation class FiltrarCampamentosServlet
 */
@WebServlet(name="FiltrarCampamentos", urlPatterns="/FiltrarCampamentos")
public class FiltrarCampamentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarCampamentosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		CustomerBean Usuario = (CustomerBean)sesion.getAttribute("Usuario");

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
		CampListBean CampamentosNvl = new CampListBean();
		CampListBean CampamentosMin = new CampListBean();

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
			GestorCampamentos gestorC = new GestorCampamentos();
            GestorInscripciones gestorI = new GestorInscripciones();
			ArrayList<DTOCampamento> campamentos = null;
            
			if(request.getParameter("nivelEducativo") != null){
				campamentos = gestorC.filtrarNivelCampamentos(NivelEducativo.valueOf(request.getParameter("nivelEducativo")),prop, sqlprop);
				CampamentosNvl.setMiLista(campamentos);
				request.setAttribute("CampamentosNvl", CampamentosNvl);
            }
            else if(request.getParameter("minPlazas") != null){
				campamentos = gestorI.filtrarPlazasCampamentos(Integer.parseInt(request.getParameter("minPlazas")),prop, sqlprop);
				CampamentosMin.setMiLista(campamentos);
				request.setAttribute("CampamentosMin", CampamentosMin);
			} 
			request.getRequestDispatcher("/mvc/view/asistente/FiltrarCampamentosView.jsp").forward(request, response);
		}
	}

}
