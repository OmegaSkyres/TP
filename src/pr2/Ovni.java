package pr2;

public class Ovni extends EnemyShip implements IExecuteRandomActions {
    private int row;
    private int column;
    private static int life;
    private Game game;
    private int points = 25;
    private boolean active;

    public Ovni(Game game, int x, int y, int life){
        super(game,x,y,life);
        game = game;
        row = x;
        column = y;
        life = life;
        active = false;
    }

    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "O[" + life + "]";
        return nave ;
    }

    public void moveLeft() {
        if (column > 0) column--;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void incrementPositionY(){
        this.column++;
    }

    public void move(){
        if(getPositionY() == 0){
          deleteOvni();
          
        }
        else {
        	moveLeft();
        	System.out.println(column);
        }
    }

    public int getPoints() {
        return points;
    }

    public int getPositionY() {
        return column;
    }

    @Override
    public void computerAction() {
        if(IExecuteRandomActions.canGenerateRandomOvni(game)){
            if(!active){
                active = true;
            }
        }
    }

    @Override
    public void onDelete() {
        row = 0;
        column = 8;
        active = false;
        life = 1;
        game.enableShockWave();
        game.receivePoints(points);
    }
}
