package pr1;

import pr1.Game;

public class Ovni {
    private int row;
    private int column;
    private int life = 1;
    private Game game;
    private int points = 25;
    private boolean active;
    private boolean direction;

    public Ovni(int x, int y){
        row = x;
        column = y;
        active = false;
        direction = false;
    }

    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "O[" + life + "]";
        return nave ;
    }

    public void moveRight() {
        if (column < Game.COLS - 1) column++;
    }

    public void moveLeft() {
        if (column > 0) column--;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void incrementPositionY(){
        this.column++;
    }

    public void incrementPositionX(){
        this.row++;
    }

    public void move(){
        if(getPositionX() == 0){
          deleteOvni();
        }
        else moveLeft();
    }

    private void deleteOvni() {
        column = 9;
    }

    public int getPoints() {
        return points;
    }
}
