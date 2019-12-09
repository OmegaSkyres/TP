package pr2;

import pr2.Exceptions.FileContentsException;
import pr2.Exceptions.InsufficientPointsException;
import pr2.Exceptions.MissileInflightException;
import pr2.util.MyStringUtils;
import pr2.view.BoardPrinter;
import pr2.view.GamePrinter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
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


	public Game(Level level, Random random) {
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
		currentCycle = 0;
	}

	public void initGame() {
		currentCycle = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this, DIM_X / 2, DIM_Y - 1);
		board.add(player);
	}

	public void update() {
		board.computerAction(); //Como primero los genero los genero una posicion anterior para despues moverlos
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
		return !player.isAlive() || AlienShip.onTheFloor();
	}

	private boolean playerWin() {
		return AlienShip.allDead();
	}

	public void exit() {
		doExit = true;
	}

	public String getWinnerMessage() {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}

	public boolean isOnBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < DIM_X && y < DIM_Y;
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
		if(!player.move(numCells)){
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean shootLaser() throws MissileInflightException {
		return player.shootMissile();

	}

	public boolean buySuperMissile() throws MissileInflightException, InsufficientPointsException {
		return player.buySuperMissile();
	}

	@Override
	public boolean shockWave() {
		boolean ok = false;
		if(player.shockWave()){
			ok = true;
		}
		return  ok;
	}

	@Override
	public void receivePoints(int points) {
		player.setPoints(points);
	}

	@Override
	public void enableShockWave() {
		player.setShockwave(false);
	}

	@Override
	public void enableMissile() {
		player.enableMissile();
	}

	@Override
	public String toString() {
		return infoToString();
	}

	public void damageNearbyObjects(int x, int y) {

	}
	/*
	public void store(BufferedWriter outChars) { //El store
		outChars.write();
		outChars.write(player.getPoints);
		outChars.write(currentRules.toString());
	}

	public void load(BufferedReader inStream) throws IOException, FileContentsException, CloneNotSupportedException {
		Game failSafe = (Game) this.clone();
		boolean loading = false;
		String line = inStream.readLine().trim();
		while (line != null && !line.isEmpty()) {
			GameObject gameObject = GameObjectGenerator.parse(line, this, verifier);
			if (gameObject == null) {
				throw new FileContentsException("invalid file, " + "unrecognised line prefix");
			}
			board.add(gameObject);
			line = inStream.readLine().trim();
		}
		catch (Exception ex){
			restoreGame(failstore);
		}
	}
	*/
}
