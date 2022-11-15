package Data_Structures.Heaps;

/**
 * Класс реализует структуру данных куча на базе массива.
 * Массив подменяет собой двоичное дерево в котором индексы потомков и родителей
 * рассичтываются по следующим формулам:
 * leftChildIndex = 2*index+1;
 * rightChildIndex = 2*index+2;
 * parentIndex = (index-1)/2;
 */
public class Heap {

    private Node[] array;
    private final int MAX_SIZE;
    private int curSize;

    Heap (int size){
        this.MAX_SIZE = size;
        this.array = new Node[MAX_SIZE];
        this.curSize = 0;
    }
//**************************************************************************************
    // Вставка ноды
    boolean insert(int key){
        if (curSize==MAX_SIZE){
            return false;
        } else {
            Node newNode = new Node(key);
            array[curSize] = newNode;
            trickUp(curSize++);
        }
        return true;
    }

    // Перемещение ноды вверх
    private void trickUp(int index) {
        Node temp = array[index];
        int parent = (index-1)/2;

        while (index>0 && array[index].key>array[parent].key){
            array[index] = array[parent];
            index = parent;
            parent = (index-1)/2;
        }
        array[index] = temp;
    }
//**************************************************************************************
    // Удаление максимального элемента
    Node delete(){
        Node tmp = array[0];
        array[0]=array[--curSize];
        trickleDown(0);
        return tmp;
    }
    // Метод "просеивания" вниз
    private void trickleDown(int index) {
        Node top  = array[index];
        int largerChild;

        while(index<curSize/2){ // Пока у узла есть хотябы один потомок
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;

            // Определение обльшего потомка
            if (rightChild<curSize // Правый потомок существует
                    && array[leftChild].key < array[rightChild].key){
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // Ключ верхнего узела больше?
            if (top.key >= array[largerChild].key){
                break;
            }

            // Ключ верхнего узла  меньше, потомок сдвигается вверх
            array[index] = array[largerChild];
            index = largerChild;
        }
        array[index] = top;
    }
//**************************************************************************************
    // Метод сортировки данных путём случайного добавления данных в дерево
    // и дальнейшим просеиванием их
    void sort(int[] array){
        Heap heap = new Heap(array.length);
        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.delete().key;
        }
    }
//***********************************************************
    // Вариант метода ускоренной сортировки.
    // Метод просеивания вызывается только для N/2 значений.
    void sort2(int[] array){
        // Заполняем пирамиду рандомными числами
        Heap heap2 = new Heap(array.length);
        for (int i = 0; i < array.length; i++) {
            heap2.array[i] = new Node(array[i]);
            heap2.curSize++;
        }
        // Строим правильную пирамиду
        for (int i = array.length/2-1; i >= 0; i--) {
            heap2.trickleDown(i);
        }
        // Копируем значения обратно в массив
        for (int i = 0; i < array.length; i++) {
            array[i] = heap2.delete().key;
        }
    }
//***********************************************************
    // Рекурсивное решение для построения правильной пирамиды
    void sort2rec(int[] array){
        Heap heap2 = new Heap(array.length);
        for (int i = 0; i < array.length; i++) {
            heap2.array[i] = new Node(array[i]);
            heap2.curSize++;
        }
        // хипифай сортирует дерево рекурсивно
        heapify(0, heap2);

        // далее просто копируем обратно данные в массив
        for (int i = 0; i < array.length; i++) {
            array[i] = heap2.delete().key;
        }
    }
    private void heapify(int index, Heap heap){
        // Рекурсивные вызовы для левого поддерева и правого поддерева
        // Начиная с левого потомка, потом к правому потомку.
        // После возврата управления вызов trickleDown для текущего индекса
        // Базовый случай - индекс больше чем N/2-1;
        if (index>heap.array.length/2-1){
            return;
        } else {
            heapify(2*index+1,heap);//Левый потомок
            heapify(2*index+2,heap);//Правый потомок
            heap.trickleDown(index);
        }
    }
//*****************************************************************************************************
    // Метод сортировки in-place
    // Тут мы не создаём объкт куча и сортируем исходный массив
    static void heapSortInPlace(int[] array){

        int curSize = array.length;
        // сперва строим в самом массиве праильную пирамиду, ускоренно.
        for (int index = array.length/2-1; index >=0 ; index--) {
            trickleDownForIntArray(array,index,curSize);
        }

        // Теперь копируем корень в ТМП, делаем ремув, копируем тмп в освободившюуся ячейку.
        for (int i = 0; i < array.length; i++) {
            int tmp = array[0];
            removeForIntArray(array,--curSize);
            array[curSize] = tmp;
        }
    }
    //***************************************
    // Просеивание вниз
    private static void trickleDownForIntArray(int[] array,int index, int curSize){
        int top  = array[index];
        int largerChild;

        while(index<curSize/2){ // Пока у узла есть хотябы один потомок
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;

            // Определение обльшего потомка
            if (rightChild<curSize // Правый потомок существует
                    && array[leftChild] < array[rightChild]){
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // Ключ верхнего узела больше?
            if (top >= array[largerChild]){
                break;
            }

            // Ключ верхнего узла  меньше, потомок сдвигается вверх
            array[index] = array[largerChild];
            index = largerChild;
        }
        array[index] = top;
    }
    //**********************************************
    // Удаление из исходного массива
    private static void removeForIntArray(int[] array, int curSize){
        array[0] = array[curSize];
        trickleDownForIntArray(array,0,curSize);
    }
//**************************************************************************************

    private static class Node{
        int key;
        private Node(int key){
            this.key = key;
        }
    }
}
