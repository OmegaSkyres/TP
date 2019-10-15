package tp.pr1;

public enum Level { EASY, HARD, INSANE;
    public static Level transform(String difficulty) {
        if ("easy".equals(difficulty)) {
            return EASY;
        }

        else if ("hard".equals(difficulty)) {
            return HARD;
        }

        else if ("insane".equals(difficulty)){
            return INSANE;
        }

        else {
            System.out.println("Wrong Difficulty.");
            return null;
        }
    }
}
