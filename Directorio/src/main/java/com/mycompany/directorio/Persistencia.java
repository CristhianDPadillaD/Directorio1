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
import javax.servlet.ServletContext;

/**
 *
 * @author ADMIN
 */
public class Persistencia {
    
    ArrayList <Contacto> contactico = new ArrayList<>();
  
    public static void escribirContacto(ServletContext contexto, ArrayList<Contacto> contactico) {
         String path= contexto.getRealPath("data.txt");
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
    
     public static ArrayList<Contacto> cargarContacto(ServletContext contexto) {
        
        ArrayList<Contacto> contactico = new ArrayList<>();
        String p="data.txt";
        String path= contexto.getRealPath(p);
        File arc= new File (path);
        System.out.println("El archivo serializado se encuentra en: "+path);
         try {
               FileInputStream fileIn=new FileInputStream(path);
               ObjectInputStream ois=new ObjectInputStream(fileIn);
             
            
             contactico = (ArrayList<Contacto>) ois.readObject();
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
