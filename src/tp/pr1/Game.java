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
    private Level level;

    
    public Game(Level difficulty, int seed){
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
    	if(numberEnemies == 0){
			System.out.println("Players Win");
		}
    	if(isGameOver()){
    		System.out.println("Aliens win");
		}
        if(missile.getEnable()){
        	for(int i = 0; i < listRegularShips.list.length; i++){
				if(missile.missilePositionY() == listRegularShips.list[i].getPositionY()){
					resetMissile();
					ship.recibeDamage(1);
				}
			}
			for(int i = 0; i < listDestroyerShips.list.length; i++) {
        	 	if (missile.missilePositionY() == listDestroyerShips.list[i].getPositionY()) {
					resetMissile();
					destroyerShip.recibeDamage(1);
				}
			}
			if(ovni.isActive()){
				if (missile.missilePositionY() == ovni.getPositionY()){
					resetMissile();
					ovni.recibeDamage(1);
					if(!superpower){
						superpower = true;
					}
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

	public boolean posibleOvni(Level level){
		int random = newRandom();
		boolean generate = false;
		if(level.toString() == "EASY"){
			if(random == 2) {
				generate = true;
			}
		}
		else if(level.toString() == "HARD"){
			if (random == 3){
				generate = true;
			}
		}
		else if (level.toString() == "INSANE"){
			if (random == 1){
				generate = true;
			}
		}
		return generate;
	}

	public String position(int numRows, int numCols) {
    	String string = " ";
		if (numRows == ROWS - 1) {
			if (numCols == player.UCMShipPositionY()) {
				if(player.life == 0){
					return player.deathString();
				}
				else return player.toString();
			}
			else return " ";
		}
		if (numberEnemies > 0){
			for(int i = 0; i < listRegularShips) //TODO HACER ESTO
		}
		if (missileLaunch) {
			if (numRows == missile.missilePositionY() && numCols == missile.missilePositionX()) {
				missile.setEnable();
				return missile.toString();
			}
			else return " ";
		}
		if (bombLauch) {
			if (numRows == bomb.getPositionY() && numCols == bomb.getPositionX()) {
				bomb.setActive(true);
				return bomb.toString();

			}
			else return " ";
		}
		if(!ovni.isActive()) {
			if (posibleOvni(level)) {
				if (numRows == 0) {
					if (numCols == ovni.getPositionY()) {
						return ovni.toString();
					}
				}
			}
		}
		return string;
	}

	public int getPosShip() {
		return player.UCMShipPositionY();
	}
	public int newRandom(){

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
			missile.row = player.UCMShipPositionY();
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
		if ((player.life == 0) || colisionRegularShip() || colisionDestroyerShip()){
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
    
	public boolean colisionRegularShip() {
    	boolean ok = false;
		for (int i = 0; i < listRegularShips.list.length; i++) {
			if (listRegularShips.list[i].getPositionX() == player.UCMShipPositionX()) {
				ok = true;
			}
		}
		return ok;
	}

	public boolean colisionDestroyerShip() {
    	boolean ok = false;
		for (int i = 0; i < listRegularShips.list.length; i++) {
			if (listRegularShips.list[i].getPositionX() == player.UCMShipPositionX()) {
				ok = true;
			}

		}
		return ok;
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

	public int getNumberEnemies() {
		return numberEnemies;
	}

	public int getPoints() {
		return points;
	}

	public int addPoints(int newpoints){
    	return points = points + newpoints;
	}

	public int reduceNumberEnemies(int newNumber){
    	return numberEnemies = numberEnemies - newNumber;
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
		System.out.println("Game Over" + "\n" + "\n");
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

}
