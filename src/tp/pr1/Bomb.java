package tp.pr1;

public class Bomb {
    private DestroyerShip ship;
    private boolean active;
    private int positionX;
    private int positionY;

    public Bomb(){

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
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isActive() {
        return active;
    }
}
