package Graphs.Minimum_Spanning_Tree;

public class MST_Starter {

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex('A');   // 0
        graph.addVertex('B');   // 1
        graph.addVertex('C');   // 2
        graph.addVertex('D');   // 3
        graph.addVertex('E');   // 4

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(0,4);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);

//        graph.mstDFS();
        graph.mstBFS();

    }

}
