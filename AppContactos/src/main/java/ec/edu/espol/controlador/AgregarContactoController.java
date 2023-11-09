/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import Exceptiones.CampoVacioException;
import ec.edu.espol.appcontactos.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    @FXML
    private FlowPane correosFP;
    @FXML
    private TextField correoTf;
    @FXML
    private FlowPane redesSocialesFP;
    @FXML
    private TextField redesSocialesTf;
    @FXML
    private FlowPane fechasFP;
    @FXML
    private TextField fechasTf;
    @FXML
    private FlowPane contactosRelacionadosFP;
    @FXML
    private TextField contactosRelacionadosTf;
    @FXML
    private Button agregarContactoRelacionadoBoton;
    @FXML
    private TextField apellidoTf;
    @FXML
    private TextField empresaTf;

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
        String nombre = nombreTf.getText();      
        String apellido = apellidoTf.getText();
        String empresa = empresaTf.getText();
        List<String> telefonos = getTelefonos();
        List<String> correos = getCorreos();
        List<String> redesSociales = getRedesSociales();
        List<String> fechas = getFechas();
        
        try {           
            camposLLenadosCorrectamente(nombre,apellido,empresa, telefonos);
            
            System.out.println(telefonos);
            System.out.println(correos);

        } catch (CampoVacioException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe ingresar al menos uno de los tres campos: nombre, apellido, empresa.");
            alert.show();
        }
    }

    @FXML
    private void cambiarFotoDePerfil(MouseEvent event) {
    }

    @FXML
    private void agregarTelefono(MouseEvent event) {             
        telefonosFP.getChildren().add(newTextField("Telefono"));
    }

    @FXML
    private void agregarCorreo(MouseEvent event) {
        correosFP.getChildren().add(newTextField("Correo Electrónico"));
    }

    @FXML
    private void agregarRedSocial(MouseEvent event) {
        redesSocialesFP.getChildren().add(newTextField("Red Social"));
    }

    @FXML
    private void agregarFecha(MouseEvent event) {
        fechasFP.getChildren().add(newTextField("Fecha"));
    }

    @FXML
    private void agregarContactoRelacionado(MouseEvent event) {
    }
    
    private void camposLLenadosCorrectamente(String nombre, String apellido, String empresa, List<String> telefonos) throws CampoVacioException{
        if (nombre.equals("") && apellido.equals("") && empresa.equals("")) {          
            throw new CampoVacioException();
        }
    }
    
    private List<String> getTelefonos() {                    
        return nodeListToStringList(telefonosFP.getChildren());
    }
    
    private List<String> getCorreos() {
        return nodeListToStringList(correosFP.getChildren());
    }
    
    private List<String> getRedesSociales() {
        return nodeListToStringList(redesSocialesFP.getChildren());
    }
    
    private List<String> getFechas() {
        return nodeListToStringList(fechasFP.getChildren());
    }
    
    private List<String> nodeListToStringList(List<Node> nodeList) {
        //Change for own tda
        /*
        Crea una lista de Strings a partir de un lista de Nodos (TextField)
        para poder extraer los datos y poder crear la instancia de contacto e introducirla en
        la lista de contactos
        */
        List<String> stringList = new ArrayList<>();
        
        for (Node node : nodeList) {
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                if (!tf.getText().equals(""))
                    stringList.add(tf.getText());
            }
        }        
        return stringList;
    }
    
    private TextField newTextField(String promptText) {
        /*
        Este metodo crea un TextField con un prompText que recibe como parámetro
        para facilitar la creación de los TextFields donde se van a introducir los datos
        */
        TextField tf = new TextField();
        tf.setPromptText(promptText);
        tf.setPrefWidth(290);
        
        return tf;
    }
    
}
