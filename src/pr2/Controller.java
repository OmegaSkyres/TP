package pr2;

//import java.util.Random;

import pr2.Commands.Command;
import pr2.Commands.CommandGenerator;
import pr2.view.GamePrinter;
import pr2.view.PrinterGenerator;

import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner in;
    private String unknownCommandMsg = "Unknown Command" + "\n";
    GamePrinter printer;

    public Controller(Game game, Scanner in) {
        this.game = game;
        this.in = in;
        printer = PrinterGenerator.parse("BOARDPRINTER");

    }

    public void run() {
        while (!game.isFinished()) {
                System.out.println(game + printer.toString(game));
                System.out.print("Command > ");
                String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
                try {
                    Command command = CommandGenerator.parse(words);
                    if (command != null) {
                        if (command.execute(game))
                            System.out.println(game + printer.toString(game));
                    } else {
                        System.out.format(unknownCommandMsg);
                    }
                    game.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

    }
}
