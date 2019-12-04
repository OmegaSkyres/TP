package pr2;

public class RegularShip extends AlienShip {
    private int row;
    private int column;
    private static int life = 3;
    private Game game;
    private int points = 5;
    private static boolean floor;
    //private static String cadena = "[R]egular ship: Points: 5 - Harm: 0 - Shield: 2";


    public RegularShip(Game game, int x, int y){
        super(game,x,y,3);
        row = x;
        column = y;
        floor = false;
        this.game = game;
    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {
        game.receivePoints(points);
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

    public static boolean getOnTheFloor(){
        return floor;
    }
}
