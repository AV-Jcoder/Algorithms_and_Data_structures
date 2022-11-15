package Data_Structures.Lists;

/**
 * Очередь на базе связного списка.
 * методы:
 * add() - добавление в конец
 * remove() - удаление из начала.
 * isEmpty();
 * display();
 */
public class LinkedQueue {

    private LinkList list;

    LinkedQueue(){
        this.list = new LinkList();
    }

    public void add(int iData, double dData){
        list.insertLast(iData,dData);
    }

    public Link remove(){
        return list.deleteFirst();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void display(){
        list.displayList();
    }

}
