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
		row = x;
		column = y;
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
	public void computerAction() { //No hace nada

	}

	@Override
	public void move() {
		row--;
	}
	@Override
	public String toString() {
		return "oo";
	}

	@Override
	public void onDelete() {
		game.enableMissile();
		reset();
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

	public void shoot() {
		if (isEnable()) {
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		} else {
			setPositionX(player.UCMShipPositionX() + 1);
			setPositionY(player.UCMShipPositionY());
		}
	}

	@Override
	public boolean receiveBombAttack(int damage) {
		boolean ok;
		if(life > 0){
			life = life - damage;
			ok = true;
		}
		else{
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean performAttack(GameObject other) {
		return (other.receiveMissileAttack(1));
	}
}
