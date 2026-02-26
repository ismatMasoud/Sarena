package javaFx.Sarena.views.leaderboard;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class LeaderboardView extends AnchorPane {
    private ImageView leaderboardImage;
    private GridPane leaderboard;
    private Button backButton;

    public LeaderboardView() {
        initializeNodes();
        layoutNodes();
    }


    private void initializeNodes() {
        leaderboardImage = new ImageView(new Image(getClass().getResource("/images/leaderboard.png").toExternalForm()));
        leaderboardImage.setFitWidth(650);
        leaderboardImage.setFitHeight(780);
        leaderboardImage.setPreserveRatio(false);

        ImageView backImg = new ImageView(new Image(getClass().getResource("/images/backbutton.png").toExternalForm()));
        backImg.setFitWidth(300);
        backImg.setFitHeight(170);

        backButton = new Button();
        backButton.setGraphic(backImg);
        backButton.setStyle("-fx-background-color: transparent;");
    }

    private void layoutNodes() {
        this.getChildren().addAll(leaderboardImage, backButton);

        AnchorPane.setTopAnchor(leaderboardImage, 0.0);
        AnchorPane.setLeftAnchor(leaderboardImage, 0.0);

        AnchorPane.setTopAnchor(backButton, 480.0);
        AnchorPane.setLeftAnchor(backButton, 170.0);

    }

    public Button getBackButton() {return backButton;}
}

