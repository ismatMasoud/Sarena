package javaFx.Sarena.views.instructionspage;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class InstructionsView extends AnchorPane {
    private ImageView background;
    private Button okButton;
    private Button backButton;

    public InstructionsView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        background = new ImageView(new Image(getClass().getResource("/images/instructions.png").toExternalForm ()));
        background.setFitHeight(780);
        background.setFitWidth(650);
        background.setPreserveRatio(false);

        ImageView okImg = new ImageView(new Image(getClass().getResource("/images/okbutton.png").toExternalForm()));
        okImg.setFitWidth(300);
        okImg.setFitHeight(170);

        okButton = new Button();
        okButton.setGraphic(okImg);
        okButton.setStyle("-fx-background-color: transparent;");


        ImageView backImg = new ImageView(new Image(getClass().getResource("/images/backbutton.png").toExternalForm()));
        backImg.setFitWidth(300);
        backImg.setFitHeight(170);

        backButton = new Button();
        backButton.setGraphic(backImg);
        backButton.setStyle("-fx-background-color: transparent;");
    }

    private void layoutNodes() {
        this.getChildren().addAll(background, okButton, backButton);

        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);

        AnchorPane.setTopAnchor(okButton, 470.0);
        AnchorPane.setLeftAnchor(okButton, 180.0);

        AnchorPane.setTopAnchor(backButton, 590.0);
        AnchorPane.setLeftAnchor(backButton, 180.0);

    }

    public Button getOkButton() {return okButton;}
    public Button getBackButton() {return backButton;}
}
