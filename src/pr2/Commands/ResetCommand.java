package pr2.Commands;

import pr2.Game;

public class ResetCommand extends Command {
    public ResetCommand(){
        super("reset","r","","Starts a new game.");
    }

    @Override
    public boolean execute(Game game) {
        game.reset();
        System.out.println("The game has been RESET \n");
        System.out.print(game);
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
