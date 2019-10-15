package tp.pr1;

public enum Level { EASY, HARD, INSANE;
    public static Level transform(String difficulty) {
        if ("EASY".equals(difficulty)) {
            return EASY;
        }

        else if ("HARD".equals(difficulty)) {
            return HARD;
        }

        else if ("INSANE".equals(difficulty)){
            return INSANE;
        }

        else {
            System.out.println("Wrong Difficulty.");
            return null;
        }
    }
}
