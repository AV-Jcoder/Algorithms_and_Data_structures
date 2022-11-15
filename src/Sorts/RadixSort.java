package Sorts;

public class RadixSort {
//*******************************************************************************************
    public static void main(String[] args) {

        int[] array = new int[100_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*10000);
        }

//        for (int i : array) {
//            System.out.printf("[%d] ",i);
//        }

        long startTime = System.currentTimeMillis();
        array = radixSort(array);
        long endTime = System.currentTimeMillis();
        System.out.printf("This took %d ms.\n",(endTime-startTime));


//        for (int i : array) {
//            System.out.printf("[%d] ",i);
//        }


    }
//*******************************************************************************************
    public static int[] radixSort(int[] array){
        int maxDigits = getMaxDigits(array);
        int digit = 1;
        int divider = 1;
        while (digit<=maxDigits){
            array = distributionSort(array,divider);
            digit++;
            divider*=10;
        }
        return array;
    }
//*******************************************************************************************
    private static int[] distributionSort(int[] array, int divider){
        int[] minMax = getMinMax(array,divider);
        int min = minMax[0];
        int max = minMax[1];
        int[] support = new int[max-min+1];

        for (int i : array) {
            support[getKey(i,divider)-min]++;
        }

        int index  = -1;
        for (int i = 0; i < support.length; i++) {
            index = index+support[i];
            support[i] = index;
        }

        int[] sort = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            sort[support[getKey(array[i],divider)-min]] = array[i];
            support[getKey(array[i],divider)-min]--;
        }
        return sort;
    }
//*******************************************************************************************
    private static int[] getMinMax(int[] array, int divider) {
        int min = getKey(array[0],divider);
        int max = min;
        for (int num : array) {
            int tmp = getKey(num,divider);
            if (tmp<min)min = tmp;
            if (tmp>max)max = tmp;
        }
        return new int[]{min,max};
    }
//*******************************************************************************************
    private static int getMaxDigits(int[] array) {
        int maxDigit = getDigit(array[0]);
        for (int element : array) {
            int elementDigit = getDigit(element);
            if (maxDigit < elementDigit) {
                maxDigit = elementDigit;
            }
        }
        return maxDigit;
    }
//*******************************************************************************************
    private static int getDigit(int num){
        int digit = 1;
        int divider = 10;
            while (num>=divider){
                digit++;
                divider*=10;
            }
        return digit;
    }
//*******************************************************************************************
    private static int getKey(int num, int divider){
        return num%(divider*10)/divider;
    }
//*******************************************************************************************
}
