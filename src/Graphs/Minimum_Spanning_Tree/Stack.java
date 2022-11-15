package Graphs.Minimum_Spanning_Tree;

/**
 * Стэк. Li-Fo.
 * Сохраняет индексы вершин
 * в массиве array.
 */
public class Stack {

    int top = -1;
    int[] array;

    Stack(int size){
        this.array = new int[size];
    }

    public void push(int vertex){
        array[++top] = vertex;
    }

    public int pick(){
        return array[top];
    }

    public int pop(){
        return array[top--];
    }

    public boolean isEmpty(){
        return top == -1;
    }
}
