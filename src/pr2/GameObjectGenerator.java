package pr2;

import pr2.Exceptions.FileContentsException;

public class GameObjectGenerator {
    private static GameObject[] availableGameObjects = {
            new UCMShip(),
            new Ovni(),
            new RegularShip(),
            new DestroyerShip(),
            new ExplosiveShip(),
            new Shockwave(),
            new Bomb(),
            new Missile(),
            new SuperMissile()
    };

    public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws FileContentsException {
        GameObject gameObject = null;
        for (GameObject go : availableGameObjects) {
            gameObject = go.parse(stringFromFile, game, verifier);
            if (gameObject != null) break;
        }
        return gameObject;
    }


}
