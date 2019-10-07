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
            case "s":
            	game.posibleLaunch();
            	
            	break;
            }
        	game.setCycle(game.getCycle() + 1);
        }       
    }
    
}
