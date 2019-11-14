package pr2;

public abstract class AlienShip extends EnemyShip {
    private static int numberEnemies;
    private boolean direction;
    public AlienShip(Game game, int x, int y, int live) {
        super(game, x, y, live);
        numberEnemies = 0;
        direction = false;
    }

    public static int getRemainingAliens() {
        return numberEnemies;
    }

    public static boolean allDead() {
        boolean ok = false;
        if(numberEnemies == 0){
            ok = true;
        }
        return ok;
    }

    public void move(){ //TODO COMO MIRO AHORA SI HAY UN BORDE O NO YA QUE DEBO COMPROBAR POR EL MOVIMIENTO EN COMUN
        if(!border() && direction == false){
            x++;
        }
        else if (!border() && direction == true){
            x--;
        }
        else {
            y++;
            changedirection();
        }
    }
    private void changedirection() {
        if(direction == true){
            direction = false;
        }
        else{
            direction = true;
        }
    }

    private boolean border() {
        boolean ok = false;
        if(x == 0){
            direction = true;
            ok = true;
        }
        else if (x == game.DIM_X){
            direction = false;
            ok = true;
        }
        else ok = false;
        return ok;
    }
}
