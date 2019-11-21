package pr2.Commands;

import pr2.Game;

public class UpdateCommand extends Command {
    static final String helpMessage = "Update the game.";
    public UpdateCommand(){
        super("update","u","", helpMessage);
    }

    @Override
    public boolean execute(Game game) {
        game.update();
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
