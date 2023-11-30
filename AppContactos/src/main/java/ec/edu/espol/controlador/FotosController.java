/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.modelo.Contacto;
import ec.edu.espol.modelo.Foto;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author User
 */
public class FotosController implements Initializable{
    
    private LinkedListCircular<Foto> contactoActualFotos = (LinkedListCircular)ContactosController.contactos.get(ContactosController.getContactoActual()).getFotos();
    
    private int imagenActual = 0;

    @FXML
    private ImageView imagenMostrada;

    @FXML
    private void regresarVistaContacto(MouseEvent event) {
        try {
            App.setRoot("contactos");
        } catch (IOException ex) {}
    }

    @FXML
    private void agregarFoto(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Escoger foto");
        File photoSelect = fc.showOpenDialog(null);
        
        if (photoSelect != null) {
            Image selected = new Image(photoSelect.toURI().toString());
            System.out.println(photoSelect.toURI().toString());

            contactoActualFotos.add(new Foto(photoSelect.toURI().toString()));
            
            imagenMostrada.setImage(new Image(contactoActualFotos.get(contactoActualFotos.size()-1).getUrl(),true));
            imagenActual = contactoActualFotos.size() - 1;
        }
    }

    @FXML
    private void retrocederEnListaFoto(MouseEvent event) {
        imagenActual++;
        Image im = new Image(contactoActualFotos.get(imagenActual).getUrl());
        imagenMostrada.setImage(im);
    }

    @FXML
    private void avanzarEnListaFoto(MouseEvent event) {
        imagenActual--;
        Image im = new Image(contactoActualFotos.get(imagenActual).getUrl());
        imagenMostrada.setImage(im);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenActual = 0;
        if (!contactoActualFotos.isEmpty()) {
            Image im = new Image(contactoActualFotos.get(0).getUrl(), true);
            imagenMostrada.setImage(im);
        }
    }
    
}
