package pr2;

public class Missile extends Weapon{
	private Game game;
	private UCMShip player;
	private int row;
	private int column;
	public boolean active;
	
	public Missile(Game game, int x, int y) {
		super(game,x,y,1);
		game = game;
		this.player = new UCMShip(game, game.DIM_X / 2, game.DIM_Y - 1);
		row = player.UCMShipPositionX() - 1;
		column = player.UCMShipPositionY();
		active = false;
	}

	public int missilePositionX() {
		return row;
	}
	
	public int missilePositionY() {
		return column;
	}

	public int setPositionX(int x){
		return row = x;
	}

	public int setPositionY(int y){
		return column = y;
	}

	@Override
	public void move() {
		row--;
	}
	@Override
	public String toString() {
		return "oo";
	}
	
	public void setEnable() {
		active = true;
	}
	
	public boolean isEnable() {
		return this.active;
	}
	
	public void reset() {
		row = player.UCMShipPositionX() - 1;
		column = player.UCMShipPositionY();
	}
}
