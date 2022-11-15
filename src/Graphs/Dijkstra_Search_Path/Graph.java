package Graphs.Dijkstra_Search_Path;

public class Graph {
    //***********************************************************************//
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1_000_000;
    private Vertex[] vertexList;
    private int[][] adjMatrix;
    private int nVerts;
    private int nTree;
    private DistPar[] sPath;
    private int currentVert;
    private int startToCurrent;
    //***********************************************************************//
    public Graph(){
        this.vertexList = new Vertex[MAX_VERTS];
        this.adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        this.nVerts = 0;
        this.nTree = 0;
        this.sPath = new DistPar[MAX_VERTS];
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                this.adjMatrix[i][j] = INFINITY;
            }
        }
    }
    //***********************************************************************//
    public void addVertex(char label){
        this.vertexList[nVerts++] = new Vertex(label);
    }
    //***********************************************************************//
    public void addEdge(int start, int end, int weight){
        this.adjMatrix[start][end] = weight;
    }
    //***********************************************************************//
    // Поиск кратчайшего пути.
    public void path(){
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        // Перемещение строки роасстояний из adjMatrix в sPath.
        for (int i = 0; i < nVerts; i++) {
            int tempDist = adjMatrix[startTree][i];
             sPath[i] = new DistPar(startTree,tempDist);
        }

        // Пока все вершины не окажутся в дереве.
        while (nTree < nVerts){

            // Берём вершину с минимальной ценой перехода.
            int indexMin = getMin();
            int minDistance = sPath[indexMin].distance;

            // Если такой вершины нет.
            if (minDistance == INFINITY){
                System.out.println("Граф содержит недостижимые вершины.");
                break; // Построение дерева минимального пути завершено.
            }
            else {
                currentVert  = indexMin;
                startToCurrent = sPath[indexMin].distance;
                // Минимальное расстояние от startTree до currentVert - startToCurrent
            }

            // Включение текущей вершины в дерево
            vertexList[currentVert].isInTree = true;
            nTree++;

            // Обновление массива sPath[].
            adjust_sPath();
        }

        // Печатаем путь. Вывод содержимого sPath.
        displayPath();
    }
    //***********************************************************************//
    private void displayPath() {

        for (int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i].label + "=");
            if (sPath[i].distance == INFINITY) System.out.print("inf");
            else System.out.print(sPath[i].distance);
            char parent = vertexList[sPath[i].parentVert].label;
            System.out.print("(" + parent + ")\n");
        }
    }
    //***********************************************************************//
    // Метод обновляет содержимое массива кратчайших путей sPath[].
    private void adjust_sPath() {

        int colomn = 1;
        while (colomn<nVerts){
            if (vertexList[colomn].isInTree){
                colomn++;
                continue;
            }
            // Вычисление расстояний для одного элемента sPath.
                                 // Дистанция от текущей вершины к смежной.
            int currentToFringe = adjMatrix[currentVert][colomn];
                                // Суммирование расстояний.
            int startToFringe = startToCurrent + currentToFringe;
                            // Определение расстояния текущего элемента sPath[].
            int sPathDist = sPath[colomn].distance;

            // Сравнение расстояний.
            if (startToFringe < sPathDist){ // Если новые данные меньше, чем в sPath
                //Обновляем данные
                sPath[colomn].parentVert = currentVert;
                sPath[colomn].distance = startToFringe;
            }
            colomn++;
        }// end of while(colomn<nVerts).
    }

    //***********************************************************************//
    // Метод возвращает индекс вершины с минимальным значением дистанции.
    private int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int i = 0; i < nVerts; i++) {
            if (sPath[i].distance < minDist && !vertexList[i].isInTree){
                minDist = sPath[i].distance;
                indexMin = i;
            }
        }
        return indexMin;
    }
    //***********************************************************************//


} // end of class Graph.
