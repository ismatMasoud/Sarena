package javaFx.Sarena.views.game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameView extends AnchorPane {
    private ImageView background;
    private Button leaderboardButton;
    private Button exitButton;
    private Button aboutButton;
    private Button NewGameButton;
    private Button RulesGameButton;
    private Button UndoGameButton;


    public GameView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {


        background = new javafx.scene.image.ImageView(new Image(getClass().getResource("/images/background.png").toExternalForm()));
        background.setFitWidth(650);
        background.setFitHeight(780);
        background.setPreserveRatio(false);


        javafx.scene.image.ImageView leaderImg = new javafx.scene.image.ImageView(new Image(getClass().getResource("/images/leaderboardbutton.png").toExternalForm()));
        leaderImg.setFitWidth(320);
        leaderImg.setFitHeight(170);

        leaderboardButton = new Button();
        leaderboardButton.setGraphic(leaderImg);
        leaderboardButton.setStyle("-fx-background-color: transparent;");

        javafx.scene.image.ImageView aboutImg = new javafx.scene.image.ImageView(new Image(getClass().getResource("/images/aboutbutton.png").toExternalForm()));
        aboutImg.setFitWidth(280);
        aboutImg.setFitHeight(180);

        aboutButton = new Button();
        aboutButton.setGraphic(aboutImg);
        aboutButton.setStyle("-fx-background-color: transparent;");

        javafx.scene.image.ImageView exitImg = new javafx.scene.image.ImageView(new Image(getClass().getResource("/images/exitbutton.png").toExternalForm()));
        exitImg.setFitWidth(180);
        exitImg.setFitHeight(170);

        exitButton = new Button();
        exitButton.setGraphic(exitImg);
        exitButton.setStyle("-fx-background-color: transparent;");

    }

    private void layoutNodes() {

    }
}

