package pr1;

import pr1.Game;

public class DestroyerShip extends AlienShip{
    private int row;
    private int column;
    private int life = 1;
    private Game game;
    private int points = 10;
    private boolean launch;

    public DestroyerShip(int x, int y){
        super(game,x,y,1);
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
    public void incrementPositionX(){
        this.row++;
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

    public int getPositionX(){
        return row;
    }

    public int getPositionY(){
        return column;
    }

    public int getPoints() {
        return points;
    }

    public boolean bombLaunch() {
        return launch;
    }


    public void setBombLaunch(boolean effect){
        launch = effect;
    }

}
