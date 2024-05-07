package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class GreedyBestFirst {
  public static List<String> findShortestPath(String start, String goal, Set<String> wordSet) {
    if(start.length()!=goal.length() || !wordSet.contains(start) || !wordSet.contains(goal)){
      System.out.println("Node visited\t: 0");
      return null;
    }
    List<String> visited = new ArrayList<>();
    visited.add(start);
    String next = new String();
    int nodeCount=0;
    next = getNeighbors(start, goal, visited, wordSet);
    if(next.isBlank() || visited.contains(next)){
      System.out.println("Node visited\t: "+ nodeCount);
      return null;
    }
    visited.add(next);
    nodeCount++;
    
    while (!next.equals(goal)) {
      getNeighbors(next, goal, visited, wordSet);
      next = getNeighbors(next, goal, visited, wordSet);
      if(visited.contains(next)){
        System.out.println("Node visited: \t" + nodeCount);
        return null;
      }
      visited.add(next);
      nodeCount++;
    }
    System.out.println("Node visited\t: " + nodeCount);
    return visited;

  }

  private static String getNeighbors(String word, String goal, List<String> visited, Set<String> wordSet) {
    String next;
    next = word;
    Integer diff = getDiff(word, goal);
    char[] chars = word.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char originalChar = chars[i];
        for (char c = 'z'; c >= 'a'; c--) {
            if (c != originalChar) {
                chars[i] = c;
                String neighbor = new String(chars);

                if (wordSet.contains(neighbor) && !visited.contains(neighbor) && getDiff(neighbor, goal)<= diff) {
                    next = neighbor;
                    diff = getDiff(neighbor, goal);
                }
            }
        }
        chars[i] = originalChar;
    }
    return next;
  }

  private static int getDiff(String word, String goal){
    char[] startWord = word.toCharArray();
    char[] goalWord = goal.toCharArray();
    int diff = 0;
    for(int i=0; i<startWord.length; i++){
      if(startWord[i]!=goalWord[i]){
        diff++;
      }
    }
    return diff;
  }
  
}
