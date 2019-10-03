package tp.pr1;

public class UCMShip {
    
    private int resistance;
	private static final int row = 7;
	private int col;
	
	public UCMShip() {
		col = 4;
		resistance = 3;	
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
    	if(resistance == 0) return false;
    	else return true;
    }
}
