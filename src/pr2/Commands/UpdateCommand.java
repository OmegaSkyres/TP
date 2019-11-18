package pr2.Commands;

import pr2.Game;

public class UpdateCommand extends Command {
    public UpdateCommand(){
        super("update","u","","Update the game.");
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
