package pr2.view;
import pr2.Game;
import pr2.util.MyStringUtils;


public abstract class GamePrinter {

    protected String printerName;
    protected String helpText;

    public abstract String toString(Game game);

    public abstract GamePrinter parse(String name);

    public String helpText() {
        return  "  " + printerName + " : " + helpText + "\n";
    }

    public abstract void setGame(Game game);
}