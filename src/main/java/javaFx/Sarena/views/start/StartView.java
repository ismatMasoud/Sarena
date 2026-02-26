package javaFx.Sarena.views.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class StartView extends AnchorPane {

    private ImageView background;
    private TextField nameField;
    private Button startButton;
    private Button leaderboardButton;
    private Button aboutButton;
    private Button exitButton;

    public StartView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {


        background = new ImageView(new Image(getClass().getResource("/images/background.png").toExternalForm()));
        background.setFitWidth(650);
        background.setFitHeight(780);
        background.setPreserveRatio(false);

        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setPrefWidth(250);
        nameField.setPrefHeight(30);



        ImageView startButtonImg = new ImageView(new Image(getClass().getResource("/images/startbutton.png").toExternalForm()));
        startButtonImg.setFitWidth(320);
        startButtonImg.setFitHeight(180);

        startButton = new Button();
        startButton.setGraphic(startButtonImg);
        startButton.setStyle("-fx-background-color: transparent;");


        ImageView leaderImg = new ImageView(new Image(getClass().getResource("/images/leaderboardbutton.png").toExternalForm()));
        leaderImg.setFitWidth(320);
        leaderImg.setFitHeight(170);

        leaderboardButton = new Button();
        leaderboardButton.setGraphic(leaderImg);
        leaderboardButton.setStyle("-fx-background-color: transparent;");

        ImageView aboutImg = new ImageView(new Image(getClass().getResource("/images/aboutbutton.png").toExternalForm()));
        aboutImg.setFitWidth(280);
        aboutImg.setFitHeight(180);

        aboutButton = new Button();
        aboutButton.setGraphic(aboutImg);
        aboutButton.setStyle("-fx-background-color: transparent;");

        ImageView exitImg = new ImageView(new Image(getClass().getResource("/images/exitbutton.png").toExternalForm()));
        exitImg.setFitWidth(180);
        exitImg.setFitHeight(170);

        exitButton = new Button();
        exitButton.setGraphic(exitImg);
        exitButton.setStyle("-fx-background-color: transparent;");
    }

    private void layoutNodes() {
        this.getChildren().addAll(background, nameField, startButton, leaderboardButton, aboutButton, exitButton);

        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);

        AnchorPane.setTopAnchor(nameField, 250.0);
        AnchorPane.setLeftAnchor(nameField, 200.0);

        AnchorPane.setTopAnchor(startButton, 260.0);
        AnchorPane.setLeftAnchor(startButton, 160.0);

        AnchorPane.setTopAnchor(leaderboardButton, 350.0);
        AnchorPane.setLeftAnchor(leaderboardButton, 155.0);

        AnchorPane.setTopAnchor(aboutButton, 440.0);
        AnchorPane.setLeftAnchor(aboutButton, 180.0);

        AnchorPane.setTopAnchor(exitButton, 530.0);
        AnchorPane.setLeftAnchor(exitButton, 230.0);


    }

    public TextField getNameField() { return nameField; }
    public Button getStartButton() { return startButton; }
    public Button getLeaderboardButton() { return leaderboardButton; }
    public Button getAboutButton() { return aboutButton; }
    public Button getExitButton() { return exitButton; }
}
