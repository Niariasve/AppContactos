/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarContactoController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Button agregar;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button agregarProfilePicButton;
    @FXML
    private TextField nombreTf;
    @FXML
    private FlowPane telefonosFP;
    @FXML
    private TextField telefonoTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salirDeAgregarContacto(MouseEvent event) {
        try {
            App.setRoot("contactos");
        } catch (IOException ex) {}
    }

    @FXML
    private void agregarContacto(MouseEvent event) {
    }

    @FXML
    private void cambiarFotoDePerfil(MouseEvent event) {
    }

    @FXML
    private void agregarTelefono(MouseEvent event) {
    }
    
}
