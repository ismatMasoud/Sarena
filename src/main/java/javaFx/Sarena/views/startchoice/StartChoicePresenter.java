package javaFx.Sarena.views.startchoice;

import javaFx.Sarena.model.StartMode;
import javaFx.Sarena.views.game.GamePresenter;
import javaFx.Sarena.views.game.GameView;
import javaFx.Sarena.views.instructionspage.InstructionsPresenter;
import javaFx.Sarena.views.instructionspage.InstructionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class StartChoicePresenter {

    private final StartChoiceView view;
    private final Stage stage;
    private final String playerName;

    public StartChoicePresenter(StartChoiceView view, Stage stage, String playerName) {
        this.view = view;
        this.stage = stage;
        this.playerName = playerName;
        addEventHandlers();
    }

    private void addEventHandlers() {

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToInstructions();
            }
        });

        // CONTINUE -> naar game als er een keuze gemaakt is
        view.getContinueButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartMode mode = view.getSelectedStartMode();

                if (mode == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No choice made");
                    alert.setHeaderText(null);
                    alert.setContentText("Please choose who starts the game.");
                    alert.showAndWait();
                } else {
                    updateViewToGame(mode);
                }
            }
        });
    }


    private void updateViewToInstructions() {
        InstructionsView instructionsView = new InstructionsView();
        new InstructionsPresenter(instructionsView, stage, playerName);

        Scene scene = new Scene(instructionsView, 650, 780);
        stage.setScene(scene);
    }
    private void updateViewToGame(StartMode mode) {

        GameView gameView = new GameView();

        new GamePresenter(gameView, stage, playerName, mode);

        Scene scene = new Scene(gameView, 900, 700);
        stage.setScene(scene);
    }
}