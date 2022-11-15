package Data_Structures.Lists;

/**
 * Класс реализует связный список с упорядоченными данными.
 * insert(T value) - добавляет элемент, сохраняя отношение порядка.
 * @param <T>
 * Исправлена проверка на отношение порядка.
 */
public class SortedList<T extends Comparable<? super T>> {

    private Box<T> head;

    public void insert(T value){
        if (head == null){
            head = new Box<>(value);
            return;
        }
        if (head.value.compareTo(value)>0){// если  value меньше, то вставка в начало
            Box<T> box = new Box<>(value);
            box.next = head;
            head = box;
            return;
        } else {
            Box<T> box = new Box<>(value);
            Box<T> current = head;
            while (current.next != null){
                if (current.next.value.compareTo(value)>0){ // если current.next больше
                    box.next = current.next;                // то вставка перед ним.
                    current.next = box;
                    return;
                }
                current = current.next;
            }
            current.next = box;
        }
    }


    @Override
    public String toString() {
        return "SortedList{" +
                "head=" + head +
                '}';
    }

    private static class Box<T>{
        private final T value;
        private Box<T> next;

        private Box(T value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
