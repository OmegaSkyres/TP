package pr2;

public abstract class Ship extends GameObject {
    public Ship(Game game, int x, int y, int live) {
        super(game, x, y, live);
    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {

    }
}
