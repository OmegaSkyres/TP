package tp.pr1;

public class DestroyerShip {
    private int file;
    private int column;
    private int life = 1;
    private Game game;
    private int points = 10;

    public DestroyerShip(){

    }

    public String toString(){
        String nave;
        return nave = "D[" + life +"]";
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
