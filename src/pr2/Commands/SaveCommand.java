package pr2.Commands;

import pr2.Controller;
import pr2.Game;
import pr2.util.MyStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SaveCommand extends Command {
    String Filename;
    private boolean filename_confirmed;
    public static final String filenameInUseMsg = "The file already exists ; do you want to overwrite it ? (Y/N)";
    static final String helpMessage = "save one class of a game.";

    public SaveCommand(String commandName, String helpInfo) {
        super(commandName,"s"," ", helpMessage);
    }

    public SaveCommand() {
        super("save", "s","", helpMessage);
    }

    public void execute(Game game, Controller controller) throws IOException {
        game.store();
        controller.printGame();
    }

    public Command parse(String[] commandWords, Controller controller) {
        if("save".equals(commandWords[0])){
            Filename = commandWords[1];
            Scanner in = new Scanner(System.in);
            try {
                confirmFileNameStringForWrite(Filename, in);
            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
            return new SaveCommand(Filename, helpMessage);
        }
        else{
            return null;
        }
    }

    private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws IOException {
        String loadName = filenameString;
        filename_confirmed = false;
        while (!filename_confirmed) {
            if (MyStringUtils.validFileName(loadName)) {
                File file = new File(loadName);
                if (!file.exists())
                    filename_confirmed = true;
                else {
                    loadName = getLoadName(filenameString, in);
                }
            } else {
                throw new IOException("Invalid filename: the filename contains invalid characters");
            }
        }
        return loadName;
    }

    public String getLoadName(String filenameString, Scanner in) throws IOException {
        String newFilename = null;
        boolean yesOrNo = false;
        while (!yesOrNo) {
            System.out.print(filenameInUseMsg + ": ");
            String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
            if (responseYorN.length == 1) {
                switch (responseYorN[0]) {
                    case "y":
                        yesOrNo = true;

                    case "n":
                        yesOrNo = true;
                        System.out.println("Please enter another filename:");
                        confirmFileNameStringForWrite(in.nextLine(), in);
                    default:
                        System.out.println("Please answer 'Y' / 'N' ");
                        // ADD SOME CODE HERE
                }
            }
            else {
                if (responseYorN.length == 0){
                    throw new IOException("save must be followed by a filename");
                }
                else{
                    throw new IOException("Invalid filename: the filename contains spaces");
                }
            }
        }
        return newFilename;
    }