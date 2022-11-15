package Data_Structures.Lists;

/**
 * Реализация стека на базе списка.
 * Методы push(); pop(); isEmpty(); displayStack();
 */
public class LinkedStack {

    private LinkList list;

    public LinkedStack(){
        this.list = new LinkList();
    }

    public void push(int iData, double dData){
        list.insertFirst(iData,dData);
    }

    public Link pop(){
        return list.deleteFirst();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void displayStack(){
        list.displayList();
    }

}
