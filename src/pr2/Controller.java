package pr2;

//import java.util.Random;

import pr2.Commands.Command;
import pr2.Commands.CommandGenerator;

import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner in;
    private String unknownCommandMsg = "Unknown Command" + "\n";

    public Controller(Game game, Scanner in) {
        this.game = game;
        this.in = in;
    }


    public void run() {
        while (!game.isFinished()) {
            System.out.println(game);
            String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
            try {
                Command command = CommandGenerator.parse(words);
                if (command != null) {
                    if (command.execute(game))
                        System.out.println(game);
                } else {
                    System.out.format(unknownCommandMsg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
