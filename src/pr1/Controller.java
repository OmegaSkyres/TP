package pr1;

//import java.util.Random;
import pr1.Game;

import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner in;
    private int x = 25;;

    public Controller(Game game, Scanner in) {
        this.game = game;
        this.in = in;
    }


    public void run(){   
        while (!game.isFinished()) {
        	System.out.println(game);
            String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
            Command command = CommandGenerator.parse(words);
            if (command != null) {
                if (command.execute(game))
                    System.out.println(game);
            }
            else {
                System.out.format(unknownCommandMsg);
            }

            System.out.print("Command > ");
        	
        	switch(words[0]) {
                case "move":
                    //TODO ARREGLAR
                case "left":
                case "a":
                    game.moveLeft();
                    break;
                case "right":
                case "d":
                    game.moveRight();
                    break;
                case "shoot":
                case "s":
                    game.posibleLaunch();
                    break;
                case "superpower":
                case "w":
                    game.shockwave();
                    break;
                case "reset":
                case "r":
                    game.reset();
                    break;
                case "list":
                case "l":
                    game.list();
                    break;
                case "exit":
                case "e":
                    game.exit();
                    break;
                case "help":
                case "h":
                    game.help();
                    break;
                case "none":
                case "n":
                case " ":
                    game.skip();
                    break;
                default:
                    System.out.println("Error" );
                    break;

            }
        	game.setCycle(game.getCycle() + 1);
            game.update();
        }
        game.getWinnerMessage();
    }
    
}
