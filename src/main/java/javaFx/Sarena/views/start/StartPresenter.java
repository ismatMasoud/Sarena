package javaFx.Sarena.views.start;

import javaFx.Sarena.views.about.AboutPresenter;
import javaFx.Sarena.views.about.AboutView;
import javaFx.Sarena.views.instructionspage.InstructionsPresenter;
import javaFx.Sarena.views.instructionspage.InstructionsView;
import javaFx.Sarena.views.leaderboard.LeaderboardPresenter;
import javaFx.Sarena.views.leaderboard.LeaderboardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPresenter {
    private final StartView view;
    private final Stage stage;

    public StartPresenter(StartView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        addEventHandelrs();

    }

    private void addEventHandelrs() {


        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToInstructions();
            }
        });

        // LEADERBOARD -> Leaderboard
        view.getLeaderboardButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToLeaderboard();
            }
        });

        // ABOUT -> About
        view.getAboutButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateViewToAbout();
            }
        });


        // EXIT -> sluiten (logisch)
        view.getExitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        // startknop blokkeren tot naam ingevuld is
        view.getStartButton().setDisable(true);
        view.getNameField().textProperty().addListener((obs, oldValue, newValue) -> {
            view.getStartButton().setDisable(newValue.trim().isEmpty());
        });
    }
        private void updateViewToInstructions() {
            String playerName = view.getNameField().getText().trim();
            if (playerName.isEmpty()) return;

            InstructionsView instructionsView = new InstructionsView();
            new InstructionsPresenter(instructionsView, stage, playerName);
            Scene scene = new Scene(instructionsView, 650, 780);
            stage.setScene(scene);
        }
        private void updateViewToLeaderboard() {
            LeaderboardView leaderboardView = new LeaderboardView();
            new LeaderboardPresenter(leaderboardView, stage);

            Scene scene = new Scene(leaderboardView, 650, 780);
            stage.setScene(scene);
        }

        private void updateViewToAbout() {
            AboutView aboutView = new AboutView();
            new AboutPresenter(aboutView, stage);

            Scene scene = new Scene(aboutView, 650, 780);
            stage.setScene(scene);
        }
}
