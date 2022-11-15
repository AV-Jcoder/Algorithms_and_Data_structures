package Data_Structures.Lists;

/**
 * Повторение матреиала. Двусвязный упорядоченный список.
 * Вставка в начало и конец невозможна.
 * Вставка возможна только по отношению порядка, перебором всего списка элементов. В результате
 * вставки меняются четыре ссылки: (<--prv Insertion nxt-->)2-у текущего вставляемого элемента,
 * 2-у соседних элементов. (Prev--> Insertion <--Next).
 * @param <T>
 */

public class DoublyLinkedListVer2<T extends Comparable<? super T>> {

    private Box<T> first;
    private Box<T> last;
    //************************************************************************************************
    // Вставка в начало - служебный метод
    private void insertFirst(T data){
        if (first == null){
            first = new Box<>(data);
            last = first;
            return;
        } else {
            Box<T> box = new Box<>(data);
            box.next = first;
            first.prev = box;
            first = box;
        }
    }
    //************************************************************************************************
    // Вставка в конец - служебный метод
    private void insertLast(T data){
        if (last == null){
            last = new Box<>(data);
            first = last;
            return;
        } else {
            Box<T> box = new Box<>(data);
            last.next = box;
            box.prev = last;
            last = box;
        }
    }
    //************************************************************************************************
    // Вставка по отношению порядка
    public boolean insert(T data) {
        if (first == null) {
            insertFirst(data);
            return true;
        } else if (first.value.compareTo(data) > 0) {
            insertFirst(data);
            return true;
        } else if(last.value.compareTo(data)< 0) {
            insertLast(data);
            return true;
        } else {
            Box<T> newBox = new Box<>(data);
            Box<T> current = first.next;
            while (current != null) {
                if (current.value.compareTo(data)>0){
                    newBox.next = current;
                    newBox.prev = current.prev;
                    newBox.prev.next = newBox;
                    newBox.next.prev = newBox;
                    break;
                }
                current = current.next;
            }
            return true;
        }
    }
    //************************************************************************************************
    // Удаление первого элемента из списка
    public T removeFirst(){
        Box<T> result = first;
        if (first == null){
            return null;
        } else if (first == last){
            first = null;
            last = null;
            return result.value;
        } else {
            first.next.prev = null;
            first = first.next;
            return result.value;
        }
    }
    //************************************************************************************************
    // Удаление последнего элемента из списка
    public T removeLast(){
        Box<T> result = last;
        if (first == null){
            return null;
        } else if (first == last){
            first = null;
            last = null;
            return result.value;
        } else {
            last.prev.next = null;
            last = last.prev;
            return result.value;
        }
    }
    //************************************************************************************************
    // Удаление по ключу
    public boolean deleteKey(T key){
        if (first == null){
            return false;
        } else if (first.value == key) {
            removeFirst();
            return true;
        } else if (last.value == key) {
            removeLast();
            return true;
        } else {
            Box<T> box = first.next;
            while (box != null){
                if (box.value == key){
                    box.prev.next = box.next;
                    box.next.prev = box.prev;
                    return true;
                }
                box = box.next;
            }
        }
        return false;
    }
    //************************************************************************************************
    // Вывод на дисплей first--->last
    public void display(){
        if (first == null){
            System.out.println("list is empty");
        } else {
            Box<T> current = first;
            while (current!=null){
                System.out.print("-["+current.value+"]-");
                current = current.next;
            }
            System.out.println(" ");
        }
    }
    //************************************************************************************************
    // Класс - контейнер
    private static class Box<T> {
        private T value;
        private Box<T> next;
        private Box<T> prev;

        private Box(T value){
            this.value = value;
        }
    }


}
