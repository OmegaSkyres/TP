package pr2.Commands;

import pr2.Game;

public class ExitCommand extends Command {
    static final String helpMessage = "Terminates the program.";
    public ExitCommand(){
        super("exit","e"," ",helpMessage);
    }
    @Override
    public boolean execute(Game game) {
        game.exit();
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("exit".equals(commandWords[0]) || "e".equals(commandWords[0])) {
            return new ExitCommand();
        }

        return null;
    }

}
