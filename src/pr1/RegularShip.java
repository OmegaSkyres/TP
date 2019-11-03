package pr1;

import pr1.Game;

public class RegularShip {
    private int row;
    private int column;
    private int life = 3;
    private Game game;
    private int points = 5;


    public RegularShip(int x, int y){
        row = x;
        column = y;
    }

    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "C[" + life +"]";
        return nave;
    }

    public void moveRight() {
        if (column < Game.DIM_Y - 1) column++;
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

    public boolean isDead() {
        if(life == 0) return true;
        else return false;
    }

    public void recibeDamage(int damage){
        if(this.life > 0){
            this.life = this.life - damage;
        }
    }


    public int getPositionY(){
        return column;
    }

    public int getPositionX(){
        return row;
    }

    public void incrementPositionX(){
        this.row++;
    }

    public int getPoints() {
        return points;
    }
}
