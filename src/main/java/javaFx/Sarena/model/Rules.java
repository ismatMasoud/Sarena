package javaFx.Sarena.model;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    public static boolean isValidMove(Board board, Move move) {
        if (board == null || move == null) {
            return false;
        }

        int fromIndex = move.getFromIndex();
        int toIndex = move.getToIndex();

        if (fromIndex < 0 || fromIndex >= Board.SIZE) {
            return false;
        }

        if (toIndex < 0 || toIndex >= Board.SIZE) {
            return false;
        }

        if (fromIndex == toIndex) {
            return false;
        }

        Cell fromCell = board.getCell(fromIndex);

        if (fromCell.isEmpty()) {
            return false;
        }

        if (!board.areNeighbors(fromIndex, toIndex)) {
            return false;
        }

        return true;
    }

    public static List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        if (board == null) {
            return validMoves;
        }

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

    public static boolean isGameOver(Board board) {
        return getValidMoves(board).isEmpty();
    }
}