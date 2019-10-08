package tp.pr1;

public class UCMShip {

    public int life; //TODO PREGUNTAR POR ESTE PARAMETRO SI ES PUBLICO O PRIVADO
	private static final int row = 7;
	private int column;
	
	public UCMShip() {
		column = 4;
		life = 3;
	}
	
	public int UCMShipPosition() {
		return column;
	}

    public String toString(){
    	String nave;
    	return nave = "^__^";
    }
    
    public void moveRight() {
    	if (column < Game.COLS - 1) column++;
    }
    
    public void moveLeft() {
    	if (column > 0) column--;
    }
    
    public boolean isAlive() {
    	if(life == 0) return false;
    	else return true;
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
