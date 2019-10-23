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
    public GamePrinter printer;
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
    private int initialRowR;
    private int initialRowD;
    private int xR;
    private int xD;
    private int posList;


    
    public Game(Level difficulty, int seed){
    	this.player = new UCMShip();
    	this.missile = new Missile();
    	this.bomb = new Bomb();
    	this.ovni = new Ovni(0,7);
    	this.rand = new Random();
    	this.level = difficulty;
    	this.listRegularShips = new RegularShipList(difficulty,xR,3);
		this.printer = new GamePrinter(this, ROWS, COLS);
    	initialRowD = listRegularShips.getX();
		this.listDestroyerShips= new DestroyerShipList(difficulty,COLS,initialRowD);
		this.bombList = new BombList();
    	this.numberEnemies = listRegularShips.getSizeList() + listDestroyerShips.getSizeList();
    	setCycle(0);
    	this.points = 0;
    	this.lifes = 3;
    }

	public void update(){
		DestroyerShip[] listD = listDestroyerShips.getList();
		RegularShip[] listR = listRegularShips.getList();
		Bomb[] listB = bombList.getList();
    	if(numberEnemies == 0){
			System.out.println("Players Win");
		}
    	if(isGameOver()){
    		System.out.println("Aliens win");
		}
        if(missile.isEnable()){
        	for(int i = 0; i < listRegularShips.getSizeList(); i++){
				if(missile.missilePositionX() - 1 == listR[i].getPositionX()){
					resetMissile();
					listR[i].recibeDamage(1);
				}
			}
			for(int i = 0; i < listDestroyerShips.getSizeList(); i++) {
        	 	if (missile.missilePositionX() - 1 == listD[i].getPositionX()) {
					resetMissile();
					listD[i].recibeDamage(1);
				}
			}
			for(int i = 0; i < bombList.getSizeList(); i++){
				if(missile.missilePositionX() - 1 == listB[i].getPositionX()){
					resetMissile();
					listB[i].setActive(false);
				}
			}
			if(ovni.isActive()){
				if (missile.missilePositionX() - 1 == ovni.getPositionX()){
					resetMissile();
					ovni.recibeDamage(1);
					if(!superpower){
						superpower = true;
					}
				}
			}

			missile.missileMove();
		}
		if(bomb.isActive()){
			attackbomb(missile.missilePositionX(),missile.missilePositionY(),1);
		}
		moveDestroyerShip();
        moveRegularShips();
        if(ovni.isActive()){
        	ovni.move();
		}

        
        if (missile.missilePositionX() < 0) {
        	resetMissile();
        }
        if(bomb.getPositionX() < 0){
        	resetBomb();
		}
        if(ovni.isActive()){
        	ovni.move();
		}

    }

	private void resetBomb() {
    	bomb.setActive(false);
	}

	public boolean posibleBomb(Level level){
    	boolean generate = false;
		if(level.toString() == "EASY"){
			if(rand.nextDouble() < 0.1) {
				generate = true;
			}
		}
		else if(level.toString() == "HARD"){
			if (rand.nextDouble() < 0.3){
				generate = true;
			}
		}
		else if (level.toString() == "INSANE"){
			if (rand.nextDouble() < 0.5){
				generate = true;
			}
		}
		return generate;

	}

	public boolean posibleOvni(Level level){
		boolean generate = false;
		if(level.toString() == "EASY"){
			if(rand.nextDouble() < 0.5) {
				generate = true;
			}
		}
		else if(level.toString() == "HARD"){
			if (rand.nextDouble() < 0.2){
				generate = true;
			}
		}
		else if (level.toString() == "INSANE"){
			if (rand.nextDouble() < 0.1){
				generate = true;
			}
		}
		return generate;
	}

	public int determineSpeed(Level level){
    	int speed = 0;
		if(level.toString() == "EASY"){
			speed = 3;
		}
		else if(level.toString() == "HARD"){
			speed = 2;
		}
		else if (level.toString() == "INSANE"){
			speed = 1;
		}
		return speed;
	}

	public String position(int numRows, int numCols) {
		String string = " ";
    	RegularShip shipE = listRegularShips.getShip(numRows,numCols);
    	if(shipE != null){
    		return shipE.toString();
		}
    	DestroyerShip shipDestroyer = listDestroyerShips.getDestroyerShip(numRows,numCols);
    	if(shipDestroyer != null){
    		return shipDestroyer.toString();
		}
    	Bomb bomb = bombList.getBomb(numRows,numCols);
		if(bomb != null){
			return bomb.toString();
		}
		if (numRows == ROWS - 1) {
			if (numCols == player.UCMShipPositionY()) {
				if(player.life == 0){
					return player.deathString();
				}
				else return player.toString();
			}
		}
		if (missileLaunch) {
			if (numRows == missile.missilePositionX() && numCols == missile.missilePositionY()) {
				missile.setEnable();
				return missile.toString();
			}
		}
		posibleBomb(level);
		if (numRows == 0 && numCols == 8) {
			this.ovni.setActive(false);
			if(!ovni.isActive()) {
				if (posibleOvni(level)) {
					ovni.setActive(true);
					return ovni.toString();
				}
			}

		}
		return string;
	}

	public int getPosShip() {
		return player.UCMShipPositionY();
	}
	
	public void posibleLaunch() {
		
		if(missile.isEnable()){
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		}
		else {
			missileLaunch = true;
			missile.setPositionX(player.UCMShipPositionX() - 1);
			missile.setPositionY(player.UCMShipPositionY());
		}

	}
	
	public void resetMissile() {
			missile.active = false;
			missileLaunch = false;
			missile.reset();
		
	}
	
	public String toStringBoard() {
		return printer.toString();
	}
	
	public boolean isGameOver() {
		if (player.life == 0 || colisionRegularShip() || colisionDestroyerShip()){
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
		RegularShip shipE = listRegularShips.getShip(player.UCMShipPositionX(),player.UCMShipPositionY());
		if(shipE != null){
			ok = true;
		}
		return ok;
	}

	public boolean colisionDestroyerShip() {
    	boolean ok = false;
		DestroyerShip shipDestroyer = listDestroyerShips.getDestroyerShip(player.UCMShipPositionX(),player.UCMShipPositionY());
		if(shipDestroyer != null){
			ok = true;
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
	
	public void moveLeft() {
		player.moveLeft();
	}
	
	public void moveRight() {
		player.moveRight();
	}

	public void moveRegularShips(){
		RegularShip[] list = listRegularShips.getList();
		for(int i = 0; i <listRegularShips.getSizeList(); i++){
			list[i].move();
		}
	}

	public void moveDestroyerShip(){
		DestroyerShip[] list = listDestroyerShips.getList();
		for(int i = 0; i <listDestroyerShips.getSizeList(); i++){
			list[i].move();
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
		RegularShip[] listR = listRegularShips.getList();
		DestroyerShip[] listD = listDestroyerShips.getList();
    	if(superpower){
    		for(int i = 0; i <listRegularShips.getSizeList(); i++){
    			listR[i].recibeDamage(1);
			}
			for(int i = 0; i <listDestroyerShips.getSizeList(); i++){
				listD[i].recibeDamage(1);
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
