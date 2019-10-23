package tp.pr1;

public class RegularShip {
    private int currentShips;
    private int row;
    private int column;
    private int life = 3;
    private Game game;
    private int points = 5;
    private boolean direction = false;

    public RegularShip(int x, int y){
        row = x;
        column = y;
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
            game.reduceNumberEnemies(1);
            game.addPoints(points);
        }
    }


    public int getPositionY(){
        return column;
    }

    public int getPositionX(){
        return row;
    }
}
