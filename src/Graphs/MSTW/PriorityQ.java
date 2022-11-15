package Graphs.MSTW;

/**
 * Приоритетная очередь.
 * Хранит рёбра.
 * Элементы распологаются в порядке убывания весов рёбер (distance), от
 *  0 до size-1.
 *
 */
public class PriorityQ {

    private Edge[] queEdges;
    private int curSize;

    public PriorityQ(int maxSize) {
        this.queEdges = new Edge[maxSize];
        this.curSize = 0;
    }

    /**
     * Метод для вставки ребра в приоритетную очередь
     * @param item
     */
    public void insert(Edge item){
        int j; // Указатель для вставки
        for (j=0; j < curSize; j++) // Поиск места для вставки
            if (item.distance >= queEdges[j].distance)
                break;
                                                                            //     [j]         [k]
        for (int k = curSize -1; k>=j ; k--) { // Перемещение элементов вправо [0] [1] [2] [3] [4] curSize:5
            queEdges[k+1] = queEdges[k];
        }
        queEdges[j] = item; // Вставка нового ребра в массив.
        curSize++;
    }

    /**
     * Извлечение наименьшего элемента из массива
     */
    public Edge removeMin(){
        return queEdges[--curSize];
    }

    /**
     * Удаление элемента из позиции N,
     * затем перемещение влево
     */
    public void removeN(int n){
        for (int i = n; i  < curSize-1; i++){
            queEdges[i] = queEdges[i+1];
        }
        curSize--;
    }

    /**
     * Чтение наименьшего элемента из массива.
     */
    public Edge peekMin(){
        return queEdges[curSize-1];
    }

    /**
     * Получение количества элементов
     */
    public int getCurSize(){
        return curSize;
    }

    /**
     * Проверка пустой очереди
     */
    public boolean isEmpty(){
        return curSize == 0;
    }

    /**
     * Чтение элемента с энной позиции
     */
    public Edge peekN(int n){
        return queEdges[n];
    }

    /**
     * Поиск ребра в очереди с нужной конечной вершиной.
     * return индекс в очереди.
     */
    public int findEdge(int findDestVertx){
        for (int i = 0; i < curSize; i++) {
            if(queEdges[i].destVert == findDestVertx){
                return i;
            }
        }
        return -1;
    }
}
