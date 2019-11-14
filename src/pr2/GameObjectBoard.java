package pr2;

public class GameObjectBoard {
    private GameObject[] objects;
    private int currentObjects;

    public GameObjectBoard (int width, int height) {
        currentObjects = 1;
    }

    private int getCurrentObjects () {
        return currentObjects;
    }

    public void add (GameObject object) {
        objects[currentObjects] = object;
        currentObjects++;
    }

    private GameObject getObjectInPosition (int x, int y) {
        GameObject object = null;
        for(int i = 0; i < currentObjects; i++){
            if(x == objects[i].x && y == objects[i].y){
                object = objects[i];
            }
        }
        return object;
    }

    private int getIndex(int x, int y) {
        int index = 0;
        for(int i = 0; i < currentObjects; i++){
            if(x == objects[i].x && y == objects[i].y){
                index = i;
            }
        }
        return index;
    }

    private void remove (GameObject object) {
        int i = 0;
        while(objects[i] != object && i < currentObjects){
            i++;
            if(objects[i] == object){
                for(int j = i; i < currentObjects - 1; j++){
                    objects[j] = objects[j+1];
                }
                currentObjects--;
            }
        }
    }


    public void update() {
        int speed = determineSpeed(level);
        if (contador == speed) {
            listRegularShips.moveRegularShips(this);
            listDestroyerShips.moveDestroyerShips(this);
            contador = 1;
        } else {
            contador++;
        }
        listBombs.moveBombs();

        if (ovni.isActive()) {
            ovni.move();
        } else {
            if (level.posibleOvni()) { //TODO CAMBIAR POR canGenarateRandomOvni
                ovni.setActive(true);
            }
        }
        if (missileLaunch) {
            missile.missileMove();
            listRegularShips.isAttack(missile.missilePositionX(), missile.missilePositionY(),this);
            listDestroyerShips.isAttack(missile.missilePositionX(), missile.missilePositionY(),this);
            listBombs.isAttack(missile.missilePositionX(), missile.missilePositionY(),this);
            ovni.isAttack(missile.missilePositionX(), missile.missilePositionY(),this);

            if (missile.missilePositionX() < 0) {
                resetMissile();
            }
        }
        listDestroyerShips.newBombs(); //TODO GENERAR LAS NUEVAS BOMBAS
    }

    private void checkAttacks(GameObject object) {
        // TODO implement
    }

    public void computerAction() {
        // TODO implement
    }

    private void removeDead() {
        for(int i = currentObjects; i >= 0; i--){
            if(objects[i].live == 0){
                remove(objects[i]);
            }
        }
    }

    public String toString(int x, int y) {
        String chain;
        GameObject object = getObjectInPosition(x,y);
        if (object == null){
            chain = " ";
        }
        else{
            chain = object.toString();
        }
        return chain;
    }

}
