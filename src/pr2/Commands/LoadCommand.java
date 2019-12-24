package pr2.Commands;

import pr2.Controller;
import pr2.Exceptions.CommandExecuteException;
import pr2.Game;
import pr2.util.MyStringUtils;

import java.io.*;

public class LoadCommand extends Command {
    private String filename;
    private String filenameHead = "— Space Invaders v2.0 —";
    private String filenameMsg ="Wrong Document";
    private boolean filename_confirmed;
    static final String helpMessage = "Load one class of a game.";

    public LoadCommand(String commandInfo, String helpInfo) {
        super(commandInfo, "l", " ", helpMessage);
    }

    public LoadCommand() {
        super("load", "l", "Load", helpMessage);
    }

    @Override
    public Command parse(String[] commandWords) {
        if ("load".equals(commandWords[0])) {
            filename = commandWords[1];
            try {
                confirmFileNameStringForWrite(filename);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return this;
        } else {

            return null;
        }
    }

    @Override
    public boolean execute(Game game) throws IOException, CommandExecuteException {
        Boolean ok = false;
        try {
            BufferedReader inChars = new BufferedReader(new FileReader(filename));
            String firstLine = inChars.readLine().trim();
            if(firstLine.equals(filenameHead)){
                throw new CommandExecuteException(filenameMsg);
            }
            ok = true;
           // game.load(inChars);

        } catch (FileNotFoundException | CommandExecuteException ex) {
            throw new CommandExecuteException(filenameMsg);
        }
        System.out.println("Game successfully loaded from file:" + filename + ".dat");
        return ok;
    }


    private String confirmFileNameStringForWrite(String filenameString) throws IOException {
        String loadName = filenameString;
        filename_confirmed = false;
        while (!filename_confirmed) {
            if (MyStringUtils.validFileName(loadName)) {
                File file = new File(loadName);
                if (!file.exists())
                    filename_confirmed = true;
                throw new IOException("File not found");
            } else {
                throw new IOException("Invalid filename: the filename contains invalid characters");
            }
        }
        return loadName;
    }
}
