package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.appcontactos.Tda.MyArrayList;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class FavoritosController implements Initializable {

    public static MyArrayList<Contacto> favoritos = new MyArrayList<>();

    @FXML
    private Label numFav;
    @FXML
    private ImageView imagen;
    @FXML
    private ScrollPane listaFP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedListCircular<Contacto> c = ContactosController.contactos;
        numFav.setText(String.valueOf(favoritos.size()));
        VBox medio = new VBox();
        medio.setSpacing(2); // ESPACIADO
        int num = favoritos.size();

        for (int i = 0; i < num; i++) {
            crearContenedorDeContacto(favoritos, i, medio);
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
    
    private void crearContenedorDeContacto(List<Contacto> contactos, int i, VBox medio) {
        VBox contenedor = new VBox();
        contenedor.setId(Integer.toString(i));
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
        //
        contenedor.setOnMouseClicked(event -> {
            System.out.println(contenedor.getId());
            Contacto c = contactos.get(Integer.parseInt(contenedor.getId()));
            ContactosController.contactoMostrado = ContactosController.contactos.indexOf(c);
            ContactosController.listaSelectFlag = true;
            try {
                App.setRoot("contactos");
            } catch (IOException ex) {}            
        });
        //
        medio.getChildren().add(contenedor);

    }

}
