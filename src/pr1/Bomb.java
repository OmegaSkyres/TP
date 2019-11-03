package pr1;

import pr1.Game;

public class Bomb {
    private int row;
    private int column;
    private boolean active;
    private DestroyerShip ship;

    public Bomb(int x, int y, DestroyerShip newShip){
        row = x + 1;
        column = y;
        active = true;
        ship = newShip;

    }

    public String toString(){
        String bomb = " ";
        if(active){
            bomb = ".";
        }
        else {
            bomb = " ";
        }
        return bomb;
    }

    public void move() {
        row++;
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

    public void resetBomb() {
        active = false;
    }
}
