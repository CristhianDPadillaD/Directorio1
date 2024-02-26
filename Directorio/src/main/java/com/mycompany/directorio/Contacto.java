/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Contacto  implements Comparable<Contacto>, Serializable{
    
    private int id; 
    
    private String nombre;
    
    private String apellido;
    
    private String correo;
    
    private String direccion;
    
    private String telefono; 
    
    private Contacto izq;
    
    private Contacto der;

    
    //Constructor vacio
    public Contacto() {
    }
    
    

    public Contacto(int id, String nombre, String apellido, String correo, String direccion, String telefono, Contacto izq, Contacto der) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.izq = izq;
        this.der = der;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Contacto getIzq() {
        return izq;
    }

    public void setIzq(Contacto izq) {
        this.izq = izq;
    }

    public Contacto getDer() {
        return der;
    }

    public void setDer(Contacto der) {
        this.der = der;
    }

    @Override
    public int compareTo(Contacto o) {
     // Comparar los nombres de los contactos
    return this.nombre.compareTo(o.nombre);
    }
    
      public void insertar( Contacto nuevo ) 
    {
      

        if( compareTo( nuevo ) > 0 )
        {
            // Debe agregar el nuevo contacto por el sub�rbol izquierdo
            if( izq == null )
                izq = nuevo;
            else
                izq.insertar( nuevo );
        }
        else
        {
            // Debe agregar el nuevo contacto por el sub�rbol derecho
            if( der == null )
                der = nuevo;
            else
                der.insertar( nuevo );
        }
    }

//    
//         public Contacto buscar( String unNombre )
//    {
//        if( nombre.compareToIgnoreCase( unNombre ) == 0 )
//            return this;
//        else if( nombre.compareToIgnoreCase( unNombre ) > 0 )
//            return ( izq == null ) ? null : izq.buscar( unNombre );
//        else
//            return ( der == null ) ? null : der.buscar( unNombre );
//    }

          public Contacto buscarIterativo( String unNombre )
    {
        Contacto p = this;
        while( p != null )
        {
            int comp = p.nombre.compareToIgnoreCase( unNombre );
            if( comp == 0 )
                return p;
            else if( comp > 0 )
                p = p.izq;
            else
                p = p.der;
        }
        return null;
    }
   
    
}
