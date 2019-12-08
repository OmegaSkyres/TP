package pr2;

public class ExplosiveShip extends AlienShip{
	private int life;
    private int points = 5;
	private boolean active;

	public ExplosiveShip(Game game, int x, int y, int life) {
		super(game, x, y, 3);
		this.life = life;
		active = false;
	}

    @Override
    public void computerAction() {

    }

    public void moveRight() {
        if (y < Game.DIM_Y - 1) y++;
    }

    public void moveLeft() {
        if (y > 0) y--;
    }
    public void move(boolean direction){
            if(direction){
                moveRight();
            }
            else{
                moveLeft();
            }
        }

    public void recibeDamage(int damage){
        if(this.life > 0){
            this.life = this.life - damage;
        }
    }
	
	public String toString() {
		String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "E[" + life +"]";
        return nave;
	}

    @Override
    public void onDelete() {
        if (active) {
            this.game.damageNearbyObjects(x, y);
            AlienShip.setterRemaingAliens(AlienShip.getRemainingAliens()-1);
        }


    }

}
