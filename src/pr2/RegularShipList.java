package pr2;

public class RegularShipList {
    private RegularShip[] list;
    private RegularShip ship;
    private int contador = 0;
    private Game game;
    private int x = 1;
    private int y;
    private boolean direction;

    public RegularShipList(Game game) {
        game = game;
        list = new RegularShip[8];
        direction = false;

    }



    public void addShip(RegularShip ship) {
        list[contador] = ship;
        contador++;
    }

    public RegularShip[] getList() {
        return list;
    }

    public int getSizeList() {
        return contador;
    }

    public int getX() {
        return x;
    }

    public RegularShip getShip(int x, int y) {
        RegularShip ship = null;
        for (int i = 0; i < contador; i++) {
            if (x == list[i].getPositionX() && y == list[i].getPositionY()) {
                ship = list[i];
            }
        }
        return ship;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void incrementPositionX() {
        for (int i = 0; i < contador; i++) {
            list[i].incrementPositionX();
        }
    }

    public void isAttack(int x, int y, Game game) {
        for (int i = 0; i < contador; i++) {
            if (x == list[i].getPositionX() && y == list[i].getPositionY() && !list[i].isDead()) {
                game.resetMissile();
                list[i].recibeDamage(1);
                if (list[i].isDead()) {
                    game.points = game.points + list[i].getPoints();
                    game.numberEnemies = game.numberEnemies - 1;
                }
            }
        }
    }

    public void shockwave() {
        for(int i = 0; i < contador; i++){
            list[i].recibeDamage(1);
            if(list[i].isDead()){
                game.points = game.points + list[i].getPoints();
                game.numberEnemies = game.numberEnemies - 1;
            }
        }
    }

    public void moveRegularShips(Game game){
        if(!bordeR()){
            for(int i = 0; i < contador; i++){
                list[i].move(getDirection());
            }
        }
        else{
            game.edge = true;
            incrementPositionX();
        }
    }

    private boolean bordeR() {
        boolean ok = false;
        if(list[0].getPositionY() == 0 && getDirection() != true){
            setDirection(true);
            ok = true;
        }
        else if (list[getSizeList()-1].getPositionY() == 8 && getDirection() != false){
            setDirection(false);
            ok = true;
        }
        else ok = false;
        return ok;
    }
}
