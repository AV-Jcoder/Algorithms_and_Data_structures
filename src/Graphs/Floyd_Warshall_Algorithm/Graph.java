package Graphs.Floyd_Warshall_Algorithm;

/**
 * Класс реализует Граф.
 */
public class Graph {
//---------------------------------------------------------------------------------------
    private static final int maxVertsCount = 10; // Максимально возможное количество вершин.
    private Vertex[] vertsList;                  // Список вершин.
    private int[][] adjMatrix;                   // Матрица смежности.
    private int vertsCount; // Текущее количество вершин. Нужно для добавления вершин через метод addVertex().
    //-----------------------------------------------------------------------------------
    public Graph() {
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
    //Метод для добавления связей между вершинами - заполнение матрицы.
    public void addEdge(int start, int end){
        adjMatrix[start][end] = 1; //
    }
    //-----------------------------------------------------------------------------------
    // Трансзитивное замыкание исходного графа
    public void fwa(){

        OuterLoop:
        for (int row1 = 0; row1 < vertsCount; row1++) {
            MiddleLoop:
            for (int col = 0; col < vertsCount; col++) {
                if (adjMatrix[row1][col] == 1){
                    int z = row1;
                    InnerLoop:
                    for (int row2 = 0; row2 < vertsCount; row2++) {
                        if (adjMatrix[row2][z] == 1){
                            adjMatrix[row2][col] = 1;
                        }
                    }
                }
            }
        }
        System.out.println("Транзитивное замыкание графа выполнено.");
    }
    //-----------------------------------------------------------------------------------
    /**
     * Вывод на экран матрицы смежности
     */
    public void printAdjMatrix(){
        System.out.print("  ");
        for (int i = 0; i <vertsCount; i++) {
            System.out.printf("%s ",vertsList[i].getLabel());
        }
        for (int i = 0; i < vertsCount; i++) {
            System.out.println("");
            System.out.printf("%s ",vertsList[i].getLabel());
            for (int j = 0; j < vertsCount; j++) {
                System.out.printf("%d ", adjMatrix[i][j]);
            }
        }
        System.out.println("\n===========");
    }
    //-----------------------------------------------------------------------------------
}
