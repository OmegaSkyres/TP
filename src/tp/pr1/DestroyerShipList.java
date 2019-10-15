package tp.pr1;

public class DestroyerShipList {
    DestroyerShip[] list;
    private int contador = 0;
    private DestroyerShip ship = new DestroyerShip();

    public DestroyerShipList(Level level){
        list = new DestroyerShip[8];
        initPosition(level);
    }

    public void initPosition(Level level) {
        if(level.toString() == "EASY"){
            for(int i = 0; i < 2; i++){
                list[i] = new DestroyerShip();
            }
        }
        else if(level.toString() == "HARD"){
            for(int i = 0; i < 2; i++){
                list[i] = new DestroyerShip();
            }
        }
        else if (level.toString() == "INSANE") {
            for(int i = 0; i < 4; i++){
                list[i] = new DestroyerShip();
            }
        }
    }

    public DestroyerShip getDestroyerShip(int contador){
        return list[contador];
    }

    public void addDestroyerShip(DestroyerShip ship){
        list[contador] = ship;
        contador++;
    }
}
