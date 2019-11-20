package pr2.Commands;

import pr2.Controller;
import pr2.Game;
import pr2.util.MyStringUtils;

import java.io.File;
import java.io.IOException;

public class LoadCommand extends Command {
    private String Filename;
    private boolean filename_confirmed;
    static final String helpMessage = "load one class of a game.";

    public LoadCommand(String commandInfo, String helpInfo) {
        super(commandInfo,"l"," ", helpMessage);
    }

    public LoadCommand() {
        super("load","l"," ", helpMessage);
    }

    public Command parse(String[] commandWords, Controller controller) {
        if("load".equals(commandWords[0])){
            Filename = commandWords[1];
            try {
                confirmFileNameStringForWrite(Filename);
            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }

            return this;
        }

        else{

            return null;
        }
    }

    public void execute(Game game, Controller controller) throws IOException {
        game.load(Filename);
        System.out.println("Game successfully loaded from file:" + Filename);
        controller.printGame();
    }

    private String confirmFileNameStringForWrite(String filenameString) throws IOException {
        String loadName = filenameString;
        filename_confirmed = false;
        while (!filename_confirmed) {
            if (MyStringUtils.validFileName(loadName)) {
                File file = new File(loadName);
                if (!file.exists())
                    filename_confirmed = true;
                throw new IOException ("File not found");
            }
            else {
                throw new IOException("Invalid filename: the filename contains invalid characters");
            }
        }
        return loadName;
    }
