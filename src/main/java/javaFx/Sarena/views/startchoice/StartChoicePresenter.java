package javaFx.Sarena.views.startchoice;

import javaFx.Sarena.views.instructionspage.InstructionsPresenter;
import javaFx.Sarena.views.instructionspage.InstructionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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

        // BACK -> terug naar InstructionsView
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToInstructions();
            }
        });

        // CONTINUE -> voorlopig niks doen
        view.getContinueButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

//                StartChoiceView.StartMode mode = view.getSelectedMode();
//
//                System.out.println("CONTINUE pressed");
//                System.out.println("Player: " + playerName);
//                System.out.println("Start mode: " + mode);

                // later zet je hier updateViewToGame();
            }
        });
    }

    private void updateViewToInstructions() {
        InstructionsView instructionsView = new InstructionsView();
        new InstructionsPresenter(instructionsView, stage, playerName);

        Scene scene = new Scene(instructionsView, 650, 780);
        stage.setScene(scene);
    }
}