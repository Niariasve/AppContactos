/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.modelo;

import java.util.List;

/**
 *
 * @author alexc
 */
public class Contacto {

    private String nombre;
    private String apellido;
    private String empresa;    
    private String direccion;
    private List<String> emails;
    private List<String> numerosTelefonicos;
    private List<String> redesSociales;
    private List<String> fechasDeInteres;
    private List<Contacto> contactosRelacionados;
    private List<Foto> Fotos;

    public Contacto(String nombre, String apellido, String empresa, String direccion, List<String> emails, List<String> numerosTelefonicos, List<String> redesSociales, List<String> fechasDeInteres, List<Contacto> contactosRelacionados, List<Foto> Fotos) {       
        this.nombre = nombre;
        this.apellido = apellido;
        this.empresa = empresa;        
        this.direccion = direccion;
        this.emails = emails;
        this.numerosTelefonicos = numerosTelefonicos;
        this.redesSociales = redesSociales;
        this.fechasDeInteres = fechasDeInteres;
        this.contactosRelacionados = contactosRelacionados;
        this.Fotos = Fotos;
    }
    

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getNumerosTelefonicos() {
        return numerosTelefonicos;
    }

    public void setNumerosTelefonicos(List<String> numerosTelefonicos) {
        this.numerosTelefonicos = numerosTelefonicos;
    }

    public List<String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public List<String> getFechasDeInteres() {
        return fechasDeInteres;
    }

    public void setFechasDeInteres(List<String> fechasDeInteres) {
        this.fechasDeInteres = fechasDeInteres;
    }

    public List<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(List<Contacto> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }

    public List<Foto> getFotos() {
        return Fotos;
    }

    public void setFotos(List<Foto> Fotos) {
        this.Fotos = Fotos;
    }
}
