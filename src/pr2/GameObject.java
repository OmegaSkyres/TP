package pr2;

public abstract class GameObject implements IAttack {
    protected int x;
    protected int y;
    protected int life;
    protected Game game;

    public GameObject( Game game, int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.life = life;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return this.life > 0;
    }

    public int getLive() {
        return this.life;
    }

    public boolean isOnPosition(int x, int y) {
        return this.x == x && this.y == y;
    }

    public void getDamage (int damage) {
        this.life = damage >= this.life ? 0 : this.life - damage;
    }

    public boolean isOut() {
        return !game.isOnBoard(x, y);
    }
    public boolean receiveExplosionAttack(int damage) {
        getDamage(damage);
        return true;
    }

    public abstract void computerAction();
    public abstract void onDelete();
    public abstract void move();
    public abstract String toString();

    public abstract String toStringified();

    public abstract GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier);
}

