package pr2.Exceptions;

public class CommandParseException extends Exception {
    private static String Pmessage;

    public CommandParseException(String string) {
        super(Pmessage);
        Pmessage = string;
    }


    public String getMessage() {
        return Pmessage;
    }
}