package pr2;

public class BoardInitializer {

    private Level level;
    private GameObjectBoard board;
    private Game game;
    private int positionIni = ((game.DIM_X/2)-(level.getNumRegularAliensPerRow()/2));

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
        board.add(new Ovni(game,0,8,1)); //Mirar si antes tengo que mirar si lo puedo generar
    }

    private void initializeRegularAliens () {
        for(int i = 0; i < level.getNumRowsOfRegularAliens(); i++){
            for(int j = 0; j < level.getNumRegularAliens(); j++) {
                board.add(new RegularShip(game,positionIni,i));
                positionIni++;
            }
        }
    }

    private void initializeDestroyerAliens() {
        for(int i = 0; i < level.getNumRegularAliens(); i++) {
            board.add(new DestroyerShip(game,positionIni+1,i));
            positionIni++;
        }
    }
}

