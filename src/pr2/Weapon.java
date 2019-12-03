package pr2;

public abstract class Weapon extends GameObject {
    public Weapon(Game game, int x, int y, int live) {
        super(game, x, y, live);
    }

    @Override
    public boolean performAttack(GameObject other) {
        if(isAlive() || other.isAlive()){
            return false;
        }
        else{
            return true; //Revisar
        }
    }
}
