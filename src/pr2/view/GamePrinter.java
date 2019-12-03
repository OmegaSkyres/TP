package pr2.view;
import pr2.Game;
import pr2.util.MyStringUtils;


public abstract class GamePrinter {
    public abstract String toString(Game game);
    public abstract GamePrinter parse(String name);
    public abstract String helpText();
}
