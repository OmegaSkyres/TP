package pr2;

public class RegularShip extends AlienShip {
    private Game game;
    private int points = 5;
    private static boolean floor;
    //private static String cadena = "[R]egular ship: Points: 5 - Harm: 0 - Shield: 2";


    public RegularShip(Game game, int x, int y){
        super(game,x,y,3);



        floor = false;
        this.game = game;
    }

    @Override
    public void computerAction() {
        /*
        if(IExecuteRandomActions.canGenerateTransPos(game)){
            ExplosiveShip explosiveShip = new ExplosiveShip(game,x,y,3);
            game.addObject(explosiveShip);
            life = 0;

        }
         */
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

}
