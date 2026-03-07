package javaFx.Sarena.model;

public class Cell {

    private final int index;

    private Tower tower;

    public Cell(int index){
        this.index = index;
        this.tower = new Tower();
    }

    public int getIndex(){
        return index;
    }

    public Tower getTower(){
        return tower;
    }

    public void setTower(Tower tower){
        this.tower = tower;
    }
    public boolean isEmpty(){
        return tower.isEmpty();
    }
}