package Graphs.Minimum_Spanning_Tree;

public class Queue {

    private int[] queArray;
    private int head;
    private int tail;
    // -------------------------------------------------------------
    public Queue(int size) {
        queArray = new int[size];
        head = -1;
        tail = -1;
    }
    // -------------------------------------------------------------
    public void insert(int vertex) { // Вставка элемента в конец очереди.
        queArray[++tail] = vertex;
    }
    // -------------------------------------------------------------
    public int remove(){            // Извлечение элемента из начала очереди.
        return queArray[++head];
    }
    // -------------------------------------------------------------
    public boolean isEmpty(){ // true, если очередь пуста
        return (tail == head);
    }
// -------------------------------------------------------------
} // Конец класса Queue
//////////////////////////////




