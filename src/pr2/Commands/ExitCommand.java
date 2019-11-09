package pr2.Commands;

import pr2.Game;

public class ExitCommand extends Command {
    public ExitCommand(){
        super();
    }
    public ExitCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public boolean execute(Game game) {
        game.exit();
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("exit".equals(commandWords[0])) {
            return new ExitCommand();
        }

        return null;
    }

}
