package pr1;

import pr1.Game;

public class DestroyerShip {
    private int row;
    private int column;
    private int life = 1;
    private Game game;
    private int points = 10;

    public DestroyerShip(int x, int y){
        row = x;
        column = y;
    }

    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "D[" + life +"]";
        return nave;
    }

    public void moveRight() {
        if (column < Game.COLS - 1) column++;
    }

    public void moveLeft() {
        if (column > 0) column--;
    }

    public void move(boolean direction){
        if(direction){
            moveRight();
        }
        else{
            moveLeft();
        }
    }
    public void incrementPositionX(){
        this.row++;
    }


    public boolean isDead() {
        if(life == 0) return false;
        else return true;
    }

    public void recibeDamage(int damage){
        if(this.life > 0){
            this.life = this.life - damage;
        }
    }

    public int getPositionX(){
        return row;
    }

    public int getPositionY(){
        return column;
    }

    public int getPoints() {
        return points;
    }
}
