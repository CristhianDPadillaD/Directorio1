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
 * @author cuati
 */


public class Directorio implements Serializable{
    ArrayList <Contacto> misContactos = new ArrayList <>(); 
    
    private Contacto contactoRaiz;
 
    
    
    public Directorio(){
        contactoRaiz= null;
}
    /**
     * 
     * @param id
     * @param nombre
     * @param apellido
     * @param correo
     * @param direccion
     * @param telefono 
     */
    public void agregarContacto (int id, String nombre, String apellido, String correo, String direccion, String telefono ){
    
                Contacto c = new Contacto(id, nombre, apellido, correo, direccion, telefono, null, null);  
                
                
        if( contactoRaiz == null )
            contactoRaiz = c;
        else
            contactoRaiz.insertar( c );
        
    }
    
    public boolean verificar (){
        return contactoRaiz !=null;
        
    }
    
   
    
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
     
     
          public static String listarContactos (ServletContext context, HttpServletRequest request, String terminoBusqueda) throws IOException, ClassNotFoundException{
           //Llenamos la lista con la informacion del archivo
           Directorio listaContactos = cargarContacto(context);
           //En caso de estar vacia se crea una
            if (listaContactos == null) {
                 listaContactos = new Directorio();
            }
           String tabla="";//Variable que contiene la tabla

           if (terminoBusqueda==null){
               tabla=listaContactos.generarTabla(listaContactos.contactoRaiz);
           }else if (!terminoBusqueda.isEmpty() ){
               tabla = listaContactos.generarTablaBusqueda(listaContactos.contactoRaiz, terminoBusqueda);
           }
               

               return tabla;
    }
          /**
           * 
           * @param raiz
           * @return 
           */
       public String generarTabla(Contacto raiz) {
    StringBuilder tablaHTML = new StringBuilder();
    generarTablaRecursivo(raiz, tablaHTML);
    return tablaHTML.toString();
}

private void generarTablaRecursivo(Contacto actual, StringBuilder tablaHTML) {
    if ( actual!= null) {
        // Recorrido en orden: primero el hijo izquierdo, luego el nodo actual, y finalmente el hijo derecho
        generarTablaRecursivo(actual.getIzq(), tablaHTML);
        
        // Agregar el nodo actual a la tabla HTML
        tablaHTML.append("<tr>");
        tablaHTML.append("<td>").append(actual.getId()).append("</td>");
        tablaHTML.append("<td>").append(actual.getNombre()).append("</td>");
        tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-id=\"" + actual.getNombre() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
        tablaHTML.append("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\"" + actual.getId() + "\" hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></td>");
        tablaHTML.append("</tr>");

        generarTablaRecursivo(actual.getDer(), tablaHTML);
    }
}


       public String generarTablaBusqueda(Contacto raiz, String terminoBusqueda) {
    StringBuilder tablaHTML = new StringBuilder();
           generarTablaRecursivaBusqueda(raiz, tablaHTML, terminoBusqueda);
    return tablaHTML.toString();
}

private void generarTablaRecursivaBusqueda(Contacto actual, StringBuilder tablaHTML, String terminoBusqueda) {
    if (actual != null) {
        
        
        // Comparar el nombre del contacto con el término de búsqueda (ignorando mayúsculas y minúsculas)
        if (actual.getNombre().equalsIgnoreCase(terminoBusqueda)) {
            // Si hay coincidencia, agregar el nodo actual a la tabla HTML
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(actual.getId()).append("</td>");
            tablaHTML.append("<td>").append(actual.getNombre()).append("</td>");
            tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + actual.getId() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
            tablaHTML.append("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\"" + actual.getId() + "\" hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></td>");
            tablaHTML.append("</tr>");
        }
        
        // Recorrer el subárbol izquierdo nuevamente
        generarTablaRecursivaBusqueda(actual.getIzq(), tablaHTML, terminoBusqueda);  // Este llamado duplicado podría ser el problema
        
        // Recorrer el subárbol derecho
        generarTablaRecursivaBusqueda(actual.getDer(), tablaHTML, terminoBusqueda);
    }
}





/**
 * 
 * @param nombre
 * @return 
 */
    public Contacto buscarContacto( String nombre )
    {
        return contactoRaiz == null ? null : contactoRaiz.buscarIterativo(nombre );
    }

    
    public boolean verificarExistencia (String nombre,HttpServletRequest request,ServletContext context){
      if (contactoRaiz != null){
          
          if (contactoRaiz.buscarIterativo(nombre)!=null){
              return true;
              
          }else{
              return false;
          }
       
      }else{
          return false;
      }
    }
    
 public static void escribirUltimoIdentificador(int ultimoIdentificador, ServletContext context) {
    String rutaRelativa = "/data/ultimoIdentificador.txt";
    String rutaAbsoluta = context.getRealPath(rutaRelativa);
    File archivo = new File(rutaAbsoluta);

    try (FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(ultimoIdentificador);
    } catch (IOException e) {
        System.out.println("Error al escribir el último identificador");
    }
}

public static int cargarUltimoIdentificador(ServletContext context) {
    int ultimoIdentificador = 1; // Valor por defecto

    String rutaRelativa = "/data/ultimoIdentificador.txt";
    String rutaAbsoluta = context.getRealPath(rutaRelativa);
    File archivo = new File(rutaAbsoluta);

    if (archivo.exists() && archivo.isFile()) {
        try (FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ultimoIdentificador = (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el último identificador");
        }
    }

    return ultimoIdentificador;
}



}
