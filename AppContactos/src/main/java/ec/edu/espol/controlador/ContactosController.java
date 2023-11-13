package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.modelo.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    @FXML
    private Button modoLista;
    @FXML
    private VBox parteArriba;
    @FXML
    private HBox parteAbajo;
    @FXML
    private VBox completa;
    @FXML
    private Button atras;
    @FXML
    private Button seguir;
    @FXML
    private ImageView catalogo;

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
        } catch (IOException ex) {
        }
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

    private VBox contenidoOriginal;
    private HBox contenidoOriginal2;

    @FXML
    private void modoLista(ActionEvent ev) {
        guardarContenidoOriginal();

        parteArriba.getChildren().clear();
        VBox medio = new VBox();
        int num = contactos.size();

        for (int i = 0; i < num; i++) {
            BorderPane contenedor = new BorderPane();
            String textoEmpresa = contactos.get(i).getEmpresa();
            String textoNombre = contactos.get(i).getNombre() + " " + contactos.get(i).getApellido();

            Label textoContacto;

            if (textoEmpresa != null && !textoEmpresa.isEmpty()) {
                textoContacto = new Label(textoEmpresa);
            } else {
                textoContacto = new Label(textoNombre);
            }

            BorderPane.setMargin(textoContacto, new javafx.geometry.Insets(15, 0, 15, 0)); // para Margen
            contenedor.setCenter(textoContacto);
            contenedor.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            medio.getChildren().add(contenedor);

        }
        ScrollPane lista = new ScrollPane(medio);
        lista.setFitToWidth(true);
        parteArriba.getChildren().add(lista);
        parteAbajo.getChildren().clear();
        Label label = new Label("Número de Contactos Registrados: " + num);
        parteAbajo.getChildren().add(label);
        parteAbajo.setAlignment(Pos.CENTER); //alineacion

    }

    @FXML
    private void modoRevista(MouseEvent ev) throws IOException {
        if (contactos.isEmpty()) {
            App.setRoot("contactos");
        }
        restaurarContenidoOriginal();

    }

    private void guardarContenidoOriginal() {
        contenidoOriginal = new VBox();
        contenidoOriginal.getChildren().addAll(parteArriba.getChildren());
        contenidoOriginal2 = new HBox();
        contenidoOriginal2.getChildren().addAll(parteAbajo.getChildren());
    }

    private void restaurarContenidoOriginal() {
        parteArriba.getChildren().clear();
        parteArriba.getChildren().addAll(contenidoOriginal.getChildren());
        parteAbajo.getChildren().clear();
        parteAbajo.getChildren().addAll(contenidoOriginal2.getChildren());
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
