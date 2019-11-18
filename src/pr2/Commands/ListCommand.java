package pr2.Commands;

import pr2.Game;

public class ListCommand extends Command {
    public ListCommand(){ //TODO PREGUNTAR POR SI NECESITAN 2 CONSTRUCTORES CADA COMANDO
        super("list","l","","Prints the list of available ships.");
    }

    @Override
    public boolean execute(Game game) { //Realiza esta funcion???
            System.out.println(" [R]egular ship: Points: 5 - Harm: 0 - Shield: 2" + "\n" +
                    "[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1" + "\n" +
                    "[O]vni: Points: 25 - Harm: 0 - Shield: 1" + "\n" +
                    "^__^: Harm: 1 - Shield: 3"+ "\n" + "\n");
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
