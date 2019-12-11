package pr2;

public class Ovni extends EnemyShip implements IExecuteRandomActions {
    private int row;
    private int column;
    private static int life = 1;
    private Game game;
    private int points = 25;
    private boolean active;

    public Ovni(Game game, int x, int y){
        super(game,x,y,life);
        this.game = game;
        active = false;
    }

    public String toString(){
        String nave;
        if (life == 0 || !active){
            nave = " ";
        }
        else nave = "O[" + life + "]";
        return nave ;
    }

    @Override
    public String toStringified() {
        return "O;" + x + "," + y +"\n";
    }

    public void moveLeft() {
        if (y > 0) y--;
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
        if(active){
            if(y == 0){
                deleteOvni();
            }
            else {
                moveLeft();
            }
        }
    }

    private void deleteOvni() {
        x = 0;
        y = 9;
        active = false;
        life = 1;
    }


    @Override
    public void computerAction() {
        if(!active){
            if(IExecuteRandomActions.canGenerateRandomOvni(game)){
                active = true;
            }
        }
    }

    @Override
    public void onDelete() {
        deleteOvni();
        game.enableShockWave();
        game.receivePoints(points);
    }
}
