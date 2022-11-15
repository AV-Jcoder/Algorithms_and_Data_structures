package Sorts;
/**
 * Сортировка с двумя попутными указателями.
 * С определением местоположения пивота.
 * Относится в скоростным сортировкам.
 * Задействует принципы разделяй и влавствуй.
 *  + Асимптотика O(N*logN).
 *  W(N*logN) - вероятностный случай для лучшего барьерного элемента.
 *  + in place.
 *  - UnStable.
 */
public class QuickSort_2nd {
    static long swapCount = 0;
    static long deepStack = 0;
    public static void main(String[] args) {
        int[] array = new int[17];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*100);
        }
        try {
            quickSortSecond(array);
        } catch (Throwable e){
            e.printStackTrace();
        }
        for (int i : array) {
            System.out.printf("[%d] ", i);
        }
        System.out.printf("\nSwap count : %d." +
                          "\nDeep stack : %d.", swapCount, deepStack);
    }

    private static void quickSortSecond(int[] array) {
        int firstIndex = 0;
        int lastIndex = array.length-1;
        quickSortSecondHelper(array, firstIndex, lastIndex);
    }

    private static void quickSortSecondHelper(int[] array, int firstIndex, int lastIndex) {
        deepStack++;
        if (firstIndex >= lastIndex) return;
        int pivotPosition = findPivotPosition(array,firstIndex,lastIndex);
        quickSortSecondHelper(array, firstIndex, pivotPosition-1);
        quickSortSecondHelper(array, pivotPosition+1,lastIndex);
    }

    private static int findPivotPosition(int[] array, int firstIndex, int lastIndex) {

        int pivot = array[lastIndex];    // Пивот берется из самого правого значения.
        int guessPiIndex = firstIndex-1; // Предпологаемый, будущий индекс Пивота.
        int leftHand = firstIndex;       // Указатель для прохода по массиву.

        while (leftHand < lastIndex){ // Сортировка значений
            if (array[leftHand] < pivot){
                guessPiIndex++;
                swap(array, guessPiIndex, leftHand);
            }
            leftHand++;
        }

        guessPiIndex++;// Сдвигаем индекс с последнего меньшего значения, или с -1 индекса, если все значения были больше пивота.
        swap(array,guessPiIndex,lastIndex);// Индекс для пивота найден, ставим на место.

        return guessPiIndex;
    }

    private static void swap(int[] array, int A, int B) {
        swapCount++;
        int temp = array[A];
        array[A] = array [B];
        array[B] = temp;
    }
}
