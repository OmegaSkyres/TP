package pr2;

import pr2.Exceptions.MissileInflightException;

public class SuperMissile extends Weapon {
	private int cost;
	private boolean active;
	private UCMShip player;

	public SuperMissile(Game game, int X, int Y) {
		super(game, X, Y, 2);
		cost = 20;
		// TODO Auto-generated constructor stub
	}

	public SuperMissile(Game game, int x, int y, UCMShip ship){
		super(game,x,y,2);
		cost = 20;
		this.game = game;
		active = false;
		player = ship;
	}

	@Override
	public void computerAction() {

	}

	@Override
	public void onDelete() {
		reset();
	}

	@Override
	public void move() {
		if(active){
			x--;
			if(x < 0){
				life = 0;
			}
		}
	}

	@Override
	public String toString() {
		if(active){
			return "^";
		}
		else{
			return " ";
		}
	}

	@Override
	public String toStringified() {
		return "M;" + x + "," + y +"\n";
	}

	@Override
	public boolean receiveBombAttack(int damage) {
		boolean ok;
		if(life > 0 && active){
			life = life - damage;
			ok = true;
		}
		else{
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean performAttack(GameObject other)
	{
		boolean ok = false;
		if(other.x == x && other.y == y && active){
			if(other.receiveMissileAttack(2)){
				life = 0;
				active = false;
				ok = true;
			}
		}
		return ok;
	}


	public void setEnable() {
		active = true;
	}

	public boolean isEnable() {
		return this.active;
	}

	public void reset() {
		active = false;
	}

	public boolean shoot() throws MissileInflightException {
		boolean ok = false;
		if (isEnable() && !isOut()) {
			throw new MissileInflightException("Ya hay un misil lanzado");
		} else {
			active = true;
			life = 2;
			x = player.x;
			y = player.y;
			ok = true;
		}
		return ok;
	}

	public int getCost() {
		return cost;
	}
}