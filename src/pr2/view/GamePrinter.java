package pr2.view;
import pr2.Game;
import pr2.util.MyStringUtils;


public interface GamePrinter {
    String toString(Game game);
    public GamePrinter parse(String name);
    public String helpText();
}
