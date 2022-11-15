package Sorts;

/**
 * Класс реализует сортировку кучей, in place.
 * Скорость алгоритма O(N*logN), очень близко по скорости к быстрой сортировке,
 * но всё равно проигрывает.
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] array = new int[100_000_000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random()*10000);
        }

        long startTime = System.currentTimeMillis();
        heapSortInPlace(array);
        long endTime = System.currentTimeMillis();
        System.out.printf("This took :%d ms",endTime-startTime);
    }

//******************************************************************************
    // Метод сортировки in-place
    // Тут мы не создаём объкт куча, а сортируем исходный массив
    static void heapSortInPlace(int[] array){

        int curSize = array.length;
        // сперва строим в самом массиве праильную пирамиду, ускоренно.
        for (int index = array.length/2-1; index >=0 ; index--) {
            trickleDownForIntArray(array,index,curSize);
        }

        // Теперь копируем корень в ТМП, делаем ремув, копируем тмп в освободившюуся ячейку.
        for (int i = 0; i < array.length; i++) {
            int tmp = array[0];
            removeForIntArray(array,--curSize);
            array[curSize] = tmp;
        }
    }
    //***************************************
    // Просеивание вниз
    private static void trickleDownForIntArray(int[] array,int index, int curSize){
        int top  = array[index];
        int largerChild;

        while(index<curSize/2){ // Пока у узла есть хотябы один потомок
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;

            // Определение обльшего потомка
            if (rightChild<curSize // Правый потомок существует
                    && array[leftChild] < array[rightChild]){
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // Ключ верхнего узела больше?
            if (top >= array[largerChild]){
                break;
            }

            // Ключ верхнего узла  меньше, потомок сдвигается вверх
            array[index] = array[largerChild];
            index = largerChild;
        }
        array[index] = top;
    }
    //**********************************************
    // Удаление из исходного массива
    private static void removeForIntArray(int[] array, int curSize){
        array[0] = array[curSize];
        trickleDownForIntArray(array,0,curSize);
    }
//**************************************************************************************

}
