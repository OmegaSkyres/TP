package pr2.Main;

import pr2.Controller;

import pr2.Game;
import pr2.Level;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int seed = 0;
        Random rand;
        String difficulty = args[0];
        if(args.length > 1){
            seed = Integer.parseInt(args[1]);
        }
        else {
            seed = new Random(System.currentTimeMillis()).nextInt();
        }
        rand = new Random(seed);
        Level level = Level.fromParam(difficulty);
    	Game game = new Game(level,rand);
    	Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(game, sc);
        controller.run();
        
    }
}
