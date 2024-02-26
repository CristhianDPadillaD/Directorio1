/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.mycompany.directorio.Contacto;
import com.mycompany.directorio.Directorio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SvMostrar", urlPatterns = {"/SvMostrar"})
public class SvMostrar extends HttpServlet {
Directorio mostrarContacto = new Directorio();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
    }


    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String identificador = request.getParameter("id");
    System.out.println(identificador);

    try {
        mostrarContacto = Directorio.cargarContacto(request.getServletContext());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(SvMostrar.class.getName()).log(Level.SEVERE, null, ex);
    }

    Contacto muestra = mostrarContacto.buscarContacto(identificador);

    response.setContentType("text/html"); // Mover aquí la configuración del tipo de contenido y la codificación de caracteres

    String contactoHtml = "<h2>ID:" + muestra.getId() + "</h2>" +
            "<p>Nombre: " + muestra.getNombre() + "</p>" +
            "<p>Apellido: " + muestra.getApellido() + "</p>" +
            "<p>Direccion: " + muestra.getDireccion() + "</p>" +
            "<p>Telefono: " + muestra.getTelefono()+ "</p>" +
            "<p>Correo: " + muestra.getCorreo()+ "</p>";

    response.getWriter().write(contactoHtml);

    processRequest(request, response);
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
