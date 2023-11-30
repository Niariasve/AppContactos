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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    @FXML
    private ComboBox<String> Opciones;

    @FXML
    private TextField Busqueda;

    @FXML
    private Button filtrarB;

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
            crearContenedorDeContacto(contactos, i, medio);
        }

        listaFP.setContent(medio);

        ObservableList<String> opcionesLista = FXCollections.observableArrayList(
                "Apellido y Primer Nombre",
                "Empresa",
                "Dirección"
        );

        Opciones.setItems(opcionesLista);

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

    @FXML
    private void filtrar(MouseEvent event) {
        if (Busqueda.getText().isEmpty()) {
            filtrarSinTextBox();
        } else {
            filtrarConTextBox();    
        }
    }

    private boolean cumpleConFiltro(Contacto contacto, String filtro, String OpcionSelecionada) {
        switch (OpcionSelecionada) {

            case "Apellido y Primer Nombre":
                System.out.println("Filtrando por Apellido y Primer Nombre");
                System.out.println("Nombre del contacto: " + contacto.getNombre());
                System.out.println("Apellido del contacto: " + contacto.getApellido());
                return contacto.getNombre().toLowerCase().contains(filtro) || contacto.getApellido().toLowerCase().contains(filtro);
            case "Empresa":
                return contacto.getEmpresa().toLowerCase().contains(filtro);
            case "Dirección":
                return contacto.getDireccion().toLowerCase().contains(filtro);
            // Puedes agregar más casos según tus necesidades
            default:
                return false;
        }
    }

    private void filtrarConTextBox() {
        listaFP.setContent(null);
        VBox medio = new VBox();

        String filtro = Busqueda.getText().toLowerCase();
        String opcionSeleccionada = Opciones.getValue();

        LinkedListCircular<Contacto> listaFiltrada = new LinkedListCircular<>();
        for (Contacto contacto : contactos) {
            if (cumpleConFiltro(contacto, filtro, opcionSeleccionada)) {
                listaFiltrada.add(contacto);
            }
        }

        for (int i = 0; i < listaFiltrada.size(); i++) {
            crearContenedorDeContacto(listaFiltrada, i, medio);
        }

        listaFP.setContent(medio);
    }

    private void filtrarSinTextBox() {
        listaFP.setContent(null);
        VBox medio = new VBox();

        String opcionSeleccionada = Opciones.getValue();

        Comparator<Contacto> comparator = getComparator(opcionSeleccionada);
        contactos.sort(comparator);

        for (int i = 0; i < contactos.size(); i++) {
            crearContenedorDeContacto(contactos, i, medio);
        }

        listaFP.setContent(medio);

    }

    private Comparator<Contacto> getComparator(String opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case "Apellido y Primer Nombre":
                return Comparator.comparing(Contacto::getNombre).thenComparing(Contacto::getApellido);
            case "Empresa":
                return Comparator.comparing(Contacto::getEmpresa);
            case "Dirección":
                return Comparator.comparing(Contacto::getDireccion);
            // Puedes agregar más casos según tus necesidades
            default:
                // En caso de que la opción seleccionada no coincida con ninguna opción conocida,
                // simplemente devuelve un comparador que no realiza ningún cambio en el orden.
                return Comparator.comparing(Contacto::getNombre);
        }
    }

    private void crearContenedorDeContacto(List<Contacto> contactos, int i, VBox medio) {
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
}
