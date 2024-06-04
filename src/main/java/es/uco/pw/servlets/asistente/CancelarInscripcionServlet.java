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
import es.uco.pw.business.inscripcion.DTOInscripcion;
import es.uco.pw.business.inscripcion.GestorInscripciones;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.business.usuario.GestorUsuarios;
import es.uco.pw.display.javabean.CampListBean;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.business.actividad.*;


/**
 * Servlet implementation class CancelarInscripcionServlet
 */
@WebServlet(name="CancelarInscripcion", urlPatterns="/CancelarInscripcion")
public class CancelarInscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarInscripcionServlet() {
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

		CustomerBean Usuario = (CustomerBean)session.getAttribute("Usuario");
		
		if ( Usuario == null || !Usuario.getRol().equals("asistente"))
		{
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
		else
		{
			GestorInscripciones gestorI = new GestorInscripciones();
			GestorUsuarios gestorU = new GestorUsuarios();

			DTOUsuario usuario = gestorU.usuarioPorID(Usuario.getId(), prop, sqlprop);
			ArrayList<DTOCampamento> campamentos = gestorI.listarCampamentosCancelables(usuario, prop, sqlprop);
			CampListBean campListBean = new CampListBean();
			campListBean.setMiLista(campamentos);

			request.setAttribute("CampamentosCancelables", campListBean);
			request.getRequestDispatcher("/mvc/view/asistente/CancelarInscripcionView.jsp").forward(request, response);
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

		DTOCampamento campamento = new DTOCampamento();
        DTOUsuario usuario = new DTOUsuario();
        GestorInscripciones gestorI = new GestorInscripciones();
		if(Usuario == null || !Usuario.getRol().equals("asistente")){
			request.setAttribute("ACL", "No tiene permitido entrar alli");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
            if(request.getParameter("campamento") != null){
            campamento = gestorI.buscarCampamento(campamento, Integer.parseInt(request.getParameter("campamento")), prop, sqlprop);
            usuario.setIdentificador(Usuario.getId());
                if(campamento != null){
                    if(gestorI.cancelarInscripcion(usuario, campamento, prop, sqlprop)){
                        session.setAttribute("mensaje", "Inscripción cancelada con éxito");
						response.sendRedirect(request.getContextPath());

					}
                    else{
                        request.setAttribute("ErrCancelar", "No se ha podido cancelar la inscripción");
                        request.getRequestDispatcher("/mvc/view/asistente/CancelarInscripcionView.jsp").forward(request, response);
                    }                    
                }
            }
			else
			{
				request.setAttribute("ErrEmpty", "No hay campamentos disponibles para cancelar");
				request.getRequestDispatcher("/mvc/view/asistente/CancelarInscripcionView.jsp").forward(request, response);
			}
        }
    }
}
