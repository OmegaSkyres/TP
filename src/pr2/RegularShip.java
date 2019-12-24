package pr2;

public class RegularShip extends AlienShip {
    private Game game;
    private int points = 5;


    public RegularShip(Game game, int x, int y){
        super(game,x,y,3);
        this.game = game;
    }

    public RegularShip() {
        super();
    }

    @Override
    public void computerAction() {
        if(IExecuteRandomActions.canGenerateTransPos(game)){
            game.receivePoints(-5); //Para contrarrestar los que gano al destruir una Regular
            ExplosiveShip explosiveShip = new ExplosiveShip(game,x,y,3);
            game.addObject(explosiveShip);
            AlienShip.setRemaingAliens(1);
            life = 0;

        }
    }

    @Override
    public void onDelete() {
        game.receivePoints(points);
        AlienShip.setterRemaingAliens(AlienShip.getRemainingAliens()-1);
    }

    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "C[" + life +"]";
        return nave;
    }

    @Override
    public String toStringified() {
        String dir = "left";
        if (!isLeft)
            dir = "right";

        return "R;" + x + "," + y + ";" + life + ";" + (3 - game.getCycle()%3) + ";" + dir + "\n";
    }

    @Override
    public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
        return null;
    }

}
