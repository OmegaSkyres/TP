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
    private Random rand;


    
    public Game(Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
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

	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
				player.stateToString() +
				"Remaining aliens: " + (AlienShip.getRemainingAliens()) + "\n";
	}


	public void skip() {
    	this.currentCycle++;
	}

	@Override
	public boolean move(int numCells) {
    	boolean ok = true;
    	if(){ //Comprabar que se sale excepcción
			player.move();
		}

	}

	@Override
	public boolean shootLaser() {
		if(missile.isEnable()){
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		}
		else {
			missile.active = true;
			missile.setPositionX(player.UCMShipPositionX()+1);
			missile.setPositionY(player.UCMShipPositionY());
		}
		return false;
	}

	@Override
	public boolean shockWave() {
    	return false;
	}

	@Override
	public void receivePoints(int points) {
    	player.setPoints(points);
	}

	@Override
	public void enableShockWave() {
    	player.setShockwave(true);
	}

	@Override
	public void enableMissile() {
    	player.enableMissile();

	}

	@Override
	public String toString() {
		return infoToString() + controller.;
	}

	public void store() { //El store
	}

	public void load(String filename) {
	}
}
