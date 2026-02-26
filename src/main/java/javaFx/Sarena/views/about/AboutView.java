package javaFx.Sarena.views.about;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AboutView extends AnchorPane {

    private ImageView background;
    private Button backButton;

    public AboutView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {

        background = new ImageView(new Image(getClass().getResource("/images/aboutbackground.png").toExternalForm()));
        background.setFitHeight(780);
        background.setFitWidth(650);
        background.setPreserveRatio(false);

        ImageView backImg = new ImageView(new Image(getClass().getResource("/images/backbutton.png").toExternalForm()));

        backImg.setFitWidth(300);
        backImg.setFitHeight(170);

        backButton = new Button();
        backButton.setGraphic(backImg);
        backButton.setStyle("-fx-background-color: transparent;");
    }

    private void layoutNodes() {

        this.getChildren().addAll(background, backButton);

        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);

        AnchorPane.setTopAnchor(backButton, 570.0);
        AnchorPane.setLeftAnchor(backButton, 165.0);
    }

    public Button getBackButton() {
        return backButton;
    }
}
