import java.util.ArrayList;
// import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.PriorityQueue;
import java.util.Set;



public class GreedyBestFirst {
  public static List<String> findShortestPath(String start, String goal, Set<String> wordSet) {
    if(start.length()!=goal.length()){
      return null;
    }
    // PriorityQueue<WordNode> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
    Map<String, String> cameFrom = new HashMap<>();
    List<String> visited = new ArrayList<>();
    // Map<String, Integer> costSoFar = new HashMap<>();
    Map<String, Integer> costToGoal = new HashMap<>();
    // List<String> res = new ArrayList<>();
    
    cameFrom.put(start, null);
    costToGoal.put(start, 0);
    visited.add(start);
    String next = new String();
    next = getNeighbors(start, goal, visited, wordSet);
    if(next.isBlank() || visited.contains(next)){
      return null;
    }
    visited.add(next);
    
    while (!next.equals(goal)) {
      getNeighbors(next, goal, visited, wordSet);
      next = getNeighbors(next, goal, visited, wordSet);
      if(visited.contains(next)){
        return null;
      }
      visited.add(next);
      
      // for (String next : getNeighbors(current.word, wordSet)){
        
      // }
    }
    return visited;

  }

  private static String getNeighbors(String word, String goal, List<String> visited, Set<String> wordSet) {
    String next;
    next = word;
    Integer diff = getDiff(word, goal);
    char[] chars = word.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char originalChar = chars[i];
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != originalChar) {
                chars[i] = c;
                String neighbor = new String(chars);
                // System.out.println(neighbor);
                // System.out.println(getDiff(neighbor, goal));
                // System.out.println(wordSet.contains(neighbor));
                // System.out.println(!visited.contains(neighbor));

                if (wordSet.contains(neighbor) && !visited.contains(neighbor) && getDiff(neighbor, goal)<= diff) {
                  
                  System.out.println(neighbor);
                    next = neighbor;
                    diff = getDiff(neighbor, goal);
                }
            }
        }
        chars[i] = originalChar;
    }
    System.out.println(visited);
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
