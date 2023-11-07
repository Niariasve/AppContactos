module ec.edu.espol.appcontactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.appcontactos to javafx.fxml;
    exports ec.edu.espol.appcontactos;
    
    opens ec.edu.espol.controlador to javafx.fxml;
    exports ec.edu.espol.controlador;
}
