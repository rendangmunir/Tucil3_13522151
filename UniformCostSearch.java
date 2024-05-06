import java.util.*;

public class UniformCostSearch {

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
                int newCost = costSoFar.get(current.word) + 1;
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

    // private static List<String> getNeighbors(String word, Set<String> wordSet) {
    //     List<String> neighbors = new ArrayList<>();
    //     char[] chars = word.toCharArray();
    //     for (int i = 0; i < chars.length; i++) {
    //         char originalChar = chars[i];
    //         for (char c = 'a'; c <= 'z'; c++) {
    //             if (c != originalChar) {
    //                 chars[i] = c;
    //                 String neighbor = new String(chars);
    //                 if (wordSet.contains(neighbor)) {
    //                     neighbors.add(neighbor);
    //                 }
    //             }
    //         }
    //         chars[i] = originalChar;
    //     }
    //     return neighbors;
    // }

    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
    }

    




    
}
