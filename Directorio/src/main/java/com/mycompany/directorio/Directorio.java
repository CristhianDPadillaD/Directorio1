/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;

import com.mycompany.directorio.Contacto;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 
 */

public class Directorio implements Serializable {


// declaramos contacto raiz
    private Contacto contactoRaiz;

    
    //Constructor de la clase Directorio, se inicializa en null
    public Directorio() {
        contactoRaiz = null;
    }

       /**
     * Agrega un nuevo contacto al directorio
     * @param id Identificador del contacto
     * @param nombre Nombre del contacto
     * @param apellido Apellido del contacto
     * @param correo Correo electrónico del contacto
     * @param direccion Direccion del contacto
     * @param telefono Numero de teléfono del contacto
     */
    public void agregarContacto(int id, String nombre, String apellido, String correo, String direccion, String telefono) {

        Contacto c = new Contacto(id, nombre, apellido, correo, direccion, telefono, null, null);

        if (contactoRaiz == null) {
            contactoRaiz = c;
        } else {
            contactoRaiz.insertar(c);
        }

    }
     /**
     * Verifica si el directorio tiene algún contacto
     * @return true si el directorio no esta vacio y false en caso contrario
     */
    public boolean verificar() {
        return contactoRaiz != null;

    }
   /**
     * Elimina un contacto del directorio
     * @param nombre Nombre del contacto a eliminar
     */
    public void eliminarContacto(String nombre) {
        contactoRaiz = contactoRaiz.eliminar(nombre);

    }
     /**
     * Escribe el directorio de contactos en un archivo
     * @param contactos Directorio de contactos a escribir
     * @param context Contexto del servlet
     * @throws FileNotFoundException Si no se encuentra el archivo
     * @throws IOException Si ocurre un error de entrada/salida
     */
    public static void escribirContacto(Directorio contactos, ServletContext context) throws FileNotFoundException, IOException {

        String rutaRelativa = "/data/contactosAgregados.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        try (FileOutputStream fos = new FileOutputStream(archivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(contactos);
        } catch (IOException e) {
            System.out.println("Error al escribir");
        }
    }
   /**
     * Carga el directorio de contactos desde un archivo
     * @param context Contexto del servlet
     * @return El directorio de contactos cargado desde el archivo
     * @throws IOException Si ocurre un error de entrada o salida
     * @throws ClassNotFoundException Si la clase no se encuentra al leer el objeto serializado
     */
    public static Directorio cargarContacto(ServletContext context) throws IOException, ClassNotFoundException {

        Directorio contactos = new Directorio();

        String rutaRelativa = "/data/contactosAgregados.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        if (archivo.exists() && archivo.isFile()) {
            try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) {

                contactos = (Directorio) ois.readObject();
            } catch (EOFException e) {
                System.out.println("El archivo está vacío");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo");
            }
        } else {
            System.out.println("El archivo no existe");
        }

        return contactos;
    }
       /**
     * Obtiene una tabla de contactos del directorio
     * Dependiendo de el parametro de busqueda cambia la tabla
     * @param context Contexto del servlet
     * @param request Solicitud HTTP
     * @param terminoBusqueda Término de búsqueda opcional
     * @return Una cadena HTML que representa la tabla de contactos
     * @throws IOException Si ocurre un error de entrada/salida
     * @throws ClassNotFoundException Si la clase no se encuentra al leer el objeto serializado
     */
        public static String listarContactos(ServletContext context, HttpServletRequest request, String terminoBusqueda) throws IOException, ClassNotFoundException {
            //Llenamos la lista con la informacion del archivo
            Directorio listaContactos = cargarContacto(context);
            //En caso de estar vacia se crea una
            if (listaContactos == null) {
                listaContactos = new Directorio();
            }
            String tabla = "";//Variable que contiene la tabla

            if (terminoBusqueda == null) {
                tabla = listaContactos.generarTabla(listaContactos.contactoRaiz);
            } else if (!terminoBusqueda.isEmpty()) {
                tabla = listaContactos.generarTablaBusqueda(listaContactos.contactoRaiz, terminoBusqueda);
            }

            return tabla;
        }

        /**
     * Genera una tabla HTML de contactos a partir de un nodo raíz
     * @param raiz Nodo raíz del árbol de contactos
     * @return Una cadena HTML que representa la tabla de contactos
     */
        public String generarTabla(Contacto raiz) {
            StringBuilder tablaHTML = new StringBuilder();
            generarTablaRecursivo(raiz, tablaHTML);
            return tablaHTML.toString();
        }
        
     /**
     * Método de tipo privado que genera una tabla HTML de contactos
     * @param actual Nodo actual del árbol
     * @param tablaHTML StringBuilder que almacena la tabla HTML
     */
        private void generarTablaRecursivo(Contacto actual, StringBuilder tablaHTML) {
            if (actual != null) {
                // Recorrido en orden: primero el hijo izquierdo, luego el nodo actual, y finalmente el hijo derecho
                generarTablaRecursivo(actual.getIzq(), tablaHTML);

                // Agregar el nodo actual a la tabla HTML
                tablaHTML.append("<tr>");
                tablaHTML.append("<td>").append(actual.getId()).append("</td>");
                tablaHTML.append("<td>").append(actual.getNombre()).append("</td>");
                tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-nombre=\"" + actual.getNombre() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
                tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminarTareaModal\" data-nombre=\"" + actual.getNombre() + "\"><i class=\"fa-solid fa-trash\"></i></a>");
                tablaHTML.append("</tr>");

                generarTablaRecursivo(actual.getDer(), tablaHTML);
            }
        }
  /**
     * Genera una tabla HTML de contactos basada en un parametrode busqueda
     * @param raiz Nodo raíz del árbol de contactos
     * @param terminoBusqueda Término de búsqueda
     * @return Una cadena HTML que representa la tabla de contactos
     */
        public String generarTablaBusqueda(Contacto raiz, String terminoBusqueda) {
            StringBuilder tablaHTML = new StringBuilder();
            generarTablaRecursivaBusqueda(raiz, tablaHTML, terminoBusqueda);
            return tablaHTML.toString();
        }

          /**
     * Método privado que genera una tabla HTML de contactos basada en un parametro de búsqueda
     * @param actual Nodo actual del árbol
     * @param tablaHTML StringBuilder que almacena la tabla HTML
     * @param terminoBusqueda Término de búsqueda
     */

        private void generarTablaRecursivaBusqueda(Contacto actual, StringBuilder tablaHTML, String terminoBusqueda) {
            if (actual != null) {

                // Comparar el nombre del contacto con el término de búsqueda (ignorando mayúsculas y minúsculas)
                if (actual.getNombre().equalsIgnoreCase(terminoBusqueda)) {
                    // Si hay coincidencia, agregar el nodo actual a la tabla HTML
                    tablaHTML.append("<tr>");
                    tablaHTML.append("<td>").append(actual.getId()).append("</td>");
                    tablaHTML.append("<td>").append(actual.getNombre()).append("</td>");
                    tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-nombre=\"" + actual.getNombre() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
                    tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminarTareaModal\" data-nombre=\"" + actual.getNombre() + "\"><i class=\"fa-solid fa-trash\"></i></a>");
    //            tablaHTML.append("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\"" + actual.getId() + "\" hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></td>");
                    tablaHTML.append("</tr>");
                }

                // Recorrer el subárbol izquierdo nuevamente
                generarTablaRecursivaBusqueda(actual.getIzq(), tablaHTML, terminoBusqueda);  // Este llamado duplicado podría ser el problema

                // Recorrer el subárbol derecho
                generarTablaRecursivaBusqueda(actual.getDer(), tablaHTML, terminoBusqueda);
            }
        }
        
      /**
     * Busca un contacto en el directorio por su nombre
     * @param nombre Nombre del contacto a buscar
     * @return El contacto encontrado, o null si no se encuentra
     */
        public Contacto buscarContacto(String nombre) {
            return contactoRaiz == null ? null : contactoRaiz.buscarIterativo(nombre);
        }
        
       /**
     * Verifica si un contacto existe en el directorio
     * @param nombre Nombre del contacto a verificar
     * @param request Solicitud HTTP
     * @return true si el contacto existe o false si no existe
     */
        public boolean verificarExistencia(String nombre, HttpServletRequest request) {
            if (contactoRaiz != null) {

                if (nombre != null) {
                    if (contactoRaiz.buscarIterativo(nombre) != null) {
                        return true;

                    } else {
                        return false;
                    }
                }

            } else {
                return false;
            }
            return false;
        }
       /**
     * Escribe el ultimo Id en un archivo
     * @param ultimoIdentificador Ultimo id a escribir
     * @param context Contexto del servlet
     */
        public static void escribirUltimoIdentificador(int ultimoIdentificador, ServletContext context) {
            String rutaRelativa = "/data/ultimoIdentificador.txt";
            String rutaAbsoluta = context.getRealPath(rutaRelativa);
            File archivo = new File(rutaAbsoluta);

            try (FileOutputStream fos = new FileOutputStream(archivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(ultimoIdentificador);
            } catch (IOException e) {
                System.out.println("Error al escribir el último identificador");
            }
        }
        /**
         * Carga el archivo de ultimo Id
         * @param context Contexto del servlet
         * @return el numero del ultimo id
         */
        public static int cargarUltimoIdentificador(ServletContext context) {
            int ultimoIdentificador = 1; // Valor por defecto

            String rutaRelativa = "/data/ultimoIdentificador.txt";
            String rutaAbsoluta = context.getRealPath(rutaRelativa);
            File archivo = new File(rutaAbsoluta);

            if (archivo.exists() && archivo.isFile()) {
                try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) {
                    ultimoIdentificador = (int) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Error al cargar el último identificador");
                }
            }

            return ultimoIdentificador;
        }

    }
