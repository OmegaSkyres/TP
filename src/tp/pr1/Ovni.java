package tp.pr1;

public class Ovni {
    private int row = 0;
    private int column = 8;
    private int life = 1;
    private Game game;
    private int points = 25;
    private boolean active;

    public Ovni(){

    }

    public String toString(){
        String nave;
        return nave = "C[3]";
    }

    public void moveRight() {
        if (column < Game.COLS - 1) column++;
    }

    public void moveLeft() {
        if (column > 0) column--;
    }

    public boolean isAlive() {
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
        if(active){
            return true;
        }
        else return false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
