package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordSet {

    public static Set<String> readWordSetFromFile(String filename, int length) {
        Set<String> wordSet = new HashSet<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if(word.length()==length){
                    wordSet.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
        return wordSet;
    }
}
