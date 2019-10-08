package tp.pr1;

public class Ovni {
    private int file = 0;
    private int column = 8;
    private int life = 1;
    private Game game;
    private int points = 25;

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
}
