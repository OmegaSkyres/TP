package pr1;

import pr1.Game;

public class DestroyerShipList {
    private DestroyerShip[] list;
    private int contador = 0;
    public Game game;
    private int x;
    private int y;
    private boolean direction;

    public DestroyerShipList(Game game){
        game = game;
        list = new DestroyerShip[4];
        direction = false;
    }

    public DestroyerShip getDestroyerShip(int x , int y){
        DestroyerShip ship = null;
        for(int i = 0; i < contador; i++){
            if(x == list[i].getPositionX() && y == list[i].getPositionY()){
                ship = list[i];
            }
        }
        return ship;
    }

    public void addDestroyerShip(DestroyerShip ship){
        list[contador] = ship;
        contador++;
    }

    public DestroyerShip[] getList() {
        return list;
    }

    public int getSizeList(){
        return contador;
    }

    public boolean getDirection(){
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void incrementPositionX(){
        for(int i = 0; i < contador; i++){
            list[i].incrementPositionX();
        }
    }

    public void reduceContador() {
        contador--;
    }

    public void isAttack(int x, int y, Game game) {
        for(int i = 0; i < contador; i++) {
            if (x == list[i].getPositionX() && y == list[i].getPositionY() && !list[i].isDead()) {
                game.resetMissile();
                list[i].recibeDamage(1);
                if(list[i].isDead()){
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

    private boolean bordeD() {
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

    public void moveDestroyerShips(Game game) {
        if(!bordeD() && !game.edge){
            for (int i = 0; i < contador; i++) {
                list[i].move(getDirection());
            }
        }
        else {
            incrementPositionX();
            changedirection();
            game.edge = false;
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
}
