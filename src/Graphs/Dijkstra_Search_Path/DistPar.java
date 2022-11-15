package Graphs.Dijkstra_Search_Path;

/**
 * Класс хранит информацию о "родительской" вершине и дистанции от неё,
 * для проверяемой нами вершины.
 */

public class DistPar {
        int distance;       // Расстояние от родительской вершины до текущей.
        int parentVert;     // Родитель текущей вершины.

    public DistPar(int pV, int dist) {
        this.distance = dist;
        this.parentVert = pV;
    }

}
