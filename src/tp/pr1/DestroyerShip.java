package tp.pr1;

public class DestroyerShip {
    private int row;
    private int column;
    private int life = 1;
    private Game game;
    private int points = 10;
    private boolean direction = false;

    public DestroyerShip(int x, int y){
        row = x;
        column = y;
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

    public void move(){
        if(getPositionY() == 0 || getPositionY() == 8){
            incrementPositionX();
            direction = true;
        }
        else if(direction){
            moveRight();
        }
        else moveLeft();
    }
    public void incrementPositionX(){
        this.row++;
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
            game.reduceNumberEnemies(1);
            game.addPoints(points);
        }
    }


    public String deleteShip() {
        String nave;
        return " ";
    }
    public int getPositionX(){
        return row;
    }

    public int getPositionY(){
        return column;
    }
}
