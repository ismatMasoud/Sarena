module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javaFx.Sarena to javafx.fxml;
    exports javaFx.Sarena;
}