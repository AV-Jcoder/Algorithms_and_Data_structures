package Sorts;

/**
 * Сортировка с двумя противоположными указателями.
 * Без определения местоположения пивота.
 * Относится в скоростным сортировкам.
 * Задействует принципы разделяй и влавствуй.
 *  + Асимптотика O(N*logN).
 *  W(N*logN) - вероятностный случай для лучшего барьерного элемента.
 *  + in place.
 *  - UnStable.
 */
public class QuickSort {
    static long swapCount = 0;
    static long deepStack = 0;
    public static void main(String[] args) {
        int[] array = {3,2};  //new int[500_000_000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (int) (Math.random()*2_000_000);
//        }
        try {
            quickSort(array);
        }catch (Throwable e) {
            System.out.println(e);
        }
        for (int i : array) {
            System.out.printf("[%d] ", i);
        }
        System.out.printf("\nSwap count : %d.\nDeep stack : %d.", swapCount, deepStack);
    }

    static void quickSort(int[] array){
        // left & right - указатели которые движутся друг к другу
        // указывают от и до какого индекса необходимо сортировать массив
        int firstIndex = 0;
        int lastIndex = array.length-1;
        quickSortHelper(array, firstIndex, lastIndex);
    }

    private static void quickSortHelper(int[] array, int firstIndex, int lastIndex) {
        deepStack++;
        if (firstIndex >= lastIndex) return;
        int left = firstIndex;
        int right = lastIndex;

        // Выбираем опорный элемент
        int pivot = array[lastIndex];

        while(left < right)  {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        // рекурсивный случай
        quickSortHelper(array, firstIndex, right);
        quickSortHelper(array, left, lastIndex);
    }

    private static void swap(int[] array, int A, int B) {
        swapCount++;
        int temp = array[A];
        array[A] = array [B];
        array[B] = temp;
    }


}




