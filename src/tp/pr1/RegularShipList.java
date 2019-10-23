package tp.pr1;

public class RegularShipList {
    private RegularShip[] list;
    private RegularShip ship;
    private int contador = 0;
    private Game game;
    private int x = 1;
    private int y;

    public RegularShipList(Level level, int x, int initialColum) {
        list = new RegularShip[8];
        y = initialColum;
        initPosition(level,y);

    }

    public void initPosition(Level level, int y) {

        if(level.toString() == "EASY"){
            for(int i = 0; i < 4; i++){
                list[i] = new RegularShip(x,y);
                contador++;
                y++;
            }
        }
        else if(level.toString() == "HARD" || level.toString() == "INSANE"){
            for(int i = 0; i < 8; i++){
                if(i == 4){
                    x++;
                    y = 3;
                }
                list[i] = new RegularShip(x,y);
                contador++;
                y++;
            }
        }
    }
    
    public void addShip(RegularShip ship) {
    	list[contador] = ship;
    	contador++;
    }

    public RegularShip[] getList() {
        return list;
    }

    public int getSizeList(){
        return contador;
    }

    public int getX() {
        return x;
    }

    public RegularShip getShip(int x, int y){
        RegularShip ship = null;
        for(int i = 0; i < contador; i++){
            if(x == list[i].getPositionX() && y == list[i].getPositionY()){
               ship = list[i];
            }
        }
        return ship;
    }
}
