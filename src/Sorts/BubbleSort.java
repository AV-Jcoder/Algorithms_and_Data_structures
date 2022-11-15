package Sorts;

/**
 * Алгоритм сортировки Пузырьком.
 * Bubble sort.
 * O(n2/2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {5,2,1,6,4,3};
        bubbleSort(array);
        for (int i: array) {
            System.out.printf("[%d] ", i);
        }
    }
    static void bubbleSort(int[] array){// [1][5][4][3][9]
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i; j++) {// Укоротить условие на 1 индекс, т.к
                if (array[j]>array[j+1]) {             // j+1 выходит за границу массива
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }

        }

    }
}
