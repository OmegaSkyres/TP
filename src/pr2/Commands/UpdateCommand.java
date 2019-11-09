package pr2.Commands;

import pr2.Game;

public class UpdateCommand extends Command {
    public UpdateCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("update".equals(commandWords[0])) {
            return new UpdateCommand();
        }

        return null;
    }
}
