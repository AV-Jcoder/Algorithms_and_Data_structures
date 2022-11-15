package Data_Structures.Lists;

/**
 * Двусвязный список. Может удалять последний элемент из списка за О(1) времени, в отличии
 * от односвязного, который удаляет за О(n) времени, перебираясь от начала в конец.
 * Немного сложнее односвязного списка, т.к. при вставке и удалении требуется менять
 * четыре ссылки вместо двух.
 * Расход памяти увеличен из за хранения дополнительной ссылки на предыдущий элемент.
 * методы:
 * insertFirst(T data);
 * insertLast(T data);
 * deleteFirst();
 * deleteLast();
 * delete(T key) - удаление по ключу.
 * displayList();
 * displayForward();
 */
public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
//**********************************************************************************************************
    // Вставка в начало
    public void insertFirst(T data){
        if (head == null){
            head = new Node<>(data);
            tail = head;
            return;
        } else {
            Node<T> newNode = new Node<>(data);
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }
//**********************************************************************************************************
    // Вставка в конец
    public void insertLast(T data){
        if (tail == null){
            tail = new Node<>(data);
            head = tail;
            return;
        } else {
            Node<T> newNode = new Node<>(data);
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }
//**********************************************************************************************************
    // Удаление первого элемента
    public void deleteFirst(){
        if (head == null){
            return;
        } else if (head == tail){
            head = null;
            tail = null;
            return;
        } else {
            head.next.previous = null;
            head = head.next;
        }
    }
//**********************************************************************************************************
    // Удаление последнего элемента
    public void deleteLast(){
        if (tail == null){
            return;
        } else if (tail == head){
            tail = null;
            head = null;
        } else {
            tail.previous.next = null;
            tail = tail.previous;
        }
    }
//**********************************************************************************************************
    // Удаление по ключу
    public boolean delete(T key){
        if (head == null){
            return false;
        } else if (head.value == key){
            deleteFirst();
            return true;
        } else {
            Node<T> current = head.next;
            while(current != null){
                if (current.value == key){
                    if (current.next != null) {
                        current.next.previous = current.previous;
                        current.previous.next = current.next;
                        return true;
                    } else { // Если элемент крайний, то перекидываем tail.
                        tail.previous.next = null;
                        tail = tail.previous;
                        return true;
                    }
                }
                current = current.next;
            }
        }
        return false;
    }
//**********************************************************************************************************
    // Вывод на дисплей
    public void displayList(){
        if (head == null){
            System.out.println("List is empty");
            return;
        } else {
            Node<T> current = head;
            System.out.println("*******************************************************");
            System.out.println("Head ---> Tail");
            while(current!=null){
                System.out.print("[" + current.value + "]--");
                current = current.next;
            }
            System.out.println(" ");
        }
    }
//**********************************************************************************************************
    // Вывод на дисплей реверсивно
    public void displayForward(){
        if (tail == null){
            System.out.println("List is empty");
            return;
        } else {
            Node<T> current = tail;
            System.out.println("*******************************************************");
            System.out.println("Tail ---> Head");
            while(current!=null){
                System.out.print("[" + current.value + "]--");
                current = current.previous;
            }
            System.out.println(" ");
        }
    }
//**********************************************************************************************************
//**********************************************************************************************************
//**********************************************************************************************************
    // Класс контейнер
    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previous;

        private Node(T value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
//**********************************************************************************************************