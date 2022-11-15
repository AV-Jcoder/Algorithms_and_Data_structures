package Sorts;

/**
 * Сортировка вставками.
 * Insertion sort.
 * - O(n2)
 * + in place.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {6,2,1,5,4,3};
        insertionSort(array);
        for (int i : array) {
            System.out.printf("[%d] ", i);
        }
    }

    static void insertionSort(int[] array){

        int i = 1;

        while(i < array.length){
            int current = array[i];
            int j = i;
            while (j > 0 && current < array[j-1] ){
                array[j] = array[j-1];
                j--;
            }
            array[j] = current;
            i++;
        }
    }

}
