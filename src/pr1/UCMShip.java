package pr1;

import pr1.Game;

public class UCMShip {

    public int life;
	private int row;
	private int column;
	private Game game;
	
	public UCMShip(Game game, int x, int y) {
		game = game;
		row = x;
		column = y;
		life = 3;
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

	public void recibeDamage(int damage){
		if(this.life > 0){
			this.life = this.life - damage;
		}
	}

	public boolean isAlive() {
		if(life == 0){
			return false;
		}
		else return true;
	}
}
