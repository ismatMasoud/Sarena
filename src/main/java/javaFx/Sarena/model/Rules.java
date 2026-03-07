package javaFx.Sarena.model;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    /*
     * Controleert of een move geldig is.
     * Voor nu maken we het simpel:
     * - from en to mogen niet hetzelfde zijn
     * - startcel mag niet leeg zijn
     */
    public static boolean isValidMove(Board board, Move move) {
        if (move.getFromIndex() == move.getToIndex()) {
            return false;
        }

        Cell fromCell = board.getCell(move.getFromIndex());

        if (fromCell.isEmpty()) {
            return false;
        }

        return true;
    }

    /*
     * Geeft een lijst van alle geldige moves.
     * Voor nu heel simpel:
     * van elke niet-lege cell naar elke andere cell.
     */
    public static List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        for (int from = 0; from < Board.SIZE; from++) {
            for (int to = 0; to < Board.SIZE; to++) {
                Move move = new Move(from, to);

                if (isValidMove(board, move)) {
                    validMoves.add(move);
                }
            }
        }

        return validMoves;
    }

    /*
     * Controleert of het spel gedaan is.
     * Voor nu simpel:
     * als er geen geldige moves meer zijn.
     */
    public static boolean isGameOver(Board board) {
        return getValidMoves(board).isEmpty();
    }
}