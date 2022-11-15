package Graphs.Minimum_Spanning_Tree;

public class Vertex {
    private final char label;
    private boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }

    public boolean isItVisited(){
        return this.wasVisited;
    }

    public void setVisited(){
        this.wasVisited = true;
    }

    public char getLabel(){
        return this.label;
    }

}
