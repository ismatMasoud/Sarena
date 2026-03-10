package javaFx.Sarena.views.game;

import java.util.EnumMap;
import java.util.Map;

import javaFx.Sarena.model.PieceColor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class GameView extends AnchorPane {

    private ImageView background;

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

        // ===== ACHTERGROND =====
        var bgUrl = getClass().getResource("/images/GameView.png");
        if (bgUrl == null) {
            throw new IllegalArgumentException("Image not found: /images/GameView.png");
        }

        background = new ImageView(new Image(bgUrl.toExternalForm()));
        background.setFitWidth(900);
        background.setFitHeight(700);
        background.setPreserveRatio(false);

        // ===== LABELS =====
        lblCurrentPlayer = new Label("Current Player:");
        lblCurrentPlayer.setFont(new Font("Arial", 16));

        lblMessage = new Label("Select a start cell");
        lblMessage.setFont(new Font("Arial", 16));

        lblTurnCount = new Label("Turns: 0");
        lblTurnCount.setFont(new Font("Arial", 16));

        // ===== PIECE IMAGES LADEN =====
        pieceImages = new EnumMap<>(PieceColor.class);
        pieceImages.put(PieceColor.RED, loadImage("/images/RedPiece.png"));
        pieceImages.put(PieceColor.BLACK, loadImage("/images/BlackPiece.png"));
        pieceImages.put(PieceColor.WHITE, loadImage("/images/WhitePiece.png"));
        pieceImages.put(PieceColor.YELLOW, loadImage("/images/YellowPiece.png"));

        // ===== KNOPPEN MET AFBEELDINGEN =====
        btnRules = makeImageButton("/images/RulesButton.png", 150, 120);
        btnUndo = makeImageButton("/images/UndoButton.png", 150, 120);
        btnNewGame = makeImageButton("/images/NewGameButton.png", 150, 100);
        btnExit = makeImageButton("/images/exitbutton.png", 170, 150);

        // ===== 36 CELL BUTTONS =====
        cellButtons = new Button[36];

        for (int i = 0; i < cellButtons.length; i++) {
            Button button = new Button();
            button.setPrefSize(42, 42);
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
        Image image = loadImage(imagePath);

        ImageView img = new ImageView(image);
        img.setFitWidth(width);
        img.setFitHeight(height);

        Button button = new Button();
        button.setGraphic(img);
        button.setStyle("-fx-background-color: transparent;");
        return button;
    }

    private void layoutNodes() {
        getChildren().add(background);

        // ===== LABELS =====
        getChildren().addAll(lblCurrentPlayer, lblMessage, lblTurnCount);

        AnchorPane.setTopAnchor(lblCurrentPlayer, 25.0);
        AnchorPane.setLeftAnchor(lblCurrentPlayer, 40.0);

        AnchorPane.setTopAnchor(lblMessage, 55.0);
        AnchorPane.setLeftAnchor(lblMessage, 40.0);

        AnchorPane.setTopAnchor(lblTurnCount, 85.0);
        AnchorPane.setLeftAnchor(lblTurnCount, 40.0);

        // ===== ONDERAAN KNOPPEN =====
        getChildren().addAll(btnUndo, btnRules, btnNewGame, btnExit);

        AnchorPane.setBottomAnchor(btnUndo, 20.0);
        AnchorPane.setLeftAnchor(btnUndo, 180.0);

        AnchorPane.setBottomAnchor(btnRules, 20.0);
        AnchorPane.setLeftAnchor(btnRules, 290.0);

        AnchorPane.setBottomAnchor(btnNewGame, 20.0);
        AnchorPane.setLeftAnchor(btnNewGame, 400.0);

        AnchorPane.setBottomAnchor(btnExit, 20.0);
        AnchorPane.setLeftAnchor(btnExit, 510.0);

        // ===== CELL BUTTONS =====
        double startX = 90;
        double startY = 110;
        double gapX = 105;
        double gapY = 78;

        int index = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Button button = cellButtons[index];
                getChildren().add(button);

                AnchorPane.setTopAnchor(button, startY + row * gapY);
                AnchorPane.setLeftAnchor(button, startX + col * gapX);

                index++;
            }
        }
    }

    public void clearCell(int index) {
        Button button = cellButtons[index];
        button.setGraphic(null);
        button.setText("");
    }

    public void showPiece(int index, PieceColor color, int height) {
        Button button = cellButtons[index];

        Image image = pieceImages.get(color);
        if (image == null) {
            button.setGraphic(null);
            button.setText("?");
            return;
        }

        ImageView pieceView = new ImageView(image);
        pieceView.setFitWidth(28);
        pieceView.setFitHeight(28);

        button.setGraphic(pieceView);
        button.setText(String.valueOf(height));
        button.setContentDisplay(ContentDisplay.BOTTOM);
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