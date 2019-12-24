package pr2;

public class Shockwave extends Weapon {
    public Shockwave(Game game, int x, int y, int life) {
        super(game, x, y, life);
    }

    public Shockwave() {
        super();
    }

    @Override
    public void computerAction() {

    }

    @Override
    public boolean performAttack(GameObject other) { //Revisar
        if(!isAlive()){
            return false;
        }
        else if(other.receiveShockWaveAttack(1)){
            life--;
        }
        return false;

    }
    public void onDelete() {
        game.enableShockWave();
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

    @Override
    public String toStringified() {
        return null;
    }

    @Override
    public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
        return null;
    }
}
