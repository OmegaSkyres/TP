package pr2.Commands;

import pr2.Game;

public class ListCommand extends Command {
    public ListCommand(){ //TODO PREGUNTAR POR SI NECESITAN 2 CONSTRUCTORES CADA COMANDO
        super("list","l","","");

    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("list".equals(commandWords[0])) {
            return new ListCommand();
        }

        return null;
    }

}
