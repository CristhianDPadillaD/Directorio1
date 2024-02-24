/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;
import com.mycompany.directorio.Contacto;
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
 
    Contacto iContacto = null;
    
    public Directorio(){
        contactoRaiz= null;
}
    public void agregarContacto (int id, String nombre, String apellido, String correo, String direccion, String telefono ){
     id +=1;
     
                Contacto c = new Contacto(id, nombre, apellido, correo, direccion, telefono, null, null);  
                
                
        if( contactoRaiz == null )
            contactoRaiz = c;
        else
            contactoRaiz.insertar( c );
        
    }
    
    //insertar, buscar, listar, eliminar
    

    public static void escribirContacto(ServletContext contexto, Directorio contactico) {
         String path= contexto.getRealPath("archivito.txt");
         File arc= new File (path);
        try {
           
            FileOutputStream fw = new FileOutputStream(path);
            ObjectOutputStream pw =new ObjectOutputStream(fw);
            pw.writeObject(contactico);
            pw.close();
            System.out.println("se cargo exitosamente" + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ha ocurrido un error" + e.getMessage());
        }
    }
    
    
    
     public static Directorio cargarContacto(ServletContext contexto) {
        
      Directorio contactico = new Directorio();
      
        String p="archivito.txt";
        String path= contexto.getRealPath(p);
        File arc= new File (path);
        System.out.println("El archivo serializado se encuentra en: "+path);
         try {
               FileInputStream fileIn=new FileInputStream(path);
               ObjectInputStream ois=new ObjectInputStream(fileIn);
             
            
            contactico = (Directorio) ois.readObject();
             ois.close();
             System.out.println("Se leyo -----");
             System.out.println("buscando en: " + path);
         }catch (FileNotFoundException ex) {
                
                System.out.println("No se encontró el archivo");
            } catch (IOException ex) {
                
                System.out.println("Error al leer el archivo");
            } catch (ClassNotFoundException ex) {
                
                System.out.println("Clase no encontrada al deserializar");
            }
            
       return contactico;
    }
     
     
     
     
          public static String listarContactos (ServletContext context, HttpServletRequest request) throws IOException, ClassNotFoundException{
           //Llenamos la lista con la informacion del archivo
           Directorio listaContactos = cargarContacto(context);
           //En caso de estar vacia se crea una
            if (listaContactos == null) {
                 listaContactos = new Directorio();
            }
           String tabla="";//Variable que contiene la tabla

//           if (terminoBusqueda==null){
               tabla=listaContactos.generarTabla(listaContactos.contactoRaiz);
//           }else if (!terminoBusqueda.isEmpty() ){
//               tabla = listaLibro.tablaBusqueda(terminoBusqueda, request);
           //}
               

               return tabla;
    }
          
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
        tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-titulo=\"" + actual.getId() + "\"><i class=\"fa-solid fa-eye\"></i></a>");
        tablaHTML.append("<form action=\"SvPrestamo\" method=\"GET\" ><input type=\"text\" name=\"tiPres\" value=\"" + actual.getId() + "\" hidden><button type=\"submit\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-cart-shopping\"></i></button></form>");
        tablaHTML.append("<form action=\"SvEliminar\" method=\"GET\" ><input type=\"text\" name=\"inputEliminar\" value=\"" + actual.getId() + "\" hidden><button type=\"submit\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></button></form></td>");
        tablaHTML.append("</tr>");

        generarTablaRecursivo(actual.getDer(), tablaHTML);
    }
}

}
