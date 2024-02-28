/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.directorio;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Contacto implements Comparable<Contacto>, Serializable {

    // Se delcaran los atributos 
    
    //Identificador único del contacto
    private int id;

    //Nombre del contacto
     
    private String nombre;

    //Apellido del contacto
    private String apellido;

    //Correo electronico del contacto
    
    private String correo;

    //Direccion del contacto
    private String direccion;

    //Numero de telefono del contacto
    private String telefono;

    //Referencia al hijo izquierdo del arbol de contactos
    private Contacto izq;

    //Referencia al hijo derecho del arbol de contactos

    private Contacto der;

    //Constructor vacio
    public Contacto() {
    }

    //Constructo lleno
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

    //setters y getters
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

    //Compara dos contactos basándose en sus nombres
    @Override
    public int compareTo(Contacto o) {
        // Comparar los nombres de los contactos
        return this.nombre.compareTo(o.nombre);
    }

    /**
     * Inserta un nuevo contacto en el arbol
     * Si el contacto a insertar es menor que el contacto actual se inserta en el subarbol izquierdo
     * Si el contacto a insertar es mayor o igual que el contacto actual se inserta en el subarbol derecho
     *
     * @param nuevo El contacto a insertar en el árbol
     */
    public void insertar(Contacto nuevo) {

        if (compareTo(nuevo) > 0) {
            // Debe agregar el nuevo contacto por el subarbol izquierdo
            if (izq == null) {
                izq = nuevo;
            } else {
                izq.insertar(nuevo);
            }
        } else {
            // Debe agregar el nuevo contacto por el subarbol derecho
            if (der == null) {
                der = nuevo;
            } else {
                der.insertar(nuevo);
            }
        }
    }

    /**
     * Busca un contacto en el arbol basandose en su nombre
     *
     * @param unNombre El nombre del contacto a buscar
     * @return El contacto encontrado, o null si no se encuentra
     */
    public Contacto buscarIterativo(String unNombre) {
        Contacto p = this;
        while (p != null) {
            int comp = p.nombre.compareToIgnoreCase(unNombre);
            if (comp == 0) {
                return p;
            } else if (comp > 0) {
                p = p.izq;
            } else {
                p = p.der;
            }
        }
        return null;
    }

    /**
     * Devuelve el contacto con el nombre mas bajo en el arbol
     * @return El contacto con el nombre mas bajo
     */
    public Contacto darMenor() {
        return (izq == null) ? this : izq.darMenor();
    }

    /**
     * Elimina un contacto del arbol segun su nombre
     * @param unNombre El nombre del contacto a eliminar
     * @return El contacto eliminado, o null si el contacto no se encuentra
     */
    public Contacto eliminar(String unNombre) {
        if (this == null) {
            return null; // Nodo no encontrado, no hay nada que eliminar
        }

        if (nombre.compareToIgnoreCase(unNombre) == 0) {
            if (izq == null) {
                return der;
            }
            if (der == null) {
                return izq;
            }
            Contacto sucesor = der.darMenor();
            if(sucesor !=null){
                der = der.eliminar(sucesor.getNombre());
            sucesor.izq = izq;
            sucesor.der = der;
            return sucesor;
            }else{
                return izq;
            }
            
        } else if (nombre.compareToIgnoreCase(unNombre) > 0) {
             if (izq != null) {
            izq = izq.eliminar(unNombre);
        }
        } else {
             if (der != null) {
            der = der.eliminar(unNombre);
        }
        }
        return this;
    }

}
