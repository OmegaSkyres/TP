package pr2;

import static pr2.Game.DIM_Y;

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
		game = game;
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
		x = game.DIM_X / 2;
		y = DIM_Y - 1;
	}

	public void shoot() {
		if (isEnable()) {
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		} else {
			active = true;
			y = player.y;
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
	public boolean performAttack(GameObject other)
	{
		if(other.x == x && other.y == y){
			return (other.receiveMissileAttack(1));
		}
		else return false;
	}
}
