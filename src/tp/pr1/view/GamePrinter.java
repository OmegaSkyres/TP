package tp.pr1.view;
import tp.pr1.Game;
import tp.pr1.UCMShip;
import tp.pr1.util.MyStringUtils;
import tp.pr1.UCMShip;



public class GamePrinter {

    Game game;
    String[][] board;
    int numRows;
    int numCols;
    final String space = " ";
    UCMShip ship = new UCMShip();

    public GamePrinter (Game game, int rows, int cols) {
        this.game = game;
        this.numRows = rows;
        this.numCols = cols;
    }

    private void encodeGame() {
        board = new String[numRows][numCols];
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
            	board[i][j] = game.position(i, j);
            }
        }
    }

    public String toString() {
        encodeGame();

        int cellSize = 7;
        int marginSize = 2;
        String vDelimiter = "|";
        String hDelimiter = "-";

        String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
        String margin = MyStringUtils.repeat(space, marginSize);
        String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

        StringBuilder str = new StringBuilder();

        str.append(lineDelimiter);

        for(int i=0; i<numRows; i++) {
            str.append(margin).append(vDelimiter);
            for (int j=0; j<numCols; j++) {
                str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
            }
            str.append(lineDelimiter);
        }
        return str.toString();
    }
}
