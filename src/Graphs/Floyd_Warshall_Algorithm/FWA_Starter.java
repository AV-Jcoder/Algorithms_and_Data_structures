package Graphs.Floyd_Warshall_Algorithm;

public class FWA_Starter {
    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4

        graph.addEdge(0,2);
        graph.addEdge(1,0);
        graph.addEdge(1,4);
        graph.addEdge(3,4);
        graph.addEdge(4,2);

        graph.printAdjMatrix();
        graph.fwa();
        graph.printAdjMatrix();

    }
}
