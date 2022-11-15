package Sorts;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = new int[1_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*1_000_000);
        }
        long startTime = System.currentTimeMillis();
        shellSort(array);
        long endTime = System.currentTimeMillis();
        System.out.printf("This took %d ms.\n",(endTime-startTime));

//        for (int i :
//                array) {
//            System.out.printf("[%d] ",i);
//        }
    }

    private static void shellSort(int[] array) {
        int step = array.length/2;
        while (step>0){
            for (int i = step; i < array.length; i++) {
                for (int j = i; j>=step && array[j]<array[j-step]; j=j-step) {
                    int tmp = array[j];
                    array[j] = array[j-step];
                    array[j-step] = tmp;
                }
            }
            step=step/2;
        }
    }
}
