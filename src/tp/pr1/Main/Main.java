package tp.pr1.Main;

import tp.pr1.Controller;
import tp.pr1.Game;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int size = Integer.parseInt(args[0]);
        int initCells = Integer.parseInt(args[1]);
        Random random = new Random();

		/*if(args.length == 3){ //TODO Como es la semilla como argumento?
			random = args[2];
		}*/

        Scanner in = new Scanner(System.in);
        Game game = new Game();
        Controller controller = new Controller(size, initCells, random, in);
        System.out.println(size + " " + initCells);
        controller.run();
    }
}
