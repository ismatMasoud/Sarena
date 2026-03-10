package javaFx.Sarena.views.startchoice;

import javaFx.Sarena.model.StartMode;
import javaFx.Sarena.views.game.GamePresenter;
import javaFx.Sarena.views.game.GameView;
import javaFx.Sarena.views.instructionspage.InstructionsPresenter;
import javaFx.Sarena.views.instructionspage.InstructionsView;
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
        view.getBackButton().setOnAction(event -> updateViewToInstructions());

        view.getContinueButton().setOnAction(event -> {
            StartMode startMode = getSelectedStartMode();

            if (startMode == null) {
                showNoChoiceAlert();
            } else {
                updateViewToGame(startMode);
            }
        });
    }

    private StartMode getSelectedStartMode() {
        if (view.getRbPlayer().isSelected()) {
            return StartMode.PLAYER_STARTS;
        }

        if (view.getRbComputer().isSelected()) {
            return StartMode.COMPUTER_STARTS;
        }

        if (view.getRbRandom().isSelected()) {
            return StartMode.RANDOM;
        }

        return null;
    }

    private void showNoChoiceAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No choice made");
        alert.setHeaderText(null);
        alert.setContentText("Please choose who starts the game.");
        alert.showAndWait();
    }

    private void updateViewToInstructions() {
        InstructionsView instructionsView = new InstructionsView();
        new InstructionsPresenter(instructionsView, stage, playerName);

        Scene scene = new Scene(instructionsView, 650, 780);
        stage.setScene(scene);
    }

    private void updateViewToGame(StartMode startMode) {
        GameView gameView = new GameView();
        new GamePresenter(gameView, stage, playerName, startMode);

        Scene scene = new Scene(gameView, 900, 700);
        stage.setScene(scene);
    }
}