package javaFx.Sarena.views.game;

import java.util.List;
import java.util.Random;

import javaFx.Sarena.model.Board;
import javaFx.Sarena.model.Cell;
import javaFx.Sarena.model.GameModel;
import javaFx.Sarena.model.GameState;
import javaFx.Sarena.model.Move;
import javaFx.Sarena.model.PieceColor;
import javaFx.Sarena.model.StartMode;
import javaFx.Sarena.model.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * GamePresenter verbindt GameView met GameModel.
 *
 * Deze klasse:
 * - start een nieuwe game
 * - reageert op kliks op het bord
 * - maakt moves
 * - laat de computer spelen
 * - ververst de view
 */
public class GamePresenter {

    private final GameView view;
    private final Stage stage;
    private final GameModel model;

    // bewaart de eerste klik van de speler
    private Integer selectedFromIndex;

    public GamePresenter(GameView view, Stage stage, String playerName, StartMode startMode) {
        this.view = view;
        this.stage = stage;
        this.model = new GameModel();

        // start het spel in het model
        model.startNewGame(playerName, startMode);

        // nog geen startcel geselecteerd
        selectedFromIndex = null;

        addEventHandlers();
        refreshView();

        // Als computer start, laat hem meteen spelen
        if (model.getCurrentPlayer().getType().name().equals("COMPUTER")) {
            performComputerMove();
        }
    }

    private void addEventHandlers() {

        // Event handlers voor alle 36 cell-buttons
        Button[] buttons = view.getCellButtons();

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;

            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleCellClick(index);
                }
            });
        }

        // Exit knop
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
    }

    /*
     * Verwerkt klik op een cell.
     *
     * 1e klik = startcel kiezen
     * 2e klik = doelcel kiezen en move uitvoeren
     */
    private void handleCellClick(int clickedIndex) {

        // Als game over is, niets meer doen
        if (model.isGameOver()) {
            view.getLblMessage().setText("Game is over.");
            return;
        }

        // Als computer aan beurt is, mag speler niet klikken
        if (model.getCurrentPlayer().getType().name().equals("COMPUTER")) {
            view.getLblMessage().setText("Computer is thinking...");
            return;
        }

        // Eerste klik: kies startcel
        if (selectedFromIndex == null) {
            selectedFromIndex = clickedIndex;
            view.getLblMessage().setText("Start cell selected: " + clickedIndex + ". Now choose destination.");
            return;
        }

        // Tweede klik: maak move
        int fromIndex = selectedFromIndex;
        int toIndex = clickedIndex;

        Move move = new Move(fromIndex, toIndex);

        try {
            model.applyMove(move);

            // reset selectie
            selectedFromIndex = null;

            refreshView();

            // check of speler net game over maakte
            if (model.getGameState() == GameState.GAME_OVER) {
                view.getLblMessage().setText("Game over!");
                return;
            }

            // laat computer nu spelen
            performComputerMove();

        } catch (IllegalArgumentException ex) {
            view.getLblMessage().setText("Invalid move. Try again.");
            selectedFromIndex = null;
        }
    }

    /*
     * Laat de computer een random geldige move uitvoeren.
     */
    private void performComputerMove() {

        // Alleen doen als computer aan beurt is
        if (!model.getCurrentPlayer().getType().name().equals("COMPUTER")) {
            return;
        }

        List<Move> validMoves = model.getValidMoves();

        if (validMoves.isEmpty()) {
            view.getLblMessage().setText("No valid moves for computer.");
            return;
        }

        Random random = new Random();
        Move randomMove = validMoves.get(random.nextInt(validMoves.size()));

        try {
            model.applyMove(randomMove);
            refreshView();

            if (model.getGameState() == GameState.GAME_OVER) {
                view.getLblMessage().setText("Game over!");
            } else {
                view.getLblMessage().setText("Your turn.");
            }

        } catch (IllegalArgumentException ex) {
            view.getLblMessage().setText("Computer made an invalid move.");
        }
    }

    /*
     * Vernieuwt alles wat zichtbaar is in de view.
     */
    private void refreshView() {
        refreshLabels();
        refreshBoard();
    }

    /*
     * Update labels met info uit het model.
     */
    private void refreshLabels() {
        view.getLblCurrentPlayer().setText("Current Player: " + model.getCurrentPlayer().getName());
        view.getLblTurnCount().setText("Turns: " + model.getTurnCount());
    }

    /*
     * Update de 36 buttons op basis van de inhoud van het bord.
     *
     * Voor nu tonen we gewoon tekst:
     * - leeg = ""
     * - niet leeg = bovenste kleur + hoogte
     *
     * Later kan je hier images gebruiken.
     */
    private void refreshBoard() {

        Board board = model.getBoard();

        for (int i = 0; i < 36; i++) {

            Cell cell = board.getCell(i);
            Tower tower = cell.getTower();

            if (tower.isEmpty()) {
                view.clearCell(i);
            }
            else {

                PieceColor topColor =
                        tower.getPieces().get(tower.getPieces().size() - 1);

                view.showPiece(i, topColor, tower.height());
            }
        }
    }
}