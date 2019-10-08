package tp.pr1;

public class UCMShip {

    private int life;
	private static final int row = 7;
	private int col;
	
	public UCMShip() {
		col = 4;
		life = 3;
	}
	
	public int UCMShipPosition() {
		return col;
	}

    public String toString(){
    	String nave;
    	return nave = "^__^";
    }
    
    public void moveRight() {
    	if (col < Game.COLS - 1) col++;
    }
    
    public void moveLeft() {
    	if (col > 0) col--;
    }
    
    public boolean isAlive() {
    	if(life == 0) return false;
    	else return true;
    }

    public boolean isOnPosition(int x, int y){
		if(x == row && y == col) return true;
		else return false;
	}

	public void recibeDamage(int damage){
		if(this.life > 0){
			this.life = this.life - damage;
		}
	}
}
