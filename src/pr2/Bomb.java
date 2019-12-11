package pr2;

public class Bomb extends Weapon implements IExecuteRandomActions {
    private boolean active;
    private DestroyerShip ship;

    public Bomb(Game game, int x, int y, int life, DestroyerShip newShip){
        super(game,x,y,1);
        active = true;
        ship = newShip;

    }
    @Override
    public String toString(){
        String bomb = " ";
        if(active && life > 0){
            bomb = ".";
        }
        else {
            bomb = " ";
        }
        return bomb;
    }

    @Override
    public String toStringified() {
        return "B;" + x + "," + y +"\n";
    }


    @Override
    public void move() {
        if(active){
            x++;
            if(x > 7){
                life = 0;
            }
        }
    }


    @Override
    public void computerAction() {

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
        boolean ok = false;
        if(other.x == x && other.y == y) {
            if(other.receiveBombAttack(1)){
                life = 0;
                ok = true;
            }

        }
        return ok;
    }
}
