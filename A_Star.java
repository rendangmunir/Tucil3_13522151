import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class A_Star {
  public static List<String> findShortestPath(String start, String goal, Map<String, List<String>> wordSet) {
    if(start.length()!=goal.length() ||   wordSet.get(start)==null || wordSet.get(goal)==null){
      return null;
    }
    PriorityQueue<WordNode> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
    Map<String, String> cameFrom = new HashMap<>();
    Map<String, Integer> costSoFar = new HashMap<>();

    frontier.add(new WordNode(start, 0));
    cameFrom.put(start, null);
    costSoFar.put(start, 0);
    Integer nodeCount = 0;
    while (!frontier.isEmpty()) {
        WordNode current = frontier.poll();

        if (current.word.equals(goal)) {
            System.out.println("Node visited: " + nodeCount);
            return reconstructPath(cameFrom, current.word);
        }

        for (String next : wordSet.get(current.word)) {
            int newCost = costSoFar.get(current.word) + 1 + getDiff(current.word, goal);
            if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
              costSoFar.put(next, newCost);
              frontier.add(new WordNode(next, newCost));
              cameFrom.put(next, current.word);
            }
            nodeCount++;
        }
    }

    return null; // No path found
  }

    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
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
