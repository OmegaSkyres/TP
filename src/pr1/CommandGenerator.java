package pr1;

public class CommandGenerator {
    private static Command[] availableCommands = {
            new ListCommand(),
            new HelpCommand(),
            new ResetCommand(),
            new ExitCommand(),
            new ListCommand(),
            new UpdateCommand(),
            new MoveCommand(),
            new ShockwaveCommand()
    };

    public static Command parse(String[] words) {



    }


    public static String commandHelp(){

    }
}
