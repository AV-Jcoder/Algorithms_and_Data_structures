package Graphs.Floyd_Warshall_Algorithm;

/**
 * Класс - вершина(узел).
 */
public class Vertex {
    private char label;     // Название вершины, например 'A'


    public Vertex(char label) {
        this.label = label;
    }

    public char getLabel(){
        return this.label;
    }

}
