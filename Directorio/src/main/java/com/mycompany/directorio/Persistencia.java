/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;

import java.util.ArrayList;
import com.mycompany.directorio.Contacto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;

/**
 *
 * @author ADMIN
 */
public class Persistencia {
    
    
    
    public static void escribirArchivo(ServletContext contexto, Directorio contactico)throws FileNotFoundException{
        File archivo = new File ("Reporte.txt");
        PrintWriter pluma = new PrintWriter (archivo);
            
            pluma.println(contactico);
        }
        
        
    public static void leerArchivo (ServletContext context) throws FileNotFoundException{
        File archivo = new File ("Reporte.txt");
        
        
    }
  
  
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
}
