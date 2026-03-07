package javaFx.Sarena.model;


public class Board {

    public static final int SIZE = 36;

    private final Cell[] cells;

    public Board() {
        cells = new Cell[SIZE];

        // Maak alle 36 vakjes van het bord aan
        for (int i = 0; i < SIZE; i++) {
            cells[i] = new Cell(i);
        }
    }

    // Zet een eenvoudige startopstelling op het bord
    public void initialize() {
        PieceColor[] colors = PieceColor.values();

        for (int i = 0; i < SIZE; i++) {
            Tower tower = new Tower();

            // Voor nu krijgt elk vakje 1 kleur
            tower.push(colors[i % colors.length]);

            cells[i].setTower(tower);
        }
    }

    // Geef een bepaalde cell terug
    public Cell getCell(int index) {
        if (index < 0 || index >= SIZE) {
            throw new IllegalArgumentException("Index must be between 0 and 35");
        }
        return cells[index];
    }

    // Voer een move uit: verplaats tower van fromCell naar toCell
    public void executeMove(Move move) {
        int fromIndex = move.getFromIndex();
        int toIndex = move.getToIndex();

        Cell fromCell = getCell(fromIndex);
        Cell toCell = getCell(toIndex);

        Tower movingTower = fromCell.getTower();
        Tower targetTower = toCell.getTower();

        // Voeg de stukken van de startcel toe aan de doelcel
        targetTower.pushAll(movingTower);

        // Maak de startcel leeg
        fromCell.setTower(new Tower());
    }

    public Cell[] getCells() {
        return cells;
    }
}