/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;
import com.mycompany.directorio.Contacto;
import java.io.Serializable;
import java.util.ArrayList;
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
    public void agregarContacto (int id, String nombre, String apellido, String correo, String direccion, String telefono ){
                Contacto c = new Contacto(id, nombre, apellido, correo, direccion, telefono, null, null);
        if( contactoRaiz == null )
            contactoRaiz = c;
        else
            contactoRaiz.insertar( c );
        
    }
    
    //insertar, buscar, listar, eliminar
    
    
}
