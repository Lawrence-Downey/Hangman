import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File("C:\\Users\\coope\\OneDrive\\Hangman\\word_Bank.txt"));
        Scanner inputDevice = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        while (readFile.hasNext()){
            words.add(readFile.nextLine());
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        while(true) {
            printWordState(word, playerGuesses);
            getPlayerGuess(inputDevice, word, playerGuesses);
            if(printWordState(word, playerGuesses)){
                System.out.println("You win!");
                break;
            }
            System.out.println("Please enter your guess for the word");
            if(inputDevice.nextLine().equals(word)){
                System.out.println("You win!");
                break;
            }else{
                System.out.println("Incorrect guess. Please try again.");
            }
        }

    }

    private static void getPlayerGuess(Scanner inputDevice, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = inputDevice.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++){
            if(playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correctCount++;
            }else{
                System.out.print("-");
            }
        }
        System.out.println();
        return(word.length() == correctCount);
    }
}
