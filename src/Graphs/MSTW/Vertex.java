package Graphs.MSTW;

/**
 * Класс описывает вершину графа.
 * Поле label - название вершины, например 'A'
 * Поле isInTree - включена вершина в дерево или нет.
 */
public class Vertex {

    private final char label;
    private boolean isInTree;

    public Vertex(char label) {
        this.label = label;
        this.isInTree = false;
    }

    public boolean isInTree(){
        return this.isInTree;
    }

    public void setIsInTree(){
        this.isInTree = true;
    }

    public char getLabel(){
        return this.label;
    }

}
