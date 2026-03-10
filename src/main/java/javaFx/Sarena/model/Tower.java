package javaFx.Sarena.model;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private final List<PieceColor> pieces = new ArrayList<>();

    public int height() {
        return pieces.size();
    }

    public boolean isEmpty() {
        return pieces.isEmpty();
    }

    public void push(PieceColor color) {
        if (color == null) {
            throw new IllegalArgumentException("Piece color cannot be null.");
        }
        pieces.add(color);
    }

    public void pushAll(Tower other) {
        if (other == null) {
            throw new IllegalArgumentException("Tower cannot be null.");
        }
        pieces.addAll(other.pieces);
    }

    public PieceColor getTopColor() {
        if (isEmpty()) {
            return null;
        }
        return pieces.get(pieces.size() - 1);
    }

    public List<PieceColor> getPieces() {
        return new ArrayList<>(pieces);
    }
}