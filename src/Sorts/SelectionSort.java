package Sorts;

/**
 * Алгоритм сортировки Выбором.
 * Selection sort.
 * O(n2/2).
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {5,2,1,6,4,3};
        selectionSort(array);
        for (int i: array) {
            System.out.printf("[%d] ", i);
        }
    }
    static void selectionSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length ; j++) {
                if (array[i]>array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
