package pr2.Commands;

import pr2.Game;

public class HelpCommand extends Command {
    static final String helpMessage = "Prints this help message.";
    public HelpCommand() {
        super("help","h","", helpMessage);
    }

    @Override
    public boolean execute(Game game) {
        System.out.println(CommandGenerator.commandHelp());
    return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("help".equals(commandWords[0]) || "h".equals(commandWords[0])) {
            return new HelpCommand();
        }

        return null;
    }
}
