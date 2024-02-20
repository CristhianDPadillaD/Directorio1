/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;
import com.mycompany.directorio.Contacto;
/**
 *
 * @author cuati
 */
public class Directorio {
        private Contacto contactoRaiz;
 
    
    public Directorio(){
        contactoRaiz= null;
}
    public void agregarContacto (int id, String nombre, String apellido, String correo, String direccion, String telefono, Contacto izq, Contacto der ){
                Contacto c = new Contacto(id, nombre, apellido, correo, direccion, telefono, izq, der);
        if( contactoRaiz == null )
            contactoRaiz = c;
        else
            contactoRaiz.insertar( c );
        
    }
    
}
