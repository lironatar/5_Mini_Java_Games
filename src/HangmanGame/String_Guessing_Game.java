package HangmanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class String_Guessing_Game {

    public static void main(String[] args) throws FileNotFoundException {
        int difficulty =0;
        String file = null;
        while (true) {
            if (difficulty == 1 || difficulty == 2 || difficulty == 3) break;
            else {
                System.out.println("Type 1 for easy mode(3k words), 2 for medium (20k words), and 3 for hard(30k words)");
                Scanner difficultyInput = new Scanner(System.in);
                difficulty = difficultyInput.nextInt();
            }
        }
        switch (difficulty) {
            case 1 -> file = "./src/HangmanGame/3k_words.txt";
            case 2 -> file = "./src/HangmanGame/10k_words.txt";
            case 3 -> file = "./src/HangmanGame/30k_words.txt";
            default -> {
            }
        }

        List<String> words = new ArrayList<>();
        File directory = new File(file);
        Scanner scanner = new Scanner(new File(directory.getAbsolutePath()));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        int userRemainingTries = difficulty * 3 + 5;

        Random rand = new Random();
        // nextInt makes the index an int value, then we choose the 1 to max value
        String word = words.get(rand.nextInt(words.size()));

        char[] myChars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            myChars[i] = ' ';
        }
        HashMap<Character, Boolean> hashKeyVal = new HashMap<>();
        System.out.println(Arrays.toString(myChars));
        int counter = 0;
        System.out.println("Enter a guess");
        boolean isCorrect = false;

        while (userRemainingTries >= 1) {
            if (counter == word.length()) {
                break;
            }
            System.out.println("letters used: " +hashKeyVal.keySet());
            char ch= isValidInput();
            // Will check if user put the same char again
            String stringChar ="";
            if(!hashKeyVal.containsKey(ch)) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == ch) {
                        myChars[i] = ch;
                        isCorrect = true;
                        counter++;
                    }
                }
            }else{
                isCorrect= true;

                System.out.println("You have already entered this letter, Try another one ");
            }
            hashKeyVal.put(ch,isCorrect);
            System.out.println(Arrays.toString(myChars));
            if (!isCorrect) {
                userRemainingTries--;
                System.out.println("Try again, Remaining tries: " + userRemainingTries);
            }
            isCorrect=false;
        }
        System.out.println(word);
        if (userRemainingTries > 0) {
            System.out.println("Congrats, You won!");
        } else {
            System.out.println("Game over");
            System.out.println("The word was: " + word);
        }
    }

    public static char isValidInput() {
        while (true) {
            Scanner wordUserInput = new Scanner(System.in);
            String inputWord = wordUserInput.nextLine();
            if (inputWord.length() > 1) {
                System.out.println("Only 1 letter allowed, Try again");
            } else if (inputWord.isEmpty()) {
                System.out.println("Empty input");
            } else if (!Character.isLetter(inputWord.charAt(0))) {
                System.out.println("Try Letter");
            } else {
                char ch = inputWord.charAt(0);
                return ch;
            }
        }
    }

}

