package pr2;

public class Shockwave extends Weapon {
    private boolean a;
    public Shockwave(Game game, int x, int y, int life) {
        super(game, x, y, life);
    }

    @Override
    public void computerAction() {

    }

    @Override
    public boolean performAttack(GameObject other) { //Revisar
        if(isAlive()){
            return false;
        }
        else if(other.receiveShockWaveAttack(1)){
            life = 0;
            return true;
        }
        else{
            return true;
        }
    }
    public void onDelete() {
        game.enableShockWave();
        game.delete(x, y);

    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }
    public boolean performAttack() {
        //game.damageAllShips();
        return true;

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
