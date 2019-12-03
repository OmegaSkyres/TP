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
        board.add(new Ovni(game, 0, 8));
    }

    private void initializeRegularAliens() {
        for (int i = 0; i < level.getNumRowsOfRegularAliens(); i++) {
            for (int j = 0; j < level.getNumRegularAliens(); j++) {
                board.add(new RegularShip(game, i + 1, positionIni));
                AlienShip.setRemaingAliens(1);
                positionIni++;

            }
            positionXD = i + 1;
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


