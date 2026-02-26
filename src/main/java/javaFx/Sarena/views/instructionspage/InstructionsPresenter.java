package javaFx.Sarena.views.instructionspage;


import javaFx.Sarena.views.startchoice.StartChoicePresenter;
import javaFx.Sarena.views.startchoice.StartChoiceView;
import javaFx.Sarena.views.start.StartPresenter;
import javaFx.Sarena.views.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionsPresenter {

    private final InstructionsView view;
    private final Stage stage;
    private final String playerName;

    public InstructionsPresenter(InstructionsView view, Stage stage, String playerName) {
        this.view = view;
        this.stage = stage;
        this.playerName = playerName;


        addEventHandlers();
    }

    private void addEventHandlers() {

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                StartView startView = new StartView();
                new StartPresenter(startView, stage); // ✅ model doorgeven

                Scene scene = new Scene(startView, 650, 780);
                stage.setScene(scene);
            }
        });

        view.getOkButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                StartChoiceView startChoiceView = new StartChoiceView();
                new StartChoicePresenter(startChoiceView, stage, playerName); // ✅ model param + geen komma fout

                Scene scene = new Scene(startChoiceView, 650, 780);
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

                stage.setScene(scene);
            }
        });
    }
}