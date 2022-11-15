package Data_Structures.Arrays;

/**
 * Класс релизует упорядоченный массив,
 * в котором скорость операций занимает:
 * Поиск - О(LogN) Binary Search;
 * Вставка - О(N) в худшем случае в начало отсортированного массива, т.к. потребуется сдвиг всех ячеек;
 * Вставка - О(N/2) в среднием случае, со сдвигом половины массива на 1 ячейку к концу.
 * Удаление - O(N) в худшем случае, из начала отсортированного массива, со сдвигом всех ячеек к началу.
 * Удаление - О(N/2) в среднием случае, со сдвигом половины массива на 1 ячейку к началу,
 * Удаление со сдвигом необходимо для заполнения "пустоты", чтобы не ломать отношение порядка
 * для алгоритма поиска. На самом деле для примитивов пустоты  не возникает(null - только для объектов).
 * Мы просто перезаписываем значения удаляемой ячейки значением из следующей ячейки.
 */

public class OrderedArray implements Collection {
//******************************************************************************************************
    private int[] array;
    private int nElms;
    public final int CAPACITY;
//******************************************************************************************************
    OrderedArray(int capacity) {
        this.CAPACITY = capacity;
        this.array = new int[CAPACITY];
        this.nElms = 0;
    }
//******************************************************************************************************
    // Размер массива
    public int size(){
        return this.nElms;
    }
//******************************************************************************************************
    // Поиск, возвращает индекс в массиве или -1, если не найдено.
    public int find(int searchKey){
        int min = 0;
        int max = nElms-1;
        while(min<=max){
            int mid = (min+max)/2;
            if (searchKey == array[mid]){
                return mid;
            } else if (searchKey > array[mid]){
                min = mid+1;
            } else if (searchKey < array[mid]){
                max = mid-1;
            }
        }
        return -1;
    }
//******************************************************************************************************
    // Вставка элемента в массив через линейный поиск
    public void insert(int data) {
        int index;
        // поиск индекса для вставки
        for (index = 0; index < nElms; index++) {
            if (array[index] > data) {
                break;
            }
        }
        // сдвиг к концу на 1 элемент
        for (int j = nElms; j > index; j--) {
            array[j] = array[j - 1];
        }
        array[index] = data;
        nElms++;
    }
//******************************************************************************************************
    // Вставка через бинарный поиск позиции
    public void insertBinary(int data) {
        // Проверка на заполненость
        if (nElms == CAPACITY){
            System.out.println("Память заполнена");
            return;
        }
        // Проверка крайних случаев
        if(data>array[nElms-1]){
            array[nElms] = data;
            nElms++;
            return;
        }
        // Проверка крайних случаев
        if (data < array[0]){
            shiftToEnd(0);
            array[0] = data;
            nElms++;
            return;
        }
        // Бинарный поиск
        int insertIndex = searchInsertPosition(data);
        shiftToEnd(insertIndex);
        array[insertIndex] = data;
    }
//******************************************************************************************************
    // Метод определяет индекс для вставки в массив с помощью бинарного поиска.
    public int searchInsertPosition(int target) {
        int max= array.length-1;
        int min=0;
        int mid=-1;
        while (min<=max){
            mid = (min+max)/2;
            if (array[mid]==target){
                return mid;
            } else if (array[mid]<target){
                min = mid+1;
            } else {
                max = mid-1;
            }
        }
        if (array[mid]<target) mid++;
        return mid;
    }
//******************************************************************************************************
    // Сдвиг для вставки к концу массива от указанного индекса.
    private void shiftToEnd(int fromIndex){
        for (int j = nElms; j > fromIndex; j--) {
            array[j] = array[j - 1];
        }
    }
//******************************************************************************************************
    // Удаление из массива
    public boolean delete(int data){

        int index = find(data);

        if (index >= 0 && index < nElms) {
            // Если элемент найден то "удаляем элемент", сдвигаем массив к началу.
            for (int i = index; i < nElms-1; i ++){
                array[i] = array[i+1];
            }
            nElms--;
            return true;
        }
        else {
            return false;
        }
    }
//******************************************************************************************************
    // Сдвигаем массив к началу(при удалении)
    private void shiftToStart(int fromIndex){
        for (int i = fromIndex; i < nElms-1; i++) {
            array[i] = array[i+1];
        }
    }
//******************************************************************************************************
    // Дисплэй
    public void display(){
        for (int i = 0; i < nElms; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(" ");
    }
//******************************************************************************************************
    // Удаление дубликатов
    public void deleteDuplicate(){
        for (int i = 0; i < nElms; i++){
            for (int j = i+1; j < nElms; j++) {
                if (array[i] == array[j]){
                // Сдвиг массива к началу.
                    for (int k = j; k < nElms-1; k ++){
                        array[k] = array[k+1];
                    }
                    nElms--;
                    j--;
                }
            }
        }
    }
//*****************************************Шаблон Итератор**********************************************
    // Создание объекта итератор
    @Override
    public Iterator getIterator() {
        return new ListIterator();
    }
//*****************************************Шаблон Итератор**********************************************
    // Класс реализует интерфейс интератор
    private class ListIterator implements Iterator {

        private int currentIndex=0;

        @Override
        public Object next() {
            return array[currentIndex++];
        }

        @Override
        public boolean hasNext() {
            if (currentIndex<nElms){
                return true;
            }
            return false;
        }

        @Override
        public void set(int data) {
            array[currentIndex] = data; // Ломает отношение порядка
        }

        @Override
        public boolean delete() {
            return OrderedArray.this.delete(currentIndex);
        }
    }
}