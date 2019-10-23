package tp.pr1;

public class BombList {
    private Bomb[] list;
    private int x;
    private int y;
    private int contador = 0;


    public BombList(){

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


}
