package pr2;

public abstract class Ship extends GameObject {
    public Ship(Game game, int x, int y, int life)
    {
        super(game, x, y, life);
        life = life;
    }


}
