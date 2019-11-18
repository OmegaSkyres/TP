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
		for(GameObjectBoard object : board) //Mirar si el move mueve todos los objetos de la lista o llama a update y alli ya mueve.
			board.update();
	}

	@Override
	public boolean shootLaser() {
		return false;
	} //Laser es el misil???

	@Override
	public boolean shockWave() {
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

    	return false;
	}

	@Override
	public void receivePoints(int points) {

    	points += points; //TODO DE DONDE SALEN ESTOS POINTS QUIEN LLEVA ESTE ATRIBUTO
	}

	@Override
	public void enableShockWave() {
    	Shockwave shockwave = new Shockwave(this,) { // Se añade el ShockWabe al board???

		}
    	//TODO COMO ACTIVO EL SHOCKWABE SI HA MUERTO EL OVNI Y DEBO MIRAR DE SI YA TENGO UNO??

	}

	@Override
	public void enableMissile() {
    	Missile missile = new Missile(this,player.UCMShipPositionX()+1,player.UCMShipPositionY());
    	board.add(missile);
		if(missile.isEnable()){
			System.out.println("!!!Ya hay un misil lanzado!!!");
			//Si ya hay un misil lanzado no se puede lanzar otro, pero si no se hay niguno lo lanzamos
		}
		else {
			missile.active = true;
			missile.setPositionX(player.UCMShipPositionX()+1);
			missile.setPositionY(player.UCMShipPositionY());
		}

	}

}
