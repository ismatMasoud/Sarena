package javaFx.Sarena.views.game;

import java.util.List;
import java.util.Random;

import javaFx.Sarena.model.Board;
import javaFx.Sarena.model.Cell;
import javaFx.Sarena.model.GameModel;
import javaFx.Sarena.model.GameState;
import javaFx.Sarena.model.Move;
import javaFx.Sarena.model.PlayerType;
import javaFx.Sarena.model.StartMode;
import javaFx.Sarena.model.Tower;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePresenter {

    private static final int BOARD_SIZE = 36;
    private static final double COMPUTER_DELAY_SECONDS = 1.0;

    private final GameView view;
    private final Stage stage;
    private final GameModel model;
    private final Random random;

    private Integer selectedFromIndex;

    public GamePresenter(GameView view, Stage stage, String playerName, StartMode startMode) {
        this.view = view;
        this.stage = stage;
        this.model = new GameModel();
        this.random = new Random();

        model.startNewGame(playerName, startMode);
        selectedFromIndex = null;

        addEventHandlers();
        refreshView();

        if (isComputerTurn()) {
            performComputerMoveWithDelay();
        }
    }

    private void addEventHandlers() {
        Button[] buttons = view.getCellButtons();

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnAction(event -> handleCellClick(index));
        }

        view.getBtnExit().setOnAction(event -> stage.close());
    }

    private boolean isComputerTurn() {
        return model.getCurrentPlayer().getType() == PlayerType.COMPUTER;
    }

    private void handleCellClick(int clickedIndex) {
        if (model.isGameOver()) {
            view.getLblMessage().setText("Game is over.");
            refreshLabels();
            return;
        }

        if (isComputerTurn()) {
            view.getLblMessage().setText("Computer is thinking...");
            refreshLabels();
            return;
        }

        if (selectedFromIndex == null) {
            selectedFromIndex = clickedIndex;
            view.getLblMessage().setText("Start cell selected. Now choose destination.");
            refreshLabels();
            return;
        }

        int fromIndex = selectedFromIndex;
        int toIndex = clickedIndex;
        Move move = new Move(fromIndex, toIndex);

        try {
            model.applyMove(move);
            selectedFromIndex = null;

            refreshView();

            if (model.getGameState() == GameState.GAME_OVER) {
                view.getLblMessage().setText("Game over!");
                refreshLabels();
                return;
            }

            performComputerMoveWithDelay();

        } catch (IllegalArgumentException ex) {
            view.getLblMessage().setText("Invalid move. Try again.");
            selectedFromIndex = null;
            refreshLabels();
        }
    }

    private void performComputerMoveWithDelay() {
        if (!isComputerTurn()) {
            return;
        }

        view.getLblMessage().setText("Computer is thinking...");
        refreshLabels();

        PauseTransition pause = new PauseTransition(Duration.seconds(COMPUTER_DELAY_SECONDS));
        pause.setOnFinished(event -> performComputerMove());
        pause.play();
    }

    private void performComputerMove() {
        if (!isComputerTurn()) {
            return;
        }

        List<Move> validMoves = model.getValidMoves();

        if (validMoves.isEmpty()) {
            view.getLblMessage().setText("No valid moves for computer.");
            refreshLabels();
            return;
        }

        Move randomMove = validMoves.get(random.nextInt(validMoves.size()));

        try {
            model.applyMove(randomMove);
            refreshView();

            if (model.getGameState() == GameState.GAME_OVER) {
                view.getLblMessage().setText("Game over!");
            } else {
                view.getLblMessage().setText("Your turn.");
            }

            refreshLabels();

        } catch (IllegalArgumentException ex) {
            view.getLblMessage().setText("Computer made an invalid move.");
            refreshLabels();
        }
    }

    private void refreshView() {
        refreshLabels();
        refreshBoard();
    }

    private void refreshLabels() {
        view.getLblCurrentPlayer().setText("Current Player: " + model.getCurrentPlayer().getName());
        view.getLblTurnCount().setText("Turns: " + model.getTurnCount());
    }

    private void refreshBoard() {
        Board board = model.getBoard();

        for (int i = 0; i < BOARD_SIZE; i++) {
            Cell cell = board.getCell(i);
            Tower tower = cell.getTower();

            if (tower.isEmpty()) {
                view.clearCell(i);
            } else {
                view.showTower(i, tower);
            }
        }
    }
}