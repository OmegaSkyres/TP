package pr2;

public abstract class AlienShip extends EnemyShip {
    private static int numberEnemies = 0;
    private static int numShips;
    private int numCycles;
    private static boolean floor;
    static boolean isLeft;
    static boolean moveDown;
    static boolean turnDown;
    public AlienShip(Game game, int x, int y, int life) {
        super(game, x, y, life);
        this.life = life;
        this.game = game;
        floor = false;
        numCycles = 1;
        isLeft = true;
        moveDown = false;
        numShips = 0;
        turnDown = false;

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


    @Override
    public void move() {
        if (numCycles == game.getLevel().getNumCyclesToMoveOneCell()) {
            if(turnDown) {
                if (numShips > 0 && moveDown) {
                    x++;
                    numShips--;
                    if (numShips == 0) {
                        isLeft = !isLeft;
                        moveDown = false;
                        turnDown = false;
                    }
                }
            }
            else {
                if (isLeft == true)
                    y--;
                else
                    y++;
                numShips++;
                if(onBorder() && !moveDown){
                    moveDown = true;
                }
                else if(!onBorder() && !moveDown && numShips == numberEnemies){
                    numShips = 0;
                }
                if(numShips == numberEnemies){
                    turnDown = true;
                }
            }
            numCycles = 1;
        }
        else {
            numCycles++;
        }
        if (x == game.DIM_Y - 1) {
            floor = true;
        }
    }

    public boolean onBorder() {
        if(!isLeft && y==game.DIM_X-1) return true;
        else if(isLeft && y== 0) return true;
        return false;
    }

    public static boolean onTheFloor(){
      return floor;
    }

    public static int setRemaingAliens(int number){
       return numberEnemies += number;
    }

    public static void setterRemaingAliens(int number){
        numberEnemies = number;
    }

}
