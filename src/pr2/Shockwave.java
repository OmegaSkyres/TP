package pr2;

public class Shockwave extends Weapon {
    private boolean a;
    public Shockwave(Game game, int x, int y, int life) {
        super(game, x, y, life);
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

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {

    }

    @Override
    public void move() {

    }

    @Override
    public String toString() {
        return null;
    }


}
