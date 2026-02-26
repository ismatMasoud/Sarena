package javaFx.Sarena.views.startchoice;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StartChoiceView extends AnchorPane {

    private ImageView background;

    private ToggleGroup group;
    private RadioButton rbPlayer;
    private RadioButton rbOpponent;
    private RadioButton rbRandom;

    private Button backButton;
    private Button continueButton;

    public StartChoiceView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {

        background = new ImageView(new Image(
                getClass().getResource("/images/startchoice.png").toExternalForm()
        ));
        background.setFitWidth(650);
        background.setFitHeight(780);
        background.setPreserveRatio(false);

        group = new ToggleGroup();

        rbPlayer = new RadioButton("Player starts");
        rbPlayer.setToggleGroup(group);

        rbOpponent = new RadioButton("Computer / Player 2 starts");
        rbOpponent.setToggleGroup(group);

        rbRandom = new RadioButton("Random");
        rbRandom.setToggleGroup(group);

        // Alles leeg bij start
        group.selectToggle(null);

        // Grotere klikzone (makkelijk klikken)
        rbPlayer.setPrefSize(360, 45);
        rbOpponent.setPrefSize(360, 45);
        rbRandom.setPrefSize(360, 45);

        // Buttons
        ImageView backImg = new ImageView(new Image(
                getClass().getResource("/images/backbutton.png").toExternalForm()
        ));
        backImg.setFitWidth(300);
        backImg.setFitHeight(170);

        backButton = new Button();
        backButton.setGraphic(backImg);
        backButton.setStyle("-fx-background-color: transparent;");

        ImageView contImg = new ImageView(new Image(
                getClass().getResource("/images/continuebutton.png").toExternalForm()
        ));
        contImg.setFitWidth(300);
        contImg.setFitHeight(170);

        continueButton = new Button();
        continueButton.setGraphic(contImg);
        continueButton.setStyle("-fx-background-color: transparent;");
    }

    private void layoutNodes() {
        this.getChildren().addAll(background, rbPlayer, rbOpponent, rbRandom, backButton, continueButton);

        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);

        // Plaatsing: pas aan tot het exact klopt
        AnchorPane.setTopAnchor(rbPlayer, 320.0);
        AnchorPane.setLeftAnchor(rbPlayer, 140.0);

        AnchorPane.setTopAnchor(rbOpponent, 375.0);
        AnchorPane.setLeftAnchor(rbOpponent, 140.0);

        AnchorPane.setTopAnchor(rbRandom, 430.0);
        AnchorPane.setLeftAnchor(rbRandom, 140.0);

        AnchorPane.setTopAnchor(backButton, 520.0);
        AnchorPane.setLeftAnchor(backButton, 80.0);

        AnchorPane.setTopAnchor(continueButton, 520.0);
        AnchorPane.setLeftAnchor(continueButton, 300.0);
    }

    public Button getBackButton() { return backButton; }
    public Button getContinueButton() { return continueButton; }


}
