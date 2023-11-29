/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author alexc
 */
public class EditarContactoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button editar;
    
    @FXML
    private Button CambiarFoto;
    
    @FXML 
    private String imagen;
    
    @FXML
    private ImageView profilePicture;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void cambiarFotoDePerfil(MouseEvent event) {
        FileChooser file = new FileChooser();
        file.setTitle("Escoger foto");
        File PhotoSelect = file.showOpenDialog(null);

        if (PhotoSelect != null) {
            Image selected = new Image(PhotoSelect.toURI().toString());
            profilePicture.setImage(selected);
            String url = "src/main/resources/ec/edu/espol/imgs/contactos/";
            File guardado = new File(url);
            
            if(!guardado.exists()){
                guardado.mkdirs();
            }
            
            File outputFile=new File(url+PhotoSelect.getName());
            try{
                Files.copy(PhotoSelect.toPath(),outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia de imagen guardada en: " + outputFile.getAbsolutePath());
                System.out.println(PhotoSelect.getName());
                imagen = PhotoSelect.getName();


            }catch(IOException e){
                e.printStackTrace();
            }
            
            
        }
    }
    
    @FXML
    private void eliminarContacto(MouseEvent event) {
//        VBox contenedor = (VBox) event.getSource();
//        int indice = ((VBox) contenedor.getParent()).getChildren().indexOf(contenedor);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar Contacto");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Estas seguro que deseas eliminar este contacto?");
        Optional<ButtonType> result = alerta.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            ContactosController.eliminarContacto(indice);
//        }
    }
    
    
}
