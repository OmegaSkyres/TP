package pr1;

public class BoardInitializer {

    private Level level;
    private GameObjectBoard board;
    private Game game;

    public GameObjectBoard initialize(Game game, Level level) {
        this.level = level;
        this.game = game;
        board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);

        initializeOvni();
        initializeRegularAliens();
        initializeDestroyerAliens();
        return board;
    }

    private void initializeOvni () {
        new Ovni(game);
    }

    private void initializeRegularAliens () {
        new RegularShipList(game);
    }

    private void initializeDestroyerAliens() {
        new DestroyerShipList(game);
    }
}

