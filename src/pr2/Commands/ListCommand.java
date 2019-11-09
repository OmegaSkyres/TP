package pr2.Commands;

import pr2.Game;

public class ListCommand extends Command {
    public ListCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
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
