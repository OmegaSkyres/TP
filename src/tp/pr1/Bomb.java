package tp.pr1;

public class Bomb {
    private DestroyerShip ship;
    private boolean active;
    private int row;
    private int column;

    public Bomb(){
        this.ship = new DestroyerShip();
        row = ship.getPositionX();
        column = Game.ROWS - 2;
        active = false;

    }

    public String toString(){
        String bomb;
        return bomb = ".";
    }

    public void move() {
        if (column < Game.COLS - 1) column++;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPositionX() {
        return row;
    }

    public int getPositionY() {
        return column;
    }

    public void setPositionX(int positionX) {
        this.row = positionX;
    }

    public void setPositionY(int positionY) {
        this.column = positionY;
    }

    public boolean isActive() {
        return active;
    }

    public void reset() {

    }
}
