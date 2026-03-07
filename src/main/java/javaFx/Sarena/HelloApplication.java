package javaFx.Sarena;


import javaFx.Sarena.views.start.StartPresenter;
import javaFx.Sarena.views.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        StartView startView = new StartView();
        new StartPresenter(startView, stage);

        Scene scene = new Scene(startView, 650, 780);
        stage.setTitle("Sarena");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}