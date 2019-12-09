package pr2;

import pr2.Exceptions.MissileInflightException;

public class Missile extends Weapon{
	private Game game;
	private UCMShip player;
	public boolean active;
	
	public Missile(Game game, int x, int y) {
		super(game,x,y,1);
		game = game;
		active = false;
	}

	public Missile(Game game, int x, int y, UCMShip ship){
		super(game,x,y,1);
		this.game = game;
		active = false;
		player = ship;
	}

	@Override
	public void computerAction() { //No hace nada

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
			return "oo";
		}
		else{
			return " ";
		}
	}

	@Override
	public void onDelete() {
		reset();
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

	public void shoot() {
			active = true;
			life = 1;
			x = player.x;
			y = player.y;
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
			 if(other.receiveMissileAttack(1)){
				 life = 0;
				 ok = true;
			 }
		}
		return ok;
	}
}
