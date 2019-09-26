package tp.pr1.Main;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.view.GamePrinter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GamePrinter printer = new GamePrinter(game,8,9);
        System.out.println(game);
        System.out.println(printer);


        Random random = new Random();

        Scanner in = new Scanner(System.in);
        //Controller controller = new Controller(random, in);
        //controller.run();
    }
}
