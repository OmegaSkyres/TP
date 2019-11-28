package pr2;

import pr2.Exceptions.MissileInflightException;

public class UCMShip extends Ship {

    public int life;
	private int row;
	private int points;
	private int column;
	private Game game;
	private boolean posibilityshockwave;
	private Missile missile;

	
	public UCMShip(Game game, int x, int y) {
		super(game,x,y,3);
		points = 0;
		posibilityshockwave = false;
		missile = new Missile(game,x,y);
		game.addObject(missile);
		}



	public int UCMShipPositionY() {
		return column;
	}

	public int UCMShipPositionX(){
		return row;
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
    	if (column < Game.DIM_Y - 1) column++;
    }
    
    public void moveLeft() {
    	if (column > 0) column--;
    }

    public boolean isOnPosition(int x, int y){
		if(x == row && y == column) return true;
		else return false;
	}

	@Override
	public void move() {

	}

	public boolean move(int numCells){
		boolean ok = true;
		if(column + numCells < 0 || column + numCells > 9){
			ok = false;
		}
		else{
			column = column + numCells;
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
