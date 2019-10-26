package pr1;

import pr1.Game;

public class DestroyerShipList {
    private DestroyerShip[] list;
    private int contador = 0;
    private Game game;
    private int x;
    private int y;
    private boolean direction;

    public DestroyerShipList(Level level, int numCols, int initialRow){
        list = new DestroyerShip[4];
        x = initialRow;
        initPosition(level,numCols,x);
        direction = false;
    }

    public void initPosition(Level level,int numCols,int x) {
        x = x + 1;
        int col = (numCols / 2);
        if(level.toString() == "EASY" || level.toString() == "HARD"){
            for(int i = 0; i < 2; i++){
                list[i] = new DestroyerShip(x,col);
                contador++;
                col++;
            }
        }
        else if (level.toString() == "INSANE") {
            for(int i = 0; i < 4; i++){
                list[i] = new DestroyerShip(x,col-1);
                contador++;
                col++;
            }
        }
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
}
