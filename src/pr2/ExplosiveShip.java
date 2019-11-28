package pr2;

public class ExplosiveShip extends AlienShip{
	private int row;
	private int column;
	private int life = 1;
	private int points = 0;
	private boolean active;

	public ExplosiveShip(Game game, int x, int y, int live) {
		super(game, x, y, 1);
		// TODO Auto-generated constructor stub
		row = x;
		column = y;
		active = false;
	}
	
    public void moveRight() {
        if (column < Game.DIM_Y - 1) column++;
    }

    public void moveLeft() {
        if (column > 0) column--;
    }
    public void move(boolean direction){
            if(direction){
                moveRight();
            }
            else{
                moveLeft();
            }
        }

    public boolean isDead() {
        if(life == 0) return true;
        else return false;
    }

    public void recibeDamage(int damage){
        if(this.life > 0){
            this.life = this.life - damage;
        }
    }

    public int getPositionY(){
        return column;
    }

    public int getPositionX(){
        return row;
    }

    public void incrementPositionX(){
        this.row++;
    }

    public int getPoints() {
        return points;
    }
	
	
	public String toString() {
		String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "E[" + life +"]";
        return nave;
	}
	
	public void computerAction() {
		if(IExecuteRandomActions.canGenerateTransPos(game)) {
			if(!active) active = true;
		}
	}

    @Override
    public void onDelete() {

    }

}
