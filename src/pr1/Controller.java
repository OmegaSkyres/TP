package pr1;

//import java.util.Random;
import pr1.Game;

import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner sc;
    private int x = 25;;

    public Controller(Game game, Scanner sc) {
        this.game = game;
        this.sc = sc;
    }


    public void run(){   
        while (!game.isGameOver()) {
        	System.out.println(game);
        	System.out.print("Command > ");
        	String cmd = sc.nextLine().toLowerCase();
        	
        	switch(cmd) {
                case "move":
                    //TODO ARREGLAR
                case "left":
                case "a":
                    game.moveLeft();
                    //System.out.println(game.getPosShip());
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
    }
    
}
