/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import Exceptiones.CampoVacioException;
import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.appcontactos.Tda.MyArrayList;
import ec.edu.espol.modelo.Contacto;
import ec.edu.espol.modelo.Foto;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

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
    @FXML
    private TextField direccionTf;
    @FXML
    private ComboBox cbxcontacto;

    private String Imagen;

    LinkedListCircular<Foto> e;
    MyArrayList<Contacto> contactosRelacionados;
    
    public static boolean editarFlag = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        e = new LinkedListCircular<>();
        contactosRelacionados = new MyArrayList<>();
        try {
            cargarCombo();
            cbxcontacto.setValue("Persona");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (editarFlag) {
            Contacto c = ContactosController.contactos.get(ContactosController.getContactoActual());
            nombreTf.setPromptText(c.getNombre());
            apellidoTf.setPromptText(c.getApellido());
            empresaTf.setPromptText(c.getEmpresa());
            direccionTf.setPromptText(c.getDireccion());
            
            if (!c.getNumerosTelefonicos().isEmpty()) {
                telefonoTf.setPromptText(c.getNumerosTelefonicos().get(0));
                for (int i = 1; i < c.getNumerosTelefonicos().size(); i++) {
                    telefonosFP.getChildren().add(newTextField(c.getNumerosTelefonicos().get(i)));
                }
            }
            
            if (!c.getEmails().isEmpty()) {
                correoTf.setPromptText(c.getEmails().get(0));
                for (int i = 1; i < c.getEmails().size(); i++) {
                    correosFP.getChildren().add(newTextField(c.getEmails().get(i)));
                }
            }
            
            if (!c.getRedesSociales().isEmpty()) {
                redesSocialesTf.setPromptText(c.getRedesSociales().get(0));
                for (int i = 1; i < c.getRedesSociales().size(); i++) {
                    redesSocialesFP.getChildren().add(newTextField(c.getRedesSociales().get(i)));
                }
            }
            
            if (!c.getFechasDeInteres().isEmpty()) {
                fechasTf.setPromptText(c.getFechasDeInteres().get(0));
                for (int i = 1; i < c.getFechasDeInteres().size(); i++) {
                    fechasFP.getChildren().add(newTextField(c.getFechasDeInteres().get(i)));
                }
            }
            
            if (!c.getFotos().isEmpty()) {
                e = (LinkedListCircular)c.getFotos();
                String urlFoto = c.getFotos().get(0).getUrl();
                Image im = new Image(urlFoto, true); 
                    im.errorProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {                            
                            System.out.println("Error al cargar la imagen: " + urlFoto);
                        }
                });
                Platform.runLater(() -> {
                    profilePicture.setImage(im);
                });
            }
            
            if (!c.getContactosRelacionados().isEmpty()) {
                contactosRelacionados = (MyArrayList)c.getContactosRelacionados();
            }
            ContactosController.contactos.remove(c);
            editarFlag = false;
        }
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
        String direccion = direccionTf.getText();
        List<String> telefonos = getTelefonos();
        List<String> correos = getCorreos();
        List<String> redesSociales = getRedesSociales();
        List<String> fechas = getFechas();
        List<Foto> f = e;
        contactosRelacionados = filterContactos(getContactosRelacionados());

        boolean agg = true;

        try {
            camposLLenadosCorrectamente(nombre, apellido, empresa, telefonos);
        } catch (CampoVacioException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe ingresar al menos uno de los tres campos: nombre, apellido, empresa.");
            alert.show();
            agg = false;
        }
        Contacto nuevoContacto = new Contacto(nombre, apellido, empresa, direccion, correos, telefonos, redesSociales, fechas, contactosRelacionados, f);
        if (agg) {
            ContactosController.contactos.add(nuevoContacto);
            salirDeAgregarContacto(event);
        }
    }

    @FXML
    private void cambiarFotoDePerfil(MouseEvent event) {
        FileChooser file = new FileChooser();
        file.setTitle("Escoger foto");
        File PhotoSelect = file.showOpenDialog(null);

        if (PhotoSelect != null) {
            Image selected = new Image(PhotoSelect.toURI().toString());
            System.out.println(PhotoSelect.toURI().toString());

            e.add(new Foto(PhotoSelect.toURI().toString()));

            profilePicture.setImage(selected);
            String url = "src/main/resources/ec/edu/espol/imgs/contactos/";
            File guardado = new File(url);

            if (!guardado.exists()) {
                guardado.mkdirs();
            }
        }
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
        LinkedListCircular<Contacto> lista = ContactosController.contactos;
        if (lista.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No hay contactos disponibles.");
            alert.show();
        } else {
            contactosRelacionadosFP.getChildren().add(newTextField("Contacto"));
        }
    }

    void cargarCombo() throws IOException {
        MyArrayList<String> ordenar = new MyArrayList<>();
        ordenar.add("Persona");
        ordenar.add("Empresa");
        cbxcontacto.getItems().setAll(ordenar);

    }

    @FXML
    void comboEvents(ActionEvent e) {
        String opcion = (String) cbxcontacto.getValue();
        if (opcion != null) {
            if (opcion.equals("Empresa")) {
                nombreTf.setVisible(false);
                apellidoTf.setVisible(false);
                nombreTf.setText("");
                apellidoTf.setText("");
                empresaTf.setText("");
                
            } else {
                nombreTf.setVisible(true);
                nombreTf.setText("");
                apellidoTf.setVisible(true);
                apellidoTf.setText("");
                empresaTf.setText("");
                
               
            }
        }
    }

    

    private void camposLLenadosCorrectamente(String nombre, String apellido, String empresa, List<String> telefonos) throws CampoVacioException {
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

    private List<String> getContactosRelacionados() {
        return nodeListToStringList(contactosRelacionadosFP.getChildren());
    }

    private List<String> nodeListToStringList(List<Node> nodeList) {
        //Change for own tda
        /*
        Crea una lista de Strings a partir de un lista de Nodos (TextField)
        para poder extraer los datos y poder crear la instancia de contacto e introducirla en
        la lista de contactos
         */
        List<String> stringList = new MyArrayList<>();

        for (Node node : nodeList) {
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                if (!tf.getText().equals("")) {
                    stringList.add(tf.getText());
                }
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

    private static MyArrayList<Contacto> filterContactos(List<String> contactos) {
        MyArrayList<Contacto> results = new MyArrayList<>();
        List<Contacto> c = ContactosController.contactos;

        for (String s : contactos) {
            for (Contacto cont : c) {
                if ((cont.getNombre() + " " + cont.getApellido()).contains(s) && !results.contains(cont)) {
                    results.add(cont);
                }
            }
        }
        return results;
    }
}
