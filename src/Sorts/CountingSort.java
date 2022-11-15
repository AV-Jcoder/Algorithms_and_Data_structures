package Sorts;

/**
 * Сортировка подсчётом.
 * Эффективна для малых диапазонов чисел например для чисел 0-1000, размером списка 1_000_000_000 будет
 * выдавать отличную скорость.
 * Наихудший случай O(n).
 * Подходит только для целых чисел в небольшом диапазоне.
 * Требует дополнительной памяти в размере диапазона чисел -
 *  - (для огромных чисел требует огромного кол-ва памяти).
 */
public class CountingSort {

    public static void main(String[] args) {

        int[] array = new int[100_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*10000);
        }

        long startTime = System.currentTimeMillis();
        countingSort(array);
        long endTime = System.currentTimeMillis();

        System.out.printf("This took %d ms.\n",(endTime-startTime));

//        for (int i : array) {
//            System.out.printf("[%d] ",i);
//        }

    }

    public static void countingSort(int[] array){
        int[] minMax = minMax(array);
        int min = minMax[0];
        int max = minMax[1];
        int[] support = new int[max-min+1];

        for (int element:array) {
            int index = element-min;
            support[index]++;
        }

        int index = 0;
        for (int i = 0; i < support.length; i++) {
            int j=0;
            while (j<support[i]){
                array[index]=i+min;
                index++;
                j++;
            }
        }
    }

    private static int[] minMax(int[] array){
        int min = array[0];
        int max = array[0];

        for (int element:array) {
            if (element < min) {
                min=element;
            }
            if (element>max){
                max = element;
            }
        }
        return new int[]{min,max};
    }
}
