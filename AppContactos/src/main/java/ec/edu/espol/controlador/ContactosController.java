/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.modelo.Contacto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ContactosController implements Initializable {

    //Change for own tda
    public static LinkedListCircular<Contacto> contactos = new LinkedListCircular<>();
    public static int numContactosAgg = 0;

    private int contactoMostrado = 0;
    
    private final String stockUrl = "src\\main\\resources\\ec\\edu\\espol\\imgs\\istockphoto-1337144146-612x612.jpg";   
    
    @FXML
    private ImageView agregar;
    @FXML
    private ImageView actualProfilePic;
    @FXML
    private Label nombresLabel;
    @FXML
    private Label apellidosLabel;
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
    @FXML
    private Label empresaLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //check if size is the same as sizeActual
        System.out.println(contactos);
        if (numContactosAgg != contactos.size()) {
            System.out.println("Hay que actualizar la lista");
            actualizarPantalla(contactos.size() - 1);
            numContactosAgg++;
            contactoMostrado = numContactosAgg - 1;
        } else {
            System.out.println("No es necesario actualizar");
            if (!contactos.isEmpty()) {
                actualizarPantalla(0);
            }
        }
        System.out.println(contactoMostrado);
        System.out.println(numContactosAgg);       
    }

    @FXML
    private void entrarAgregarContacto(MouseEvent event) {
        try {
            App.setRoot("agregarContacto");
        } catch (IOException ex) {
        }
    }

    public static void introducirEnContactos(Contacto contacto) {
        ContactosController.contactos.add(contacto);
    }

    public void actualizarPantalla(int mostrar) {       
        if (!contactos.isEmpty()) {
            Contacto contactoActual = contactos.get(mostrar);
            if (contactoActual != null) {
                System.out.println(contactoActual);
                nombresLabel.setText(contactoActual.getNombre());
                apellidosLabel.setText(contactoActual.getApellido());
                empresaLabel.setText(contactoActual.getEmpresa());

                telefonosLabel.setText(contactoActual.getNumerosTelefonicos().toString());
                correosLabel.setText(contactoActual.getEmails().toString());
                redesLabel.setText(contactoActual.getRedesSociales().toString());
                fechasLabel.setText(contactoActual.getFechasDeInteres().toString());   
                
                try {
                    String urlFoto;
                    if (!contactoActual.getFotos().isEmpty()) {                       
                        urlFoto = contactoActual.getFotos().get(0).getUrl();                       
                    } else {
                        urlFoto = "file:src/main/resources/ec/edu/espol/imgs/istockphoto-1337144146-612x612.jpg";                    
                    }
                    System.out.println(urlFoto);

                    Image im = new Image(urlFoto, true); // true para cargar de manera asíncrona
                    im.errorProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            // Manejar el error de carga de la imagen aquí
                            System.out.println("Error al cargar la imagen: " + urlFoto);
                        }
                    });                    
                    Platform.runLater(() -> {
                        actualProfilePic.setImage(im);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    // Manejar otras excepciones aquí
                }
            }
        }
    }

    @FXML
    private void contactoAnterior(MouseEvent event) {
        contactoMostrado = contactoMostrado - 1;
        System.out.println(contactoMostrado);
        actualizarPantalla(contactoMostrado);
    }

    @FXML
    private void contactoSiguiente(MouseEvent event) {
        contactoMostrado = contactoMostrado + 1;
        System.out.println(contactoMostrado);
        actualizarPantalla(contactoMostrado);
    }

    @FXML
    private void cambiarVistaLista(MouseEvent event) {
        try {
            App.setRoot("contactosLista");
        } catch (IOException ex) {}
    }

    @FXML
    private void CambiarVistaFotos(MouseEvent event) {
        if (!contactos.isEmpty()) {
            System.out.println(contactos.get(contactoMostrado).getFotos().size());
            try {
                App.setRoot("fotos");
            } catch (IOException ex) {}
        }
    }
    
    public void setImagen(Contacto c) {
        
    }
    
    ImageView cargarImagen(String url) {
        try {
            FileInputStream in = new FileInputStream(url);
            Image im = new Image(in);
            ImageView iv = new ImageView();
            iv.setImage(im);
            iv.setFitHeight(75);
            iv.setFitWidth(75);
            return iv;
        } catch (FileNotFoundException e) {
            System.out.println("No archivo existe");
        }
        return null;
    }
}
