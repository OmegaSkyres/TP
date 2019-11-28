package pr2.Commands;

import pr2.Commands.*;

public class CommandGenerator {
    private static Command[] availableCommands = {
            new ListCommand(),
            new HelpCommand(),
            new ResetCommand(),
            new ExitCommand(),
            new ListCommand(),
            new UpdateCommand(),
            new MoveCommand(),
            new ShockwaveCommand(),
            new LoadCommand(),
            new SaveCommand(),
            new ShootCommand()
    };

    public static Command parse(String[] words) {
        Command command = null;
        for(int i = 0; i < availableCommands.length; i++){
            command = availableCommands[i].parse(words);
        }
        return command;
    }


    public static String commandHelp(){
        String help = "";

        for(int i = 0; i < availableCommands.length; i++) {
            help += availableCommands[i].helpText() + "\n";
        }

        return help;

    }
}
