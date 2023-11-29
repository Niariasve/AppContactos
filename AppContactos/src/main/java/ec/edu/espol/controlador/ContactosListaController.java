/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.appcontactos.Tda.MyArrayList;
import static ec.edu.espol.controlador.ContactosController.contactos;
import ec.edu.espol.modelo.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
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
    
    private static MyArrayList<Contacto> contactos = new MyArrayList<>();

    @FXML
    private ScrollPane listaFP;
    @FXML
    private Label numContactosReg;
    @FXML
    private HBox modoRevista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      

        LinkedListCircular<Contacto> c = ContactosController.contactos;
        if (c.size() != contactos.size()) {                              
            for (int i = contactos.size(); i < ContactosController.numContactosAgg; i++) {
                contactos.add(c.get(i));
            }
            contactos.sort((Contacto c1, Contacto c2) -> c1.compareTo(c2));
        }
        
        numContactosReg.setText(String.valueOf(contactos.size()));

        VBox medio = new VBox();
        medio.setSpacing(2); // ESPACIADO
        int num = contactos.size();

        for (int i = 0; i < num; i++) {
            VBox contenedor = new VBox();
            contenedor.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;"); // ESTILO DEL CONTENEDOR
            contenedor.setPrefSize(395, 40); // TAMANO DEL CONTENEDOR 
            contenedor.setAlignment(Pos.CENTER_LEFT); //ALINEACION IZQ CENTRO

            String nombreCompleto;
            if (contactos.get(i).getEmpresa() != null && !contactos.get(i).getEmpresa().isEmpty()) {
                nombreCompleto = contactos.get(i).getEmpresa();

            } else {
                nombreCompleto = contactos.get(i).getNombre() + " " + contactos.get(i).getApellido();

            }

            Label textoContacto = new Label(nombreCompleto);
            textoContacto.setFont(Font.font("Segoe UI", 14));  // FUENTE DE LETRA 
            contenedor.getChildren().add(textoContacto);
            medio.getChildren().add(contenedor);
            

            
        }

        listaFP.setContent(medio);

    }

    @FXML
    private void cambiarVistaContactos(MouseEvent event
    ) {
        try {
            App.setRoot("contactos");
        } catch (IOException ex) {
        }
    }

    @FXML
    private void entrarAgregar(MouseEvent event
    ) {
        try {
            App.setRoot("agregarContacto");
        } catch (IOException ex) {
        }
    }
    
    

    
    

}
