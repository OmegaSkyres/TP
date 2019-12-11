package pr2.view;


import pr2.Game;

public class StringifierPrinter extends GamePrinter {

    public StringifierPrinter() {
        printerName = "stringifier";
        helpText = "prints the game as plain text";
    }

    public String toString(Game game) {
        String text = "\n";

        text += "-- Space Invaders --\n\n";
        text += "G:" + game.getCycle() + "\n";
        text += "L:" + game.getLevel() + "\n";
        text += game.boardToStringifier();

        return text;
    }

    @Override
    public GamePrinter parse(String name) {
        GamePrinter gp = null;

        if (printerName.equals(name))
            gp = new StringifierPrinter();

        return gp;
    }

    @Override
    public String helpText() {
        return null;
    }

    @Override
    public void setGame(Game game) {
        // TODO Auto-generated method stub

    }
}