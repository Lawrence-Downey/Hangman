import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Hangman {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File letThereBeSound = new File("Family_Friendly_Noose_Song.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(letThereBeSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        Scanner readFile = new Scanner(new File("C:\\Users\\coope\\OneDrive\\Hangman\\word_Bank.txt"));
        Scanner inputDevice = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        String[] gallows = new String[]{"  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
                                        "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="};

        while (readFile.hasNext()){
            words.add(readFile.nextLine());
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        List<Character> playerGuesses = new ArrayList<>();

        int incorrectGuess = 0;
        System.out.println(gallows[0]);
        while(true) {
            clip.start();
            clip.loop(3);
            printWordState(word, playerGuesses);
            System.out.println();
            if(!getPlayerGuess(inputDevice, word, playerGuesses)){
                incorrectGuess++;
            }
            if(printWordState(word, playerGuesses)){
                System.out.println("Congratulations!");
                System.out.println("You've won the game, saved your buddy and no longer have to worry about potential murder charges!");
                System.out.println("Thanks for playing!");
                System.out.println("Would you like to save another friend? yes or no:");
                if(inputDevice.nextLine().equals("yes")){
                    System.out.println();
                    System.out.println(gallows[0]);
                    incorrectGuess = 0;
                    continue;
                }else{
                    System.out.println("Okay. Come back again soon!");
                    break;
                }
            }
            if(incorrectGuess == 1){
                System.out.println(gallows[1]);
            }
            else if(incorrectGuess == 2){
                System.out.println(gallows[2]);
            }
            else if(incorrectGuess == 3){
                System.out.println(gallows[3]);
                System.out.println();
                System.out.println("Looks like your buddy is starting to turn a little blue.");
            }
            else if(incorrectGuess == 4){
                System.out.println(gallows[4]);
                System.out.println();
                System.out.println("If you keep guessing wrong letters he's going not going to make it.");
            }
            else if(incorrectGuess == 5){
                System.out.println(gallows[5]);
                System.out.println();
                System.out.println("I'm glad it's not me up there. No pressure but you cannot afford any more wrong guesses...");
            }
            else if(incorrectGuess == 6){
                System.out.println(gallows[6]);
                System.out.println();
                System.out.println("The good news? He's not blue anymore..");
                System.out.println("The bad news? You better buy a suit. You have a funeral to attend to.");
                System.out.println("I am sorry but you lost the game.");
                System.out.println("The word was: " + word);
                System.out.println("Would you like to try and save another friend? yes or no:");
                if(inputDevice.nextLine().equals("yes")){
                    System.out.println();
                    System.out.println(gallows[0]);
                    incorrectGuess = 0;
                    continue;
                }else{
                    System.out.println("Okay. Come back again soon!");
                    break;
                }
            }
            System.out.println();
            System.out.println("You now have a chance to guess the word and save your buddy.");
            System.out.println("Guess wrong however, and it's instant Game Over! Choose wisely!");
            System.out.println("Would you like to guess the word? (yes/no): ");
            if(inputDevice.nextLine().equals("yes")){
                System.out.println();
                System.out.println("Please enter your guess for the word");
                if(inputDevice.nextLine().equals(word)){
                    System.out.println("Congratulations!");
                    System.out.println("You've won the game, saved your buddy and no longer have to worry about potential murder charges!");
                    System.out.print("Thanks for playing!");
                    System.out.println();
                    System.out.println("Would you like to save another friend? yes or no:");
                    if(inputDevice.nextLine().equals("yes")){
                        System.out.println();
                        System.out.println(gallows[0]);
                        incorrectGuess = 0;
                        continue;
                    }else{
                        System.out.println("Okay. Come back again soon!");
                        break;
                    }
                }else{
                    System.out.println();
                    System.out.println("The good news? You don't have to worry about the $20 you owe Cecil.");
                    System.out.println("The bad news? You have to explain to Cecil's wife why she's now a widow...");
                    System.out.println("I am sorry but you lost the game.");
                    System.out.println("The word was: " + word);
                    System.out.println(gallows[6]);
                    System.out.println();
                    System.out.println("Would you like to try and save another friend? yes or no:");
                    if(inputDevice.nextLine().equals("yes")){
                        System.out.println();
                        System.out.println(gallows[0]);
                        incorrectGuess = 0;
                        continue;
                    }else{
                        System.out.println("Okay. Come back again soon!");
                        break;
                    }
                }
            }else{
                System.out.println("You either spelled yes wrong or you aren't much of a gambler");
                System.out.println();
            }
        }
    }

    private static boolean getPlayerGuess(Scanner inputDevice, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = inputDevice.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctGuess = 0;
        System.out.println("Guess the word:");
        for(int i = 0; i < word.length(); i++){
            if(playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correctGuess++;
            }else{
                System.out.print("-");
            }
        }
        System.out.println();
        return(word.length() == correctGuess);
    }
}
