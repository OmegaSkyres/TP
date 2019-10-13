package tp.pr1;

public enum Level { EASY, HARD, INSANE;
    public static Level transform(String direction) {
        if ("easy".equals(direction)) {
            return EASY;
        }

        else if ("hard".equals(direction)) {
            return HARD;
        }

        else if ("insane".equals(direction)){
            return INSANE;
        }

        else {
            System.out.println("Wrong Difficulty.");
            return null;
        }
    }
}
