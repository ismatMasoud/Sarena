package javaFx.Sarena.model;

public class Board {

    public static final int SIZE = 36;
    public static final int ROWS = 6;
    public static final int COLS = 6;

    private final Cell[] cells;

    public Board() {
        cells = new Cell[SIZE];

        for (int i = 0; i < SIZE; i++) {
            cells[i] = new Cell(i);
        }
    }

    public void initialize() {
        PieceColor[] colors = PieceColor.values();

        for (int i = 0; i < SIZE; i++) {
            Tower tower = new Tower();
            tower.push(colors[i % colors.length]);
            cells[i].setTower(tower);
        }
    }

    public Cell getCell(int index) {
        validateIndex(index);
        return cells[index];
    }

    public int[] getNeighbors(int index) {
        validateIndex(index);

        int row = index / COLS;
        int col = index % COLS;

        int[] temp = new int[4];
        int count = 0;

        if (row > 0) {
            temp[count] = index - COLS;
            count++;
        }

        if (row < ROWS - 1) {
            temp[count] = index + COLS;
            count++;
        }

        if (col > 0) {
            temp[count] = index - 1;
            count++;
        }

        if (col < COLS - 1) {
            temp[count] = index + 1;
            count++;
        }

        int[] neighbors = new int[count];
        for (int i = 0; i < count; i++) {
            neighbors[i] = temp[i];
        }

        return neighbors;
    }

    public boolean areNeighbors(int fromIndex, int toIndex) {
        validateIndex(fromIndex);
        validateIndex(toIndex);

        int[] neighbors = getNeighbors(fromIndex);

        for (int neighbor : neighbors) {
            if (neighbor == toIndex) {
                return true;
            }
        }

        return false;
    }

    public void executeMove(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }

        int fromIndex = move.getFromIndex();
        int toIndex = move.getToIndex();

        validateIndex(fromIndex);
        validateIndex(toIndex);

        if (fromIndex == toIndex) {
            throw new IllegalArgumentException("Start and destination cannot be the same.");
        }

        Cell fromCell = getCell(fromIndex);
        Cell toCell = getCell(toIndex);

        if (fromCell.isEmpty()) {
            throw new IllegalArgumentException("Start cell is empty.");
        }

        Tower movingTower = fromCell.getTower();
        Tower targetTower = toCell.getTower();

        targetTower.pushAll(movingTower);
        fromCell.setTower(new Tower());
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= SIZE) {
            throw new IllegalArgumentException("Index must be between 0 and 35.");
        }
    }

    public Cell[] getCells() {
        return cells;
    }
}