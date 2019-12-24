package pr2.Commands;

import pr2.Game;
import pr2.GameObject;

public class ListCommand extends Command {
    static final String helpMessage = "Prints the list of available ships.";
    public ListCommand(){
        super("list","l","List", helpMessage);
    }

    @Override
    public boolean execute(Game game) { //Realiza esta funcion???
            System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 3" + "\n" +
                    "[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1" + "\n" +
                    "[E]xplde ship: Points: 5 - Harm: 1 - Shield: 3" + "\n" +
                    "[O]vni: Points: 25 - Harm: 0 - Shield: 1" + "\n" +
                    "^__^: Harm: 1 - Shield: 3"+ "\n" + "\n");
            return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if("list".equals(commandWords[0]) || "l".equals(commandWords[0])) {
            return new ListCommand();
        }

        return null;
    }

}
