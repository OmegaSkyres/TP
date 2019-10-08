package tp.pr1;

import tp.pr1.util.MyStringUtils;
import tp.pr1.view.GamePrinter;

import java.util.Random;

public class Game {
	private UCMShip player;
	private RegularShip ship;
	private Missile missile;
    private RegularShipList regularShip;
    private DestroyerShipList destroyerShip;
    private boolean missileLaunch = false;
    private BombList bombList;
    public GamePrinter GamePrinter;
    private int cycle;
    private int points;
    private int lifes;
    final static int ROWS = 8;
    final static int COLS = 9;
    private int numberEnemies;
    private boolean superpower;
    private Random rand;
    
    public Game(){
    	this.player = new UCMShip();
    	this.missile = new Missile();
    	GamePrinter = new GamePrinter(this, ROWS, COLS);
    	setCycle(0);
    	points = 0;
    	lifes = 0;
    }

	public void update(){
    	/*
        setCycle(newcycle++);
        points = newpoints;
        rand = newrandom;
        superpower = newshockWave;
        numberEnemies = newnumberEnemies;
        lifes = newlifes;
        */
        if(missile.getEnable()){
			missile.missileMove();
		}

    }

	public String position(int numRows, int numCols) {
		if (numRows == ROWS - 1) {
			if (numCols == player.UCMShipPosition()) {
				return player.toString();
			}
			else return " ";
		}
		else if (missileLaunch) {
			if (numRows == missile.missilePositionY() && numCols == missile.missilePositionX()) {
				missile.setEnable();
				return missile.toString();

			}
			else return " ";
		}
		else return " ";
	}
	
	public int getPosShip() {
		return player.UCMShipPosition();
	}
	
	
	public void posibleLaunch() {
		
		if(missile.getEnable()) missileLaunch = false; //Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		else {
			missileLaunch = true;
		}

	}
	
	
	
	public String toStringBoard() {
		return GamePrinter.toString();
	}
	
	public boolean gameOver() {
		return player.isAlive();
	}

    public boolean isOver(){
        boolean over = false;

        if(player.life == 0)
            over = true;

        return over;
    }

    @Override
    public String toString() {
        String game;

        game = "Life: " + MyStringUtils.centre(Integer.toString(lifes), 5)  + "\n";
        game += "Number of cycles: " + MyStringUtils.centre(Integer.toString(getCycle()), 5)  + "\n";
        game += "Points: " + MyStringUtils.centre(Integer.toString(points), 5)  + "\n";
        game += "Remaining aliens: " + MyStringUtils.centre(Integer.toString(numberEnemies), 5)  + "\n";
        game += "Superpower: " + MyStringUtils.centre(Boolean.toString(superpower), 5)  + "\n"; //Revisar como hacer para el boolenao
        game += toStringBoard();
        return game;
    }
    

    public void reset(){
        points = 0;
    }

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getPosMissileX() {
		return missile.missilePositionX();
	}
	
	public int getPosMissileY() {
		return missile.missilePositionY();
	}
	
	public void missileMove() {
		missile.missileMove();
	}
	
	public void moveLeft() {
		player.moveLeft();
	}
	
	public void moveRight() {
		player.moveRight();
	}
	
	public boolean getMissileLaunch() {
		return missile.getEnable();
	}
	
	public void setMissileLauch() {
		this.missile.active = true;
	}

	private void moveDestroyerShip(){

	}

	public void attackbomb(int x, int y, int damage){
		if(player.isOnPosition(x,y)){
			player.recibeDamage(damage);
		}
	}

	public void shockwave() {
    	
	}

	public void list() {
	}

	public void exit() {
	}

	public void help() {
	}
}
