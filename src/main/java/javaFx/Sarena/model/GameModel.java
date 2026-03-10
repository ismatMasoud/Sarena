package javaFx.Sarena.model;

import java.util.List;

public class GameModel {

    private Board board;
    private Player human;
    private Player computer;
    private Player currentPlayer;
    private GameState gameState;
    private Phase phase;
    private int turnCount;
    private long startMillis;

    public void startNewGame(String playerName, StartMode startMode) {
        if (playerName == null || playerName.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }

        board = new Board();
        board.initialize();

        human = new Player(playerName, PlayerType.HUMAN);
        computer = new Player("Computer", PlayerType.COMPUTER);

        decideStartingPlayer(startMode);

        gameState = GameState.IN_PROGRESS;
        phase = (currentPlayer == human) ? Phase.SELECT_FROM : Phase.COMPUTER_TURN;
        turnCount = 0;
        startMillis = System.currentTimeMillis();
    }

    private void decideStartingPlayer(StartMode startMode) {
        if (startMode == null) {
            throw new IllegalArgumentException("Start mode cannot be null.");
        }

        if (startMode == StartMode.PLAYER_STARTS) {
            currentPlayer = human;
        } else if (startMode == StartMode.COMPUTER_STARTS) {
            currentPlayer = computer;
        } else {
            currentPlayer = Math.random() < 0.5 ? human : computer;
        }
    }

    public List<Move> getValidMoves() {
        return Rules.getValidMoves(board);
    }

    public void applyMove(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }

        if (!Rules.isValidMove(board, move)) {
            throw new IllegalArgumentException("Invalid move.");
        }

        board.executeMove(move);
        turnCount++;

        if (Rules.isGameOver(board)) {
            gameState = GameState.GAME_OVER;
        } else {
            nextTurn();
        }
    }

    public void nextTurn() {
        if (currentPlayer == human) {
            currentPlayer = computer;
            phase = Phase.COMPUTER_TURN;
        } else {
            currentPlayer = human;
            phase = Phase.SELECT_FROM;
        }
    }

    public boolean isGameOver() {
        return gameState == GameState.GAME_OVER;
    }

    public Board getBoard() {
        return board;
    }

    public Player getHuman() {
        return human;
    }

    public Player getComputer() {
        return computer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Phase getPhase() {
        return phase;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public long getStartMillis() {
        return startMillis;
    }
}