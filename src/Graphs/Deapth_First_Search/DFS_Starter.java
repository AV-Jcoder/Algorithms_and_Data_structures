package Graphs.Deapth_First_Search;

public class DFS_Starter {

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex('A');   // 0
        graph.addVertex('B');   // 1
        graph.addVertex('C');   // 2
        graph.addVertex('D');   // 3

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(2,3);

        graph.dfs();

    }

}
