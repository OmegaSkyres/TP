package pr2;

import pr2.util.MyStringUtils;
import pr2.view.GamePrinter;

import java.util.Random;


public class Game implements IPlayerController {
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;
	public GamePrinter printer;
	GameObjectBoard board;
	private UCMShip player;
	private Bomb bomb;
	private Missile missile;
	private Ovni ovni;
	private BoardInitializer initializer;
    private RegularShipList listRegularShips;
    private DestroyerShipList listDestroyerShips;
	private BombList listBombs;
    private boolean missileLaunch = false;
    private int seed;
    private int currentCycle;
    private boolean doExit;
    public int points;
    public int numberEnemies;
    public boolean superpower;
    private Level level;
    public boolean edge;
    private int contador;
    private Random rand;


    
    public Game(Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
    	this.missile = new Missile(this);
		this.edge = false;
		this.printer = new GamePrinter(this, DIM_X, DIM_Y);
		this.listBombs = new BombList(this);
    	this.numberEnemies = listRegularShips.getSizeList() + listDestroyerShips.getSizeList();
    	this.points = 0;
		setCycle(0);
    }

	public void initGame () {
		currentCycle = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this, DIM_X / 2, DIM_Y - 1);
		board.add(player);
	}

	public void update() {
		board.computerAction();
		board.update();
		currentCycle += 1;

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

	public int getCycle() {
		return currentCycle;
	}

	public void setCycle(int cycle) {
		this.currentCycle = cycle;
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
    	this.currentCycle++;
	}


	public Random getRandom() {
		return rand;
	}

	public Level getLevel() {
		return level;
	}

	public void reset() {
		initGame();
	}

	@Override
	public boolean move(int numCells) {
		return false;
	}

	@Override
	public boolean shootLaser() {
		return false;
	}

	@Override
	public boolean shockWave() {
		return false;
	}

	@Override
	public void receivePoints(int points) {

	}

	@Override
	public void enableShockWave() {

	}

	@Override
	public void enableMissile() {

	}
}
