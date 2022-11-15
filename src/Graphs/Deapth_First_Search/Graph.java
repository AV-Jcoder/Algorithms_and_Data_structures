package Graphs.Deapth_First_Search;

/**
 * Класс реализует метод обхода графов вглубину.
 */
public class Graph {
    private final int MAX_VERTS_COUNT = 10; // Ограничение вершин
    private Vertex[] vertexList;            // Список вершин
    private int[][] adjMatrix;              // Матрица смежности
    private int vertexCount;                // Текущее количество вершин. Для добавления вершин через метод add.
    private Stack stack;                    // Стэк, для обхода в глубину.
    //----------------------------------------------------------------------------------------------------------------------
    public Graph() {
        this.vertexList = new Vertex[MAX_VERTS_COUNT];
        this.vertexCount = 0;
        this.stack = new Stack(MAX_VERTS_COUNT);
        this.adjMatrix = new int[MAX_VERTS_COUNT][MAX_VERTS_COUNT];// Матрица по умолчанию заполнена нулями
        for (int i = 0; i < MAX_VERTS_COUNT; i++) {
            for (int j = 0; j < MAX_VERTS_COUNT; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Добавление вершин в граф.
     * @param label имя Вершины, например 'A'
     */
    public void addVertex(char label){
        if (vertexCount==MAX_VERTS_COUNT){
            System.out.println("Достигнуто максимальное число вершин.");
            return;
        }
        vertexList[vertexCount++] = new Vertex(label);
    }
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Добавление ребра между вершинами
     * @param start начальная вершина
     * @param end конечная вершина
     */
    public void addEdge(int start, int end){
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }
    //------------------------------------------------------------------------------------------------------------------
    private void displayVert(int index){
        System.out.println(vertexList[index].getLabel());
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Поиск смежных вершин по матрице смежности.
     * @param topOnStack индекс вершины, берется с верхушки стэка.
     * @return -1 если вершин больше нет.
     * @return i индекс смежной вершины.
     */
    private int getAdjUnvVertex(int topOnStack){
        for (int i = 0; i < vertexCount; i++) {
            if (adjMatrix[topOnStack][i] > 0 && !(vertexList[i].isItVisited())){ // Если есть смежная вершина &
                return i;                                                       //  & не посещёная.
            }
        }
        return -1;
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Поиск в глубину
     */
    public void dfs(){
        stack.push(0);                // Берем вершину и заносим в стэк.
        vertexList[0].setVisited();         // Отмечаем посещёной.
        this.displayVert(0);          // Печатаем.

        while (!stack.isEmpty()){           // Пока стэк не опустеет, делаем для остальных вершин.

            int curVert = stack.pick();     // Текущая вершина, считана со стэка.
            int nextVert = getAdjUnvVertex(curVert); // Ищем смежную вершину для текущей.

            if (nextVert == -1){            // Если смежная вершина не найдена,
                stack.pop();                //  извлекаем текущую вершину со стэка.
            } else {                        // Если смежная вершина найдена, то
                stack.push(nextVert);       // заносим в стэк,
                vertexList[nextVert].setVisited(); // отмечаем посещёной,
                this.displayVert(nextVert);     // печатаем.
            }
        }
        System.out.println("Все достижимые вершины пройдены.");
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
