package Sorts;

/**
 * Сортировка слиянием.
 * Относится к числу скоростных сортировок.
 * Задействуются принципы - разделяй и влавствуй.
 * Разделение происходдит на прямом ходу рекурсии.
 * Слияние происходит на обратном ходу рекурсии.
 * - Задействует дополнительное место в памяти.
 * + Сложность O(N*logN).
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[100_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*10000);
        }

        long startTime = System.currentTimeMillis();
        mergeSort(array);
        long endTime = System.currentTimeMillis();

        System.out.printf("This took %d ms.\n",(endTime-startTime));

//        for (int i : array) {
//            System.out.printf("[%d] ", i);
//        }
    }

    static void mergeSort(int[] array){

        int n = array.length; // 7
        // basic case
        if(n <= 1) return;

        // division array
        int[] left = new int [n/2]; // 3
        int[] right = new int [n-n/2]; // 4 //Если длинна нечётная

        // initial left & right
        for (int i = 0; i < n/2; i++) left[i] = array[i];
        for (int i = n/2; i < n; i++) right[i-n/2] = array[i];

        // recursive call
        mergeSort(left);
        mergeSort(right);

        // merged
        mergeSortedArrays(left, right, array);
    }

    /**
     * Вспомогательный метод
     * для слияние левой и правой
     * частей массива в основной массив.
     * @param A левая часть массива.
     * @param B правая часть массива.
     * @param C основной массив.
     */
    static void mergeSortedArrays(int[]A, int[]B, int[]C){

        // Указатели для массивов.
        int i=0, j=0, k=0;

        // Сравниваем половинки A и B, складываем по очереди в С.
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j]){
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }
        // Если в С остались не скопированные значения из А или В, то
        // Запустится один из циклов и докопирует в С значения.
        // Это происходит если в одном из массивов остаются
        // значения из верхнего рядад например [1][6][7][8] I [2][3][4][5].
        // [6][7][8] придётся докопировать.
        while (i < A.length){
            C[k++] = A[i++];
        }
        while (j < B.length) {
            C[k++] = B[j++];
        }
    }



}
