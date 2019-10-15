package tp.pr1;

public class Missile {
	private UCMShip player;
	public int x;
	private int y;
	public boolean active;
	
	public Missile() {
		this.player = new UCMShip();
		x = player.UCMShipPositionX();
		y = Game.ROWS - 2;
		active = false;
	}
	
	public int missileMove() {
		return y--;
	}

	public int missilePositionX() {
		return x;
	}
	
	public int missilePositionY() {
		return y;
	}
	
	public String toString() {
		return "oo";
	}
	
	public void setEnable() {
		active = true;
	}
	
	public boolean getEnable() {
		return this.active;
	}
	
	public void reset() {
		y = Game.ROWS - 2;
	}
}
