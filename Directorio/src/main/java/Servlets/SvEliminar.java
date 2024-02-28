/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.mycompany.directorio.Directorio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {

    //Se crea un direcotrio 
    Directorio eliminarContacto = new Directorio();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se obtiene el parametro enviado desde la modal
        String contactoEliminar = request.getParameter("inputEliminar");

        //se carga el archivo de persistencia
        try {
            eliminarContacto = Directorio.cargarContacto(request.getServletContext());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se llama a la funcion de eliminar contacto con el parametro recibido del directorio
        eliminarContacto.eliminarContacto(contactoEliminar);

        //Se escribe el archivo de persistencia con el contacto eliminado
        eliminarContacto.escribirContacto(eliminarContacto, request.getServletContext());

        //Se reenvia al index 
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
