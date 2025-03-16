package Utilities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateNums {

    //TODO: GENERATE RANDOM NUMBERS
    public static int generateRandomNumbers(int upperRound){
        return new Random().nextInt(upperRound)+1;
    }

    //TODO: GENENRATE UNIQUE NUMBERS
    public static Set<Integer> generateUniqueNumbers(int needed , int total){
        Set<Integer> generateNumbers = new HashSet<>();
        while (generateNumbers.size() < needed) {
            int randomNumber = generateRandomNumbers(total);
            generateNumbers.add(randomNumber);
        }
        return generateNumbers;
    }


}
