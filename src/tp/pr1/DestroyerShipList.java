package tp.pr1;

public class DestroyerShipList {
    private DestroyerShip[] list;
    private int contador = 0;
    private DestroyerShip ship = new DestroyerShip();

    public DestroyerShipList(Level level){



    }

    public void addDestroyerShip(DestroyerShip ship){
        list[contador] = ship;
        contador++;
    }
}
