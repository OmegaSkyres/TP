package tp.pr1;

import java.util.Random;
import java.util.Scanner;

public class Controller {
    private Game game;
    private Scanner in;


    public Controller(int size, int numInitial, Random random, Scanner in) {
        game = new Game(size, numInitial, random);
        in = in;
    }


    public void run(){
        String userCommand = "";
        System.out.print(game);
        while(!game.isOver() && !game.isBlocked() && !"exit".equals(userCommand)) {

            System.out.print("Command > ");
            userCommand = in.next().toLowerCase();

            if("move".equals(userCommand)){
                String dir = in.next().toLowerCase();
                if("right".equals(dir))
                    game.move(Direction.RIGHT);

                else if("down".equals(dir))
                    game.move(Direction.DOWN);

                else if("left".equals(dir))
                    game.move(Direction.LEFT);

                else if("up".equals(dir))
                    _game.move(Direction.UP);

                else
                    System.out.println("\nMove must be followed by a direction: up, down, left or right");
            }
    }
}
