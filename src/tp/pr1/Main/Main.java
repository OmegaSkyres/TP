package tp.pr1.Main;

import tp.pr1.Controller;

import tp.pr1.Game;
import tp.pr1.view.GamePrinter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	//Random rand = new Random();
    	Game game = new Game();
    	Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(game, sc);
        controller.run();
        
    }
}
