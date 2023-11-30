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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    public static int contactoMostrado = 0;

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
    @FXML
    private Label contactosRelacionadosLabel;
    @FXML
    private VBox espacioRelacionados;
    
    public static boolean listaSelectFlag = false;

    @FXML
    private Button favoritoB;
    @FXML
    private RadioButton favRB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!listaSelectFlag) {
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
        } else {
            actualizarPantalla(contactoMostrado);
        }
    }
    
    @FXML
    private void entrarFavoritos(MouseEvent event) {
        try {
            App.setRoot("favoritos");
        } catch (IOException ex) {
        }
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

                    Image im = new Image(urlFoto, true);
                    im.errorProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            System.out.println("Error al cargar la imagen: " + urlFoto);
                        }
                    });
                    Platform.runLater(() -> {
                        actualProfilePic.setImage(im);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void contactoAnterior(MouseEvent event) {
        contactoMostrado = contactoMostrado - 1;
        System.out.println(contactoMostrado);
        actualizarPantalla(contactoMostrado);
        mostrarContactosRelacionados();
    }

    @FXML
    private void contactoSiguiente(MouseEvent event) {
        contactoMostrado = contactoMostrado + 1;
        System.out.println(contactoMostrado);
        actualizarPantalla(contactoMostrado);
        mostrarContactosRelacionados();

    }

    @FXML
    private void cambiarVistaLista(MouseEvent event) {
        try {
            App.setRoot("contactosLista");
        } catch (IOException ex) {
        }
    }

    @FXML
    private void CambiarVistaFotos(MouseEvent event) {
        if (!contactos.isEmpty()) {
            try {
                App.setRoot("fotos");
            } catch (IOException ex) {
            }
        }
    }

    private boolean visible = false;

    @FXML
    private void clickTexto(MouseEvent event) {
        if (espacioRelacionados.isVisible()) {
            espacioRelacionados.setVisible(false);
            espacioRelacionados.setManaged(false);
        } else {
            espacioRelacionados.setVisible(true);
            espacioRelacionados.setManaged(true);
            mostrarContactosRelacionados();
        }

    }

    private void mostrarContactosRelacionados() {
        Contacto contactoActual = contactos.get(contactoMostrado);
        List<Contacto> contactosRelacionados = contactoActual.getContactosRelacionados();
        espacioRelacionados.getChildren().clear();
        if (contactosRelacionados.isEmpty()) {
            Label nombreLabel = new Label("No Hay Contactos Disponibles");
            VBox vboxContacto = new VBox();
            vboxContacto.getChildren().addAll(nombreLabel);
            vboxContacto.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px");
            espacioRelacionados.getChildren().add(vboxContacto);
        } else {
            for (Contacto c : contactosRelacionados) {
                Label nombreLabel = new Label("Nombre: " + c.getNombre());
                Label telefonoLabel = new Label("Teléfono: " + c.getNumerosTelefonicos().toString());
                VBox vboxContacto = new VBox();
                vboxContacto.getChildren().addAll(nombreLabel, telefonoLabel);
                vboxContacto.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px");
                vboxContacto.setOnMouseClicked(event -> {
                    int indice = contactos.indexOf(c);
                    if (indice != -1) {
                        contactoMostrado = indice;
                        actualizarPantalla(contactoMostrado);
                        espacioRelacionados.getChildren().remove(vboxContacto);
                    }
                });
                espacioRelacionados.getChildren().add(vboxContacto);
            }
        }
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

    public void eliminar(int indice) {
        Contacto c = contactos.get(indice);
        try {
            contactos.remove(c);
        } catch (UnsupportedOperationException ex) {
            System.out.println("aqui esta el error");
        }
        numContactosAgg--;
    }

    @FXML
    private void eliminarContacto(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar contacto");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas eliminar este contacto?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            eliminar(contactoMostrado);
            actualizarPantalla(contactoMostrado);
        }
    }

    public static int getContactoActual() {
        return contactoMostrado;
    }

    @FXML
    private void cambiarEditarContacto(MouseEvent event) {
        AgregarContactoController.editarFlag = true;
        try {
            App.setRoot("agregarContacto");
        } catch (IOException ex) {
        }
    }

}
