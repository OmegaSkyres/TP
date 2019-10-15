package tp.pr1;

public class RegularShipList {
    RegularShip[] list; //Privado o Public?
    private RegularShip ship;
    private int contador = 0;
    
    
    public RegularShipList(Level level) {
    	list = new RegularShip[8];
    	initPosition(level);
    }
    
    public void initPosition(Level level) {
        if(level.toString() == "EASY"){
            for(int i = 0; i < 4; i++){
                list[i] = new RegularShip();
            }
        }
        else if(level.toString() == "HARD"){
            for(int i = 0; i < 8; i++){
                list[i] = new RegularShip();
            }
        }
        else if (level.toString() == "INSANE") {
            for(int i = 0; i < 8; i++){
                list[i] = new RegularShip();
            }
        }
    	
    }
    
    public void addShip(RegularShip ship) {
    	list[contador] = ship;
    	contador++;
    }

    public RegularShip getRegularShip(int contador){
        return list[contador];
    }
}
