package pr1;

public class GameObjectBoard {
    private GameObject[] objects;
    private int currentObjects;
    private int contador;

    public GameObjectBoard (int width, int height) {
        contador = 1;
    }

    private int getCurrentObjects () {
        // TODO implement
    }

    public void add (GameObject object) {
        objects[currentObjects] = object;
        currentObjects++;
    }

    private GameObject getObjectInPosition (int x, int y) {
        objects[currentObjects];
    }

    private int getIndex(int x, int y) {
        // TODO implement
    }

    private void remove (GameObject object) {
        // TODO implement
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
        // TODO implement
    }

    public String toString(int x, int y) {
        // TODO implement
    }

}
