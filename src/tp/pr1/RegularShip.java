package tp.pr1;

public class RegularShip {
    private RegularShip[] ships;
    private int currentShips;
    private int row;
    private int column;
    private int life = 2;
    private Game game;
    private int points = 5;

    public RegularShip(){
        this.column = 4;
    }

    public String toString(){
        String nave;
        return nave = "C[" + life +"]";
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
        else{
            deleteShip();
            game.po
        }
    }

    public String deleteShip() {
        String nave;
        return " ";
    }

    public int getPositionY(){
        return column;
    }

    public int getPositionX(){
        return row;
    }

}
