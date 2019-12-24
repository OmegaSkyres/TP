package pr2.Commands;

import pr2.Controller;
import pr2.Game;
import pr2.util.MyStringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveCommand extends Command {
    private static String filename;
    private boolean filename_confirmed;
    public static final String filenameInUseMsg = "The file already exists ; do you want to overwrite it ? (Y/N)";
    static final String helpMessage = "Save one class of a game.";

    public SaveCommand(String commandName, String helpInfo) {
        super(commandName, "s", " ", helpMessage);
    }

    public SaveCommand() {
        super("save", "s", "Save", helpMessage);
    }


    @Override
    public boolean execute(Game game) throws IOException {
        BufferedWriter outChars = null;
        try {
            outChars = new BufferedWriter(new FileWriter(filename +".dat"));
            game.store(outChars);
            System.out.println("Game successfully saved in file: " + filename + ".dat. Use the load command to reload it");
        } catch (IOException e) {
            System.out.println("Invalid File");
        } finally {
            if (outChars != null) {
                outChars.close();
            }
        }
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        if ("save".equals(commandWords[0])) {
            filename = commandWords[1];
            setFilename(filename);
            Scanner in = new Scanner(System.in);
            try {
                confirmFileNameStringForWrite(filename, in);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return new SaveCommand(filename, helpMessage);
        } else {
            return null;
        }
    }

    private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws IOException {
        String loadName = filenameString;
        setFilename(filenameString);
        filename_confirmed = false;
        while (!filename_confirmed) {
            if (MyStringUtils.validFileName(loadName)) {
                File file = new File(loadName + ".dat");
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
            } else {
                if (responseYorN.length == 0) {
                    throw new IOException("save must be followed by a filename");
                } else {
                    throw new IOException("Invalid filename: the filename contains spaces");
                }
            }
        }
        return newFilename;
    }

    private void setFilename(String filename){
       this.filename = filename;
    }


}