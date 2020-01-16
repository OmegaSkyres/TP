package pr2;

public class ExplosiveShip extends AlienShip{
    private int points = 5;

	public ExplosiveShip(Game game, int x, int y, int life) {
		super(game, x, y, 1);

	}

    public ExplosiveShip() {

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
    public String toStringified() {
        String dir = "left";
        if (!isLeft)
            dir = "right";

        return "E;" + x + "," + y + ";" + life + ";" + (3 - game.getCycle()%3) + ";" + dir + "\n";
    }

    @Override
    public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
        return null;
    }

    @Override
    public void onDelete() {
	    GameObject other;
        for(int i = -1;i < 2; i++){
            for(int j = -1; j < 2; j++){
                if((i != 0) || (j!=0)) {
                    other = game.board.getObjectInPosition(x+i,y+j); //Para poder hacer esto he tenido que hacer este metodo publico.
                    if(other != null){
                        other.receiveExplosionAttack(1);
                    }
                }
            }
        }
        game.receivePoints(points)  ;
	    AlienShip.setterRemaingAliens(AlienShip.getRemainingAliens()-1);
    }

}

