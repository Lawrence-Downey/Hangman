import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputDevice = new Scanner(new File("\u202AC:\\Users\\coope\\OneDrive\\Documents\\Accelerated Software Development Text Books\\CP2280\\Hangman\\word_Bank.txt"));
        while (inputDevice.hasNext()){
            System.out.println(inputDevice.nextLine());
        }
    }
}
