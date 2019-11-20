package pr2;

public class DestroyerShip extends AlienShip{
    private int row;
    private int column;
    private static int life = 1;
    private Game game;
    private static int points = 10;
    private boolean bomb;

    public DestroyerShip(Game game, int x, int y, int life){
        super(game,x,y,1);
        row = x;
        column = y;
        life = life;
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

    @Override
    public void onDelete() {
        game.receivePoints(points);
    }

    @Override
    public boolean receiveShockWaveAttack(int damage) {
        recibeDamage(1);
    }

    @Override
    public void computerAction() {

    }

    public boolean bombLaunch() {
        return bomb;
    }


    public void setBombLaunch(boolean active){
        bomb = active;
    }



}
