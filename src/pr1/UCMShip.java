package pr1;

import pr1.Game;

public class UCMShip {

    public int life;
	private int row;
	private int column;
	
	public UCMShip() {
		row = 7;
		column = 4;
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
    	return nave = "^__^";
    }

    public String deathString(){
			return "!xx!";
	}
    
    public void moveRight() {
    	if (column < Game.COLS - 1) column++;
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
}
