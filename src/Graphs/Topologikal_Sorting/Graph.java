package Graphs.Topologikal_Sorting;

/**
 * Класс реализует метод топологического упорядочивания в графе.
 * Граф должен быть ориентированным и ациклическим.
 *
 */
public class Graph {
    private final int MAX_VERTS_COUNT = 10; // Ограничение вершин.
    private Vertex[] vertexList;            // Список вершин.
    private char[] sortedList;            // Отсортированный список, хранит названия вершин.
    private int[][] adjMatrix;              // Матрица смежности.
    private int vertexCount;                // Текущее количество вершин. Для добавления вершин через метод add.

    //----------------------------------------------------------------------------------------------------------------------
    public Graph() {
        this.vertexList = new Vertex[MAX_VERTS_COUNT];
        this.sortedList = new char[MAX_VERTS_COUNT];
        this.vertexCount = 0;
        this.adjMatrix = new int[MAX_VERTS_COUNT][MAX_VERTS_COUNT];// Матрица по умолчанию заполнена нулями.
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
        adjMatrix[start][end] = 1; // В ориентированном графе связь в одну сторону.
    }
    //------------------------------------------------------------------------------------------------------------------
    private void displayVert(int vertex){
        System.out.printf("%s, ", vertexList[vertex].getLabel());
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
     * Топологическая сортировка графа.
     */
    public void topo(){

        int origVertCount = vertexCount; // Сохраняем количество вершин.

        while (vertexCount>0){ // Пока в графе есть вершины
            // Получение вершины без приемников или -1
            int curVert = noSuccessors();
            if(curVert == -1){
                System.out.println("Ошибка: в графе цикл");
                return;
            }
            // Если вершина найдена копируем её в конец отсортированного массива.
            sortedList[vertexCount-1] = vertexList[curVert].getLabel();

            // Затем удаляем из основного списка вершин.
            delVert(curVert);
        }
        // После удаления всех вершин сортировка закончена.
        // Печатаем все вершины из sortedArray.
        System.out.print("Топологическая сортировка окончена:");
        for (int i = 0; i < origVertCount; i++) {
            System.out.print(sortedList[i]+" ");
        }
        System.out.println("");
    }
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Метод удаляет вершину из списка вершин и ребро матрицы смежности.
     * @param delVert текущая вершшина.
     */
    private void delVert(int delVert) {

        // Удаление вершины из списка
        if (delVert != vertexCount-1){ // Если вершина крайняя по индексу в массиве, то просто уменьшаем vertexCount.
            for (int i = delVert; i < vertexCount-1; i++) {
                vertexList[i] = vertexList[i+1];
            }

        // Удаление строки из матрицы смежности
            for (int row = delVert; row < vertexCount-1; row++) {
                moveRowUp(row, vertexCount);
            }

        // Удаление столбца из матрицы смежности
            for (int col = delVert; col < vertexCount-1; col++){
                moveColLeft(col, vertexCount-1); // -1 строчка,т.к. мы уже укоротили массив.
            }

        }
        vertexCount--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++) {
            adjMatrix[row][col] = adjMatrix[row+1][col];
        }
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length ; row++) {
            adjMatrix[row][col] = adjMatrix[row][col+1];
        }

    }
    //------------------------------------------------------------------------------------------------------------------

    // Метод возвращает вершину не имеющую приемников
    // или -1 если таковой нет.
    private int noSuccessors() {

        boolean isEdge; // Ребро в матрице adjMatrix()

        for (int row = 0; row < vertexCount; row++) {
            isEdge = false;
            for (int col = 0; col < vertexCount; col++) {
                if (adjMatrix[row][col] > 0){
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge) return row;
        }
        return -1;
    }
    //------------------------------------------------------------------------------------------------------------------
}
//------------------------------------------------------------------------------------------------------------------
