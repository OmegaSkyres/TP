package tp.pr1;

public class DestroyerShipList {
    private DestroyerShip[] list;
    private int contador = 0;
    private DestroyerShip ship = new DestroyerShip();

    public DestroyerShipList(String level){
        list = new DestroyerShip[8];

    }

    public void addDestroyerShip(DestroyerShip ship){
        list[contador] = ship;
        contador++;
    }
}
