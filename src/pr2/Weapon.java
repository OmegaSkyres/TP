package pr2;

public abstract class Weapon extends GameObject {
    private Object Bomb;

    public Weapon(Game game, int x, int y, int live) {
        super(game, x, y, live);
    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {

    }
}
