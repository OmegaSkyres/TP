package pr2;

import pr2.Exceptions.MissileInflightException;

public interface IPlayerController {
    // PLAYER ACTIONS
    public boolean move(int numCells);
    public boolean shootLaser() throws MissileInflightException;
    public boolean shockWave();

    // CALLBACKS
    public void receivePoints(int points);
    public void enableShockWave();
    public void enableMissile();
}
