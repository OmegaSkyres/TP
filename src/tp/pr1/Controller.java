package tp.pr1;

//import java.util.Random;
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
        while (game.gameOver()) {
            game.update();//game.getCycle(),game.getPoints(),game.getRandom(),game.getSuperpower() ,game.getNumerEnemies(), game.getlifes());
        	System.out.println(game);
        	System.out.print("Command > ");
        	String cmd = sc.nextLine().toLowerCase();
        	
        	switch(cmd) {
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
                    game.posibleLaunch();
                    break;
                case "superpower":
                    game.shockwave();
                    break;
                case "reset":
                    game.reset();
                    break;
                case "list":
                    game.list();
                    break;
                case "exit":
                    game.exit();
                    break;
                case "help":
                    game.help();
                    break;

            }
        	game.setCycle(game.getCycle() + 1);
        }       
    }
    
}
