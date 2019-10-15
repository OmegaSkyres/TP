package tp.pr1;

public class RegularShipList {
    RegularShip[] list; //Privado o Public?
    private RegularShip ship;
    private int contador = 0;
    
    
    public RegularShipList(String level) {
    	list = new RegularShip[8];
    	initPosition(level);
    }
    
    public void initPosition(String level) {
        if(level == "EASY"){
            for(int i = 0; i < 4; i++){
                list[i] = new RegularShip();
            }
        }
        else if(level == "HARD"){
            for(int i = 0; i < 8; i++){
                list[i] = new RegularShip();
            }
        }
        else if (level == "INSANE") {
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
