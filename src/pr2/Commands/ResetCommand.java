package pr2.Commands;

import pr2.Game;

public class ResetCommand extends Command {
    public ResetCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("reset".equals(commandWords[0])) {
            return new ResetCommand();
        }

        return null;
    }
}
