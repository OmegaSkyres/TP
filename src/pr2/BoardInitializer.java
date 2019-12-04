package pr2;

public class BoardInitializer {

    private Level level;
    private GameObjectBoard board;
    private Game game;
    private int positionIni;
    private int positionIniD;
    private int positionXD;

    public GameObjectBoard initialize(Game game, Level level) {
        this.level = level;
        this.game = game;
        board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
        positionIni = ((game.DIM_X / 2) - (level.getNumRegularAliensPerRow() / 2));
        positionIniD = ((game.DIM_X / 2) - (level.getNumDestroyerAliensPerRow() / 2));


        initializeRegularAliens();
        initializeDestroyerAliens();
        initializeOvni();
        return board;
    }

    private void initializeOvni() {
        board.add(new Ovni(game, 0, 9));
    }

    private void initializeRegularAliens() {
        for (int i = 1; i <= level.getNumRowsOfRegularAliens(); i++) {
            for (int j = 0; j < level.getNumRegularAliensPerRow(); j++) {
                board.add(new RegularShip(game, i, positionIni));
                AlienShip.setRemaingAliens(1);
                positionIni++;
            }
            positionIni = (game.DIM_X / 2) - (level.getNumRegularAliensPerRow() / 2);
            positionXD = i;
        }

    }

    private void initializeDestroyerAliens() {
        for (int i = 0 ; i < level.getNumDestroyerAliens(); i++) {
            board.add(new DestroyerShip(game, positionXD + 1, positionIniD));
            AlienShip.setRemaingAliens(1);
            positionIniD++;
        }
    }
}


