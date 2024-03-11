import java.util.*;

public class puzzle_8_bidirectional {

    private static final int SIZE = 3;
    private static final int[][] GOAL_STATE = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    public static void main(String[] args) {
        int[][] initialBoard = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };

        bidirectionalSearch(initialBoard, GOAL_STATE);
    }

    private static void bidirectionalSearch(int[][] initialBoard, int[][] goalState) {
        Queue<Node> forwardQueue = new LinkedList<>();
        Queue<Node> backwardQueue = new LinkedList<>();

        Set<String> forwardVisited = new HashSet<>();
        Set<String> backwardVisited = new HashSet<>();

        Node startNode = new Node(initialBoard, null, 0);
        Node goalNode = new Node(goalState, null, 0);

        forwardQueue.add(startNode);
        backwardQueue.add(goalNode);

        while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {
            Node forwardNode = forwardQueue.poll();
            Node backwardNode = backwardQueue.poll();

            if (meetInMiddle(forwardNode, backwardVisited)) {
                System.out.println("Solution found!");
                printBidirectionalSolution(forwardNode, backwardNode);
                return;
            }

            forwardVisited.add(hashBoard(forwardNode.board));
            backwardVisited.add(hashBoard(backwardNode.board));

            expandNode(forwardQueue, forwardVisited, forwardNode);
            expandNode(backwardQueue, backwardVisited, backwardNode);
        }

        System.out.println("No solution found.");
    }

    private static boolean meetInMiddle(Node node, Set<String> backwardVisited) {
        return backwardVisited.contains(hashBoard(node.board));
    }

    private static void expandNode(Queue<Node> queue, Set<String> visited, Node node) {
        for (Node child : node.generateChildren()) {
            String childHash = hashBoard(child.board);
            if (!visited.contains(childHash)) {
                queue.add(child);
                visited.add(childHash);
            }
        }
    }

    private static void printBidirectionalSolution(Node forwardNode, Node backwardNode) {
        Stack<Node> forwardPath = new Stack<>();
        Stack<Node> backwardPath = new Stack<>();

        while (forwardNode != null) {
            forwardPath.push(forwardNode);
            forwardNode = forwardNode.parent;
        }

        while (backwardNode != null) {
            backwardPath.push(backwardNode);
            backwardNode = backwardNode.parent;
        }

        while (!forwardPath.isEmpty()) {
            Node current = forwardPath.pop();
            printBoard(current.board);
            System.out.println();
        }

        backwardPath.pop(); // Avoid duplicate printing of the meeting state

        while (!backwardPath.isEmpty()) {
            Node current = backwardPath.pop();
            printBoard(current.board);
            System.out.println();
        }
    }

    private static String hashBoard(int[][] board) {
        return Arrays.deepToString(board);
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Node {
        private int[][] board;
        private Node parent;
        private int depth;

        public Node(int[][] board, Node parent, int depth) {
            this.board = board;
            this.parent = parent;
            this.depth = depth;
        }

        public Iterable<Node> generateChildren() {
            // Implement the logic to generate possible child nodes here
            // Ensure to move the empty tile in legal directions (up, down, left, right)
            // Check for boundaries to avoid illegal moves
            // Create new nodes with the updated board configuration
            // Return the list of valid child nodes
            // Note: Be careful not to modify the current node's board directly

            // Example (you need to implement this method):
            // List<Node> children = new ArrayList<>();
            // ... generate child nodes ...
            // return children;

            
            return null;
        }
    }
}

