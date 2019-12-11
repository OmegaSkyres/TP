package pr2;

public class ExplosiveShip extends AlienShip{
    private int points = 5;

	public ExplosiveShip(Game game, int x, int y, int life) {
		super(game, x, y, 3);
	}

    @Override
    public void computerAction() {

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
	    this.game.damageNearbyObjects(x, y);
	    AlienShip.setterRemaingAliens(AlienShip.getRemainingAliens()-1);
    }

}
