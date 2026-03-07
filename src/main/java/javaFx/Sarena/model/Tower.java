package javaFx.Sarena.model;

import java.util.ArrayList;
import java.util.List;

public  class Tower {
    private final List<PieceColor> pieces = new ArrayList<>();

    public int height(){
        return pieces.size();
    }

    public boolean isEmpty(){
        return pieces.isEmpty();
    }

    public void push(PieceColor color){
        pieces.add(color);
    }

    public void pushAll(Tower others){
        pieces.addAll(others.pieces);
    }

    public List<PieceColor> getPieces(){
        return pieces;
    }
}