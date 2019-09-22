package tp.pr1;

import java.util.Random;

public class Game {
    private RegularShipList regularShip;
    private DestroyerShipList destroyerShip;
    private BombList bombList;
    private int round;
    private int points;
    private Random rand;

    public Game(){

    }

    public void update(int newround, int newpoints, Random newrandom){
        round = newround;
        points = newpoints;
        rand = newrandom;

    }

}
