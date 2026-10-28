import java.util.*;

class Node {
    String name;
    int g;
    int h;
    Node parent;

    Node(String name, int g, int h) {
        this.name = name;
        this.g = g;
        this.h = h;
    }

    int f() {
        return g + h;
    }
}

public class aStar {

    public static void main(String[] args) {

        Node A = new Node("A", 0, 5);
        Node B = new Node("B", 1, 4);
        Node C = new Node("C", 2, 2);
        Node D = new Node("D", 3, 0);

        PriorityQueue<Node> open = new PriorityQueue<>(
            Comparator.comparingInt(Node::f)
        );

        open.add(A);

        Map<String, List<Node>> graph = new HashMap<>();

        graph.put("A", Arrays.asList(B, C));
        graph.put("B", Arrays.asList(D));
        graph.put("C", Arrays.asList(D));
        graph.put("D", new ArrayList<>());

        Map<String, Integer> cost = new HashMap<>();
        cost.put("A", 0);

        while (!open.isEmpty()) {

            Node current = open.poll();

            if (current.name.equals("D")) {
                System.out.println("Goal Reached!");
                System.out.println("Path:");

                Stack<String> path = new Stack<>();

                while (current != null) {
                    path.push(current.name);
                    current = current.parent;
                }

                while (!path.isEmpty()) {
                    System.out.print(path.pop() + " ");
                }

                return;
            }

            for (Node next : graph.get(current.name)) {

                int newCost = current.g + 1;

                if (!cost.containsKey(next.name)
                        || newCost < cost.get(next.name)) {

                    cost.put(next.name, newCost);
                    next.g = newCost;
                    next.parent = current;

                    open.add(next);
                }
            }
        }

        System.out.println("Path not found!");
    }
}