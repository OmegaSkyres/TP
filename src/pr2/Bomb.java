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
        if(!active){
            if(IExecuteRandomActions.canGenerateRandomBomb(game)){
                active = true;
            }
        }
    }

    @Override
    public void onDelete() {
        ship.enableBomb();
    }

    @Override
    public boolean receiveShockWaveAttack(int damage) {
        boolean ok;
        if(life > 0){
            life = life - damage;
            ok = true;
        }
        else{
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean receiveMissileAttack(int damage) {
        boolean ok;
        if(life > 0){
            life = life - damage;
            ok = true;
        }
        else{
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean performAttack(GameObject other) {
        return other.receiveBombAttack(1);
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
