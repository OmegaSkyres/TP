package pr2.Commands;

import pr2.Exceptions.MissileInflightException;
import pr2.Game;

import java.io.IOException;

public class ShootCommand extends Command {
    static final String helpMessage = "Launch a missile.";
    public ShootCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    public ShootCommand(){
        super("Shoot","s","",helpMessage);
    }

    @Override
    public boolean execute(Game game) throws IOException, MissileInflightException {
        try {
            game.shootLaser();
        }catch (MissileInflightException m){
            System.out.println("!!!Ya hay un misil lanzado!!!");
        }
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("shoot".equals(commandWords[0]) || "s".equals(commandWords[0])) {
            return new ShootCommand();
        }
        else {
            return null;
        }
    }
}
