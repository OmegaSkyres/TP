package pr2;

import pr2.Exceptions.InsufficientPointsException;
import pr2.Exceptions.MissileInflightException;

public class UCMShip extends Ship {
	private int points;
	private Game game;
	private boolean posibilityshockwave;
	private Missile missile;
	private SuperMissile superMissile;
	private Shockwave s;

	
	public UCMShip(Game game, int x, int y) {
		super(game,y,x,3);
		points = 0;
		life = 3;
		posibilityshockwave = false;
		this.game = game;
		missile = new Missile(game,y-1,x,this);
		superMissile = new SuperMissile(game,y-1,x,this);
		}


    public String toString(){
    	String nave;
		if (life == 0) {
			nave = "!xx!";
		}
		else {
			nave = "^__^";
		}
		return nave;
    }

	@Override
	public String toStringified() {
		return "P;" + x + "," + y + ";" + life + ";" + points +
				";" + posibilityshockwave + ";" +"\n";
	}

	public void moveRight() {
    	if (y < Game.DIM_Y - 1) y++;
    }
    
    public void moveLeft() {
    	if (y > 0) y--;
    }

    public boolean isOnPosition(int newx, int newy){
		if(newx == x && newy == y) return true;
		else return false;
	}

	@Override
	public void move() {

	}

	public boolean move(int numCells){
		boolean ok = true;
		if(y + numCells < 0 || y + numCells > 8){
			ok = false;
		}
		else{
			y = y + numCells;
		}
		return ok;
	}

	@Override
	public void onDelete() { //no hace nada

	}

	@Override
	public void computerAction() { //No hace nada

	}

	@Override
	public boolean receiveBombAttack(int damage) {
		boolean ok = false;
			if(this.life > 0){
				ok = true;
				recibeDamage(damage);
			}
		return ok;
	}

	public boolean shockWave(){
		boolean ok = false;
		if(posibilityshockwave) {
			executeShockwave();
			ok = true;
		}
		return ok;
	}

	public void executeShockwave(){
		s = new Shockwave(game,0,0,AlienShip.getRemainingAliens());
		game.addObject(s);

	}


	public boolean shootMissile() throws MissileInflightException {
		boolean ok = false;
		if (missile.isEnable() && !isOut()) {
			throw new MissileInflightException("Ya hay un misil lanzado");
		}
		else{
			ok = true;
			game.addObject(missile);
			missile.shoot();
		}
		return ok;
	}


	public void recibeDamage(int damage){
		this.life = this.life - damage;
	}

    public String stateToString() {
		return "Lifes: " + life + "\n" + "Points: " + points + "\n" + "Superpower: " + posibilityshockwave + "\n";
    }

    public void setShockwave(boolean active){
		posibilityshockwave = active;
	}

	public void setPoints(int points) {
		this.points += points;
	}

	public void enableMissile() {
		missile.setEnable();
	}

	public boolean buySuperMissile() throws MissileInflightException, InsufficientPointsException {
		boolean ok = false;
		if(!superMissile.isEnable()){
			if (points >= 20) {
				ok = true;
				System.out.println(" Missile acquired");
				game.addObject(superMissile);
				superMissile.shoot();
				points -= 20;
			}

			else
				throw new InsufficientPointsException("Not enough points!!!");

		}
		else{
			throw new MissileInflightException("Ya hay un misil lanzado!!!");
		}
		return ok;

	}
}
