package Graphs.Breads_First_Search;

public class BFS_Loader {

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addVertex('G'); // 6
        graph.addVertex('H'); // 7
        graph.addVertex('I'); // 8
        graph.addVertex('J'); // 9

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
        graph.addEdge(1,5);
        graph.addEdge(1,9);
        graph.addEdge(2,6);
        graph.addEdge(2,9);
        graph.addEdge(4,5);
        graph.addEdge(3,7);
        graph.addEdge(3,8);

        graph.bfs();
    }
}
