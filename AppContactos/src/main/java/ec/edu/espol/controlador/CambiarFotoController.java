/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Foto;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author alexc
 */


public class CambiarFotoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Foto> listaFotos;
    
    private VBox fotosVbox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarListaFoto(); 
        HBox fotosHBox = new HBox(); 
        
        fotosHBox.getChildren().clear();
        for(Foto foto: listaFotos){
            String rutaImagen = "/ec/edu/espol/imgsPerfiles/" + foto.getUrl();
            ImageView imageView = new ImageView(new Image(getClass().getResource(rutaImagen).toExternalForm()));
            imageView.setFitHeight(80);
            imageView.setFitWidth(80); 
            
            fotosHBox.getChildren().add(imageView);
            
        }
        fotosVbox.getChildren().add(fotosHBox);
    }
    
    private void cargarListaFoto(){
        int cantidadDeFotos = 4; 
        listaFotos = new LinkedList<>();

        for (int i = 1; i <= cantidadDeFotos; i++) {
            String persona = "Persona" + i + ".jpg";
            listaFotos.add(new Foto(persona));
        }
    }
    
    
}
