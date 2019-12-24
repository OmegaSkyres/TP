package pr2;

public class DestroyerShip extends AlienShip{
    private Game game;
    private static int points = 10;
    private boolean bomb;
    private static boolean floor;
    private Bomb b;

    public DestroyerShip(Game game, int x, int y){
        super(game,x,y,1);
        floor = false;
        this.game = game;
    }

    public DestroyerShip() {

    }


    public String toString(){
        String nave;
        if (life == 0){
            nave = " ";
        }
        else nave = "D[" + life +"]";
        return nave;
    }

    @Override
    public String toStringified() {
        String dir = "left";
        if (!isLeft)
            dir = "right";
        return "D;" + x + "," + y + ";" + life + ";" + (3 - game.getCycle()%3) + ";" + dir + "\n";
    }

    @Override
    public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
        return null;
    }


    @Override
    public void onDelete() {
        game.receivePoints(points);
        AlienShip.setterRemaingAliens(AlienShip.getRemainingAliens()-1);
    }

    @Override
    public void computerAction() {
        if(IExecuteRandomActions.canGenerateRandomBomb(game) && !bomb){
            b = new Bomb(game,x,y,1,this);
            game.addObject(b);
            bomb = true;
        }
    }

    @Override
    public boolean performAttack(GameObject other) {
        return false;
    }


    public void enableBomb(){
        bomb = false;
    }



}
