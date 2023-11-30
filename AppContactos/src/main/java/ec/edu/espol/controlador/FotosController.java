/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Contacto;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author User
 */
public class FotosController {
    
    private Contacto contactoActual = ContactosController.contactos.get(ContactosController.getContactoActual());

    @FXML
    private ImageView imagenMostrada;

    @FXML
    private void regresarVistaContacto(MouseEvent event) {
    }

    @FXML
    private void agregarFoto(MouseEvent event) {
    }

    @FXML
    private void retrocederEnListaFoto(MouseEvent event) {
    }

    @FXML
    private void avanzarEnListaFoto(MouseEvent event) {
    }
    
}
