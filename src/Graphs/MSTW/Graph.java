package Graphs.MSTW;

/**
 * Взвешанный не направленный граф.
 *
 */
public class Graph {
//---------------------------------------------------------------------------------------------------------//
    // Поля класса //
    private final int MAX_VERTS = 20;
    private final int INFINITY = Integer.MAX_VALUE;
    private Vertex[] vertexList;
    private int[][] adjMatrix;
    private int nVerts;
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree;
//---------------------------------------------------------------------------------------------------------//
    // Конструктор //
    public Graph() {
        this.vertexList = new Vertex[MAX_VERTS];
        this.thePQ = new PriorityQ(MAX_VERTS);
        this.nVerts = 0;
        this.adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMatrix[i][j] = INFINITY;
            }
        }
    }
//---------------------------------------------------------------------------------------------------------//
    // Добавить вершину в список.//
    public void addVertex(char label){
        vertexList[nVerts++] = new Vertex(label);
    }
//---------------------------------------------------------------------------------------------------------//
    // Добавление ребер в матрицу смежности //
    public void addEdge(int start, int end, int weight){
        adjMatrix[start][end] = weight;
        adjMatrix[end][start] = weight;
    }
//---------------------------------------------------------------------------------------------------------//
    // Вывод названия вершины //
    public void displayVertex(int v){
        System.out.println(vertexList[v].getLabel());
    }
//---------------------------------------------------------------------------------------------------------//
    // Добавление в очередь конечных вершин и цены перехода к ним.//
    public void putInPQ(int newVert, int newDist){

        //Существует-ли  другое ребро с той же конечной вершиной
        int queIndex = thePQ.findEdge(newVert);
        if (queIndex != -1){
            // Если есть получаем его
            Edge oldEdge = thePQ.peekN(queIndex);
            int oldDist = oldEdge.distance;

            // Если новое ребро короче
            if (oldDist > newDist){
                thePQ.removeN(queIndex);// Удалить старое ребро
                Edge theEdge  = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);// Добавить новое
            } // Иначе оставляем всё как есть.
        }
        // Если ребра с той же конечной вершиной не существует, добавляем в очередь новое ребро.
        else {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }
//---------------------------------------------------------------------------------------------------------//
    // Минимальное остовое дерево  для взвешанного ненаправленного графа //
    public void mstW() {

        // Начиная с ячейки 0 //
        this.currentVert = 0;

        // Пока все вершиниы не будут включены в дерево //
        while (nTree < nVerts - 1) {

            // Включаем в дерево текущую вершину //
            vertexList[currentVert].setIsInTree();
            nTree++;

            // Вставка в приоритетную очередь всех смежных вершин с текущей //
            for (int i = 0; i < nVerts; i++) {
                if (currentVert == i) continue;                   // Пропускаем текущую вершину.
                if (vertexList[i].isInTree()) continue; // Пропускаем если уже в дереве.
                int distance = adjMatrix[currentVert][i];
                if (distance == INFINITY) continue;               // Пропускаем если рёбер нет.
                putInPQ(i, distance);                              // Добавляем в приоритетную очередь.
            }

            //  Проверка очереди. Возможно есть изолированные вершины до которых нет возможности добраться. //
            if (thePQ.getCurSize() == 0) {
                System.out.println("Graph not connected");
                return;
            }

            // Удаление ребра с минимальным расстоянием из очереди //
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // Вывод ребра от начальной вершины до текущей //
            System.out.println(vertexList[sourceVert].getLabel());
            System.out.println(vertexList[currentVert].getLabel());
            System.out.println("");
        } // Конец wile();

    } // Конец mstW();
//---------------------------------------------------------------------------------------------------------//


} // Конец class Graph;
