package pr2.Commands;

import pr2.Game;

public class MoveCommand extends Command {
    static final String helpMessage ="Moves UCM-Ship to the indicated direction.";
    int numCells; //Hay que guardar el valor del parseador
    public MoveCommand(){
        super("move","m","",helpMessage);
    }

    @Override
    public boolean execute(Game game) {
        game.move(numCells);
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("move".equals(commandWords[0])) {
            if("left".equals(commandWords[1])){
                numCells = -1 * Integer.parseInt(commandWords[2]);
                return new MoveCommand();
            }
            else if ("right".equals(commandWords[1])){
                numCells = Integer.parseInt(commandWords[2]);
                return new MoveCommand();

            }


        }

        return null;
    }
}
