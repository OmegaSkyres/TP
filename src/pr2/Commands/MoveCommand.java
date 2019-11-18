package pr2.Commands;

import pr2.Game;

public class MoveCommand extends Command {
    int numCells; //Hay que guardar el valor del parseador
    public MoveCommand(){
        super("move","m","","Moves UCM-Ship to the indicated direction."); //TODO PREGUNTAR SI LLEVA UN ATRIBUTO MAS NUMCELLS
    }

    @Override
    public boolean execute(Game game) {
        game.move(numCells);
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("move".equals(commandWords[0])) {
            if("left".equals(commandWords[1]) || "right".equals(commandWords[1])){
                numCells = Integer.parseInt(commandWords[3]);
                return new MoveCommand();
            }
            else{

            }


        } //TODO HACER AQUI

        return null;
    }
}
