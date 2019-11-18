package pr2.Commands;

import pr2.Game;

public class ShockwaveCommand extends Command {

    public ShockwaveCommand(){
        super("ShockWave","s","","UCM-Ship releases a shock wave.");
    }



    @Override
    public boolean execute(Game game) {
        game.enableShockWave();
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
