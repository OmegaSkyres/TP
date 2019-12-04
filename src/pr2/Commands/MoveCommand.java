package pr2.Commands;

import pr2.Game;

public class MoveCommand extends Command {
    static final String helpMessage ="Moves UCM-Ship to the indicated direction.";
    private int numCells = 0; //Hay que guardar el valor del parseador
    public MoveCommand(){
        super("move","m","",helpMessage);

    }

    public MoveCommand(int newNumCells){
        super("move","m","",helpMessage);
        numCells = newNumCells;
    }

    @Override
    public boolean execute(Game game) {
        return game.move(numCells);
    }

    @Override
    public Command parse(String[] commandWords) {
        if("move".equals(commandWords[0])) {
            if("left".equals(commandWords[1])){
                if(commandWords.length == 2){
                    numCells = -1;
                }
                else{
                    numCells = -1 * Integer.parseInt(commandWords[2]);
                }
            }
            else if ("right".equals(commandWords[1])){
                if(commandWords.length == 2){
                    numCells = 1;
                }
                else{
                    numCells = Integer.parseInt(commandWords[2]);
                }

            }

            return new MoveCommand(numCells);
        }

        return null;
    }
}
