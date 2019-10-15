package tp.pr1;

public class Missile {
	private UCMShip player;
	public int row;
	private int column;
	public boolean active;
	
	public Missile() {
		this.player = new UCMShip();
		row = player.UCMShipPositionX();
		column = Game.ROWS - 2;
		active = false;
	}
	
	public int missileMove() {
		return column--;
	}

	public int missilePositionX() {
		return row;
	}
	
	public int missilePositionY() {
		return column;
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
		column = Game.ROWS - 2;
	}
}
