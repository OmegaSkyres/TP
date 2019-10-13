package tp.pr1;

public class RegularShipList {
    RegularShip[] list;
    private RegularShip ship;
    private int contador = 0;
    
    
    public RegularShipList(String level) {
    	list = new RegularShip[8];
    	ship = new RegularShip();
    }
    
    public void initPosition() {
    	
    }
    
    public void addShip(RegularShip ship) {
    	list[contador] = ship;
    	contador++;
    }

    public RegularShip getRegularShip(int contador){
        return list[contador];
    }
}
