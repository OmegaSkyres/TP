package tp.pr1;

import tp.pr1.util.MyStringUtils;
import tp.pr1.view.GamePrinter;

import java.util.Random;

public class Game {
	private UCMShip player;
	private RegularShip ship;
	private DestroyerShip destroyerShip;
	private Bomb bomb;
	private Missile missile;
    private RegularShipList listRegularShips;
    private DestroyerShipList listDestroyerShips;
    private Ovni ovni;
    private boolean missileLaunch = false;
    private boolean bombLauch = false;
    private BombList bombList;
    public GamePrinter GamePrinter;
    private int seed;
    private int cycle;
    private int points;
    private int lifes;
    final static int ROWS = 8;
    final static int COLS = 9;
    private int numberEnemies;
    private boolean superpower;
    private Random rand;
    private String level;

    
    public Game(String difficulty, int seed){
    	this.player = new UCMShip();
    	this.missile = new Missile();
    	this.listRegularShips = new RegularShipList(level);
    	this.listDestroyerShips= new DestroyerShipList(level);
    	GamePrinter = new GamePrinter(this, ROWS, COLS);
    	setCycle(0);
    	points = 0;
    	lifes = 3;

    }

	public void update(){
    	if(isGameOver()){
    		System.out.println("Aliens win");
		}
        if(missile.getEnable()){
        	if(missile.missilePositionY() == ship.getPositionY()){
        		resetMissile();
        		ship.recibeDamage(1);
			}
        	else if (missile.missilePositionY() == destroyerShip.getPositionY()){
        		resetMissile();
        		destroyerShip.recibeDamage(1);
			}
        	else if (missile.missilePositionY() == ovni.getPositionY()){
        		resetMissile();
        		ovni.recibeDamage(1);
        		if(!superpower){
					superpower = true;
				}

			}
			missile.missileMove();
		}
        
        if (missile.missilePositionY() < 0) {
        	resetMissile();
        }
        if(ovni.isActive()){
        	ovni.move();
		}

    }

	public String position(int numRows, int numCols) {
		if (numRows == ROWS - 1) {
			if (numCols == player.UCMShipPositionY()) {
				if(player.life == 0){
					return player.deathString();
				}
				else return player.toString();
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
		else if (bombLauch) {
			if (numRows == bomb.getPositionY() && numCols == bomb.getPositionX()) {
				bomb.setActive(true);
				return missile.toString();

			}
			else return " ";
		}
		else if (level == "EASY"){
			if(!ovni.isActive()){
				if(numeroRandom() == 2){
					if(numRows == 0){
						if(numCols == ovni.getPositionY()){
							return ovni.toString();
						}
					}
				}
			}
			 if (numRows == ROWS - 8){
				 if(numCols == COLS - 6) {
					for(int i = 0; i < 4; i++){
						return ship.toString();
					}
				}
			 }
			 if(numRows == ROWS - 7) {
				 if(numCols == COLS - 5) {
					 for (int i = 0; i < 2; i++) {
						 return destroyerShip.toString();
					 }
				 }
			 }
		}

		else if (level == "HARD"){
			if(!ovni.isActive()) {
				if (numeroRandom() == 3) {
					if (numRows == 0) {
						if (numCols == ovni.getPositionY()) {
							return ovni.toString();
						}
					}
				}
			}
			if (numRows == ROWS - 8){
				if(numCols == COLS - 6) {
					for(int i = 0; i < 4; i++){
						return ship.toString();
					}
				}
			}
			if(numRows == ROWS - 7) {
				if(numCols == COLS - 6) {
					for(int i = 0; i < 4; i++){
						return ship.toString();
					}
				}
			}
			if(numRows == ROWS - 6) {
				if(numCols == COLS - 5) {
					for (int i = 0; i < 2; i++) {
						return destroyerShip.toString();
					}
				}
			}
		}

		else if (level == "INSANE"){
			if(!ovni.isActive()) {
				if (numeroRandom() == 1) {
					if (numRows == 0) {
						if (numCols == ovni.getPositionY()) {
							return ovni.toString();
						}
					}
				}
			}
			if (numRows == ROWS - 8){
				if(numCols == COLS - 6) {
					for(int i = 0; i < 4; i++){
						return ship.toString();
					}
				}
			}
			if(numRows == ROWS - 7) {
				if(numCols == COLS - 6) {
					for(int i = 0; i < 4; i++){
						return ship.toString();
					}
				}
			}
			if(numRows == ROWS - 6) {
				if(numCols == COLS - 5) {
					for (int i = 0; i < 4; i++) {
						return destroyerShip.toString();
					}
				}
			}
		}
		else return " ";
	}
	
	public int getPosShip() {
		return player.UCMShipPositionY();
	}
	public int numeroRandom(){

		double random = Math.random();// generamos un numero al azar entre 0 y 1
		if (random < 0.1)// el 10% restante
			return 1;

		if(random > 0.1 && random < 0.6)// el 50% de las veces
			return 2;

		else if(random > 0.6 && random <0.8)// el 20% de las veces
			return 3;

		else return 0;
	}
	
	public void posibleLaunch() {
		
		if(missile.getEnable()) missileLaunch = false; //Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		else {
			missileLaunch = true;
			missile.x = player.UCMShipPositionY();
		}

	}
	
	public void resetMissile() {
			missile.active = false;
			missileLaunch = false;
			missile.reset();
		
	}
	
	public String toStringBoard() {
		return GamePrinter.toString();
	}
	
	public boolean isGameOver() {
		if ((player.life == 0) || (ship.getPositionX() == player.UCMShipPositionX()) || destroyerShip.getPositionX() == player.UCMShipPositionX()){
			return true;
		}
		else return false;
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

	public void moveRegularShips(){
		for(int i = 0; i <listRegularShips.list.length; i++){
			listRegularShips.list[i].moveLeft();
		}
	}

	public void moveDestroyerShip(){
		for(int i = 0; i <listDestroyerShips.list.length; i++){
			listRegularShips.list[i].moveLeft();
		}
	}



	public void attackbomb(int x, int y, int damage){
		if(player.isOnPosition(x,y)){
			player.recibeDamage(damage);
		}
	}

	public void shockwave() {
    	if(superpower){
    		for(int i = 0; i <listRegularShips.list.length; i++){
    			listRegularShips.list[i].recibeDamage(1);
			}
			for(int i = 0; i <listDestroyerShips.list.length; i++){
				listRegularShips.list[i].recibeDamage(1);
			}
    		if(ovni.isActive()){
    			ovni.recibeDamage(1);
			}

		}
    	
	}

	public void list() {
		System.out.println(" [R]egular ship: Points: 5 - Harm: 0 - Shield: 2" + "\n" +
				"[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1" + "\n" +
				"[O]vni: Points: 25 - Harm: 0 - Shield: 1" + "\n" +
				"^__^: Harm: 1 - Shield: 3"+ "\n" + "\n");

	}

	public void exit() {
	}

	public void help() {
    	System.out.println("move <left|right><1|2>: Moves UCM-Ship to the indicated direction." + "\n" +
				"shoot: UCM-Ship launches a missile." + "\n" +
		"shockWave: UCM-Ship releases a shock wave. " + "\n" +
		"list: Prints the list of available ships. " + "\n" +
		"reset: Starts a new game." + "\n" +
		"help: Prints this help message." + "\n" +
		"exit: Terminates the program." + "\n" +
	    "[none]: Skips one cycle."+ "\n" + "\n");

	}

	public void skip() {
    	this.cycle++;
	}

	public String selectDifficulty(){

	}
}
