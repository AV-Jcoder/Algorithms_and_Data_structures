package Data_Structures.Heaps;

public class HeapTreeApp {
    public static void main(String[] args) {

        // Проверка работы кучи
//        HeapTree heapTree = new HeapTree();
//        heapTree.insert(5);
//        heapTree.insert(2);
//        heapTree.insert(1);
//        heapTree.insert(8);
//        heapTree.insert(9);
//        heapTree.display();
//        heapTree.delete();
//        heapTree.display();

        // Сортировка кучей
        int[] array = new int[1_000_000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random()*2_000_000);
//            System.out.printf("[%d] ",array[i]);
        }
        System.out.println(" ");

        long startTime = System.currentTimeMillis();
        Heap.heapSortInPlace(array);
        long endTime = System.currentTimeMillis();
        System.out.printf("This took :%d ms",endTime-startTime);

//        for (int i = 0; i < array.length; i++) {
//            System.out.printf("[%d] ",array[i]);
//        }

//        // Сортирование
//        for (int i = 0; i < array.length; i++) {
//            heapTree.insert(array[i]);
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = heapTree.delete();
//            System.out.printf("[%d] ",array[i]);
//        }
//        System.out.println(" ");
    }
}
