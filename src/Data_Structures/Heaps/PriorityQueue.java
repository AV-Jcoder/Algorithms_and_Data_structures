package Data_Structures.Heaps;

/**
 * Класс реализует приоритетную очередь на базе пирамиды,
 * где операции вставки и удаления выполняются
 * за O(log*n) время.
 * По эта абстрактная структура является примитивной обёрткой для класса кучи.
 */
class PriorityQueue {

    HeapTree heap;

    void insert(int key){
        heap.insert(key);
    }

    void delete (){
        heap.delete();
    }

}
