package javaFx.Sarena.views.about;

import javaFx.Sarena.views.start.StartPresenter;
import javaFx.Sarena.views.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutPresenter {
    private final AboutView view;
    private final Stage stage;

    public AboutPresenter(AboutView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        addEventhandlers();
    }

    private void addEventhandlers() {

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                StartView startView = new StartView();
                new StartPresenter(startView, stage);

                Scene scene = new Scene(startView, 650, 780);
                stage.setScene(scene);
            }
        });
    }
}
