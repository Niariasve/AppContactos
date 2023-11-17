/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.modelo.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ContactosListaController implements Initializable {

    @FXML
    private ScrollPane listaFP;
    @FXML
    private Label numContactosReg;
    @FXML
    private ImageView agregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Contacto> contactos = ContactosController.contactos;
        VBox medio = new VBox();
        int num = contactos.size();
        numContactosReg.setText(String.valueOf(num));

        for (int i = 0; i < num; i++) {
            BorderPane contenedor = new BorderPane();
            Label textoContacto = new Label();

            String nombreCompleto;

            if (contactos.get(i).getEmpresa() != null && !contactos.get(i).getEmpresa().isEmpty()) {

                nombreCompleto = contactos.get(i).getEmpresa();

            } else {

                nombreCompleto = contactos.get(i).getNombre() + " " + contactos.get(i).getApellido();

            }

            textoContacto = new Label(nombreCompleto);

            BorderPane.setMargin(textoContacto, new javafx.geometry.Insets(20, 0, 20, 0)); // para Margen
            contenedor.setCenter(textoContacto);
            contenedor.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            contenedor.setAlignment(textoContacto, Pos.CENTER_LEFT);

            medio.getChildren().add(contenedor);

   
            HBox.setHgrow(contenedor, Priority.ALWAYS);

        }

        listaFP.setContent(medio);

    }

    @FXML
    private void cambiarVistaContactos(MouseEvent event) {
        try {
            App.setRoot("contactos");
        } catch (IOException ex) {
        }
    }

    @FXML
    private void entrarAgregar(MouseEvent ev
    ) {
        try {
            App.setRoot("agregarContacto");
        } catch (IOException ex) {
        }
    }

}
