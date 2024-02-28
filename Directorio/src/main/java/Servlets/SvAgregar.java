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
 * @author
 */
@WebServlet(name = "SvAgregar", urlPatterns = {"/SvAgregar"})
public class SvAgregar extends HttpServlet {

    //Se crea una instancia de la clase directorio
    Directorio agregarDirect = new Directorio();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //se lee el Id de persistencia
        int identificador = Directorio.cargarUltimoIdentificador(getServletContext());

        //Se obtienen los atributos del nuevo contacto 
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");
        String celular = request.getParameter("telefono");

        //Se carga la persistencia en el directorio creado
        try {
            agregarDirect = Directorio.cargarContacto(request.getServletContext());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Condicional por si existe un contacto con un mismo nombre
        if (agregarDirect.verificarExistencia(nombre, request) == false) {

            //en caso de no existir se agrega el contacto al directorio y se incrementa el identificador en 1
            agregarDirect.agregarContacto(identificador, nombre, apellido, correo, direccion, celular);
            identificador++;

            // Se escribe para mantener persistencia tanto el contacto como el ultimo Id
            Directorio.escribirUltimoIdentificador(identificador, getServletContext());
            Directorio.escribirContacto(agregarDirect, request.getServletContext());

            //Se redirecciona al index
            response.sendRedirect("index.jsp");
        } else {
            //En caso de que existe un nombre repetido se redirecciona al jsp con un parametro que ocasiona un alert
            response.sendRedirect("index.jsp?repetido=true");

        }

        System.out.println(celular);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
