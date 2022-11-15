package Data_Structures.Lists;

/**
 * Класс реализует связный список. Содержит в себе контейнер данных типа Link.
 * для операции с данными класс предоставляет методы
 * v1:
 * insertFirst();
 * deleteFirst();
 * delete(int key); - удаление по ключу.
 * displayList();
 * find(); - поиск элемента по ключу.
 * v2: добавление указателя на конец списка Link last, вставка в конец списка, удаление из конца списка.
 * insertLast();
 * deleteLast; - пребором всего списка.
 * v3: Добавление итератора.
 */
//******************************************************************************************************
public class LinkList implements Collection{

    private Link first;
    private Link last;

    public LinkList(){
        this.first = null;
        this.last = null;
    }
    //******************************************************************************************************
    // Проверка - лист пустой или нет
    public boolean isEmpty(){
        return this.first == null;
    }
    //******************************************************************************************************
    // Вставка в начало листа
    public void insertFirst(int iData, double dData){
        Link newLink = new Link(iData,dData);
        if (isEmpty()){ // Если вставляем первый элемент, то определяем указатель last
            this.last = newLink;
        }
        newLink.next = first;
        first = newLink;
    }
    //******************************************************************************************************
    // Вставка в конец
    public void insertLast(int iData, double dData){
        Link newLink = new Link(iData, dData);
        if (isEmpty()){ // Если список пуст, то определяем указатель first
            this.first = newLink;
        }
        else {
            last.next = newLink;
        }
        last = newLink;
    }
    //******************************************************************************************************
    // Удаление из начала листа
    public Link deleteFirst(){
        if (isEmpty()){
            return null;
        }
        Link temp = first;
        if (first.next == null){ // Если элемент последний то
            last = null;         // указатель last тоже обнуляем
        }
        first = first.next;
        return temp;
    }
    //******************************************************************************************************
    // Удаление из конца листа
    public Link deleteLast(){
        if (isEmpty()){
            return null;
        }
        Link element = last;
        if (first.next == null){ // Если элемент единственный то
            first = null;        // указатели first и last обнуляем
            last = null;
            return element;
        }
        Link current = first;
        while(current.next != null){
            if (current.next == last){
                last = current;
                current.next = null;
                break;
            }
            current = current.next;
        }
        return element;
    }
    //******************************************************************************************************
    // Вывод на дисплей списка
    public void displayList(){
        if (first==null){
            System.out.println("List is empty");
            return;
        }
        System.out.println("First ---> Last");
        Link current = first;
        while(current != null){
            current.displayLink();
            current = current.next;
        }
        System.out.println(" ");
    }
    //******************************************************************************************************
    // Поиск элемента по ключу
    public Link find(int key){
        Link current = first;
        while (current!=null){
            if (current.iData == key){
                break;
            }
            current = current.next;
        }
        return current;
    }
    //******************************************************************************************************
    // Удаление элемента по ключу
    public Link delete(int key){
        if (isEmpty()){
            return null;
        }
        Link current = first;
        Link result;
        if (first.iData == key){ // Если элемент первый
            first = first.next;
            return current;
        } else {
            while (current.next != null){
                if (current.next.iData == key){ // Если элемент найден.
                    result = current.next;
                    if (result == last){  // Если элемент последний, то
                        last = current;         // перекидываем указатель last назад.
                        last.next = null;       // и забываем об объекте.
                    } else {  // Если элемент не последний
                    current.next = current.next.next; // Выкидываем ссылку из листа.
                    }
                    return result;
                }
                current = current.next;
            }
            return current; // Вернуть null, если не найден ключ
        }
    }
    //******************************************************************************************************
    // Метод возващает инстанс итератора
    @Override
    public Iterator getIterator() {
        return new ListIterator();
    }
    //******************************************************************************************************
    // Итератор
    private class ListIterator implements Iterator{

        private Link lastReturned;
        private Link current;

        private ListIterator(){
            this.current = first;
        }

        @Override
        public Object next() {
            lastReturned = current;
            current = current.next;
            return lastReturned;
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        // Итератор удаляет
        public boolean delete(){
            return LinkList.this.delete(lastReturned.iData)!=null;
        }
    }
}
