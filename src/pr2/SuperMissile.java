package pr2;

public class SuperMissile extends Weapon {
	private Game game;
	private UCMShip player;
	private int row;
	private int column;
	public boolean active;

	public SuperMissile(Game game, int x, int y, int life) {
		super(game, x, y, life);
		// TODO Auto-generated constructor stub
		game = game;
		active = false;
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
