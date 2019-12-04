package pr2;

import pr2.Exceptions.MissileInflightException;

public class UCMShip extends Ship {

    public int life;
	private int points;
	private Game game;
	private boolean posibilityshockwave;
	private Missile missile;

	
	public UCMShip(Game game, int x, int y) {
		super(game,y,x,3);
		points = 0;
		life = 3;
		posibilityshockwave = false;
		missile = new Missile(game,y,x);
		game.addObject(missile);
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
		Shockwave s = new Shockwave(game,0,0,1);
		game.addObject(s);

	}


	public void shootMissile() throws MissileInflightException {
		missile.shoot();
	}

	public void recibeDamage(int damage){
		this.life = this.life - damage;
	}

	public boolean isAlive() {
		if(life == 0){
			return false;
		}
		else return true;
	}

    public String stateToString() {
		return "Lifes: " + life + "\n" + "Points: " + points + "\n";
    }

    public void setShockwave(boolean active){
		posibilityshockwave = active;
	}

	public void setPoints(int points) {
		points += points;
	}

	public void enableMissile() {
		missile.setEnable();
	}


}
