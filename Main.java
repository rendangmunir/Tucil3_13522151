import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Start Word: ");
    String startWord = scanner.nextLine();
    System.out.print("End Word: ");
    String endWord = scanner.nextLine();
    Set<String> wordSet = WordSet.readWordSetFromFile("data/dictionary.txt", startWord.length());
    if(startWord.length()!=endWord.length()){

    }
    System.out.println("Pilih Algoritma!");
    System.out.println("1. UCS");
    System.out.println("2. Greedy Best First");
    System.out.println("3. A*");
    System.out.print("Pilihan(1/2/3): ");
    int Pilihan = scanner.nextInt();
    Map<String, List<String>> graph = WordSet.constructWordGraph(wordSet);
    List<String> shortestPath = new ArrayList<>();
    if(Pilihan==1){
      shortestPath = UniformCostSearch.findShortestPath(startWord, endWord, graph);
    }else if (Pilihan==2) {
      System.out.println("GBFS");
      shortestPath = GreedyBestFirst.findShortestPath(startWord, endWord, wordSet);
    }else{
      shortestPath = A_Star.findShortestPath(startWord, endWord, graph);
    }

    if (shortestPath != null) {
        System.out.println("Shortest Path from " + startWord + " to " + endWord + ": " + shortestPath);
    } else {
        System.out.println("No path found from " + startWord + " to " + endWord);
    }
    scanner.close();
    
  }
}
