package javaFx.Sarena.views.game;

import java.util.EnumMap;
import java.util.Map;

import javaFx.Sarena.model.PieceColor;
import javaFx.Sarena.model.Tower;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameView extends AnchorPane {

    private static final double BACKGROUND_WIDTH = 900;
    private static final double BACKGROUND_HEIGHT = 700;

    private static final double CELL_SIZE = 48;
    private static final double START_X = 120;
    private static final double START_Y = 90;
    private static final double GAP_X = 95;
    private static final double GAP_Y = 68;

    private static final double PIECE_WIDTH = 32;
    private static final double PIECE_HEIGHT = 32;
    private static final double STACK_OFFSET = 6;

    private ImageView background;

    private VBox infoBox;
    private HBox buttonBar;

    private Button[] cellButtons;

    private Label lblCurrentPlayer;
    private Label lblMessage;
    private Label lblTurnCount;

    private Button btnRules;
    private Button btnUndo;
    private Button btnNewGame;
    private Button btnExit;

    private Map<PieceColor, Image> pieceImages;

    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        background = new ImageView(loadImage("/images/GameView.png"));
        background.setFitWidth(BACKGROUND_WIDTH);
        background.setFitHeight(BACKGROUND_HEIGHT);
        background.setPreserveRatio(false);

        lblCurrentPlayer = new Label("Current Player:");
        lblCurrentPlayer.setFont(new Font("Arial", 16));
        lblCurrentPlayer.setStyle("-fx-text-fill: white;");

        lblMessage = new Label("Select a start cell");
        lblMessage.setFont(new Font("Arial", 16));
        lblMessage.setStyle("-fx-text-fill: white;");

        lblTurnCount = new Label("Turns: 0");
        lblTurnCount.setFont(new Font("Arial", 16));
        lblTurnCount.setStyle("-fx-text-fill: white;");

        infoBox = new VBox(8);
        infoBox.getChildren().addAll(lblCurrentPlayer, lblMessage, lblTurnCount);

        pieceImages = new EnumMap<>(PieceColor.class);
        pieceImages.put(PieceColor.RED, loadImage("/images/RedPiece.png"));
        pieceImages.put(PieceColor.BLACK, loadImage("/images/BlackPiece.png"));
        pieceImages.put(PieceColor.WHITE, loadImage("/images/WhitePiece.png"));
        pieceImages.put(PieceColor.YELLOW, loadImage("/images/YellowPiece.png"));

        btnRules = makeImageButton("/images/RulesButton.png", 150, 120);
        btnUndo = makeImageButton("/images/UndoButton.png", 150, 120);
        btnNewGame = makeImageButton("/images/NewGameButton.png", 150, 100);
        btnExit = makeImageButton("/images/exitbutton.png", 170, 150);

        buttonBar = new HBox(15);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getChildren().addAll(btnUndo, btnRules, btnNewGame, btnExit);

        cellButtons = new Button[36];
        for (int i = 0; i < cellButtons.length; i++) {
            Button button = new Button();
            button.setPrefSize(CELL_SIZE, CELL_SIZE);
            button.setStyle("-fx-background-color: transparent;");
            button.setText("");
            cellButtons[i] = button;
        }
    }

    private Image loadImage(String imagePath) {
        var url = getClass().getResource(imagePath);
        if (url == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }
        return new Image(url.toExternalForm());
    }

    private Button makeImageButton(String imagePath, double width, double height) {
        ImageView imageView = new ImageView(loadImage(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent;");
        return button;
    }

    private void layoutNodes() {
        getChildren().add(background);
        getChildren().add(infoBox);
        getChildren().add(buttonBar);

        AnchorPane.setTopAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);

        AnchorPane.setTopAnchor(infoBox, 20.0);
        AnchorPane.setLeftAnchor(infoBox, 30.0);

        AnchorPane.setBottomAnchor(buttonBar, 20.0);
        AnchorPane.setLeftAnchor(buttonBar, 170.0);

        int index = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Button button = cellButtons[index];
                getChildren().add(button);

                AnchorPane.setTopAnchor(button, START_Y + row * GAP_Y);
                AnchorPane.setLeftAnchor(button, START_X + col * GAP_X);

                index++;
            }
        }
    }

    public void clearCell(int index) {
        Button button = cellButtons[index];
        button.setGraphic(null);
        button.setText("");
    }

    public void showTower(int index, Tower tower) {
        Button button = cellButtons[index];

        if (tower == null || tower.isEmpty()) {
            button.setGraphic(null);
            button.setText("");
            return;
        }

        Pane stackPane = new Pane();
        stackPane.setPrefSize(30, 30);

        int level = 0;

        for (PieceColor color : tower.getPieces()) {
            Image image = pieceImages.get(color);

            if (image != null) {
                ImageView pieceView = new ImageView(image);
                pieceView.setFitWidth(PIECE_WIDTH);
                pieceView.setFitHeight(PIECE_HEIGHT);
                pieceView.setLayoutX(0);
                pieceView.setLayoutY(-level * STACK_OFFSET);

                stackPane.getChildren().add(pieceView);
                level++;
            }
        }

        button.setGraphic(stackPane);
        button.setText("");
    }

    public Button[] getCellButtons() {
        return cellButtons;
    }

    public Label getLblCurrentPlayer() {
        return lblCurrentPlayer;
    }

    public Label getLblMessage() {
        return lblMessage;
    }

    public Label getLblTurnCount() {
        return lblTurnCount;
    }

    public Button getBtnRules() {
        return btnRules;
    }

    public Button getBtnUndo() {
        return btnUndo;
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnExit() {
        return btnExit;
    }

}