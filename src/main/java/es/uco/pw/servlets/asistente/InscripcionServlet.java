package es.uco.pw.servlets.asistente;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.campamento.GestorCampamentos;
import es.uco.pw.business.inscripcion.GestorInscripciones;
import es.uco.pw.business.inscripcion.RegistroTardio;
import es.uco.pw.business.usuario.DTOUsuario;
import es.uco.pw.business.usuario.GestorUsuarios;
import es.uco.pw.data.dao.campamento.DAOcampamento;
import es.uco.pw.data.dao.inscripcion.DAOinscripcion;
import es.uco.pw.display.javabean.CampListBean;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.InscripcionBean; 
import es.uco.pw.business.actividad.*;


/**
 * Servlet implementation class RegistroCampamentosServlet
 */
@WebServlet(name="Inscripcion", urlPatterns="/Inscripcion")
public class InscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscripcionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if ( Usuario == null || !Usuario.getRol().equals("asistente") )
        {
            request.setAttribute("ACL", "No tiene permitido entrar alli");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }   else
        {
            CampListBean campListBean = new CampListBean();
            GestorInscripciones gestor = new GestorInscripciones();
            ArrayList<DTOCampamento> campamentos = gestor.listarCampamentosDisponibles(prop, sqlprop);
            campListBean.setMiLista(campamentos);
            request.setAttribute("Campamentos", campListBean);
            request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerBean Usuario = (CustomerBean)session.getAttribute("Usuario");
        InscripcionBean inscripcionBean = new InscripcionBean();

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
            //recibo tipo, e id del campamento
			GestorCampamentos gestorC = new GestorCampamentos();
            GestorUsuarios gestorU = new GestorUsuarios();
            GestorInscripciones gestorI = new GestorInscripciones();
			DTOCampamento campamento = new DTOCampamento();
            DTOUsuario usuario = new DTOUsuario();
            Integer tipo = 0;
            float precio = -1;
            int numeroActividades = 0;
            
            if ( (request.getParameter("campamento") != null && request.getParameter("tipo") != null) || (inscripcionBean.getTipo() != null &&  inscripcionBean.getIdCampamento() != 0) && request.getParameter("confirm") == null){
                
                if(inscripcionBean.getIdCampamento()==null)
                {
                    inscripcionBean.setIdCampamento(Integer.parseInt(request.getParameter("campamento")));
                }
                if(inscripcionBean.getTipo()==null)
                {
                    if (request.getParameter("tipo").equals("completa"))
                    {
                        inscripcionBean.setTipo(1);
                    }
                    else if (request.getParameter("tipo").equals("parcial"))
                    {
                        inscripcionBean.setTipo(2);
                    }
                }
            
               
                //seteo el id del campamento y del usuario para el registro
                campamento = gestorC.campamentoPorId(inscripcionBean.getIdCampamento(), prop, sqlprop);
                usuario = gestorU.usuarioPorID(Usuario.getId(), prop, sqlprop);
                tipo = inscripcionBean.getTipo();
                numeroActividades = gestorI.numeroActividadesCampamento(inscripcionBean.getIdCampamento(), prop, sqlprop);
                //TARDÍA

                if ((precio = gestorI.calcularPrecio(numeroActividades, tipo , prop, sqlprop)) > 0)
                {
                    inscripcionBean.setPrecio(precio);

                    if (gestorI.funcionCalcularTardio(inscripcionBean.getIdCampamento(), prop, sqlprop)){
                        // session.setAttribute("precio", precio);
                        // session.setAttribute("estado", "tardio");
                        inscripcionBean.setEstado("tardio");
                        inscripcionBean.setTipo(tipo);
                        session.setAttribute("Inscripcion", inscripcionBean);
                        request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                    }
                    
                    
                    else if (gestorI.funcionCalcularTemprano(inscripcionBean.getIdCampamento(), prop, sqlprop)){
                        // session.setAttribute("precio", precio);
                        // session.setAttribute("estado", "temprano");
                        inscripcionBean.setEstado("temprano");
                        inscripcionBean.setTipo(tipo);
                        session.setAttribute("Inscripcion", inscripcionBean);
                        request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                    }
                    else{

                        session.removeAttribute("Inscripcion");
                        request.setAttribute("ErrRegistro", "No se ha podido realizar el registro");
                        request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                    }

                    

                }
                else{
                    request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);

                }
            }
            else{
                if ( request.getParameter("confirm") != null )
                {  
                    inscripcionBean = (InscripcionBean)session.getAttribute("Inscripcion");
                    campamento = gestorC.campamentoPorId(inscripcionBean.getIdCampamento(), prop, sqlprop);
                    usuario = gestorU.usuarioPorID(Usuario.getId(), prop, sqlprop);
                    tipo = inscripcionBean.getTipo();
                    precio = inscripcionBean.getPrecio();



                    if (request.getParameter("confirm").equals("Si"))
                    {
                       

                        if (inscripcionBean.getEstado().equals("tardio")){
                            if  (gestorI.realizarRegistroTardio(precio, campamento, usuario, tipo, prop, sqlprop))
                            {
                                session.removeAttribute("Inscripcion");
                                session.setAttribute("mensajeExito", "Inscripción realizada con éxito");
                                response.sendRedirect(request.getContextPath());

                            }
                            else
                            {

                                request.setAttribute("ErrRegistro", "No se ha podido realizar el registro");
                                session.removeAttribute("Inscripcion");

                                request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                            }
                            

                        }
                        
                        else if (inscripcionBean.getEstado().equals("temprano")){
                            if  (gestorI.realizarRegistroTemprano(precio, campamento, usuario, tipo, prop, sqlprop))
                            {

                                session.setAttribute("mensajeExito", "Inscripción realizada con éxito");
                                session.removeAttribute("Inscripcion");
                                response.sendRedirect(request.getContextPath());

                            }
                            else
                            {
                                session.removeAttribute("Inscripcion");
                                request.setAttribute("ErrRegistro", "No se ha podido realizar el registro");
                                request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                            }
                        }
                        else{
                            session.removeAttribute("Inscripcion");
                            request.setAttribute("ErrRegistro", "No se ha podido realizar el registro");
                            request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                        }
                    }
                    else if (request.getParameter("confirm").equals("No"))
                    {
                        session.removeAttribute("Inscripcion");
                        session.setAttribute("mensajeExito", "Inscripción cancelada con éxito");
                        response.sendRedirect(request.getContextPath());

                    }
                }
                else{
                    session.removeAttribute("Inscripcion");
                    request.getRequestDispatcher("/mvc/view/asistente/InscripcionView.jsp").forward(request, response);
                }
            }
                                 
            
          
        }
    }
}