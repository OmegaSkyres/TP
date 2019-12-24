package pr2.Commands;

import pr2.Exceptions.InsufficientPointsException;
import pr2.Exceptions.MissileInflightException;
import pr2.Game;

import java.io.IOException;

    public class BuySuperMissileCommand extends Command {
        static final String helpMessage = "Buy a Super Missile and launch it.";
        public BuySuperMissileCommand(String name, String shortcut, String details, String help) {
            super(name, shortcut, details, help);
        }

        public BuySuperMissileCommand(){
            super("BuySuperMissile","b","BuySuperMissile",helpMessage);
        }

        @Override
        public boolean execute(Game game) throws IOException, MissileInflightException {
            try {
                game.buySuperMissile();
            }catch (MissileInflightException | InsufficientPointsException m){
                System.out.println("!!!Ya hay un misil lanzado o Puntos Insuficientes!!!");
            }
            return false;
        }

        @Override
        public Command parse(String[] commandWords) {
            if("buy".equals(commandWords[0]) || "b".equals(commandWords[0])) {
                return new BuySuperMissileCommand();
            }
            else {
                return null;
            }
        }
    }
