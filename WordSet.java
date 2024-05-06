import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public static Map<String, List<String>> constructWordGraph(Set<String> wordSet) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String word1 : wordSet) {
            graph.putIfAbsent(word1, new ArrayList<>());
            for (String word2 : wordSet) {
                if (!word1.equals(word2) && isNeighbor(word1, word2)) {
                    graph.get(word1).add(word2);
                }
            }
        }

        return graph;
    }

    public static boolean isNeighbor(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }

        return diffCount == 1;
    }
}
