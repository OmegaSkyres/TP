package pr2.Commands;

import pr2.Game;

public class ShockwaveCommand extends Command {
    public ShockwaveCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("shockwave".equals(commandWords[0])) {
            return new ShockwaveCommand();
        }

        return null;
    }
}
