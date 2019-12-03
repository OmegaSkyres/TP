package pr2;

public abstract class AlienShip extends EnemyShip {
    private static int numberEnemies = 0;
    private boolean direction;
    private int life;
    private boolean floor;
    public AlienShip(Game game, int x, int y, int life) {
        super(game, x, y, life);
        direction = false;
        life = life;
        floor = false;
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

    public static boolean onTheFloor(){
        return DestroyerShip.getOnTheFloor() || RegularShip.getOnTheFloor();

    }

    public static int setRemaingAliens(int number){
       return numberEnemies += number;
    }

}
