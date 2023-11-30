package ec.edu.espol.controlador;

import ec.edu.espol.appcontactos.App;
import ec.edu.espol.appcontactos.Tda.LinkedListCircular;
import ec.edu.espol.appcontactos.Tda.MyArrayList;
import ec.edu.espol.modelo.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FavoritosController implements Initializable {

    private static MyArrayList<Contacto> contactos = new MyArrayList<>();

    @FXML
    private Label numFav;
    @FXML
    private ImageView imagen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedListCircular<Contacto> c = ContactosController.contactos;
        numFav.setText(String.valueOf(contactos.size()));

    }

    @FXML
    private void cambiarVistaContactos(MouseEvent event) {
        try {
            App.setRoot("contactos");
        } catch (IOException ex) {
        }
    }

}
