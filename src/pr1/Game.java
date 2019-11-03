package pr1;

import pr1.util.MyStringUtils;
import pr1.view.GamePrinter;



public class Game {
	public final static int DIM_X = 8;
	public final static int DIM_Y = 9;
	public GamePrinter printer;
	private UCMShip player;
	private Bomb bomb;
	private Missile missile;
	private Ovni ovni;
    private RegularShipList listRegularShips;
    private DestroyerShipList listDestroyerShips;
	private BombList listBombs;
    private boolean missileLaunch = false;
    private int seed;
    private int cycle;
    private boolean doExit;
    public int points;
    public int numberEnemies;
    public boolean superpower;
    private Level level;
    public boolean edge;
    private int contador;


    
    public Game(Level difficulty, int seed){
		this.player = new UCMShip(this, DIM_X - 1, DIM_Y / 2);
    	this.missile = new Missile(this);
    	this.ovni = new Ovni(this);
    	this.level = difficulty;
		this.edge = false;
		this.printer = new GamePrinter(this, DIM_X, DIM_Y);
    	this.listRegularShips = new RegularShipList(this);
		this.listDestroyerShips= new DestroyerShipList(this);
		initPositionR(1,((DIM_Y/2) - (level.getNumberPerRowRegular()/2))+1);
		initPositionD(level.getNumberRowRegular() + 1, ((DIM_Y/2)  - (level.getNumberPerRowDestroyer()/2))+1);
		this.listBombs = new BombList(this);
    	this.numberEnemies = listRegularShips.getSizeList() + listDestroyerShips.getSizeList();
    	this.points = 0;
    	this.contador = 1;
		setCycle(0);
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
			if (level.posibleOvni()) {
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
		//listDestroyerShips.newBombs(); //TODO GENERAR LAS NUEVAS BOMBAS
	}

	public void initPositionR(int x, int y) {
			for (int i = 0; i < level.getNumberRegular(); i++) {
				if (i == 4) {
					x++;
					y = 3;
				}
				listRegularShips.addShip(new RegularShip(x, y));
				y++;
			}
	}

	public void initPositionD(int x,int y) {
			for(int i = 0; i < level.getNumberDestroyer(); i++){
				listDestroyerShips.addDestroyerShip(new DestroyerShip(x,y));
				y++;
			}
    }


	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}

	public boolean aliensWin() {
		return !player.isAlive() || colisionRegularShip() || colisionDestroyerShip();
	}

	private boolean playerWin () {
		if(numberEnemies == 0){
			return true;
		}
		else return false;
	}

	public void exit() {
		doExit = true;
	}

	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}




	@Override
	public String toString() {
		String game;

		game = "Life: " + MyStringUtils.centre(Integer.toString(this.player.life), 5)  + "\n";
		game += "Number of cycles: " + MyStringUtils.centre(Integer.toString(getCycle()), 5)  + "\n";
		game += "Points: " + MyStringUtils.centre(Integer.toString(points), 5)  + "\n";
		game += "Remaining aliens: " + MyStringUtils.centre(Integer.toString(numberEnemies), 5)  + "\n";
		game += "Superpower: " + MyStringUtils.centre(Boolean.toString(superpower), 5)  + "\n";
		game += toStringBoard();
		return game;
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
    	bomb = listBombs.getBomb(numRows,numCols);
		if(bomb != null && bomb.isActive()){
			return bomb.toString();
		}
		if (numRows == DIM_X - 1) {
			if (numCols == player.UCMShipPositionY()) {
				return player.toString();
			}
		}
		if (missileLaunch) {
			if (numRows == missile.missilePositionX() && numCols == missile.missilePositionY()) {
				missile.setEnable();
				return missile.toString();
			}
		}
		if (numRows == ovni.getPositionX() && numCols == ovni.getPositionY()) {
			if(ovni.isActive()) {
				return ovni.toString();
			}

		}
		return string;
	}
	
	public void posibleLaunch() {
		
		if(missile.isEnable()){
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		}
		else {
			missileLaunch = true;
			missile.setPositionX(player.UCMShipPositionX());
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

	public boolean attackbomb(int x, int y, int damage){
    	boolean ok = false;
		if(player.isOnPosition(x,y)){
			player.recibeDamage(damage);
			ok =true;
		}
		else ok = false;
		return ok;
	}

	public void shockwave() {

    	if(superpower){
    		listRegularShips.shockwave();
    		listDestroyerShips.shockwave();
    		if(ovni.isActive()){
    			ovni.recibeDamage(1);
				if(ovni.isDead()) {
					points = points + ovni.getPoints();
					ovni.deleteOvni();
				}
			}
    		superpower = false;
		}
    	
	}

	public void list() {
		System.out.println(" [R]egular ship: Points: 5 - Harm: 0 - Shield: 2" + "\n" +
				"[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1" + "\n" +
				"[O]vni: Points: 25 - Harm: 0 - Shield: 1" + "\n" +
				"^__^: Harm: 1 - Shield: 3"+ "\n" + "\n");

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
