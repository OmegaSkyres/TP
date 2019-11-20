package pr2.Commands;

import pr2.Game;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help","h","","Prints this help message.");
    }
    @Override
    public boolean execute(Game game) {
    return false;
    } //TODO INVOCA A COMMANDHELP

    @Override
    public Command parse(String[] commandWords) {
        if("help".equals(commandWords[0])) {
            return new HelpCommand();
        }

        return null;
    }
}
