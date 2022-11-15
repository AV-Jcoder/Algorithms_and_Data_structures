package Graphs.Breads_First_Search;

/**
 * Класс реализует метод обхода графов вширину.
 */
public class Graph {
//---------------------------------------------------------------------------------------
    private static final int maxVertsCount = 10; // Максимально возможное количество вершин.
    private Vertex[] vertsList;                  // Список вершин.
    private int[][] adjMatrix;                   // Матрица смежности.
    private int vertsCount; // Текущее количество вершин. Нужно для добавления вершин через метод addVertex().
    private Queue queue;      // Очередь
    //-----------------------------------------------------------------------------------
    public Graph() {
        this.queue = new Queue(maxVertsCount);
        this.vertsList = new Vertex[maxVertsCount];
        this.vertsCount = 0;
        this.adjMatrix = new int[maxVertsCount][maxVertsCount];
        for (int i = 0; i < maxVertsCount; i++) {
            for (int j = 0; j < maxVertsCount; j++) {
                this.adjMatrix[i][j] = 0;   // Заполняем матрицу связей нулевыми значениями по умолчанию.
            }
        }
    }
    //-----------------------------------------------------------------------------------
    // Метод для добавления вершины в список.
    public void addVertex(char name){
        if (vertsCount == maxVertsCount){
            System.out.println("Превышение количества вершин.");
            return;
        }
        vertsList[vertsCount++] = new Vertex(name);
    }
    //-----------------------------------------------------------------------------------
    private void displayVert(int index){
        System.out.println(vertsList[index]);
    }
    //-----------------------------------------------------------------------------------
    //Метод для добавления связей между вершинами - заполнение матрицы.
    public void addEdge(int start, int end){
        adjMatrix[start][end] = 1; // Делаем двустороннюю связь, т.е.
        adjMatrix[end][start] = 1; // неориентированный граф.
    }
    //-----------------------------------------------------------------------------------
    // Обход графа в ширину
    public void bfs(){
        queue.insert(0);                         // Заносим в очередь вершину.
        vertsList[0].setWasVisited();                  // Отмечаем посещёной.
        System.out.println(vertsList[0]);              // Печатаем название.


        while (!queue.isEmpty()) {                 // Повторяем операции пока очередь не пуста.

            int curVert = queue.remove(); // Извлекаем вершину из очереди и делаем текущей.
            int nextVert;
            // Ищем следущую вершину, которую еще не посещали
            while ((nextVert=getAdjVertex(curVert)) != -1){
                queue.insert(nextVert);       // Заносим в очередь.
                vertsList[nextVert].setWasVisited(); // Отмечаем.
                displayVert(nextVert); // Печатаем название.
            }
        }
        // Очередь пуста, обход закончен.
        System.out.println("Все доступные вершины пройдены.");
    }
    //-----------------------------------------------------------------------------------
    /**
     * Метод для обхода по матрице смежности.
     * Возвращает первый попавшийся индекс
     * вершины в массиве или -1 если
     * вершина не найдена или уже
     * отмечена.
     */
    private int getAdjVertex(int curVert){ // Параметр принимает индекс текущей вершины.
        for (int i = 0; i < vertsCount; i++) { // Ищем связи по матрице.
            if(adjMatrix[curVert][i] == 1 && !vertsList[i].isItVisited()){ // Если связь есть и вершина не отмечена, то
                return i;                       //  Возвращаем её индекс.
            }
        }
        return -1; // Вершина не найдена.
    }
//-----------------------------------------------------------------------------------
}
