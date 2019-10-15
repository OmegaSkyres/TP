package tp.pr1;

public class DestroyerShipList {
    DestroyerShip[] list;
    private int contador = 0;
    private DestroyerShip ship = new DestroyerShip();

    public DestroyerShipList(String level){
        list = new DestroyerShip[8];
        initPosition(level);
    }

    public void initPosition(String level) {
        if(level == "EASY"){
            for(int i = 0; i < 4; i++){
                list[i] = new DestroyerShip();
            }
        }
        else if(level == "HARD"){
            for(int i = 0; i < 8; i++){
                list[i] = new DestroyerShip();
            }
        }
        else if (level == "INSANE") {
            for(int i = 0; i < 8; i++){
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
