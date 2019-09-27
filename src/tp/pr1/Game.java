package tp.pr1;

import tp.pr1.util.MyStringUtils;

import java.util.Random;

public class Game {
    private RegularShipList regularShip;
    private DestroyerShipList destroyerShip;
    private BombList bombList;
    private int round = 1;
    private int points = 0;
    private int lifes = 3;
    private int numberEnemies;
    private boolean superpower;
    private Random rand;

    public Game(){


    }


    public void update(int newround, int newpoints, Random newrandom, boolean newshockWave, int newnumberEnemies, int newlifes){
        round = newround;
        points = newpoints;
        rand = newrandom;





    }

    public void initGame(){
        UCMShip ship = new UCMShip(7,4);
    }

    public void validate(int i, int j){
        if ()
    }
    /*
    public boolean isOver(){
        boolean over = false;

        if(round == Game.Objective)
            over = true;

        return over;
    }

    public Boolean isBlocked() {
        return _board.isBlocked();
    }
    */
    @Override
    public String toString() {
        String game;

        game = "Life: " + MyStringUtils.centre(Integer.toString(lifes), 5)  + "\n";
        game += "Number of cycles: " + MyStringUtils.centre(Integer.toString(round), 5)  + "\n";
        game += "Points: " + MyStringUtils.centre(Integer.toString(points), 5)  + "\n";
        game += "Remaining aliens: " + MyStringUtils.centre(Integer.toString(numberEnemies), 5)  + "\n";
        //game += "Superpower: " + MyStringUtils.centre(Boolean.toString(superpower), 5)  + "\n"; //Revisar como hacer para el boolenao
        return game;
    }
    /*
    public void reset(){
        board.initBoard();
        points = 0;
        randomInitCells();
    }

     */
}
