package pr2.Exceptions;

public class ShockWaveException extends Exception{
    private static String message = "Failed to shoot";

    public ShockWaveException() {
        super(message);
    }
}