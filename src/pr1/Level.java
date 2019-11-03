package pr1;

import java.util.Random;

public enum Level { EASY, HARD, INSANE;
    Random rand = new Random();
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

    public boolean posibleBomb(){
        boolean generate = false;
        if(toString() == "EASY"){
            if(rand.nextDouble() < 0.1) {
                generate = true;
            }
        }
        else if(toString() == "HARD"){
            if (rand.nextDouble() < 0.3){
                generate = true;
            }
        }
        else if (toString() == "INSANE"){
            if (rand.nextDouble() < 0.5){
                generate = true;
            }
        }
        return generate;

    }

    public boolean posibleOvni(){
        boolean generate = false;
        if(toString() == "EASY"){
            if(rand.nextDouble() < 0.5) {
                generate = true;
            }
        }
        else if(toString() == "HARD"){
            if (rand.nextDouble() < 0.2){
                generate = true;
            }
        }
        else if (toString() == "INSANE"){
            if (rand.nextDouble() < 0.1){
                generate = true;
            }
        }
        return generate;
    }
    public int getNumberPerRowRegular(){
        int number = 4;
        return number;

    }

    public int getNumberPerRowDestroyer(){
        int number = 0;
        if(toString() == "EASY" || toString() == "HARD"){
            number = 2;
        }
        else number = 4;
        return number;

    }

    public int getNumberRowRegular(){
        int number = 0;
        if(toString() == "HARD" || toString() == "INSANE"){
            number = 2;
        }
        else {
            number = 1;
        }
        return number;
    }

    public int getNumberRegular(){
        int number = 0;
        if(toString() == "EASY"){
            number = 4;
        }
        else if (toString() == "HARD" || toString() == "INSANE"){
            number = 8;
        }
        return number;

    }

    public int getNumberDestroyer(){
        int number = 0;
        if(toString() == "EASY" || toString() == "HARD"){
            number = 2;
        }
        else if (toString() == "INSANE"){
            number = 4;
        }
        return number;

    }

}
