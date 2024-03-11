import java.util.*;

class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination, int weight) {
        adjacencyList.computeIfAbsent(source, k -> new HashMap<>()).put(destination, weight);
        adjacencyList.computeIfAbsent(destination, k -> new HashMap<>()).put(source, weight); // Assuming undirected graph
    }

    public Map<String, Integer> uniformCostSearch(String startCity) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> distances.getOrDefault(node.city, 0)));

        Set<String> closed = new HashSet<>  ();


        // Initialize distances
        for (String city : adjacencyList.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(startCity, 0);

        priorityQueue.add(new Node(startCity, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            String currentCity = currentNode.city;

            for (Map.Entry<String, Integer> neighbor : adjacencyList.get(currentCity).entrySet()) {
                String neighborCity = neighbor.getKey();
                if(closed.contains(neighborCity)) continue;
                int edgeWeight = neighbor.getValue();
                int newDistance = distances.get(currentCity) + edgeWeight;

                if (newDistance < distances.get(neighborCity)) {
                    distances.put(neighborCity, newDistance);
                    priorityQueue.add(new Node(neighborCity, newDistance));
                }
            }

            closed.add(currentCity);

        }

        return distances;
    }

    private static class Node {
        String city;
        int distance;

        public Node(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }
}

public class UCS_ALGO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a graph
        Graph graph = new Graph();

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Input edges and their weights
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter edge " + (i + 1) + " (source destination weight): ");
            String source = scanner.next();
            String destination = scanner.next();
            int weight = scanner.nextInt();

            graph.addEdge(source, destination, weight);
        }

        System.out.print("Enter the start city: ");
        String startCity = scanner.next();

        // Perform Uniform Cost Search
        Map<String, Integer> shortestDistances = graph.uniformCostSearch(startCity);

        // Display the results
        System.out.println("\nShortest distances from " + startCity + " to every other city:");
        for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

