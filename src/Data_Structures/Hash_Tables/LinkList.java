package Data_Structures.Hash_Tables;

/**
 * упорядоченный связный список для хеш-таблицы
 */
class LinkList {

    private Link first;

    LinkList(){

    }
//********************************************
    void insert(int data){
        Link newLink = new Link(data);
        if (first == null){
            first = newLink;
            return;
        } else if (data<first.data) {
            newLink.next = first;
            first = newLink;
            return;
        } else {
            Link current = first;
            while (current.next!=null){
                if (current.next.data>data){
                    break;
                }
                current = current.next;
            }
            newLink.next = current.next;
            current.next = newLink;
        }
    }
//********************************************
    Link get(int key){
        if (first==null){
            return null;
        } else if (first.data==key){
            return first;
        } else {
            Link current = first;
            while (current.next!=null){
                if (current.next.data==key){
                    return current.next;
                }

                current = current.next;
            }
        }
        return null;
    }
//********************************************
    boolean delete(int key){
        if (first==null){
            return false;
        } else if (first.data==key){
            first = first.next;
            return true;
        } else {
            Link current = first;
            while (current.next!=null){
                if (current.next.data==key){
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
//********************************************
    void showList(){
        if (first==null){
            return;
        } else {
            Link current = first;
            while (current!=null){
                System.out.printf("[%d]-",current.data);
                current = current.next;
            }
        }
    }
//********************************************
    private static class Link{
        private int data;
        private Link next;
        private Link(int data){
            this.data = data;
        }
    }
}
