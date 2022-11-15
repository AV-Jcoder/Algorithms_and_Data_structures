package Graphs.Breads_First_Search;

/**
 * Класс - вершина(узел) в графе для реализации алгоритма поиска в ширину.
 */
public class Vertex {
    private char label;     // Название вершины, например 'A'
    private boolean wasVisited; // Отмечена вершина или нет.

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }

    public boolean isItVisited() {
        return this.wasVisited;
    }

    // Метод устанавливает флаг
    public void setWasVisited() {
        this.wasVisited = true;
    }

    @Override
    public String toString() {
        return "Вершина " + label + " отмечена.";
    }
}
