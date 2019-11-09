package pr2.Commands;

import pr2.Game;

public class MoveCommand extends Command {
    public MoveCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("move".equals(commandWords[0])) {
            return new MoveCommand();
        }

        return null;
    }
}
