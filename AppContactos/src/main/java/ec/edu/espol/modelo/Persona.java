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
public class Persona extends Contacto{
    
    public Persona(String nombre, String numeroTelefono, String direccion, List<String> emails, List<String> numerosTelefonicos, List<String> redesSociales, List<Foto> Fotos, List<String> fechasDeInteres, List<Contacto> contactosRelacionados) {
        super(nombre, numeroTelefono, direccion, emails, numerosTelefonicos, redesSociales, Fotos, fechasDeInteres, contactosRelacionados);
    }
    
}
