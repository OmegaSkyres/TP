package pr2;

public class Bomb extends Weapon implements IExecuteRandomActions {
    private int row;
    private int column;
    private boolean active;
    private int life;
    private DestroyerShip ship;

    public Bomb(Game game, int x, int y, int life, DestroyerShip newShip){
        super(game,x,y,1);
        row = x + 1;
        column = y;
        life = life;
        active = true;
        ship = newShip;

    }
    @Override
    public String toString(){
        String bomb = " ";
        if(active){
            bomb = ".";
        }
        else {
            bomb = " ";
        }
        return bomb;
    }


    @Override
    public void move() {
        row++;
    }


    @Override
    public void computerAction() {
        if(IExecuteRandomActions.canGenerateRandomBomb(game)){
            if(!active){
                active = true;
            }
        };
    }

    @Override
    public void onDelete() {

    }

    public int getPositionX() {
        return row;
    }

    public int getPositionY() {
        return column;
    }

    public void setPositionX(int positionX) {
        this.row = positionX;
    }

    public void setPositionY(int positionY) {
        this.column = positionY;
    }


    public boolean isActive() {
        return active;
    }

    public void resetBomb() {
        active = false;
    }
}
