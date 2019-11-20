package pr2;

public class UCMShip extends Ship {

    public int life;
	private int row;
	public int points;
	private int column;
	private Game game;
	private boolean shockwave;
	
	public UCMShip(Game game, int x, int y) {
		super(game,x,y,3);
		points = 0;
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

	@Override
	public void onDelete() { //Que hace realmente??

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
		shockwave = active;
	}

}
