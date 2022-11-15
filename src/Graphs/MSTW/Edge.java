package Graphs.MSTW;

/**
 * Класс реализует ребро графа.
 * Поля src & dest (начало и конец ребра) являются индексами вершин.
 * Поле distance - цена перехода или вес ребра.
 */
public class Edge {
    public int srcVert; // Индекс начальной вершины ребра.
    public int destVert; // Индекс конечной вершины ребра.
    public int distance; // Расстяние от начальной до конечной вершины.

    public Edge(int srcVert, int destVert, int distance) {
        this.srcVert = srcVert;
        this.destVert = destVert;
        this.distance = distance;
    }
}
