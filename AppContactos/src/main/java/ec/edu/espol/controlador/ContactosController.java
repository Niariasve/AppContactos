/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.modelo.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ContactosController implements Initializable {
    
    public static List<Contacto> contactos;

    @FXML
    private Button editar;
    @FXML
    private ImageView agregar;
    @FXML
    private ImageView actualProfilePic;
    @FXML
    private Label nombresLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label EmpresaLabel;
    @FXML
    private Label telefonosLabel;
    @FXML
    private Label correosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label redesLabel;
    @FXML
    private Label fechasLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Change for own tda
        contactos = new ArrayList<>();
    }    

    @FXML
    private void entrarAgregarContacto(MouseEvent event) {
        try {
            App.setRoot("agregarContacto");
        } catch (IOException ex) {}
    }
    
    public static void introducirEnContactos(Contacto contacto) {
        ContactosController.contactos.add(contacto);
    }
}
