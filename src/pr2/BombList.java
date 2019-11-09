package pr2;

public class BombList {
    private Bomb[] list;
    private int contador;
    private Game game;


    public BombList(Game newGame){
        game = newGame;
        list = new Bomb[4];
        contador = 0;

    }
    public Bomb getBomb(int x, int y){
        Bomb bomb = null;
        for(int i = 0; i < contador; i++){
            if(x == list[i].getPositionX() && y == list[i].getPositionY()){
                bomb = list[i];
            }
        }
        return bomb;
    }

    public void addBomb(Bomb bomb){
        list[contador] = bomb;
        contador++;
    }


    public Bomb[] getList() {
        return list;
    }

    public int getSizeList(){
        return contador;
    }

    public void reduceContador(){
        contador--;
    }

    public void moveBombs() {
        for(int i = 0; i < contador; i++){
            if(list[i] != null){
                list[i].move();
            }
        }
    }

    public void isAttack(int x, int y, Game game) {
        for (int i = 0; i < contador; i++) {
            if (x == list[i].getPositionX() && y == list[i].getPositionY() && list[i].isActive()) {
                game.resetMissile();
            }
        }
    }
    public void resetBomb() {
        for (int i = 0; i < contador; i++) {
            if (list[i].getPositionX() == 8 || game.attackbomb(list[i].getPositionX(), list[i].getPositionY(), 1)) {
                list[i].resetBomb();
                //TODO DESACTIVAR LA BOMBA QUE HAY EN
                if (contador == list.length - 1) {
                    stackBombList();
                }
            }
        }
    }

    public void stackBombList() {
        for(int i = 0; i < contador; i++){
            if(!list[i].isActive()){
                list[i] = list[i + 1];
                reduceContador();
            }
        }
    }
        //TODO ARREGLAR
    /*
    public void newBombs() {
        for(int i = 0; i < contador; i++){
            if(!list[i].isDead() && !list[i].bombLaunch()){
                if(game.posibleBomb()){
                    list[i] = new Bomb(list[i].getPositionX(), list[i].getPositionY());
                    bombList.addBomb(listD[i].getBomb());
                    listD[i].setBombLaunch(true);
                }
            }
        }
    */
}
