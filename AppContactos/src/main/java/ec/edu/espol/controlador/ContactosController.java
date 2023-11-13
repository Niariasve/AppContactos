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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author User
 */

public class ContactosController implements Initializable {
    
    //Change for own tda
    public static List<Contacto> contactos = new ArrayList<>();
    public static int numContactosAgg = 0;
    
    private int contactoMostrado = 0;

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
            if (contactos.size() > 0) {
                actualizarPantalla(0);
            }
        }
        System.out.println(contactoMostrado);
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
    
    public void actualizarPantalla(int mostrar) {
        Contacto contactoActual = contactos.get(mostrar);
        System.out.println(contactoActual);
        nombresLabel.setText(contactoActual.getNombre());
        apellidosLabel.setText(contactoActual.getApellido());
        empresaLabel.setText(contactoActual.getEmpresa());
        
        telefonosLabel.setText(contactoActual.getNumerosTelefonicos().toString());
        correosLabel.setText(contactoActual.getEmails().toString());
        redesLabel.setText(contactoActual.getRedesSociales().toString());
        fechasLabel.setText(contactoActual.getFechasDeInteres().toString());
    }

    @FXML
    private void contactoAnterior(MouseEvent event) {
        /*
        Este metodo actualmente lanzará excepciones de ArrayOutOfBoundsException
        esta pensado para en cuando ya este implementada nuestra clase linkedlistcircular
        funcione correctamnte y no lance ninguna excepcion.
        */
        contactoMostrado = contactoMostrado - 1;
        System.out.println(contactoMostrado);
        actualizarPantalla(contactoMostrado);
    }

    @FXML
    private void contactoSiguiente(MouseEvent event) {
        /*
        Este metodo actualmente lanzará excepciones de ArrayOutOfBoundsException
        esta pensado para en cuando ya este implementada nuestra clase linkedlistcircular
        funcione correctamnte y no lance ninguna excepcion.
        */
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
}
