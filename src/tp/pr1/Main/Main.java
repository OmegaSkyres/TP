package tp.pr1.Main;

import tp.pr1.Controller;

import tp.pr1.Game;
import tp.pr1.view.GamePrinter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int seed = 0;
        String dificultad = args[0];
        if(args.length > 1){
            seed = Integer.parseInt(args[1]);
        }
    	Game game = new Game();//dificultad,seed);
    	Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(game, sc);
        controller.run();
        
    }
}
