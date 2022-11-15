package Sorts;

/**
 * Сортировка распределённым подсчётом
 * Скорость O(n);
 * Доп память в размере n+d; d-диапазон ключей
 */
public class DistributionSort {
//*******************************************************************************************
    public static void main(String[] args) {

        int[] array = new int[100_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*10000);
        }

        long startTime = System.currentTimeMillis();
        array = distributionSort(array);
        long endTime = System.currentTimeMillis();
        System.out.printf("This took %d ms.\n",(endTime-startTime));


    }
//*******************************************************************************************
    // Сортировка распределённым подсчётом
    public static int[] distributionSort(int[] array) {
        int[] minMax = getMinMax(array);
        int min = minMax[0];
        int max = minMax[1];
        int[] support = new int[max+1-min];

        // Заполнение вспомогательной последовательности
        for (int i : array) {
            support[i-min]++;
        }

//        // Обратная аккумуляция
//        int size = array.length;
//        for (int i = support.length-1; i >=0 ; i--) {
//            size = size-support[i];
//            support[i] = size;
//        }

        // Прямая аккумуляция
        int index = -1;
        for (int i = 0; i < support.length; i++) {
            index = index+support[i];
            support[i]=index;
        }

        // Создание и заполнение отсортированного массива
        int[] sort = new int[array.length];
        for (int i = 0; i < array.length; i++){
            sort[support[array[i]-min]] = array[i];
            support[array[i]-min]--;// Уменьшаем при прямой, при обратной увеличиваем.
        }
        return sort;
    }
//*******************************************************************************************
    // Получение максимума и минимума
    private static int[] getMinMax(int[] array){
        int min = array[0];
        int max = array[0];

        for (int element : array) {
            if (element < min) min = element;
            if (element > max) max = element;
        }
        return new int[]{min,max};
    }
//*******************************************************************************************
}
