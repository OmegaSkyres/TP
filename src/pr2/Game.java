package pr2;

import pr2.util.MyStringUtils;
import pr2.view.GamePrinter;

import java.util.Random;


public class Game implements IPlayerController {
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;
	GameObjectBoard board;
	private UCMShip player;
	private BoardInitializer initializer;
    private int currentCycle;
    private boolean doExit;
    private Level level;
    public boolean edge;
    private Random rand;


    
    public Game(Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
		this.edge = false;
		currentCycle = 0;
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

	public Random getRandom() {
		return rand;
	}
	public Level getLevel() {
		return level;
	}
	public void reset() {
		initGame();
	}
	public void addObject(GameObject object) {
		board.add(object);
	}
	public String positionToString(int x, int y) {
		return board.toString(x, y);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}

	public boolean aliensWin() {
		return !player.isAlive() || colisionRegularShip() || colisionDestroyerShip();
	}

	private boolean playerWin () {
		return AlienShip.allDead();
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

	public boolean isOnBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < DIM_X && y < DIM_Y;
	}


	public String position(int numRows, int numCols) {

	}
	
	public void posibleLaunch() {
		

	}
	
	public void resetMissile() {
			missile.active = false;
			missileLaunch = false;
			missile.reset();
		
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

	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
				player.stateToString() +
				"Remaining aliens: " + (AlienShip.getRemainingAliens()) + "\n";
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

	@Override
	public boolean move(int numCells) {
		//TODO COMO HACER ESTE MOVE PARA LOS OBJETOS DEL TABLERO
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

    	points += points; //TODO DE DONDE SALEN ESTOS POINTS QUIEN LLEVA ESTE ATRIBUTO
	}

	@Override
	public void enableShockWave() {
    	Shockwave shockwave = new Shockwave(this,) {

		}
    	//TODO COMO ACTIVO EL SHOCKWABE SI HA MUERTO EL OVNI Y DEBO MIRAR DE SI YA TENGO UNO??

	}

	@Override
	public void enableMissile() {
    	Missile missile = new Missile(this,player.UCMShipPositionX()+1,player.UCMShipPositionY());
		if(missile.isEnable()){
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		}
		else {
			missile.active = true;
			missile.setPositionX(player.UCMShipPositionX());
			missile.setPositionY(player.UCMShipPositionY());
		}

	}

}
