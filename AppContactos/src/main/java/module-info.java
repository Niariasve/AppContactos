module ec.edu.espol.appcontactos {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.appcontactos to javafx.fxml;
    exports ec.edu.espol.appcontactos;
}
